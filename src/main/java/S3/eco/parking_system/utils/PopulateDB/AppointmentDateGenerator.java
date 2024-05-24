package S3.eco.parking_system.utils.PopulateDB;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AppointmentDateGenerator {
    private final Random random;
    private final int startHour;
    private final int endHour;

    public AppointmentDateGenerator(Random random, int startHour, int endHour) {
        this.random = random;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Set<LocalDateTime> generateDateTimesAround(LocalDateTime base, int dayRange, int count) {
        return generateDateTimesAfter(base.minusDays(dayRange), dayRange * 2 + 1, count);
    }
    public Set<LocalDateTime> generateDateTimesAfter(LocalDateTime start, int dayCount, int count) {
        Set<LocalDateTime> dateTimes = new HashSet<>();

        start = start.withHour(startHour).withMinute(0);

        while (dateTimes.size() < count) {
            LocalDateTime day = start.plusDays(random.nextInt(dayCount));
            LocalDateTime generated = day.plusMinutes(random.nextInt((endHour - startHour) * 60));
            dateTimes.add(generated);
        }
        return dateTimes;
    }
}

