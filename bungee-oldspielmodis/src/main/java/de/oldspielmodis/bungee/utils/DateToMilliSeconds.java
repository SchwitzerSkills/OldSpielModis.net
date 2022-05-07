package de.oldspielmodis.bungee.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToMilliSeconds {

    public static long getMilliseconds(String date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
        Date d;
        try {
            d = simpleDate.parse(date);
            return d.getTime();
        } catch (ParseException e) {
        }
        return 0;
    }

    public static long getMilliseconds2(String date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date d;
        try {
            d = simpleDate.parse(date);
            return d.getTime();
        } catch (ParseException e) {
        }
        return 0;
    }
}
