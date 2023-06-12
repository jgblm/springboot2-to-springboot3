package top.jgblm.jdk_upgrade;

public class RecordFeature {
    public static void main(String[] args) {
        User user = new User("jgblm", 35);
        System.out.println(user.name());
        System.out.println(user.age());
        System.out.println(user);
        System.out.println(user.hashCode());
    }

    record User(String name, int age) {
        // record的构造方法，没有()
        public User {
            if (name == null) {
                throw new RuntimeException("name can not be null.");
            }
        }
    }
}
