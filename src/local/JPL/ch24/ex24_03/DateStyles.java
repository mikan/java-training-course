/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch24.ex24_03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateStyles {

    public static void main(String[] args) throws ParseException {
        printStyles("2014/05/11");
    }

    public static void printStyles(String dateStr) throws ParseException {
        Locale japan = new Locale("ja", "JP");
        Date date = DateFormat.getDateInstance(DateFormat.SHORT, japan).parse(dateStr);
        System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
        System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM).format(date));
        System.out.println(DateFormat.getDateInstance(DateFormat.LONG).format(date));
        System.out.println(DateFormat.getDateInstance(DateFormat.FULL).format(date));
    }
}
// Results:
// SHORT:   14/05/11
// MEDIUM:  2014/05/11
// LONG:    2014/05/11
// FULL:    2014年5月11日
