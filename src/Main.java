import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String htmlFile = parseFile("data/code.html");

        Document document = Jsoup.parse(htmlFile); // парсит HTML-файл с помощью jsoup

        Elements elements = document.select("span.dropdown__link-text"); // Выбираем все элементы <span> с классом 'dropdown__link-text'

        elements.forEach(element -> { // Для каждого найденного элемента выводим его текст в консоль
            System.out.println(element.text());
        });

//        System.out.println(htmlFile);
    }

    public static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            List<String> stringList = Files.readAllLines(Paths.get(path));
            stringList.forEach(l -> builder.append(l + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return builder.toString();
    }
}
