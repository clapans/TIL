import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class ListMethod {
    public static void main(String[] args) {
        List<String> lst = Arrays.asList("java","python","c");
        System.out.println(lst.get(0));
        System.out.println(lst.indexOf("c"));
        lst.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(lst.toString());
        ListIterator<String> itr = lst.listIterator();

        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
