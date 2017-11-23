public class MyHashTable {

    private int tableSize = 128;
    private MyHashElement arr[] = new MyHashElement[tableSize];
    private int elementCount;

    public MyHashTable() {
        for (int i = 0; i < tableSize; i++) {
            arr[i] = null;
        }
    }

    public int get(String key) {
        int h = getHashCode(key);
        int i = h & (tableSize - 1);
        MyHashElement e = arr[i];

        if (e == null) {
            return -1;
        } else {
            while (e.getNext() != null && !e.getKey().equals(key)) {
                e = e.getNext();
            }
            if (e.getKey().equals(key)) {
                return e.getVal();
            } else {
                return -1;
            }
        }
    }

    public void put(String key, int val) {
        int h = getHashCode(key);
        int i = h & (tableSize - 1);
        MyHashElement e = arr[i];

        if (e == null) {
            arr[i] = new MyHashElement(val, key);
            elementCount++;
        } else {
            while (e.getNext() != null && !e.getKey().equals(key)) {
                e = e.getNext();
            }
            if (e.getKey().equals(key)) {
                e.setVal(val);
            } else {
                e.setNext(new MyHashElement(val, key));
                elementCount++;
            }
        }

        if (elementCount >= 0.75*tableSize) {
            resizeHashTable();
        }
    }

    public void remove(String key) {
        int h = getHashCode(key);
        int i = h & (tableSize - 1);
        MyHashElement e = arr[i];
        MyHashElement pred = null;

        if (e == null) {
        } else {
            while (e.getNext() != null && !e.getKey().equals(key)) {
                e = e.getNext();
                pred = e;
            }
            if (e.getKey().equals(key)) {
                if (pred == null) {
                    arr[i] = e.getNext();
                    elementCount--;
                } else {
                    pred.setNext(e.getNext());
                    elementCount--;
                }
            }
        }
    }

    public int getHashCode(String key){
        int h = 0;
        for (int i = 0; i < key.length(); i++) {
            h = h * 29 + key.charAt(i);
        }
        return h;
    }

    public void resizeHashTable() {
        tableSize = tableSize*2;
        MyHashElement temp[] = arr;
        arr = new MyHashElement[tableSize];

        for (int j = 0; j < temp.length; j++) {
            if (temp[j] != null) {
                this.put(temp[j].getKey(), temp[j].getVal());
            }
        }
    }

    public void setTableSize(int s) { tableSize = s; }
}
