package top.jgblm.jdk_upgrade;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class switchFeature {
    public static void main(String[] args) {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        boolean free = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                System.out.println("work hard");
                yield false;
            }
            case SATURDAY, SUNDAY -> {
                System.out.println("enjoy your time");
                yield true;
            }
        };
    }
}
