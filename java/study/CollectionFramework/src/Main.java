import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("java");
        //String[] collectionToArray = collection.toArray();
        Object[] collectionToArray = collection.toArray();
        System.out.println(collectionToArray.toString());
        collection.addAll(Arrays.asList("A", "B", "C"));
        Object[] collectionToArray2 = collection.toArray(new Object[]{});
        System.out.println(collectionToArray2.toString());
        System.out.println(collection.size());
        System.out.println(collection.isEmpty());
        System.out.println(collection.contains("java"));
        System.out.println(collection.equals(Arrays.asList("java","A","B","C")));
        collection.remove("java");
        System.out.println(collection.toString());
    }
}
