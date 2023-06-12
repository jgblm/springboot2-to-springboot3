package top.jgblm.jdk_upgrade;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public class VarFeature {
    public static void main(String[] args) {
        var myName = "jgblm";
        System.out.println(myName);

        // method reference requires an explicit target type
//        var lambda = String::toString;

        // lambda中可以使用var，此时可以在var前添加注解
        List<Object> list = List.of("name", 123, BigDecimal.valueOf(1L));
        boolean result = list.stream().anyMatch((@NonNull var element) -> element.toString().equals("123"));
        System.out.println(result);
    }
}
