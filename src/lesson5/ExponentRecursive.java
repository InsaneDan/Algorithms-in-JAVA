package lesson5;

public class ExponentRecursive {

    public static void main(String[] args) {
        System.out.println(pow(10,7));
        System.out.println(powTail(10,3));
        System.out.println(pow(-5,2));
        System.out.println(powTail(-5,3));
        System.out.println(pow(0,0));
        System.out.println(powTail(0,0));
        System.out.println(pow(0.000001,2));
        System.out.println(powTail(1.5,2));
//        System.out.println(pow(10,-3));
        System.out.println(powTail(10,-3));
    }


    public static double pow(double x, int power) {
        double res;
        if (power < 0) throw new IllegalArgumentException("Неверное значение степени");
        if (power == 0)
            return 1;
        else {
            res = pow(x * x, power/2);
            if (power % 2 == 1) res = res * x;
        }
        return res;
    }

    private static double powTail(double x, int power) {
        if (power < 0) throw new IllegalArgumentException("Неверное значение степени");
        if (power == 1) {
            return x;
        } else if (power == 0) {
            return 1;
        } else {
            return x * powTail(x, --power);
        }
    }
}
