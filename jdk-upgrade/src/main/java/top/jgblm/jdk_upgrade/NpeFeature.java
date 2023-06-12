package top.jgblm.jdk_upgrade;

import lombok.Getter;

public class NpeFeature {
    public static void main(String[] args) {
        User user = new User();
        // Cannot invoke "String.length()" because "User.getName()" is null
        System.out.println(user.getName().length());
    }

    public static class User{
        @Getter
        String name;
    }
}
