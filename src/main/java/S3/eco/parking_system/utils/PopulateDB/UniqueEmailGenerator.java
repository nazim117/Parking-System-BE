package S3.eco.parking_system.utils.PopulateDB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public enum UniqueEmailGenerator {
    INSTANCE;
    private String[] domains = new String[]{"@google.com", "@yahoo.com", "@fontys.nl", "@yandex.ru", "@abv.bg",
            "@gmail.com", "@fthis.org", "@goated.org", "@ailqk.com", "@zdrastidaiparizapasti.com", "@maimuna.net","@podaigo.com","@machkaikobra.com","@gospodinmladmerindjei.net","@udrqmpadash.org","@colgate.org","@nova.bg","@pornhub.com","@realdeal.com","@omg.com","@koolaid.net","@ivan.bg","@zalando.net","@google.net","@mozilla.org"};
    private Random random = new Random();

    // Generate a set of unique names and emails
    public ArrayList<String> generateUniqueEmails(ArrayList<String> names, int amountOfEmails) {
        HashSet<String> uniqueEmails = new HashSet<>();
        int i = 0;

        while (i < amountOfEmails) {
            String fullName = names.get(i).replaceAll("\\s+", "").toLowerCase();
            String emailPrefix = fullName.substring(0, Math.min(5, fullName.length()));
            String domain = domains[random.nextInt(domains.length)];
            String uniqueEmail = emailPrefix + domain;

            while (!uniqueEmails.add(uniqueEmail)) {
                uniqueEmail = emailPrefix + random.nextInt(100) + domain;
                i+=1;
            }
            if (i >= amountOfEmails) break; // Break the outer loop if too many attempts
        }

        return new ArrayList<>(uniqueEmails);
    }


    // Generates a unique email based on the name


}


