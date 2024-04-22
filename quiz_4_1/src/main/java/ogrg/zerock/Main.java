package ogrg.zerock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String url = "https://news.daum.net/?nil_profile=mini&nil_src=news";
        String csvFilePath = "quiz4_LKW.csv";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsIssues = doc.select("ul.list_newsissue a.link_txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                writer.write("링크,제목\n");

                for (Element issue : newsIssues) {
                    String link = issue.attr("href");
                    String title = issue.text();
                    writer.write(link + "," + title + "\n");
                    System.out.println("링크: " + link);
                    System.out.println("제목: " + title);
                    System.out.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
