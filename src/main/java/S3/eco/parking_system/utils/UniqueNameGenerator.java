package S3.eco.parking_system.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public enum UniqueNameGenerator {
    INSTANCE;
    private final String[] firstNames = {"John", "Jane", "Sam", "Sally", "Mike", "Ann", "Sander", "Danila", "Nazim", "Angel", "Claudiu", "George", "Nick", "Ivan", "Honeybadger", "Lil Raccoon", "Lil Charles", "Lil Dickie"};
    private final String[] lastNames = {"Doe", "Smith", "Brown", "Johnson", "White", "Black", "Urmum", "Urdad", "Urgeyi", "Urbith","Smakdaat"};
    private final String[] middleNames = {"Letsgoo", "Fker", "Bad", "The Rock", "Whitechapel", "Whiteknight", "Biggerdickson", "Urrdad", "Urgeyi", "Urbith","Armpit","Johnson","Uvuvueuvuweuwuugwemubwemosas"};

    private final Random random = new Random();
    private final HashSet<String> uniqueNames = new HashSet<>();

    public ArrayList<String> generateUniqueName(int amountOfNames) {
        int i = 0;

        while (i < amountOfNames) {
            String name = firstNames[random.nextInt(firstNames.length)] + " " +firstNames[random.nextInt(middleNames.length)] + " " +
                    lastNames[random.nextInt(lastNames.length)];
            if (!uniqueNames.contains(name)) {
                uniqueNames.add(name);
                i += 1;
            }
        }
        return new ArrayList<>(uniqueNames);
    }
}