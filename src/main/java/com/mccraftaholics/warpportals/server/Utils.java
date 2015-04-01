package com.mccraftaholics.warpportals.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static String formatISO(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_8601);
        return sdf.format(date);
    }

    public static Date parseIsoTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_8601);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }
}
