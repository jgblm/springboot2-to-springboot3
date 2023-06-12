package top.jgblm.jdk_upgrade;

import java.util.ArrayList;
import java.util.List;

public class InstanceFeature {
    public static void main(String[] args) {
        Object obj = init();
        if (obj instanceof List list) {
            list.add("123");
        }
        System.out.println(obj);
    }

    private static Object init() {
        return new ArrayList<String>();
    }
}
