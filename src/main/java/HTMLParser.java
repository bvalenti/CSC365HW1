import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

import static java.util.Collections.replaceAll;

public class HTMLParser {

    public FrequencyTable parseURL(String url) throws IOException {
        FrequencyTable out = new FrequencyTable();

        Document doc = Jsoup.connect(url).get();
        Elements metaTags = doc.getElementsByTag("meta");
        Elements elements = doc.select("*");


        for (Element element : elements) {
            String tmp[] = element.ownText().split(" ");
            for (int j = 0; j < tmp.length; j++) {
                if (!tmp[j].equals("")) {
                    tmp[j] = tmp[j].replaceAll("\\P{L}+", "");
//                    System.out.println(tmp[j]);
                    out.frequencies.put(tmp[j],1);
                }
            }
        }

        for (Element element : metaTags) {
            String tmp[] = element.attr("content").split(" |,|\\.|-|:|=|;|/|\\?|!|%|\\(|\\)|@");
            for (int j = 0; j < tmp.length; j++) {
                tmp[j] = tmp[j].replaceAll("\\P{L}+", "");
                if (!tmp[j].equals("")) {
//                    System.out.println(tmp[j]);
                    out.metaFrequencies.put(tmp[j], 1);
                }
            }
        }

        return out;
    }
}
