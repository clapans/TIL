import java.util.*;

public class iterator {
    public static void main(String[] args) {
        //Collection c = Arrays.asList(1,2,3,4,5);

        /*Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1,"사과");
        map.put(2,"바나나");
        map.put(3,"포도");*/

        Iterator it = c.iterator();
        //ListIterator it = c.listIterator();
        //Iterator it = map.entrySet().iterator();
        //it.next();
        //it.remove();
        while (it.hasNext()) System.out.println(it.next());
        //while (it.hasPrevious()) System.out.println(it.previous());

    }
}
