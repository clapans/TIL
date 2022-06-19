import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FruitComp implements Comparator<Fruit>{
    @Override
    public int compare(Fruit o1, Fruit o2) {
        return o1.weight - o2.weight;
    }
    public void testCode(){
        List<Integer> lst = new ArrayList<>();
        this.genericTest(lst);
    }
    static <T> void genericTest(List<T> list){
        System.out.println("generic");
    }
}

public class Comp {
    public static void main(String[] args) {
        ArrayList<Apple> appleArrayList = new ArrayList<>();
        ArrayList<Grape> grapeArrayList = new ArrayList<>();

        appleArrayList.add(new Apple("GreenApple",300));
        appleArrayList.add(new Apple("GreenApple",100));
        appleArrayList.add(new Apple("GreenApple",200));

        grapeArrayList.add(new Grape("GreenGrape",400));
        grapeArrayList.add(new Grape("GreenGrape",300));
        grapeArrayList.add(new Grape("GreenGrape",200));

        Collections.sort(appleArrayList,new FruitComp());
        Collections.sort(grapeArrayList,new FruitComp());

        for(Fruit f : appleArrayList)
            System.out.println(f.name + f.weight);
        for(Fruit f : grapeArrayList)
            System.out.println(f.name + f.weight);
        FruitComp com = new FruitComp();
        com.testCode();
    }
}
