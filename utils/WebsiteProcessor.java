package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebsiteProcessor {
    private String websiteUrl;

    public WebsiteProcessor(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public boolean isValidUrl() {
        try {
            new URL(websiteUrl).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String fetchHtmlContent() {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(websiteUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error fetching HTML content: " + e.getMessage());
        }
        return content.toString();
    }
}
