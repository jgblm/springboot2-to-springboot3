package top.jgblm.jdk_upgrade;

public class SealedFeature {
    public static void main(String[] args) {
        Animal animal = new Dog();
        System.out.println(voice(animal));
    }

    public static String voice(Animal animal) {
        if (animal instanceof Dog d) {
            return d.woof();
        } else if (animal instanceof Cat c) {
            return c.meow();
        }
        return "unknown";
    }
}

abstract sealed class Animal
        permits Dog, Cat {
}

final class Dog extends Animal {
    public String woof() {
        return "woof";
    }
}

final class Cat extends Animal {
    public String meow() {
        return "meow";
    }
}
