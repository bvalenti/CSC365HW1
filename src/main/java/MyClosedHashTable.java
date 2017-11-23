public class MyClosedHashTable {

        private int tableSize = 128;
        private MyClosedHashElement arr[] = new MyClosedHashElement[tableSize];
        private int elementCount;

        public MyClosedHashTable() {
            for (int i = 0; i < tableSize; i++) {
                arr[i] = null;
            }
        }

        //===========================================
        public int get(String key) {
            int h = getHashCode(key);
            int i = h & (tableSize - 1);
            MyClosedHashElement e = arr[i];
            int starti = i;

            if (e == null) {
                return -1;
            } else {
                while (!e.getKey().equals(key) && !(e instanceof Deleted)) {
                    i++;
                    if (i == starti) { break; }
                    if (i == tableSize) { i = 0; }
                    e = arr[i];
                }
                if (e.getKey().equals(key) && !(e instanceof Deleted)) {
                    return e.getVal();
                } else {
                    return -1;
                }
            }
        }

        //===========================================
        public void put(String key, int val) {
            int h = getHashCode(key);
            int i = h & (tableSize - 1);
            MyClosedHashElement e = arr[i];

            if (e == null) {
                arr[i] = new MyClosedHashElement(key, val);
                elementCount++;
            } else {
                while (e != null) {
                    if (e.getKey().equals(key) && !(e instanceof Deleted)) {
                        break;
                    }
                    i++;
                    if (i == tableSize) { i = 0; }
                    e = arr[i];
                }
                if (e == null) {
                    arr[i] = new MyClosedHashElement(key, val);
                    elementCount++;
                } else if (e.getKey().equals(key)) {
                    arr[i].incVal();
                }
            }

            if (elementCount >= 0.5*tableSize) {
                resizeHashTable();
            }
        }

        //===========================================
        public void remove(String key) {
            int h = getHashCode(key);
            int i = h & (tableSize - 1);
            MyClosedHashElement e = arr[i];
            int starti = i;

            if (e == null) {
            } else {
                while (!e.getKey().equals(key) && !(e instanceof Deleted)) {
                    i++;
                    if (i == starti) { break; }
                    if (i == tableSize) { i = 0; }
                    e = arr[i];
                }
                if (e.getKey().equals(key)) {
                    Deleted temp = new Deleted(e.getKey(), e.getVal());
                    arr[i] = temp;
                }
            }
        }

        //===========================================
        public int getHashCode(String key){
            int h = 0;
            for (int i = 0; i < key.length(); i++) {
                h = h * 29 + key.charAt(i);
            }
            return h;
        }

        //===========================================
        public void resizeHashTable() {
            tableSize = tableSize*2;
            MyClosedHashElement temp[] = arr;
            arr = new MyClosedHashElement[tableSize];

            for (int j = 0; j < temp.length; j++) {
                if (temp[j] != null) {
                    if (!(temp[j] instanceof Deleted)) {
                        put(temp[j].getKey(), temp[j].getVal());
                    }
                }
            }
        }

        public void setTableSize(int s) { tableSize = s; }
        public int getTableSize() { return tableSize; }
        public MyClosedHashElement getElement(int ind) { return arr[ind];}
}


