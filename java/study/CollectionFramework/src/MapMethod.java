import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMethod {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("a","java");
        map.put("b","python");
        map.put("c","c");

        System.out.println(map.entrySet().toString());
        System.out.println(map.keySet().toString());
        System.out.println(map.values().toString());
        System.out.println(map.containsKey("b"));

        for (Map.Entry<String, String> entry : map.entrySet())
            System.out.println(entry.getValue());
    }
}

