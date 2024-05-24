package S3.eco.parking_system.utils.PopulateDB;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueNameGenerator {
    private static final String[] FIRST_NAMES = {"John", "Jane", "Sam", "Sally", "Mike", "Ann", "Sander", "Danila", "Nazim", "Angel", "Claudiu", "George", "Nick", "Ivan"};
    private static final String[] LAST_NAMES = {"Doe", "Smith", "Brown", "Johnson", "White", "Black"};
    private static final int MAX_COMBINATIONS = FIRST_NAMES.length * (FIRST_NAMES.length-1) * LAST_NAMES.length;

    private final Random random;

    public UniqueNameGenerator(Random random) {
        this.random = random;
    }

    private String pickMiddleName(int firstNameIndex) {
        int index = random.nextInt(FIRST_NAMES.length - 1);
        if (index >= firstNameIndex) index += 1;

        return FIRST_NAMES[index];
    }

    public Set<String> generateUniqueNames(int count) {
        if (MAX_COMBINATIONS < count) {
            throw new IllegalStateException("Cannot generate " + count + " unique name combinations, " +
                    "the current dataset only allows for " + MAX_COMBINATIONS + " unique name combinations");
        }
        Set<String> uniqueNames = new HashSet<>(count);

        while (uniqueNames.size() < count) {
            int firstNameIndex = random.nextInt(FIRST_NAMES.length);
            String firstName = FIRST_NAMES[firstNameIndex];
            String middleName = pickMiddleName(firstNameIndex);
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

            String fullName = firstName + " " + middleName + " " + lastName;
            uniqueNames.add(fullName);
        }

        return uniqueNames;
    }
}