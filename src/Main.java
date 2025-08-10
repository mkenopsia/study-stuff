import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("hi");
        List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3));
        arr.set(0, 100);

        System.out.println(Arrays.stream(new int[]{1,2,3}).count());

        System.out.println(arr);
    }
}