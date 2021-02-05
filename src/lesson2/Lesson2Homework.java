package lesson2;

import java.util.Arrays;
import java.util.Random;

public class Lesson2Homework {

    private static final int ARRAY_SIZE = 1000;
    static int min = 0, max = 100; // диапазон допустимых значений (max - не входит в диапазон)

    private static int[] arr = new int[ARRAY_SIZE];

    public static void main(String[] args) {
        System.out.println("Заполнение массива случайными целыми числами");
        setDataArray();

        int[] arr1 = Arrays.copyOf(arr, arr.length);
        sortBubble(arr1);

        int[] arr2 = Arrays.copyOf(arr, arr.length);
        sortSelect(arr2);

        int[] arr3 = Arrays.copyOf(arr, arr.length);
        sortInsert(arr3);
    }

    public static void setDataArray() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(max - min) + min;
        }
    }

    public static void sortBubble(int[] arr) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sortBubble: длина массива " + ARRAY_SIZE + " элементов, время сортировки " + (endTime - startTime)  + " мс");
    }

    private static void swap(int indexA, int indexB, int[] arr) {
        if (indexA >= 0 && indexB >=0 && indexA < arr.length && indexB < arr.length) {
            int temp = arr[indexA];
            arr[indexA] = arr[indexB];
            arr[indexB] = temp;
        }
    }

    // O(n^2) - compare; O(n) = exchange
    public static void sortSelect(int[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(minIndex, i, arr);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sortSelect: длина массива " + ARRAY_SIZE + " элементов, время сортировки " + (endTime - startTime) + " мс");
    }

    // O(n^2) --> O(n) - compare; O(n) --> O(0) = exchange
    public static void sortInsert(int[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int in = i;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = temp;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sortInsert: длина массива " + ARRAY_SIZE + " элементов, время сортировки " + (endTime - startTime) + " мс");
    }

}

/* РЕЗУЛЬТАТ (консоль):
-------------------- JDK-1.8 --------------------
"C:\Program Files\Java\jdk1.8.0_271\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=50957:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=windows-1251 -classpath "C:\Program Files\Java\jdk1.8.0_271\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\rt.jar;C:\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson2.Lesson2Homework
Заполнение массива случайными целыми числами
sortBubble: длина массива 10000 элементов, время сортировки 187 мс
sortSelect: длина массива 10000 элементов, время сортировки 109 мс
sortInsert: длина массива 10000 элементов, время сортировки 16 мс

Process finished with exit code 0

"C:\Program Files\Java\jdk1.8.0_271\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=50965:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=windows-1251 -classpath "C:\Program Files\Java\jdk1.8.0_271\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_271\jre\lib\rt.jar;C:\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson2.Lesson2Homework
Заполнение массива случайными целыми числами
sortBubble: длина массива 100000 элементов, время сортировки 19407 мс
sortSelect: длина массива 100000 элементов, время сортировки 4938 мс
sortInsert: длина массива 100000 элементов, время сортировки 984 мс

Process finished with exit code 0

-------------------- JDK-15 --------------------
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=50979:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=windows-1251 -classpath "C:\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson2.Lesson2Homework
Заполнение массива случайными целыми числами
sortBubble: длина массива 10000 элементов, время сортировки 219 мс
sortSelect: длина массива 10000 элементов, время сортировки 110 мс
sortInsert: длина массива 10000 элементов, время сортировки 46 мс

Process finished with exit code 0

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=50974:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=windows-1251 -classpath "C:\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson2.Lesson2Homework
Заполнение массива случайными целыми числами
sortBubble: длина массива 100000 элементов, время сортировки 18958 мс
sortSelect: длина массива 100000 элементов, время сортировки 4875 мс
sortInsert: длина массива 100000 элементов, время сортировки 1093 мс

Process finished with exit code 0

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=50986:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=windows-1251 -classpath "C:\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson2.Lesson2Homework
Заполнение массива случайными целыми числами
sortBubble: длина массива 200000 элементов, время сортировки 76615 мс
sortSelect: длина массива 200000 элементов, время сортировки 19806 мс
sortInsert: длина массива 200000 элементов, время сортировки 4531 мс

Process finished with exit code 0

"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=50805:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=windows-1251 -classpath "C:\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson2.Lesson2Homework
Заполнение массива случайными целыми числами
sortBubble: длина массива 1000000 элементов, время сортировки 1896870 мс
sortSelect: длина массива 1000000 элементов, время сортировки 490771 мс
sortInsert: длина массива 1000000 элементов, время сортировки 117068 мс

Process finished with exit code 0
*/