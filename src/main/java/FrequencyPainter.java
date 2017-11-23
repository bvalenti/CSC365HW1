import javax.swing.*;
import java.awt.*;

public class FrequencyPainter extends JPanel {
    FrequencyTable tmp1 = new FrequencyTable(), tmp2 = new FrequencyTable();
    JFrame frame;

    FrequencyPainter () {
        setPreferredSize(new Dimension(1000,500));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        MyClosedHashElement e;

        g.setColor(Color.lightGray);
        g.fillRect(45,295,1000,400);
        g.fillRect(45,845,1000,400);
        g.fillRect(1270,295,2000,400);
        g.fillRect(1270,845,2000,400);
        g.setColor(Color.black);

        int count = 0;
        for (int i = 0; i < tmp1.frequencies.getTableSize(); i++) {
            if (tmp1.frequencies.getElement(i) != null && !(tmp1.frequencies.getElement(i) instanceof Deleted)) {
                e = tmp1.frequencies.getElement(i);

                for (int j = 0; j < tmp2.frequencies.getTableSize(); j++) {
                    if (tmp2.frequencies.getElement(j) != null) {
                        if (e.getKey().equals(tmp2.frequencies.getElement(j).getKey()) && !(tmp2.frequencies.getElement(j) instanceof Deleted)) {
                            if (e.getVal() != 1 || tmp2.frequencies.getElement(j).getVal() != 1) {
                                g.fillRect(50 + 2 * count, 300, 2, 3 * tmp2.frequencies.getElement(j).getVal());
                                g.fillRect(50 + 2 * count, 850, 2, 3 * e.getVal());
                                count++;
                            }
                        }
                    }
                }
            }
        }

        count = 0;
        for (int i = 0; i < tmp1.metaFrequencies.getTableSize(); i++) {
            if (tmp1.metaFrequencies.getElement(i) != null && !(tmp1.metaFrequencies.getElement(i) instanceof Deleted)) {
                e = tmp1.metaFrequencies.getElement(i);

                for (int j = 0; j < tmp2.metaFrequencies.getTableSize(); j++) {
                    if (tmp2.metaFrequencies.getElement(j) != null) {
                        if (e.getKey().equals(tmp2.metaFrequencies.getElement(j).getKey()) && !(tmp2.metaFrequencies.getElement(j) instanceof Deleted)) {
                            g.fillRect(1270 + 2 * count, 300, 2, 5 * tmp2.metaFrequencies.getElement(j).getVal());
                            g.fillRect(1270 + 2 * count, 850, 2, 5 * e.getVal());
                            count++;
                        }
                    }
                }
            }
        }
    }



    public void paintGraph(FrequencyTable toPaint1, FrequencyTable toPaint2) {
        tmp1 = toPaint1;
        tmp2 = toPaint2;

    }


    public void printerRepaint() {
        validate();
        repaint();
    }
}
