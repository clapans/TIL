import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Box<T>{
}

class parent{}
class child1 extends parent{}
class child2 extends parent{}

public class Main {
    public static void main(String[] args) {
        Box box = null;
        Box<Object> objectBox = null;
        Box<String> stringBox = null;
        //box = (Box)objectBox;
        //objectBox = (Box<Object>) box;

        //objectBox = (Box<Object>) stringBox;
        //stringBox = (Box<String>) objectBox;

        /*Box<? extends parent> box1 = new Box<parent>();
        Box<? extends parent> box2 = new Box<child1>();
        Box<? extends parent> box3 = new Box<child2>();
        Box<? extends parent> box4 = new Box<>();*/

        //Box<? extends Object> box5 = new Box<>();
        //stringBox = (Box<String>) box5;
    }
}
