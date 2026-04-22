import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Object object2 = new Object();
//        var z = object * object2;
        Integer a = 10;
        Integer b = 10;
        var x = a *b;
        System.out.println("ok");
    }

    private static <T> void unsafeAdd(List<? super T> list, T o) {
        list.add(o);
    }
}