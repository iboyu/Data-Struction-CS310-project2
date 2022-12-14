// TO DO: add your implementation and JavaDocs.
/**
 * This class uses array and link list to manually create a hash map with put
 * and get functions. The class has two parameters which are key and value. Each
 * key represent each value. The key has to be unique.
 * 
 * @author RongLian Yuan
 *
 * @param <K> k mean key
 * @param <V> v mean value
 */
public class HashMap<K, V> {

    // This HashMap implementation uses a LList<T> composed of Node<T>.
    // Since two generic parameters <K, V> are needed instead of one,
    // the Pair class below is provided to be used as follows: Node<Pair> and
    // LList<Pair>
    /**
     * The pair class has two parameters which are key and value. In order to make
     * hash map class easy to process, this class make these two parameters together
     * as "pair".
     * 
     * @author
     *
     * @param <K> k mean key
     * @param <V> v mean value
     */
    class Pair<K, V> {
        /**
         * key with type K.
         */
        private K key;
        /**
         * value with type V.
         */
        private V value;

        /**
         * Constructor to create a pair with key and value.
         * 
         * @param key   this key
         * @param value this value
         */
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * get the key of the pair.
         * 
         * @return this key
         */
        public K getKey() {
            return key;
        }

        /**
         * get the value of the pair.
         * 
         * @return this value
         */
        public V getValue() {
            return value;
        }

        /**
         * set the key to the new key.
         * 
         * @param key new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * set the value to the new value.
         * 
         * @param value new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * get the hash code of the key.
         * 
         * @return the hash code
         */
        @Override
        public int hashCode() {
            return key.hashCode();
        }

        /**
         * make sure if the key in the object equals the new key.
         * 
         * @param obj the object with key and value
         * @return true if they are equal return false if they are not equal
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (!(obj instanceof Pair))
                return false;
            Pair pair = (Pair) obj;
            return pair.key.equals(key);
        }
    }

    /**
     * array of LLists and each list is composed of Node.
     */
    private LList<Pair>[] buckets;

    /**
     * set the default capacity equals to twenty.
     */
    final static private int DEFAULT_CAPACITY = 20;

    /**
     * track how many elements in the hash map.
     */
    private int size = 0;

    /**
     * constructor.
     */
    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * overload method to create a new array with the length of the new capacity.
     * 
     * @param capacity new capacity
     */
    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        buckets = (LList<Pair>[]) new LList[capacity];
    }

    /**
     * get the size of the hash map.
     * 
     * @return the number of the elements
     */
    public int size() {
        return size;
    }

    /**
     * get the length of the hash map.
     * 
     * @return the length of the hash map
     */
    private int getCapacity() {
        return buckets.length;
    }

    /**
     * get the hash code of the key.
     * 
     * @param key use to calculate the hash code
     * @return hash code
     */
    private int getHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    /**
     * create some little border to separate the value of the hash map in order for
     * us to read easily where is the element.
     * 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LList<Pair> list : buckets) {
            sb.append("[");
            if (list != null) {
                sb.append(list.listToString());
            }
            sb.append(", ");
            sb.append("]");
        }
        return "{" + sb.toString() + "}";
    }

    /**
     * Get the key and value. Using the key to calculate the hash code and "mod" the
     * length of the list. The result is the index of the array. Put that
     * corresponding value inside the array If the index is empty, just insert the
     * value. If it is not empty: If the key is equal to the key in the array,
     * override the old value with the new value. If the key is not equal to the key
     * in the array, link this value to the bottom of the original value.
     * 
     * @param key   use to make sure the position of the value
     * @param value the value needed to be insert
     */
    @SuppressWarnings("unchecked")
    public void put(K key, V value) {

        Pair<K, V> newValue = new Pair<K, V>(key, value);
        Node<Pair> entry = new Node<Pair>(newValue);

        int index = getHash(key) % DEFAULT_CAPACITY;

        if (buckets[index] == null) {
            LList<Pair> linkedList = new LList<Pair>();

            linkedList.insertLast(entry);
            buckets[index] = linkedList;
            size++;

        } else {

            Node<Pair> oldNode = buckets[index].getFirst();
            boolean ifRepeat = false;
            while (oldNode != null) {

                if (oldNode.getValue().getKey().equals(key)) {
                    ifRepeat = true;

                    oldNode.getValue().setValue(value);
                    break;
                } else {

                    oldNode = oldNode.getNext();

                }

            }
            if (ifRepeat == false) {
                buckets[index].insertLast(entry);
                size++;
            }
        }

    }

    // Hint: This function involves LList<Pair> and Node<Pair>
    // Cost: O(1) on average, and O(n) worst case
    //
    // if element was not found return null
    /**
     * This method uses key the get the corresponding value. It still to use the key
     * to get the hash code and "mod" the length to get the index. And from that
     * index, find that corresponding list and check if the list has the value
     * corresponded to the key, if it is, return the value or return null.
     * 
     * @param key use this key to get the unique the hash code
     * @return the value corresponding to the specific key.
     */
    @SuppressWarnings("unchecked")
    public V get(K key) {

        int index = getHash(key) % DEFAULT_CAPACITY;

        Node<Pair> correct = buckets[index].getFirst();

        while (correct != null) {
            if (correct.getValue().getKey().equals(key)) {
                return (V) correct.getValue().getValue();
            }
            correct = correct.getNext();
        }

        return null;

    }

    /**
     * This class is used to test the basic functions in hash map to make sure it
     * works well. In order to make sure the following class can move smoothly.
     * 
     * @param args main method
     */
    public static void main(String args[]) {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put(i, "Val" + i);
        }

        if (map.size() == 10000) {
            System.out.println("Yay1");
        }

        if (map.get(5).equals("Val5") && map.get(500).equals("Val500") && map.get(5000).equals("Val5000")
                && map.get(9999).equals("Val9999")) {
            System.out.println("Yay2");
        }

        map.put(0, "Val" + 200);

        if (map.size() == 10000) {
            System.out.println("Yay3");
        }

    }

}