package lesson3.homework;

import lesson3.queue.IQueue;

public interface DequePart<E> extends IQueue<E> {

    boolean insertLeft(E element);

    boolean insertRight(E element);

    E removeLeft();

    E removeRight();

}
