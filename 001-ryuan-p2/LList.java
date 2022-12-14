// TO DO: add your implementation and JavaDocs.
/**
 * The class is to create the list which has head and tail, in which we can add
 * and delete.
 * 
 * @author RongLian Yuan
 *
 * @param <T> data type of the linked list
 */
public class LList<T> {
    /**
     * head is the first node of the list.
     */
    private Node<T> head;
    /**
     * tail is the last node of the list.
     */
    private Node<T> tail;

    /**
     * constructor, to initialize the class set both head and tail to null because
     * it is empty.
     */
    public LList() {

        head = null;
        tail = null;

    }

    /**
     * get the first node of the list which is head.
     * 
     * @return head (first node of the list)
     */
    public Node<T> getFirst() {

        return head;

    }

    /**
     * insert a node at the beginning of the list with value in type T.
     * 
     * @param value bring this value and insert it in the first place
     */
    public void insertFirst(T value) {
        Node<T> currentNode = new Node<T>(value);
        if (head == null) {
            head = currentNode;
            tail = head;
        } else {

            currentNode.setNext(head);
            head = currentNode;

        }

    }

    /**
     * insert a node at the beginning of the list using a node as parameter.
     * 
     * @param newNode insert this node to the beginning of the list
     */
    public void insertFirst(Node<T> newNode) {

        Node<T> currentNode = new Node<T>(newNode.getValue());

        if (head == null) {
            head = currentNode;
            tail = head;
        } else {
            currentNode.setNext(head);
            head = currentNode;
        }

    }

    /**
     * insert a node at the end of the list using a node as a parameter.
     * 
     * @param newNode insert this node to the end of the list
     */
    public void insertLast(Node<T> newNode) {

        Node<T> currentNode = new Node<T>(newNode.getValue());
        if (tail == null) {
            tail = currentNode;
            head = currentNode;
        } else {

            tail.setNext(currentNode);
            tail = currentNode;
        }

    }

    /**
     * remove the first node of the list and return this removed node.
     * 
     * @return the removed node
     */
    public Node<T> removeFirst() {
        if (head == null) {
            return null;
        } else {
            head = head.getNext();
        }

        return head;
    }

    /**
     * read all values in the node from head to tail represented as a string and
     * separated by a single space.
     * 
     * @return a string with all values in the list separated by a single space
     */
    public String listToString() {
        String allItems = "";
        Node<T> currentNode = head;
        while (currentNode != null) {
            allItems = allItems + currentNode.getValue() + " ";
            currentNode = currentNode.getNext();
        }

        return allItems.trim();

    }

    /**
     * This class is used to test some basic functions of the LList class in order
     * to make sure the functions works well and make the following coding go to the
     * correct way.
     * 
     * @param args main methods.
     */
    public static void main(String[] args) {
        /**
         * To test the basic functions.
         * 
         * @author
         *
         */
        class SomeType {
            /**
             * private integer use as a value.
             */
            private int value;

            /**
             * Get this value.
             * 
             * @param value this value.
             */
            public SomeType(int value) {
                this.value = value;
            }

            /**
             * Put the values in a string separated by a space.
             * 
             * @return value the value
             */
            public String toString() {
                return "" + value;
            }

            /**
             * Test if the object o is equals to the value in the stack.
             * 
             * @return false if it doesn't equal. return the old value if it is equal.
             */
            public boolean equals(Object o) {
                if (!(o instanceof SomeType))
                    return false;
                return ((SomeType) o).value == value;
            }
        }

        SomeType item1 = new SomeType(100);
        SomeType item2 = new SomeType(200);
        SomeType item3 = new SomeType(300);
        SomeType item4 = new SomeType(400);
        Node<SomeType> n5 = new Node<>(new SomeType(500));

        LList<SomeType> list = new LList<>();
        list.insertFirst(item1);
        list.insertFirst(item2);
        list.insertFirst(item3);
        list.insertFirst(item4);

        if (list.listToString().equals("400 300 200 100")) {
            System.out.println("Yay1");
        }

        list.insertLast(n5);
        if (list.listToString().equals("400 300 200 100 500")) {
            System.out.println("Yay2");
        }

        list.removeFirst();
        if (list.listToString().equals("300 200 100 500")) {
            System.out.println("Yay3");
        }

        if (list.getFirst().getValue().toString().equals("300")) {
            System.out.println("Yay4");
        }

        list.insertFirst(new SomeType(600));
        if (list.listToString().equals("600 300 200 100 500")) {
            System.out.println("Yay5");
        }

    }
}