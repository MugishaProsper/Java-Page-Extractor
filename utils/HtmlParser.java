package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    private String htmlContent;

    public HtmlParser(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public List<String> extractLinks() {
        List<String> links = new ArrayList<>();
        String regex = "<a[^>]+href=\"(.*?)\""; // Regular expression for extracting href attribute
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String link = matcher.group(1);
            links.add(link);
        }
        return links;
    }
}
