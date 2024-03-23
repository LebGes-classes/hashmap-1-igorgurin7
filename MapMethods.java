package Hash;
import java.util.Comparator;
import java.util.LinkedList;

public class MapMethods<K,V> implements Comparator<Input<K, V>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private int numBuckets;
    private LinkedList<Input<K, V>>[] buckets;

    public MapMethods() {
        this.numBuckets = INITIAL_CAPACITY;
        size = 0;
        buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % numBuckets);
    }

    public void put(K key, V value) {
        if (shouldResize()) {
            resize();
        }
        int index = hash(key);
        LinkedList<Input<K, V>> bucket = buckets[index];

        for (Input<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Input<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Input<K, V>> bucket = buckets[index];

        for (Input<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        LinkedList<Input<K, V>> bucket = buckets[index];

        for (Input<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    private void resize() {
        LinkedList<Input<K, V>>[] oldBuckets = buckets;
        numBuckets *= 2;
        size = 0;
        buckets = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (LinkedList<Input<K, V>> bucket : oldBuckets) {
            for (Input<K, V> entry : bucket) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private boolean shouldResize() {
        return (float) size / numBuckets >= LOAD_FACTOR;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        LinkedList<Input<K, V>> bucket = buckets[index];

        for (Input<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (LinkedList<Input<K, V>> bucket : buckets) {
            for (Input<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public int compare(Input<K, V> o1, Input<K, V> o2) {
        return 0;
    }
}


