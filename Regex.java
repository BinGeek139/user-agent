import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentParser {

    public static String[] extractOsAndVersion(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return new String[]{"Unknown", "Unknown"};
        }

        String[][] osRegexPatterns = {
                {"Windows", "Windows NT ([0-9.]+)"},
                {"Mac OS", "Mac OS X ([0-9_]+)"},
                {"iOS", "iPhone OS ([0-9_]+)|CPU OS ([0-9_]+)"},
                {"Android", "Android ([0-9.]+)"},
                {"Linux", "X11; Linux"},
                {"Unix", "UNIX"},
                {"Chrome OS", "CrOS ([^ ]+)"}
        };

        for (String[] osRegex : osRegexPatterns) {
            String osName = osRegex[0];
            String regex = osRegex[1];

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(userAgent);

            if (matcher.find()) {
                String version = matcher.group(1) != null ? matcher.group(1) : "Unknown";
                return new String[]{osName, version.replace('_', '.')};
            }
        }

        return new String[]{"Unknown", "Unknown"};
    }

    public static void main(String[] args) {
        String[] testUserAgents = {
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 14_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1",
                "Mozilla/5.0 (Linux; Android 9; SM-G960F Build/PPR1.180610.011) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Mobile Safari/537.36",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                "Mozilla/5.0 (CrOS x86_64 13904.55.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
        };

        for (String userAgent : testUserAgents) {
            String[] result = extractOsAndVersion(userAgent);
            System.out.println("User Agent: " + userAgent);
            System.out.println("OS: " + result[0] + ", Version: " + result[1]);
            System.out.println("------------");
        }
    }
}
