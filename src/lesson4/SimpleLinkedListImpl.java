package lesson4;

import java.util.*;

public class SimpleLinkedListImpl<E> implements LinkedList<E> {

    protected int size;
    protected Node<E> firstElement; //002[006 --> 005]

    @Override
    public void insertFirst(E value) {
        firstElement = new Node<>(value, firstElement); //001[005] --> //002[006-->005]
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<E> removedNode = firstElement;
        firstElement = removedNode.next;
//        firstElement = firstElement.next;

        removedNode.next = null;
        size--;
        return removedNode.item;
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

        if (current == firstElement) {
            firstElement = firstElement.next;
        } else {
            previous.next = current.next;
        }

        current.next = null;
        size--;
        return true;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return firstElement == null;
    }

    @Override
    public void display() {
        System.out.println("----------");
        Node<E> current = firstElement;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
        System.out.println("----------");
    }

    @Override
    public E getFirst() {
        return firstElement != null ? firstElement.item : null;
//        return Optional.ofNullable(firstElement)
//                .map(node -> node.item)
//                .orElse(null);
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> itr = new Iterator<E>() {

            private final SimpleLinkedListImpl<E> list = SimpleLinkedListImpl.this;
            private Node<E> current = list.firstElement;
            private Node<E> previous = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E nextValue = current.item;
                previous = current;
                current = current.next;
                return nextValue;
            }

            @Override
            public void remove() {
                if (previous == null) {
                    list.firstElement = current.next;
                    reset();
                } else {
                    previous.next = current.next;
                    if ( !hasNext() ) {
                        reset();
                    } else {
                        current = current.next;
                    }
                }
            }

            public void reset() {
                current = list.firstElement;
                previous = null;
            }
        };
        return itr;
    }
}
