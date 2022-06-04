import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

class mylist extends ArrayList implements ListIterator {
    int cursor = 0;
    int lastRet = -1;

    public mylist(int initialCapacity) {
        super(initialCapacity);
    }


    public mylist() {
        this(10);
    }

    @Override
    public String toString() {
        String tmp = "";
        ListIterator it = listIterator();

        for (int i=0; it.hasNext(); i++){
            if(i!=0) tmp += ", ";
            tmp += it.next();
        }
    }

    public ListIterator listIterator(){
        cursor = 0;
        lastRet = -1;
        return this;
    }

    public boolean hasNext() {
        return cursor != size();
    }

    public Object next() {
        Object next = get(cursor);
        lastRet = cursor++;
        return next;
    }

    public void remove() {
        if (lastRet == -1){
            throw new IllegalStateException();
        }else{
            remove(lastRet);
            cursor--;
            lastRet = -1;
        }

    }
}

public class myList {

}
