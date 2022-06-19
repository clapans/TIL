import java.util.ArrayList;

class Fruit {
    String name;
    int weight;
    public Fruit(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String toString() {return "Fruit";}}
class Apple extends Fruit {
    public Apple(String name, int weight) {
        super(name, weight);
    }

    public String toString() {return "Apple";}}
class Grape extends Fruit {
    public Grape(String name, int weight) {
        super(name, weight);
    }

    public String toString() {return "Grape";}}
class Juice {
    String name;

    public Juice(String name) {
        this.name = name + "Juice";
    }

    @Override
    public String toString() {
        return "Juice{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Juicer {
    static Juice makeJuice(ArrayList<? extends Fruit> box){
        String tmp = "";
        for (Fruit f : box)
            tmp += f + " ";
        return new Juice(tmp);
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Fruit> fruitArrayList = new ArrayList<>();
        ArrayList<Apple> appleArrayList = new ArrayList<>();

        fruitArrayList.add(new Grape("",1));
        fruitArrayList.add(new Apple("",1));
        appleArrayList.add(new Apple("",1));
        appleArrayList.add(new Apple("",1));

        System.out.println(Juicer.makeJuice(fruitArrayList));
        System.out.println(Juicer.makeJuice(appleArrayList));
    }
}
