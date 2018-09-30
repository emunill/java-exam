package com.privalia.util;

import java.util.Calendar;

public class CalendarUtil {
    /**
     *  Return the day of last week day of the month
     *
     * @param dayOfWeek Calendar day of the week
     * @param month Month
     * @param year Year in 4 digits
     * @return Day of the month
     */
    public static int getDayOfLastWeekDayOfMonth(int dayOfWeek, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month + 1, 1);

        for (int days = 1; days <= 7; days++ ) {
            calendar.add(Calendar.DAY_OF_MONTH, - 1);
            if (dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK)) break;
        }

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Date of 1rst day of next Month
     *
     * @param calendar Date
     * @return Calendar
     */
    public static Calendar getDateOfFirstDayOfNextMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);

        return calendar;
    }

    /**
     *
     * @param dayOfWeek Calendar day of the week
     * @param calendar Date
     * @return Calendar
     */
    public static Calendar getDateOfLastWeekDayOfNextMonth(int dayOfWeek, Calendar calendar) {
        calendar = getDateOfFirstDayOfNextMonth(calendar);
        int day = getDayOfLastWeekDayOfMonth(
                dayOfWeek,
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR)
        );
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar;
    }

    /**
     * Set the hour to 00:00:00 000
     *
     * @param calendar Date
     * @return Calendar
     */
    public static Calendar clearHour(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    /**
     * Custom to string calendar
     *
     * @param calendar Date
     * @return String date
     */
    public static String toString(Calendar calendar) {
        return calendar.getTime().toString();
    }
}
