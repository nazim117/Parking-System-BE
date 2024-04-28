package S3.eco.parking_system.utils.PopulateDB;

import java.util.*;

public enum DutchLicensePlateGenerator {
    INSTANCE;


    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random random = new Random();
    private final Set<String> issuedPlates = new HashSet<>();

    public ArrayList<String> generateLicensePlates(int count) {
        Set<String> plates = new HashSet<>();
        while (plates.size() < count) {
            String newPlate = generateLicensePlate();
            if (!issuedPlates.contains(newPlate)) {
                issuedPlates.add(newPlate);
                plates.add(newPlate);
            }
        }
        return new ArrayList<>(plates); // Convert the HashSet to an ArrayList
    }


    private String generateLicensePlate() {
        // Format: XX-99-XX
        return generateRandomLetters(2) + "-" + generateRandomNumbers(2) + "-" + generateRandomLetters(2);
    }

    private String generateRandomLetters(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char letter = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
            builder.append(letter);
        }
        return builder.toString();
    }

    private String generateRandomNumbers(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));  // Generates a digit from 0 to 9
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        List<String> licensePlates = DutchLicensePlateGenerator.INSTANCE.generateLicensePlates(200);
        licensePlates.forEach(System.out::println);
    }
}
