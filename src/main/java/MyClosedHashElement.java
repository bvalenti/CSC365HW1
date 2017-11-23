public class MyClosedHashElement {
    private int val;
    private String key;

    public MyClosedHashElement(String k, int v) {
        val = v;
        key = k;
    }

    public String getKey() {return key;}
    public int getVal() {return val;}
    public void incVal() {val++;}
    public void setVal(int s) {val = s;}
}
