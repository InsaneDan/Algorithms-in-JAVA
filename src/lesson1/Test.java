package lesson1;

public class Test {

    public static void main(String[] args) {
        int a = 5;// 001[5]
        System.out.println("before Main a = " + a);
        testPrimitive(a); // <--[5]
        System.out.println("after Main a = " + a); // 001 [5]

        System.out.println("-----");
        Person p = new Person("TestName", 25); // 001[077]
        System.out.println("before Main p = " + p);
        testRef(p); // <-- 077
        System.out.println("after Main p = " + p); // 001[077]


        Person p1 = new Person("TestName", 25); //001 [101]
        Person p2 = new Person("TestName", 25); //002 [202]
        System.out.println(p1 == p2); // 101 == 202
        System.out.println(p1.equals(p2));
    }

    private static void testPrimitive(int a) { // 002 [5]
        System.out.println("before testPrimitive a = " + a);
        a = 7; // 002 [7]
        System.out.println("after testPrimitive a = " + a);
    }


    private static void testRef(Person p) { // 003 [077]
        System.out.println("before testRef p = " + p);
        p.setAge(33);
//        p = new Person("Another one", 33); // 003 [088]
        System.out.println("after testRef p = " + p);
    }


}
