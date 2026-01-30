package regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        String text = "kdskfskdfm28skdfmdsf14skfksm14.12.2000kkdfkdskf00.12.10.2000";

        Pattern pattern = Pattern.compile("((0[1-9])|([12][0-9])|(3[01]))\\.((0[1-9])|(1[0-2]))\\.\\d{4}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        test(new String[] {"hi", "halo"});
    }

    public static void test(String... strings) {
        System.out.println(Arrays.toString(strings));
    }
}
