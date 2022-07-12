import java.util.*;

import static java.util.stream.Collectors.*;

class Student{
    String gender;
    String name;
    int score;

    public Student(String gender, String name, int score) {
        this.gender = gender;
        this.name = name;
        this.score = score;
    }

    static boolean isMale(Student student) {
        if (student.gender.equals("male")) return true;
        return false;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return score == student.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public String getGender() {
        return gender;
    }
}

public class partitioning {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("male","박수근",90));
        studentList.add(new Student("male","안태환", 70));
        studentList.add(new Student("male","박재영", 80));
        studentList.add(new Student("male","조항주", 90));
        studentList.add(new Student("female","권다솜", 80));
        studentList.add(new Student("female","이다인", 70));

        Map<Boolean,List<Student>> stuMap1 = studentList.stream().collect(partitioningBy(Student::isMale));
        Map<Boolean,Long> stuMap2 = studentList.stream().collect(partitioningBy(Student::isMale, counting()));
        Map<Boolean, Optional<Student>> stuMap3 = studentList.stream().collect(partitioningBy(Student::isMale, maxBy(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getScore() > o2.getScore()) return 1;
                return -1;
            }
        })));
        Map<Boolean, Student> stuMap4 = studentList.stream().collect(
                partitioningBy(Student::isMale,
                collectingAndThen(maxBy(Comparator.comparingInt(Student::getScore)),Optional::get)));

        System.out.println(stuMap1);
        System.out.println(stuMap2);
        System.out.println(stuMap3);
        System.out.println(stuMap4);

        Map<Integer,List<Student>> stuMap5 = studentList.stream().collect(groupingBy(Student::getScore));
        Map<Integer,HashSet<Student>> stuMap6 = studentList.stream().collect(groupingBy(Student::getScore,toCollection(HashSet::new)));

        System.out.println(stuMap5);
        System.out.println(stuMap6);

        Map<String,List<Student>> stuMap8 = studentList.stream().collect(groupingBy(Student::getGender));
        //Map<Integer,Map<Integer,List<Student>>> stuMap7 = studentList.stream().collect(groupingBy(Student::getScore),groupingBy(Student::getGender));
    }
}
