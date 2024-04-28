package S3.eco.parking_system.utils.PopulateDB;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum DateGenerator {
    INSTANCE;

    private static final int DAYS_RANGE = 14;
    private static final int MINUTES_IN_DAY = 24 * 60;

    public ArrayList<LocalDateTime> generateDateTime(int count) {
        ArrayList<LocalDateTime> dateTimes = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < count; i++) {
            LocalDateTime randomDateTime = generateRandomDateTime(now);
            dateTimes.add(randomDateTime);
        }
        return dateTimes;
    }

    private LocalDateTime generateRandomDateTime(LocalDateTime baseDateTime) {
        long randomMinutes = ThreadLocalRandom.current().nextLong(-DAYS_RANGE * MINUTES_IN_DAY, DAYS_RANGE * MINUTES_IN_DAY);
        return baseDateTime.plusMinutes(randomMinutes);
    }

    public static void main(String[] args) {
        List<LocalDateTime> randomDates = DateGenerator.INSTANCE.generateDateTime(5);
        randomDates.forEach(System.out::println);
    }
}

