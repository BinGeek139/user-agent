import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentParser {

    /**
     * Extracts the OS and version as a single string from the user agent.
     *
     * @param userAgent the user agent string
     * @return a string containing OS and version, or "Unknown" if not found
     */
    public static String extractOsAndVersion(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "Unknown";
        }

        String[][] osRegexPatterns = {
                {"Windows", "Windows NT ([0-9.]+)"},
                {"Mac OS X", "Mac OS X ([0-9_]+)"},
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
                return osName + " " + version.replace('_', '.');
            }
        }

        return "Unknown";
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
            String result = extractOsAndVersion(userAgent);
            System.out.println("User Agent: " + userAgent);
            System.out.println("OS and Version: " + result);
            System.out.println("------------");
        }
    }

     public static String getDeviceType(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "Unknown";
        }

        // Define Regex for each device type
        String tabletRegex = "Tablet|iPad|SM-T|Nexus 7|Tab|Kindle";
        String mobileRegex = "Mobile|Android(?!.*Tablet)|iPhone|Windows Phone";
        String desktopRegex = "Windows NT|Macintosh|X11";

        // Match against user agent
        if (Pattern.compile(tabletRegex, Pattern.CASE_INSENSITIVE).matcher(userAgent).find()) {
            return "Tablet";
        } else if (Pattern.compile(mobileRegex, Pattern.CASE_INSENSITIVE).matcher(userAgent).find()) {
            return "Mobile";
        } else if (Pattern.compile(desktopRegex, Pattern.CASE_INSENSITIVE).matcher(userAgent).find()) {
            return "Desktop";
        }

        return "Unknown";
    }
    Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36


    import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowserDetector {

    public static String getBrowser(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "Unknown Browser";
        }

        String[][] browserRegexPatterns = {
                {"Chrome", "Chrome/([\\d.]+)"},
                {"Firefox", "Firefox/([\\d.]+)"},
                {"Safari", "Version/([\\d.]+) Safari/"},
                {"Edge (Chromium)", "Edg/([\\d.]+)"},
                {"Edge (Legacy)", "Edge/([\\d.]+)"},
                {"Opera", "OPR/([\\d.]+)|Opera/([\\d.]+)"},
                {"Internet Explorer", "MSIE ([\\d.]+)|Trident/.*rv:([\\d.]+)"}
        };

        for (String[] browserRegex : browserRegexPatterns) {
            String browserName = browserRegex[0];
            String regex = browserRegex[1];

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(userAgent);

            if (matcher.find()) {
                String version = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
                return browserName + " " + (version != null ? version : "Unknown Version");
            }
        }

        return "Unknown Browser";
    }

    public static void main(String[] args) {
        String[] testUserAgents = {
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:91.0) Gecko/20100101 Firefox/91.0",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Edg/91.0.864.64",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:11.0) like Gecko Trident/7.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Opera/47.0.2631.39"
        };

        for (String userAgent : testUserAgents) {
            System.out.println("User Agent: " + userAgent);
            System.out.println("Browser: " + getBrowser(userAgent));
            System.out.println("------------");
        }
    }
}

}
