package lesson3.stack;

public interface IStack<E> {

    void push(E value);

    E pop();

    E peek();

    int size();

    boolean isEmpty();

    boolean isFull();

    void display();

}
