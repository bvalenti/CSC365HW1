public class FrequencyTable {
    MyClosedHashTable frequencies;
    MyClosedHashTable metaFrequencies;
    String urlName;

    FrequencyTable() {
        frequencies = new MyClosedHashTable();
        metaFrequencies = new MyClosedHashTable();
    }

    public double cosineSimMetric(FrequencyTable b) {
        double sim = 0;
        double magA = 0, magB = 0;
        double dotAB = 0;
        int metaWeight = 2;
        MyClosedHashElement e;

        for (int i = 0; i < b.frequencies.getTableSize(); i++) {
            if (b.frequencies.getElement(i) != null && !(b.frequencies.getElement(i) instanceof Deleted)) {
                e = b.frequencies.getElement(i);
                magB = magB + e.getVal()*e.getVal();

                for (int j = 0; j < frequencies.getTableSize(); j++) {
                    if (frequencies.getElement(j) != null) {
                        if (e.getKey().equals(frequencies.getElement(j).getKey()) && !(frequencies.getElement(j) instanceof Deleted)) {
                            dotAB = dotAB + e.getVal() * frequencies.getElement(j).getVal();
                        }
                    }
                }
            }
        }
        for (int j = 0; j < frequencies.getTableSize(); j++) {
            if (frequencies.getElement(j) != null && !(frequencies.getElement(j) instanceof Deleted)) {
                e = frequencies.getElement(j);
                magA = magA + e.getVal() * e.getVal();
            }
        }


        //Same as above but for meta data
        for (int i = 0; i < b.metaFrequencies.getTableSize(); i++) {
            if (b.metaFrequencies.getElement(i) != null && !(b.metaFrequencies.getElement(i) instanceof Deleted)) {
                e = b.metaFrequencies.getElement(i);
                magB = magB + e.getVal()*e.getVal()*metaWeight*metaWeight;

                for (int j = 0; j < metaFrequencies.getTableSize(); j++) {
                    if (metaFrequencies.getElement(j) != null) {
                        if (e.getKey().equals(metaFrequencies.getElement(j).getKey()) && !(metaFrequencies.getElement(j) instanceof Deleted)) {
                            dotAB = dotAB + e.getVal() * metaFrequencies.getElement(j).getVal()*metaWeight*metaWeight;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < metaFrequencies.getTableSize(); j++) {
            if (metaFrequencies.getElement(j) != null && !(metaFrequencies.getElement(j) instanceof Deleted)) {
                e = metaFrequencies.getElement(j);
                magA = magA + e.getVal() * e.getVal()*metaWeight*metaWeight;
            }
        }

        magB = Math.sqrt(magB);
        magA = Math.sqrt(magA);
        sim = dotAB / (magA * magB);
        return sim;
    }
}
