/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch10.ex10_03;

public class Weekday {

    public static void main(String[] args) {
        System.out.println(isWeekday(DayOfWeek.MONDAY));
        System.out.println(isWeekday2(DayOfWeek.FRIDAY));
    }

    /**
     * Weekday check. if-else version.
     * 
     * @param dow Day of week
     * @return true: weekday, false: not weekday
     */
    public static boolean isWeekday(DayOfWeek dow) {
        if (dow == DayOfWeek.SATURDAY)
            return false;
        else if (dow == DayOfWeek.SUNDAY)
            return false;
        else
            return true;
    }

    /**
     * Weekday check. switch version.
     * 
     * @param dow Day of week
     * @return true: weekday, false: not weekday
     */
    public static boolean isWeekday2(DayOfWeek dow) {
        switch (dow) {
        case SATURDAY:
        case SUNDAY:
            return false;
        default:
            return true;
        }
    }
}
