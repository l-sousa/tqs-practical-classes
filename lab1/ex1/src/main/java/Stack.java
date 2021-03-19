import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Hello world!
 *
 */
public class Stack<T>
{
    LinkedList<T> ll = new LinkedList<>();

    public boolean isEmpty() {
        if (ll.size() == 0) {
            return true;
        }
        return false;
    }

    public void push(T e) {
        ll.addFirst(e);
    }

    public T pop() {
        if (ll.size() == 0) {
            throw new NoSuchElementException();
        }
        T ret = ll.get(0);
        ll.remove(0);
        return ret;
    }

    public T peek() {
        if (ll.size() == 0) {
            throw new NoSuchElementException();
        }
        return ll.get(0);
    }

    public int size() {
        return ll.size();
    }



}