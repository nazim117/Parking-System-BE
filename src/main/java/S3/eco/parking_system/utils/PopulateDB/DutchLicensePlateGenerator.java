package S3.eco.parking_system.utils.PopulateDB;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DutchLicensePlateGenerator {
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] ZEROES = "0".repeat(10).toCharArray();
    private final Random random;

    public DutchLicensePlateGenerator(Random random) {
        this.random = random;
    }


    public Set<String> generateUniqueLicensePlates(int count) {
        Set<String> plates = new HashSet<>(count);
        while (plates.size() < count) {
            // Try to add the plates initially
            plates.add(generateLicensePlate());
        }
        return plates; // Convert the HashSet to an ArrayList
    }


    private String generateLicensePlate() {
        // Format: AB-99-CD
        StringBuilder sb = new StringBuilder();

        appendRandomLetters(sb, 2);
        sb.append("-");
        appendRandomDigits(sb, 2);
        sb.append("-");
        appendRandomLetters(sb, 2);

        return sb.toString();
    }

    @SuppressWarnings("SameParameterValue")
    private void appendRandomLetters(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            char letter = ALPHABET[random.nextInt(ALPHABET.length)];
            sb.append(letter);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void appendRandomDigits(StringBuilder sb, int count) {
        // The integer max limit is `2,147,483,648`, which
        // is exactly 10 characters. If we have 9 or fewer characters,
        // a single random call is enough to generate the numbers
        while (count > 0) {
            int takenDigitCount = Math.min(count, 9);
            int pow = (int) Math.pow(10, takenDigitCount);

            String num = String.valueOf(random.nextInt(pow));
            sb.append(ZEROES, 0, takenDigitCount-num.length());
            sb.append(num);

            count -= takenDigitCount;
        }
    }
}
