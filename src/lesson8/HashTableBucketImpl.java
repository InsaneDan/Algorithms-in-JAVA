package lesson8;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTableBucketImpl<K, V> implements HashTable<K, V> {

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
    * учитываем ограничения дженериков при создании массивов - type erasure => нельзя создать массив неизвестного типа;
    * поэтому - ArrayList и при инициализации заполнить его null, чтобы получить нужную длину
    * др.вариант: array = (T[])Array.newInstance(TClass, 10);*/

    private final ArrayList<LinkedList<Item<K, V>>> data;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTableBucketImpl(int maxSize) {
        this.data = new ArrayList<LinkedList<Item<K, V>>>(maxSize);
        for (int i = 0; i < maxSize; i++) {
            data.add(null);
        }
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.size();
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        LinkedList<Item<K, V>> bucket;
        if (data.get(index) != null) {                                      // если в ячейке массива есть значение - получаем список
            bucket = data.get(index);
        } else {
            bucket = new LinkedList<Item<K, V>>();                          // если ячейка null, создаем в ней новый экземпляр LinkedList
        }
        for (Item<K, V> item : bucket) {                                    // проверяем ключи всех элементов в bucket
            if (item.getKey().equals(key)) {                                // если ключ найден - обновляем значение
                item.setValue(value);
                return true;
            }
        }
        bucket.add(new Item<K, V>(key, value));                             // перебрали все элементы, но ключ не найден - добавляем к списку
        data.set(index, bucket);                                            //кладем список обратно в массив
        size++;                                                             //добавляем размер
        return true;                                                        // возвращаем результат
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);                                          // определяем индекс
        if (data.get(index) == null) return null;                           // нет данных - возвращаем null
        LinkedList<Item<K, V>> bucket = data.get(index);                    // проверяем ключи всех элементов в bucket
        for (Item<K, V> item : bucket) {
            if (item.getKey().equals(key)) {                                // ключ найден
                return item.getValue();
            }
        }
        return null;                                                        // ключ не найден
    }

    @Override
    public V remove(K key) {
        V val = null;                                                       // возвращаемое значение (если будет найдено)
        int index = hashFunc(key);                                          // определяем индекс
        if (data.get(index) == null) return null;                           // нет данных - возвращаем null
        LinkedList<Item<K, V>> bucket = data.get(index);                    // проверяем ключи всех элементов в bucket
        for (Item<K, V> item : bucket) {
            if (item.getKey().equals(key)) {                                // ключ найден
                val = item.getValue();                                      // сохраняем значение
                bucket.remove(item);                                        // удаляем элемент из списка
                data.set(index, bucket.size() != 0 ? bucket : null);        // возвращаем измененный список или null в массив
            }
        }
        size--;                                                             //уменьшаем размер
        return val;                                                         // возвращаем удаленное значение
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void display() {
        System.out.println("----------");
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("%d = [%s]", i, data.get(i));
            System.out.println();
        }
        System.out.println("----------");
    }
}
