package org.javase;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Clock {

    private static final List<String> CLEAR_COMMAND_ON_WINDOWS = List.of("cmd", "/c", "cls");
    private static final List<String> CLEAR_COMMAND_ON_OTHERS = List.of("clear");
    private static final String SYSTEM = System.getProperty("os.name").toLowerCase();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");

    public static void main(String[] args) {
        while (!Thread.interrupted()) {
            try {
                if (SYSTEM.contains("win")) {
                    new ProcessBuilder(CLEAR_COMMAND_ON_WINDOWS).inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder(CLEAR_COMMAND_ON_OTHERS).inheritIO().start().waitFor();
                }

                System.out.println(formatter.format(LocalTime.now()));
                Thread.sleep(1000);
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
