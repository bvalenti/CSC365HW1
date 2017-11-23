import java.io.IOException;

public class MyURLs {
    String URLS[] = new String[10];
    FrequencyTable frequencies[] = new FrequencyTable[10];

    public MyURLs() throws IOException {
        HTMLParser myParser = new HTMLParser();

        URLS[0] = "http://www.urbandictionary.com/";
        URLS[1] = "https://www.sas.com/en_us/insights/analytics/machine-learning.html";
        URLS[2] = "https://www.nytimes.com/";
        URLS[3] = "https://www.nobelprize.org/";
        URLS[4] = "https://www.nasa.gov/";
        URLS[5] = "https://www.reddit.com/r/MachineLearning/";
        URLS[6] = "http://www.eljamesauthor.com/books/fifty-shades-of-grey/";
        URLS[7] = "http://www.cnn.com/";
        URLS[8] = "https://www.expatistan.com/cost-of-living";
        URLS[9] = "http://www.guinnessworldrecords.com/";
       // URLS[10] = "http://allrecipes.com/";

        for (int i = 0; i < 10; i++) {
            frequencies[i] = myParser.parseURL(URLS[i]);
            frequencies[i].urlName = URLS[i];
        }
    }

    public void removeCommonWords(FrequencyTable freq) {
        MyClosedHashElement e;

            int count;
            for (int i = 0; i < freq.frequencies.getTableSize(); i++) {
                count = 0;
                if (freq.frequencies.getElement(i) != null && !(freq.frequencies.getElement(i) instanceof Deleted)) {
                    e = freq.frequencies.getElement(i);

                    for (int k = 0; k < frequencies.length; k++) {
                        for (int j = 0; j < frequencies[k].frequencies.getTableSize(); j++) {
                            if (frequencies[k].frequencies.getElement(j) != null) {
                                if (e.getKey().equals(frequencies[k].frequencies.getElement(j).getKey())
                                        && !(frequencies[k].frequencies.getElement(j) instanceof Deleted)) {
                                    count++;
                                    break;
                                }
                            }
                        }
                    }
                    if (count >= 8) {
                        freq.frequencies.remove(freq.frequencies.getElement(i).getKey());
                    }
                }
            }
    }



    public FrequencyTable[] getFrequencies() { return frequencies; }
}
