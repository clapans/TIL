package iter;

import java.util.Iterator;

public class myList {
    public static void main(String[] args) {
        customList lst = new customList();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        Iterator it = lst.iterator();
        //System.out.println(lst);

        it.next();

        it.remove();
        it.next();
        it.remove();
        System.out.println(lst);
    }
}
