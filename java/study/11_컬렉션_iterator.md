## iterator



컬렉션에 저장된 요소를 접근하는 데 사용되는 인터페이스로 아래 세 가지가 존재



1. Iterator,
2. ListIterator,
3. Enumeration



Iterator는 Enumeration의 신 버젼 그리고 ListIterator는 Iterator의 기능을 향상시킨 것



```java
public interface Collection {
    ...
    public Iterator iterator();
    ...
       
}
```



Collection 인터페이스에는 iterator() 메서드가 존재하고

Collection에 존재하는 구현 클래스에는 iterator() 메서드가 구현 돼있다.



| 메서드            | 설명                                                         |
| ----------------- | ------------------------------------------------------------ |
| boolean hasNext() | 읽어 올 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환한다. |
| Object next()     | 다음 요소를 읽어 온다. next()를 호출하기 전에 hasNext()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전하다. |
| void remove()     | next()로 읽어 온 요소를 삭제한다. next()를 호출한 다음에 remove()를 호출해야 한다. |



참조변수의 타입을 Collection로 하면 인스턴스 부분을 바꾼다 했을 때 전체 메서드의 동작이 보증이 된다.(대신 Collection의 메서드들만 사용한다...)



```java
StringBuffer sb = new StringBuffer();
sb.append("a");
sb.append("b");
sb.append("c");

---------------
    
sb.append("a").append("b").append("c");
```



### ListIterator와 Enumeration



Iterator는 단방향으로만 이동이 가능했는데 ListIterator는 양방향으로 이동이 가능하다.



여러 ListIterator의 메서드들 중 선택적 기능으로 되어있는 것은 반드시 구현하지 않아도 된다.(ex. remove())



그렇다 하다라도 메서드는 추상메서드라 예외처리를 메서드 구현부에 넣어준다.



```java
public void remove() {
    throw new UnsupportedOperationException();
}
```



