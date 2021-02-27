package lesson5;

import java.util.ArrayList;

public class BackPackRecursive {

    public static void main(String[] args) {
        int maxWeight = 50;

//        формируем список предметов
        ArrayList<Item> things = new ArrayList<Item>();
        things.add(new Item("Книга", 1, 600));
        things.add(new Item("Бинокль", 2, 5000));
        things.add(new Item("Аптечка", 4, 1500));
        things.add(new Item("Ноутбук", 2, 40000));
        things.add(new Item("Котелок", 1, 500));

//        создаем рюкзак с заданным максимальным весом и указываем список вещей
        BackPack bp = new BackPack(maxWeight, things);
        System.out.println("Максимальный вес рюкзака: " + maxWeight);

//        перебираем все возможные комбинации предметов
        bp.combinate(things);

//        получить "лучший" результат
        ArrayList<Item> result = bp.getBestResult();

//        вывести результат
        for (Item thing : result) {
            System.out.println(thing.toString());
        }

    }
}

class Item {
    String name;
    int weight, cost;

    public Item(String name, int weight, int cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    public String getName() { return name; }
    public int getWeight() { return weight; }
    public int getCost() { return cost; }

    @Override
    public String toString() {
        return String.format("%10s, вес: %2d, стоимость: %5d", name, weight, cost);
    }
}

class BackPack {
    int counter = 0;                    // счетчик количества рекурсий
    int maxBackPackWeight;              // максимальный вес рюкзака
    int maxPrice;                       // максимальная стоимость найденной комбинации
    ArrayList<Item> objects;            // список всех предметов
    ArrayList<Item> bestResult;         // список предметов, дающих в сумме максимальную стоимость

    public BackPack(int maxWeight, ArrayList<Item> objects) {
        this.maxBackPackWeight = maxWeight;
        this.objects = objects;
        this.maxPrice = 0;
        this.bestResult = null;
    }

    // посчитать вес набора предметов
    public int getWeightCurrentSet(ArrayList<Item> items) {
        int w = 0;
        for (Item thing : items) {
            w += thing.getWeight();
        }
        return w;
    }

    // посчитать стоимость набора предметов
    public int getValueCurrentSet(ArrayList<Item> items) {
        int v = 0;
        for (Item thing : items) {
            v += thing.getCost();
        }
        return v;
    }

    // сравнить набор предметов с сохраненным
    public void compare(ArrayList<Item> items) {
        if (bestResult == null) {
            if (getWeightCurrentSet(items) <= maxBackPackWeight) {
                bestResult = items;
                maxPrice = getValueCurrentSet(items);
            }
        } else {
            if(getWeightCurrentSet(items) <= maxBackPackWeight && getValueCurrentSet(items) > maxPrice) {
                bestResult = items;
                maxPrice = getValueCurrentSet(items);
            }
        }
    }

    // перебор всех вариантов коллекции
    public void combinate(ArrayList<Item> objects) {
        counter++; // перебираются ВСЕ комбинации, некоторые из них будут возникать повторно, счетчик не зависит от максимального веса рюкзака
        if (objects.size() > 0) compare(objects);
        for (int i = 0; i < objects.size(); i++) {
            ArrayList<Item> newSet = new ArrayList<Item>(objects);
            newSet.remove(i);
            combinate(newSet);
        }
    }

    // вернуть лучший (сохраненный) вариант
    public ArrayList<Item> getBestResult() {
        System.out.println("Выполнено рекурсий: " + counter);
        return bestResult;
    }
}

/* РЕЗУЛЬТАТ (консоль):

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=64422:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Cloud@Mail.Ru\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson5.BackPackRecursive
Максимальный вес рюкзака: 5
Выполнено рекурсий: 326
     Книга, вес:  1, стоимость:   600
   Бинокль, вес:  2, стоимость:  5000
   Ноутбук, вес:  2, стоимость: 40000

Process finished with exit code 0

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=64427:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Cloud@Mail.Ru\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson5.BackPackRecursive
Максимальный вес рюкзака: 1
Выполнено рекурсий: 326
     Книга, вес:  1, стоимость:   600

Process finished with exit code 0

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=64432:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Cloud@Mail.Ru\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson5.BackPackRecursive
Максимальный вес рюкзака: 4
Выполнено рекурсий: 326
   Бинокль, вес:  2, стоимость:  5000
   Ноутбук, вес:  2, стоимость: 40000

Process finished with exit code 0

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=64437:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Cloud@Mail.Ru\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson5.BackPackRecursive
Максимальный вес рюкзака: 50
Выполнено рекурсий: 326
     Книга, вес:  1, стоимость:   600
   Бинокль, вес:  2, стоимость:  5000
   Аптечка, вес:  4, стоимость:  1500
   Ноутбук, вес:  2, стоимость: 40000
   Котелок, вес:  1, стоимость:   500

Process finished with exit code 0

*/