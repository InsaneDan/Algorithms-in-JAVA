package lesson5;

import lesson2.ArrayImpl;

public class RecSortedArrayImpl <E extends Comparable<? super E>> extends ArrayImpl<E> {

    public RecSortedArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public RecSortedArrayImpl(int initialCapacity) {
        this.data = (E[]) new Comparable[initialCapacity];
    }


    // O(logN)
    @Override
    public int indexOf(E value) {
        return recBinaryFind(value, 0, size - 1);
    }

    private int recBinaryFind(E value, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;
        if (data[mid].equals(value)) {
            return mid;
        }

        if (value.compareTo(data[mid]) > 0) {
            return recBinaryFind(value, mid+ 1 , high);
        } else {
            return recBinaryFind(value, low , mid - 1);
        }
    }

    // O(n)
    @Override
    public void add(E value) {
        checkAndGrow();

        int index = size;
        for (int i = 0; i < size; i++) {
            if (data[i].compareTo(value) > 0) {
                index = i;
                break;
            }
        }

        if (index == size) {
            data[size++] = value;
        } else {
            doInsert(value, index);
        }
    }

    private void doInsert(E value, int index) {
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }
}
