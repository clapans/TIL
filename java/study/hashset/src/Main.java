import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Object[] arr = {"1", 1, "2", "2", "3"};
        Set<Object> set = new HashSet<>();
        for (int i = 0; i < 5; i++) set.add(arr[i]);
        for (Object i : set) System.out.println(i);
    }
}
