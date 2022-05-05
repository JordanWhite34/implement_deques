package deques;

/**
 * @see Deque
 */
public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    // Feel free to add any additional fields you may need, though.

    // create the links between the sentinel nodes in the constructor
    public LinkedDeque() {
        size = 0;
        front = new Node<>(null);
        back = new Node<>(null);
        front.next =  back;
        back.prev = front;
    }

    // adding to the front of the list
    // keeps track of previous list to then link new element to
    public void addFirst(T item) {
        size += 1;
        Node<T> newNode = new Node<>(item);
        Node<T> temp = front.next;
        front.next = newNode;
        newNode.prev = front;
        front.next.next = temp;
        temp.prev = front.next;

    }

    // add to the back of the list
    // keeps track of the previous list to then link the new element to
    public void addLast(T item) {
        size += 1;
        Node<T> newNode = new Node<>(item);
        Node<T> temp = back.prev;
        back.prev = newNode;
        newNode.next = back;
        back.prev.prev = temp;
        temp.next = back.prev;

    }

    // removes the first element at the front of the list
    // returns the value of the element that was removed
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = front.next.value;
        front.next = front.next.next;
        front.next.prev = front;
        return value;
    }

    // removes the last element in the list
    // returns the value of the element that was removed
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = back.prev.value;
        back.prev = back.prev.prev;
        back.prev.next = back;
        return value;

    }

    // returns the value at the indicated index of the list
    // has two implementations to increase the speeds of the worst case scenarios
    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        T currentValue = null;
        if (index <= (size-1)/2) {
            /* implements the algorithm for the average case scenarios
            including the best case scenario */
            int currentCount = 0;
            Node<T> current = front.next;
            while (current.next != null) {
                if (currentCount == index) {
                    currentValue = current.value;
                    break;
                }
                currentCount++;
                current = current.next;
            }
        } else {
            // implements a different algorithm to increase the efficiency of the worst case scenarios.
            int currentCount = size - 1;
            Node<T> current = back.prev;
            while (current.prev != null) {
                if (currentCount == index) {
                   currentValue = current.value;
                   break;
                }
                currentCount--;
                current = current.prev;
            }
        }
        return currentValue;
    }

    public int size() {
        return size;
    }
}
