// TO DO: JavaDocs
/**
 * A generic class representing a node in the linked list implementation.
 * 
 *
 * @author W. Masri
 * @param <T> describe type parameter.
 */
public class Node<T> {
    /**
     * The node's value.
     */
    private T value;

    /**
     * The node's link to the next element in the linked list.
     */

    private Node<T> next;

    /**
     * constructors.
     * 
     * @param value set the value to this value when create
     * @param next  set the node next to this next
     */
    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * overloading constructors.
     * 
     * @param value to set the value to this value when create
     */
    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    /**
     * getters and setters for the value and next attributes.
     * 
     * @return value
     */
    public T getValue() {
        return value;
    }

    /**
     * set the this value to the new value.
     * 
     * @param value the new value that we need to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * get the next node.
     * 
     * @return the next node
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * set the node to the next node.
     * 
     * @param next use this node to set as next node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * return the value as a string description.
     * 
     * @return a string description of the node's value attribute
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
