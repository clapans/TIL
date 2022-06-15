import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Person{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public boolean equals(Object object){
        if (object instanceof Person){
            Person tmp = (Person) object;
            return name.equals(tmp.name) && age == tmp.age;
        }
        return false;
    }

    public int hashCode(){
        return Objects.hash(name, age);
    }
}

public class HashCode {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        set.add(new Person("박수근",28));
        set.add(new Person("박수근",28));
        for (Person person : set) System.out.println(person.toString());
    }
}
