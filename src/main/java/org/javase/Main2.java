package org.javase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().lengthOfMonth());
        System.out.println(LocalDateTime.now().getDayOfMonth());
        System.out.println(TemporalAdjusters.lastDayOfMonth());
        System.out.println(LocalDate.now().getDayOfWeek().name());
        System.out.println(LocalDate.now().getMonth().getValue());
        Period until = LocalDate.of(2024, Month.JANUARY, 1).until(LocalDate.of(2025, Month.FEBRUARY, 1));
        System.out.println(until.get(ChronoUnit.MONTHS));
        long days = ChronoUnit.DAYS.between(LocalDate.of(2024, Month.JANUARY, 1), LocalDate.of(2025, Month.JANUARY, 1));
        System.out.println(days);

        ArrayList<Integer> intList = new ArrayList<>() {{
            add(1);
            add(2);
        }};

        print(intList);
    }

    public static void print(List<? extends Number> numberList) {
        numberList.forEach(System.out::println);
    }
}
