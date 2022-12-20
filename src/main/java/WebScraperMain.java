import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class WebScraperMain {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert link");
        try {
            String url = scanner.next();

            Document document = Jsoup.connect(url)
                    .timeout(5000)
                    .get();

            Elements articleElements = document.getElementsByClass("article-details");
            for (Element element : articleElements) {

                String author1 = element.select("#full-view-heading > div.inline-authors > div > div > span:nth-child(1) > a").text();
                String firstAuthor = generateAuthor(author1);


                String author2 = element.select("#full-view-heading div.authors span:nth-of-type(2) a[href^=/?term=]\n").text();
                String secondAuthor = generateAuthor(author2);

                String author3 = element.select("#full-view-heading div.authors span:nth-of-type(3) a[href^=/?term=]\n").text();
                String thirdAuthor = generateAuthor(author3);

                String title = element.select("#short-view-heading > h1").text();
                String date = element.select("#short-view-heading > div.short-citation > span.date > span.citation-year").text();
                String journal = Objects.requireNonNull(element.selectFirst("#short-view-heading > div.short-citation > span.citation-journal")).text();
                String pages = element.select("#full-view-heading > div.article-citation > div.article-source > span.cit").text();

                System.out.println(firstAuthor + secondAuthor + thirdAuthor + " - " + title+". "+journal+" "+pages);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String generateAuthor(String author){
        if (author.length() >0) {
            String[] parts = author.split(" ");
            String lastName = parts[parts.length - 1];
            String firstNameInitials = "";
            for (int i = 0; i < parts.length - 1; i++) firstNameInitials += parts[i].charAt(0);
            String author1result = lastName + " " + firstNameInitials + ", ";
            return author1result;
        }
        else return "";
    }
}
