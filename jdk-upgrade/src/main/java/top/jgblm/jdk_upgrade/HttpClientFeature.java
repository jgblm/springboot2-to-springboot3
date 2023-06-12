package top.jgblm.jdk_upgrade;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class HttpClientFeature {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://github.com")).GET().build();

        HttpClient httpClient = defaultClient();

        // synchronously
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        // asynchronously
        CompletableFuture<HttpResponse<String>> asyncResponse = httpClient
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = asyncResponse.get().body();
        System.out.println(result);

        // asynchronously with Push Promises
        // Push Promises: Http2.0的一种技术，浏览器请求某种资源时，把其他相关资源一起返回。
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(), pushPromiseHandler())
                .thenAccept(pageResponse -> {
                    System.out.println("Page response status code: " + pageResponse.statusCode());
                    System.out.println("Page response headers: " + pageResponse.headers());
                    String responseBody = pageResponse.body();
                    System.out.println(responseBody);
                })
                .join();
    }

    private static HttpClient defaultClient() {
        return HttpClient.newHttpClient();
    }

    private static HttpClient proxyClient() {
        return HttpClient.newBuilder().proxy(ProxySelector.getDefault()).build();
    }

    private static HttpClient redirectClient() {
        return HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
    }

    private static HttpClient authenticatorClient() {
        return HttpClient.newBuilder().authenticator(new Authenticator() {
        }).build();
    }

    private static HttpClient cookieClient() {
        // get cookie store
        // ((CookieManager) httpClient.cookieHandler().get()).getCookieStore()
        return HttpClient.newBuilder().cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE)).build();
    }

    private static HttpResponse.PushPromiseHandler<String> pushPromiseHandler() {
        return (HttpRequest initiatingRequest,
                HttpRequest pushPromiseRequest,
                Function<HttpResponse.BodyHandler<String>,
                        CompletableFuture<HttpResponse<String>>> acceptor) -> {
            acceptor.apply(HttpResponse.BodyHandlers.ofString())
                    .thenAccept(resp -> {
                        System.out.println(" Pushed response: " + resp.uri() + ", headers: " + resp.headers());
                    });
            System.out.println("Promise request: " + pushPromiseRequest.uri());
            System.out.println("Promise request: " + pushPromiseRequest.headers());
        };
    }
}
