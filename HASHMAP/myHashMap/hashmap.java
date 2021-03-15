
//important points 
//1) need for array of Linked list-> array (to get group in O(1)) + Linked List(to add and remove in O(1))
//2) amortized O(1) explanation (search in a group + rehashing)
//3) HashCode need + Array of (String vs String) Explanation using hashcode
//4) Finding index within array
//5) creating all functions
//6) Making it generic (k,v)
//7) Rehashing concept + function (lambda->groupsize/bucketsize >=0.7)
//8) Creating (for Each) for our HashMap(iterator and iterable)
import java.util.*;

public class hashmap<Key, Value> implements Iterable<Key> {
    public Iterator<Key> iterator() {
        Iterator it = new TravelOnMap();
        return it;
    };

    public class TravelOnMap implements Iterator<Key> {
        int lSize = size;
        int idx = 0;
        int i = 0;

        public boolean hasNext() {
            if (lSize == 0 || idx == Buckets.length)
                return false;
            return true;
        }

        public Key next() {
            Key fr = null;
            while (Buckets[idx].size() == 0)
                idx++;
            fr = Buckets[idx].get(i).key;
            i++;
            if (i == Buckets[i].size()) {
                idx++;
                i = 0;
            }
            return fr;
        }
    }

    private class HMNode {
        private Key key;
        private Value value;

        // Note: above private members are visible to outer class too
        public HMNode(Key k, Value v) {
            this.key = k;
            this.value = v;
        }

        // getters
        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        // setters
        public void setKey(Key k) {
            this.key = k;
        }

        public void setValue(Value v) {
            this.value = v;
        }

        @Override
        public String toString() {
            return key + "->" + value + "\n";
        }
    }

    private LinkedList<HMNode>[] Buckets;
    private int size = 0;

    public hashmap() {
        initBuckets(5);
    }

    private void initBuckets(int n) {
        Buckets = new LinkedList[n];
        for (int i = 0; i < n; i++)
            Buckets[i] = new LinkedList<>();
    }

    // hash function
    public int hashFunction(Key k) {
        int idx = k.hashCode();
        idx = Math.abs(idx);
        return idx % Buckets.length;
    }

    // get index within bucket

    public int getIndexWihinBucket(Key k, int idx) {
        int i = 0;
        for (HMNode Node : Buckets[idx]) {
            if (Node.key.equals(k))
                return i;
            i++;
        }
        return -1;
    }

    // put function
    public void put(Key k, Value v) {
        int idx = hashFunction(k);
        int i = getIndexWihinBucket(k, idx);
        if (i == -1) {// add
            Buckets[idx].add(new HMNode(k, v));
            size++;
        } else {// update
            Buckets[idx].get(i).value = v;
        }
        if ((Buckets[idx].size() * 1.0) / Buckets.length >= 0.7) {
            rehash();
        }
    }

    public void rehash() {
        LinkedList<HMNode>[] oList = Buckets;
        initBuckets(oList.length * 2);
        for (HMNode Node : keySet()) {
            put(Node.key, Node.value);
        }
    }

    // remove
    public Value remove(Key k) {
        int idx = hashFunction(k);
        int i = getIndexWihinBucket(k, idx);
        if (i == -1) {// add
            return null;
        } else {// update
            Value v = Buckets[idx].get(i).value;
            Buckets[idx].remove(i);
            size--;
            return v;
        }
    }

    // get function
    public Value get(Key k) {
        int idx = hashFunction(k);
        int i = getIndexWihinBucket(k, idx);
        if (i == -1) {
            return null;
        } else {
            return Buckets[idx].get(i).value;
        }
    }

    // contains key
    public boolean containsKey(Key k) {
        int idx = hashFunction(k);
        int i = getIndexWihinBucket(k, idx);
        if (i == -1) {
            return false;
        } else {
            return true;
        }
    }

    // size function
    public int size() {
        return size;
    }

    public ArrayList<HMNode> keySet() {
        ArrayList<HMNode> list = new ArrayList<>();
        for (int i = 0; i < Buckets.length; i++) {
            for (HMNode Node : Buckets[i]) {
                list.add(Node);
            }
        }
        return list;
    }
}