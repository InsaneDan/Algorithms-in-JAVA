package lesson3.deque;


import lesson3.queue.IQueue;

public interface Deque<E> extends IQueue<E> {

    boolean insertLeft(E value);
    boolean insertRight(E value);

    E removeLeft();
    E removeRight();
}