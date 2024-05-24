package S3.eco.parking_system.utils.PopulateDB;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class UniqueEmailGenerator {
    // https://email-verify.my-addr.com/list-of-most-popular-email-domains.php
    private static final String[] domains = new String[]{
            "gmail.com",
            "yahoo.com",
            "hotmail.com",
            "aol.com",
            "hotmail.co.uk"
    };
    private final Random random;

    public UniqueEmailGenerator(Random random) {
        this.random = random;
    }

    // Generate a set of unique names and emails
    public Set<String> generateUniqueEmails(List<String> names, int count) {
        Set<String> uniqueEmails = new HashSet<>();

        for (int emailIndex = 0; emailIndex < count; emailIndex++) {
            String domain = domains[random.nextInt(domains.length)];

            // Pick random person and format name
            String fullName = names.get(random.nextInt(names.size())).replaceAll("\\s+", "").toLowerCase();
            String emailPrefix = fullName.substring(0, Math.min(5, fullName.length()));

            // Try to add basic email without numbers first
            // If that fails, create an email with numbers
            if (!uniqueEmails.add(emailPrefix + "@" + domain)) {
                // Use `count` in random generator to guarantee possibility of uniqueness
                // Worst case: there is a single name in `names`
                uniqueEmails.add(emailPrefix + emailIndex + "@" + domain);
            }
        }

        return uniqueEmails;
    }
}


