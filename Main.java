import models.DatabaseManager;
import models.Link;
import utils.HtmlParser;
import utils.WebsiteProcessor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Replace with user input or CLI argument
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the url to scan : ");
        String websiteUrl = scanner.next();

        WebsiteProcessor processor = new WebsiteProcessor(websiteUrl);

        if (processor.isValidUrl()) {
            String htmlContent = processor.fetchHtmlContent();
            HtmlParser parser = new HtmlParser(htmlContent);
            List<String> extractedLinks = parser.extractLinks();

            // Initialize database manager
            DatabaseManager dbManager = new DatabaseManager("src/resources/links.db");
            dbManager.createTableIfNotExists();

            System.out.println("Extracted Links:");
            for (String url : extractedLinks) {
                System.out.println(url);
                Link link = new Link(url);
                dbManager.insertLink(link); // Save the link to the database
            }

            if(extractedLinks.isEmpty()){
                System.out.println("No links found from this url " + websiteUrl);
            }

            System.out.println("\nSaved Links from Database:");
//            List<Link> savedLinks = dbManager.getAllLinks();
//            for (Link savedLink : savedLinks) {
//                System.out.println(savedLink); // Display saved links
//            }

            dbManager.closeConnection();
        } else {
            System.out.println("Invalid URL. Please try again.");
        }
    }
}
