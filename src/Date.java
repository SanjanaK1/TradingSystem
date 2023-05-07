import java.lang.reflect.Array;
import java.util.Arrays;

;

public class Date {

    private int day;
    private int month;
    private int year;
    private boolean isLeapYear;
    public static final String[] monthNames = {"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"};

    public Date (int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public static Date parseDate(String s) {
        String[] dateSplitString = s.split("-");
        int[] dateSplit = new int[dateSplitString.length];
        for (int i = 0; i < dateSplit.length; i++) {
            dateSplit[i] = Integer.parseInt(dateSplitString[i]);
        }
        Date d = new Date(dateSplit[0], dateSplit[1], dateSplit[2]);
        return d;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("Month has to be between 1-12 inclusive");
        }
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        int month = getMonth();
        if (day <= 0) {
            throw new IllegalArgumentException("Day entered cannot be less than or equal to 0. Input: " + day);
        }
        if (month == 2) { // february and leap year handling.
            if (day > 28 && !isLeapYear() || (isLeapYear() && day > 29)) {
                throw new IllegalArgumentException("February cannot have a day of greater than 28. Input: " + isLeapYear() + " " + day);
            }
        } else if ((month % 2 == 0 && month < 7) || (month % 2 == 1 && month > 8)) { // months with 30 days.
            if (day > 30) {
                throw new IllegalArgumentException("Month " + monthNames[month - 1] + " cannot have more than 30 days.");
            }
        }
        else { // months with 31 days.
            if (day > 31) {
                throw new IllegalArgumentException("Month " + monthNames[month - 1] + " cannot have more than 31 days.");
            }
        }
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isLeapYear() {
        return isLeapYear;
    }

    public void setLeapYear() {

        isLeapYear = false; // todo: fix is leap year test.
    }
}