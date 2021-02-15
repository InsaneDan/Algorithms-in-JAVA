package lesson4;

public class TwoSideLinkedListImpl<E>
        extends SimpleLinkedListImpl<E>
        implements TwoSideLinkedList<E> {

    private Node<E> lastElement;

    @Override
    public void insertFirst(E value) {
        super.insertFirst(value);
        if (size == 1) {
            lastElement = firstElement;
        }
    }


    @Override
    public E removeFirst() {
        E removeValue = super.removeFirst();
        if (isEmpty()) {
            lastElement = null;
        }

        return removeValue;
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = firstElement;
        Node<E> previous = null;

        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }

            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (size == 1) {
            removeFirst();
            return true;
        }

        if (current == firstElement) {
            firstElement = firstElement.next;
        } else if (current == lastElement) {
            lastElement = previous;
            previous.next = null;
        }
        else {
            previous.next = current.next;
        }

        current.next = null;
        size--;
        return true;
    }

    @Override
    public void insertLast(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (isEmpty()) {
            firstElement = newNode;
//            lastElement = newNode;
        } else {
            lastElement.next = newNode;
//            lastElement = newNode;
        }
        lastElement = newNode;
        size++;
    }
}
