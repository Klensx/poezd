import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;

public class parser {

    private static String[] args;

    private static Document getPage() throws IOException {
        String url = "https://poezdato.net/raspisanie-po-stancyi/samara/elektrichki/";
        Document page = Jsoup.parse(new URL(url), 3000);

        return page;
    }

    /*private static void Retain(String[] args) throws IOException {
        Document page = getPage();
        // css query language
        String[] table;
        Element Head = page.select(table[class=th]).first();
        System.out.println(table_shadow);
        Element names = table_shadow.select("th[class=wth]")
                for (Element name : names {
            System.out.println(" Номер      Маршрут     Прибытие    Отправление");
        }
    }*/

    private static void first() {
    }

    private static String[] getNumbers(Elements allLinks) {
        String hrefs[] = new String[allLinks.size()];
        for (int i = 0; i < allLinks.size(); i++) {
            hrefs[i] = allLinks.get(i).attr("href");
        }

        String numbers[] = new String[allLinks.size()]; //номер поездов
        int index[] = new int[allLinks.size()];
        for (int i = 0; i < index.length; i++) {
            index[i] = 0;
        }
        for (int i = 0; i < allLinks.size(); i++) {
            index[i] = hrefs[i].indexOf("raspisanie-elektrichki");
        }
        for (int i = 0; i < index.length; i++) {
            if (index[i] != -1)
                numbers[i] = allLinks.get(i).text();
        }
        String tmp[] = new String[allLinks.size()];

        int i = 0;
        int j = 0;

        while (i < index.length) {
            if (numbers[i] != null) {
                tmp[j] = numbers[i];
                j++;
            }
            i++;
        }

        return tmp;
    }
    private static String[] getRouts(Elements allLinks) {

        String hrefs[] = new String[allLinks.size()];
        String routs[] = new String[allLinks.size()];

        for (int i = 0; i < allLinks.size(); i++) {
            hrefs[i] = allLinks.get(i).attr("href");
        }

        int index[] = new int[allLinks.size()];

        for (int i = 0; i < index.length; i++) {
            index[i] = 0;
        }
        for (int i = 0; i < allLinks.size(); i++) {
            index[i] = hrefs[i].indexOf("raspisanie-po-stancyi");
        }
        for (int i = 0; i < index.length; i++) {
            if (index[i] != -1) {
                routs[i] = allLinks.get(i).text();
            }
        }

        String tmp[] = new String[allLinks.size()];

        int i = 0;
        int j = 0;

        while (i < index.length) {
            if (routs[i] != null) {
                tmp[j] = routs[i];
                j++;
            }
            i++;
        }
        return tmp;
    }
    public static void main(String[] args) throws IOException {
        Document page = getPage();

        Element tableRasp = page.select("table[ class=schedule_table stacktable desktop]").first();

        if (tableRasp == null) {
            System.out.println("Not table");
            System.exit(1);
        }
        else {
        }

        Elements allLinks = tableRasp.select("td > a");
        Elements allSpan = tableRasp.select("span[class=_time]");

        String arrives[] = new String[allSpan.size()];
        String departure[] = new String[allSpan.size()];

        for (int i = 0; i < allSpan.size(); i++) {
            if (i % 2 == 0)
                arrives[i] = allSpan.get(i).text();
            else
                departure[i] = allSpan.get(i).text();
        }

        String numbers[] = getNumbers(allLinks);
        String routs[] = getRouts(allLinks);

        for (int i = 0, j = 0, k = 0, l = 1; i < numbers.length && j < routs.length && k < arrives.length && l < departure.length; i++, j = j + 2, k = k + 2, l = l + 2) {
            if (numbers[i] != null) {
                System.out.print(numbers[i] + " ");
            }
            if (routs[j] != null && routs[j + 1] != null) {
                System.out.print(routs[j] + " - " + routs[j + 1] + " ");
            }
            if (arrives[k] != null) {
                System.out.print(arrives[k] + " - ");
            }
            if (departure[l] != null) {
                System.out.println(departure[l]);
            }
        }
    }
}