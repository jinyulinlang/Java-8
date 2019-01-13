package com.gao;

import org.junit.Test;
import sun.security.jca.GetInstance;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class TestDate {
    @Test
    public void test1() {
        LocalDate now = LocalDate.now ();
        System.out.println (now);
    }

    @Test
    public void test2() {
        System.out.println (LocalDateTime.now ());
        System.out.println (LocalTime.now ());

    }

    @Test
    public void testInstance() throws InterruptedException {
        Instant start = Instant.now ();
        Thread.sleep (1000L);
        Instant end = Instant.now ();
        Duration between = Duration.between (start, end);
        long seconds = between.getSeconds ();
        System.out.println (seconds);
        long l = between.toMillis ();

        System.out.println (l);
    }

    @Test
    public void testDurantion() {
        LocalTime time = LocalTime.now ();
        LocalTime localTime = time.minusHours (1L);

        Duration between = Duration.between (time, localTime);

        System.out.println (between.toHours ());
    }

    /**
     * 时代、周期
     */
    @Test
    public void testPeriod() {
        Period period = Period.between (LocalDate.of (2017, 2, 12), LocalDate.of (2019, 2, 12));
        int days = period.getDays ();
        System.out.println (days);
        int months = period.getMonths ();
        System.out.println (months);
        System.out.println (period.getYears ());
    }

    @Test
    public void testDateFormate() {
        LocalDate now = LocalDate.now ();
        String format = now.format (DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println (format);
        String format1 = now.format (DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
        System.out.println (format1);
        String format2 = now.format (DateTimeFormatter.ISO_DATE);

        System.out.println (format2);

        String format3 = now.format (DateTimeFormatter.ISO_ORDINAL_DATE    );

        System.out.println (format3);

    }
}
