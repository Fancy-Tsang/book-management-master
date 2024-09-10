package com.book.bookmanager.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DaysUtis {

    private static final int DAYS = 30;

    public static long daysRemaining(LocalDateTime createTime){
        return DAYS - ChronoUnit.DAYS.between(createTime, LocalDateTime.now());
    }
}
