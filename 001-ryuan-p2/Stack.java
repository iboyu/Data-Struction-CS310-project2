// TO DO: add your implementation and JavaDocs.
/**
 * This class is to use LList class to build a stack. It has basic functions
 * like pop, push and peek.
 * 
 * @author RongLian Yuan
 *
 * @param <T> data type of the stack
 */
public class Stack<T> {
    /**
     * elements is a linked list. Each element have the property of the LList class.
     */
    private LList<T> elements;

    /**
     * size is the number of the elements in the stack.
     */
    private int size;

    /**
     * Constructor Initialize the stack to make it empty.
     */
    public Stack() {
        elements = new LList<T>();
        size = 0;
    }

    /**
     * insert the element at the beginning of the list.
     * 
     * @param e put this value to the beginning of the list
     */
    public void push(T e) {
        elements.insertFirst(e);
        size++;

    }

    /**
     * delete the beginning element of the list and return its value.
     * 
     * @return the value of this removed element
     */
    public T pop() {

        if (size == 0) {
            return null;
        } else {
            T thing = elements.getFirst().getValue();

            elements.removeFirst();
            size--;
            return thing;

        }

    }

    /**
     * Return the element at the beginning of the list.
     * 
     * @return the first element in the list
     */
    public T peek() {

        return elements.getFirst().getValue();
    }

    /**
     * check if the stack is empty.
     * 
     * @return true if it is empty and return false if it is not empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * get the number of the elements in the stack.
     * 
     * @return the number of the elements
     */
    public int getSize() {

        return size;
    }

    /**
     * Get the value of the stack from top to the bottom in a string form.
     * 
     * @return a string representing the values in the stack
     */
    public String toString() {

        return elements.listToString();
    }

    /**
     * This class is used to test some basic functions in stack class in order to
     * make it work well.
     * 
     * @param args main method
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
             * The value used to stored in stack.
             */
            private int value;

            /**
             * To get this value.
             * 
             * @param value this value
             */
            public SomeType(int value) {
                this.value = value;
            }

            /**
             * To get the value as a string separated by space.
             * 
             * @return the String value
             */
            public String toString() {
                return "" + value;
            }

            /**
             * To see if the value equals the value in the stack.
             * 
             * @return o false if it is not equal return the value if it is equal.
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

        Stack<SomeType> s = new Stack<>();
        s.push(item1);
        s.push(item2);

        if (s.getSize() == 2) {
            System.out.println("Yay1");
        }

        if (s.peek().toString().equals("200")) {
            System.out.println("Yay2");
        }
        if (s.pop().toString().equals("200")) {
            System.out.println("Yay3");
        }

        s.push(item3);
        s.push(item4);
        if (s.toString().equals("400 300 100")) {
            System.out.println("Yay4");
        }

        s.pop();
        s.pop();
        if (s.toString().equals("100")) {
            System.out.println("Yay5");
        }

        s.pop();
        if (s.isEmpty()) {
            System.out.println("Yay6");
        }

    }
}