package com.sanju;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public String formatDate(String inputDate) throws ParseException {
        if (inputDate == null || inputDate.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        inputFormat.setLenient(false);
        
        Date date = inputFormat.parse(inputDate);
        return outputFormat.format(date);
    }
}
