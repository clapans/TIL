### partitioningBy(), groupingBy()



partitionBy()는 첫 번째 인자를 predicate,

groupingBy()는 function

함수형 인터페이스를 구현한 람다함수를 매개변수로 받는다.



#### partitioningBy



 stream에 대하여 조건문을 통해 필터링을 진행한다. 

두 번째 매개변수로 람다함수를 받을 수 있다.(두 번째 매개변수가 없다면 반환 값은 List)



두 번째 매개변수에 들어갈 수 있는 함수로는 

1. counting() : true, false에 대한 갯수를 센다.
2. maxBy() : comparator를 매개변수로 받아 최대에 해당하는 객체를 Optional로 반환
3. collectingAndThen() : 최종 반환 결과의 타입을 바꾼다?



#### groupingBy



stream에 대하여 람다함수에 대한 기준으로 그룹화를 한다.

두 번째 매개변수로 람다함수를 받을 수 있다.(두 번째 매개변수가 없다면 반환 값은 List)



1. toCollection : 매개변수로 new 메서드를 받아 collection의 한 타입으로 반환
2. groupingBy : groupingBy 안에 groupingBy를 사용할 수 있다.