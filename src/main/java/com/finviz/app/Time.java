package com.finviz.app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	// Get current system time
    public static String GetCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
}
}
