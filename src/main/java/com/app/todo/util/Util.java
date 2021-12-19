package com.app.todo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    private static DateTimeFormatter formatter;

    public static LocalDateTime getLocalDateTimeFromString(String datetime, String dateType) {
        switch (dateType) {
            default:
            case "date":
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                break;
            case "datetime":
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
                break;
            case "fulldatetime": {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                break;
            }
        }

        return LocalDateTime.parse(datetime, formatter);
    }
}
