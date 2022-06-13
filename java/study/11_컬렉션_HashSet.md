## HashSet



- Set 인터페이스 구현한 대표적 클래스

- 중복된 요소를 저장하지 않는 것이 특징

- add나 addAll 메서드를 저장할 때 주로 쓰는 데 중복된 요소가 있으면 false를 반환

- 저장 순서를 보장해주지 않는다.(보장 받고 싶으면 LinkedHashSet)
- 타입이 다를 경우 값이 같더라도 다른 객체로 판단



```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Object[] arr = {"1", 1, "2", "2", "3"};
        Set<Object> set = new HashSet<>();
        for (int i = 0; i < 5; i++) set.add(arr[i]);
        for (Object i : set) System.out.println(i);
    }
}
```



객체의 타입이 같고 멤버변수 값이 같더라도 Hash 값이 다르기 때문에 같은 요소라는 것을 판별하기 위해서 equals와 hashCode를 Override 해야 한다.



hashCode() 메서드가 만족해야 하는 세 가지 조건



1.  실행 중인 어플리케이션 내의 동일한 객체에 대해 여러 번 메서드를 호출해도 동일한 int값을 반환해야한다.
2. equals메서드를 이용한 비교 반환이 true라면 두 객체에 대해 각각 hashCode 값이 같아야 한다.
3. 두 객체에 대해 각각 hashCode 값이 같다고 하더라도 equals 메서드가 항상 true인 것은 아니다.



hashCode의 중복을 허용을 하지만 중복되는 경우가 많아질 경우 검색속도가 느려진다.

-