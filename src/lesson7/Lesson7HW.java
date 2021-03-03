package lesson7;

import java.util.List;

public class Lesson7HW {

    public static void main(String[] args) {

        //NOTE: Неориентированный невзвешенный граф

        // формирование графа
        String[] cities = {"Москва", "Тула", "Рязань", "Калуга", "Липецк", "Тамбов", "Орел", "Саратов", "Курск", "Воронеж"};
        Graph graph = new Graph(cities.length);
        for (String city : cities) { graph.addVertex(city); }
        graph.addEdges("Москва", "Тула", "Рязань", "Калуга");
        graph.addEdges("Липецк","Тула", "Воронеж");
        graph.addEdges("Тамбов", "Рязань", "Саратов");
        graph.addEdges("Орел", "Калуга", "Курск");
        graph.addEdges("Воронеж", "Липецк", "Саратов", "Курск");

        graph.display(); // проверка структуры

        // варианты расчетов
        trackDisplay(graph, "Москва", "Воронеж");
        trackDisplay(graph, "Курск", "Тула");
        trackDisplay(graph, "Орел", "Орел");

        // при наличии двух маршрутов с одинаковым количеством шагов будет выбран тот,
        // который идет через город, указанный в массиве городов раньше (комментарий в конце);
        // трассировку нескольких маршрутов не делал
        trackDisplay(graph, "Орел", "Тамбов");
/*
        // Exception in thread "main" java.lang.IllegalArgumentException: Invalid start label
        trackDisplay(graph, "Калининград", "Москва");
        // Exception in thread "main" java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 10
        trackDisplay(graph, "Москва", "Калининград");
*/
    }

    // вывод результата; вывод в консоль из метода visitVertex - удален
    private static void trackDisplay(Graph graph, String startPoint, String endPoint) {
        List<Vertex> track = graph.trackShortWay(startPoint, endPoint);
        System.out.printf("\nСамый короткий путь из г.%s в г.%s составляет %d условных шага(ов)\n", startPoint, endPoint, track.size() - 1);
        for (Vertex vertex : track) {
            System.out.println(vertex.getLabel());
        }
    }
}

/* РЕЗУЛЬТАТ (КОНСОЛЬ):
"C:\Program Files\Java\jdk-15\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\lib\idea_rt.jar=61195:C:\Program Files\JetBrains\IntelliJ IDEA 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Cloud@Mail.Ru\GeekBrains\Algorithms in JAVA\out\production\Algorithms in JAVA" lesson7.Lesson7HW
Vertex{label='Москва'} -> Vertex{label='Тула'} -> Vertex{label='Рязань'} -> Vertex{label='Калуга'}
Vertex{label='Тула'} -> Vertex{label='Москва'} -> Vertex{label='Липецк'}
Vertex{label='Рязань'} -> Vertex{label='Москва'} -> Vertex{label='Тамбов'}
Vertex{label='Калуга'} -> Vertex{label='Москва'} -> Vertex{label='Орел'}
Vertex{label='Липецк'} -> Vertex{label='Тула'} -> Vertex{label='Воронеж'}
Vertex{label='Тамбов'} -> Vertex{label='Рязань'} -> Vertex{label='Саратов'}
Vertex{label='Орел'} -> Vertex{label='Калуга'} -> Vertex{label='Курск'}
Vertex{label='Саратов'} -> Vertex{label='Тамбов'} -> Vertex{label='Воронеж'}
Vertex{label='Курск'} -> Vertex{label='Орел'} -> Vertex{label='Воронеж'}
Vertex{label='Воронеж'} -> Vertex{label='Липецк'} -> Vertex{label='Саратов'} -> Vertex{label='Курск'}

Самый короткий путь из г.Москва в г.Воронеж составляет 3 условных шага(ов)
Москва
Тула
Липецк
Воронеж

Самый короткий путь из г.Курск в г.Тула составляет 3 условных шага(ов)
Курск
Воронеж
Липецк
Тула

Самый короткий путь из г.Орел в г.Орел составляет 0 условных шага(ов)
Орел

Самый короткий путь из г.Орел в г.Тамбов составляет 4 условных шага(ов)
Орел
Калуга
Москва
Рязань
Тамбов

Process finished with exit code 0



P.S. Два равных по длине маршрута, выбор зависит от порядка обхода вершин (т.е. от их расположения в массиве):

{"Москва", "Тула", "Рязань", "Калуга", "Липецк", "Тамбов", "Орел", "Саратов", "Курск", "Воронеж"};
                             --------
Самый короткий путь из г.Орел в г.Тамбов составляет 4 условных шага(ов)
Орел - Калуга - Москва - Рязань - Тамбов

{"Москва", "Тула", "Рязань", "Липецк", "Тамбов", "Орел", "Саратов", "Курск", "Воронеж", "Калуга"};
                                                                                        --------
Самый короткий путь из г.Орел в г.Тамбов составляет 4 условных шага(ов)
Орел - Курск - Воронеж - Саратов - Тамбов
*/