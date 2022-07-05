### Optional\<T>와 OptionalInt



#### Option\<T> 란?



'T타입의 객체'를 감싸는 래퍼 클래스이다. Optional 타입의 객체는 모든 타입의 참조변수를 담을 수 있다.



#### of 와 ofNullable



둘다 Optional의 static 메서드, 이 두 메서드를 이용해서 객체를 Optional로 감싼다.

null인지 여부가 불확실하면 ofNullable로 생성



null값으로 초기값을 지정하고 싶은 경우에는 static 메서드 empty()를 사용



```java
Optional<String> optVal = Optional.of("박수근");
Optional<String> optVal = Optional.ofNuallable(null);

Optional<String> optVal = Optional.empty();
```



#### Optional 객체 값 받기



get() : null 값의 경우 예외가 발생.

orElse(): null값의 경우 대체할 값을 지정



null인 경우 람다식으로 값을 지정하거나 예외를 발생시킬 수 있는 메서드

- orElseGet() : 값 지정
- orElseThrow() : 예외 발생



Optional도 stream 처럼 map(),filter(),flatMap() 사용 가능. 원소가 한개짜리 stream이라 생각하면 된다.



#### isPresent, ifPresent



isPresent : 객체의 값이 null이면 false를 아니면 true를 반환

ifPresent: 람다식을 매개변수로 받아서 값이 있으면 람다식을 수행



#### Optional을 반환하는 최종연산 메서드



- findAny, findFirst, max, min, reduce



여기서 reduce는 함수형 인터페이스 binaryOperator를 매개변수로 받음으로 매개변수 두개에 반환 값은 하나를 가진다. 이의 특성을 이용해서 max,min 메서드가 작성되었다.



#### OptionalInt, OptionalDouble....



기본형을 값으로 하는 Optional###



### 스트림의 최종 연산



최종 연산에 해당하는 메서드는 stream을 반환하지 않는다. (== stream을 소모한다.)



#### forEach



반환타입 void, consumer 함수형 인터페이스를 매개변수로 받는다. 주로 출력에 사용된다.



#### 조건검사



- allMatch(), anyMatch(), noneMatch(), findFirst(), findAny()



반환타입이 boolean, predicate 함수형 인터페이스를 매개변수로 받는다.



#### 통계



- count(), sum(), average(), max(), min()



기본형 스트림이 아닌 경우 count, max, min만 사용 가능



#### reduce



스트림을 순회하면서 매개변수 두개 반환 한개로 계속해서 줄여나가면서 결국 마지막 반환은 Optional



초기값이 존재하는 매개변수가 두 개 짜리 reduce 메서드도 있는 데 이 메서드는 반환 타입이 Optional이 아닌 T 타입

count,sum이 이 방식으로 작성되었다.



```java
int count = intStream.reduce(0, (a,b) -> a + 1);
int sum = intStream.reduce(0, (a,b) -> a + b);
```

