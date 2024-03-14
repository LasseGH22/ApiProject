package com.example.ApiProject.ApiConnection;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ConnectionService {

    public String[] splitTime(String dateString) throws ParseException {
        String[] dateComponents = new String[4];
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss z");
        Date date = formatter.parse(dateString);

        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        dateComponents[0] = monthFormat.format(date);

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        dateComponents[1] = dayFormat.format(date);

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        dateComponents[2] = yearFormat.format(date);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateComponents[3] = timeFormat.format(date);

        return dateComponents;
    }
}
