package lesson3.homework;
// Решил написать с нуля, но можно было использовать класс StackImpl, созданный на уроке

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReverseWithStack {
    private int arrSize;                // длина стека
    private char[] arrStack;            // массив = стек
    private int topPointer;             // индекс верхушки стека

    public StringReverseWithStack(int size) {
        topPointer = -1;
        arrSize = size;
        arrStack = new char[arrSize];
    }

    public void push(char symbol) {arrStack[++topPointer] = symbol;}

    public char pop() {return arrStack[topPointer--];}

    public char peek() {return arrStack[topPointer];}                   // не нужен в этой задаче

    public boolean isEmpty() {return (topPointer == -1);}

    public boolean isFull() {return (topPointer == arrSize -1);}        // не нужен в этой задаче
}

class ReversingString {
    private String inputString;
    private String outputString;

    public ReversingString(String in) {
        inputString = in;
    }

    public String doReverse() {
        int stackSize = inputString.length();
        StringReverseWithStack strStack = new StringReverseWithStack(stackSize);
        // заполняем стек
        for (int j = 0; j < inputString.length(); j++) {
            char ch = inputString.charAt(j);
            strStack.push(ch);
        }

        outputString = "";
        // читаем из стека и добавляем к результирующей строке
        while (!strStack.isEmpty()) {
            char ch = strStack.pop();
            outputString = outputString.concat(String.valueOf(ch));
        }

        // возвращаемый результат
        return outputString;
    }
}

class ReverseDemo {
    public static void main(String[] args) throws IOException {
        String in;
        String out;

        System.out.print("Введите строку: ");

        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        in = bufferedReader.readLine();

        // создаем объект и используем его метод
        ReversingString theReverser = new ReversingString(in);
        out = theReverser.doReverse();

        // вывод в консоль
        System.out.println("Перевернутая строка: " + out);
    }
}