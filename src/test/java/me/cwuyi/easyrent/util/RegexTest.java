package me.cwuyi.easyrent.util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    private static final String DOUBAN_DOMAIN = "https://www.douban.com/";
    private static final Pattern DOUBAN_TOPIC_URL_PATTERN = Pattern.compile(DOUBAN_DOMAIN + "group/topic/[0-9]+/");

    @Test
    public void retriveDigit(){
        String url = "https://www.douban.com/group/topic/116945613/";

        if (DOUBAN_TOPIC_URL_PATTERN.matcher(url).matches()) {
//            Matcher matcher = DOUBAN_TOPIC_URL_PATTERN.matcher(url);
//            matcher.find();
            System.out.println(url.replaceAll("\\D", "").trim());
            System.out.println(url);
        }
    }
}
