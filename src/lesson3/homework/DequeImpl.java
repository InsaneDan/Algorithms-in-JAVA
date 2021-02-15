package lesson3.homework;


import lesson3.queue.QueueImpl;

public class DequeImpl<E> extends QueueImpl<E> implements DequePart<E> {

    public DequeImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean insertRight(E element) {
        return super.insert(element);
    }

    @Override
    public E removeLeft() {
        return super.remove();
    }

    @Override
    public boolean insertLeft(E element) {
        if (isFull()) {
            return false;
        }
        if (head == DEFAULT_HEAD)
            head = data.length;

        data[--head] = element;
        size++;

        return true;
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            return  null;
        }
        if (tail == DEFAULT_TAIL)
            tail = data.length - 1;

        size--;
        return data[tail--];
    }
}

class DequeDemo {
    public static void main(String[] args) {

        DequeImpl<Integer> dq = new DequeImpl<>(5);

        dq.insertRight(1);
        dq.insertRight(2);
        dq.insertRight(3);
        dq.insertLeft(4);
        dq.insertLeft(5);    // 5 4 1 2 3

        dq.removeLeft();            // 4 1 2 3
        dq.removeRight();           // 4 1 2

        while (!dq.isEmpty()) {
            System.out.println(dq.removeLeft());
        }

    }
}