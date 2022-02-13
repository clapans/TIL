## HTML



### HTML 이란

Hyper Text Markup Language

Hyper Link를 통해 텍스트를 이동한다.



### HTML 구조

- 구조
  1. html: 문서 최상위 구조
  2. head: 문서 메타테이터
  3. body: 본문

- DOM(Document Object Model) 트리로 이루어져 있다. (파이썬 들여쓰기 비슷)

- 요소들은 태그들로 둘러쌓여 있고 닫는 태그가 따로 없는 경우도 있다(hr,br)

- 태그마다 속성을 가질 수 있고 사용할 수 있는 속성은 태그 마다 다르다.
- 시맨틱 태그: 의미만 품고 있는 태그 div와 사실 똑같 ex) header, nav, aside, ....
- inline 요소 : a, b, i, br, img, span
- block 요소: p, hr, ul, ol, div
- table : 
  1. thead: tr, th 로 구성 th 글씨가 tr이랑 차이가 있음
  2. tbody: tr,td
  3. tfoot: tr,td
- form : 데이터를 서버에 제출
- input : 입력 (text,password,email,checkbox,radio..)
- label : 입력 범위를 늘려줌 유저친화적



## CSS



- 구조:
  1. 선택자
  2. 속성
  3. 값

- 선택자
  - 기본 선택자 - 클래스(.), 아이디(#), 속성, 요소 선택자
  - 결합자 - 자식 결합자(>), 자손 결합자, 일반 형제 결합자(~) : 그냥 뒤, 인접 형제(+): 바로 뒤
  - Pseudo Class

- 우선순위

  !important > 인라인 > id > class > 속성 > pseudo-class > 요소, pseudo-element

- 상속

  - 부모 속성이 자식에서도 적용된다. -text 요소는 상속, block 요소는 상속 x

- 크기

  - em,rem : em은 상속이 가능해서 같은 em이라도 들여쓰기 위치에 따라 크기가 다름
  - viewpoint : 사용자 창을 기준 vw,vh,vmin,vmax

- box model

  - content box > padding > border > margin
  - box-sizing: border-box;
  - border : size, style, color

- display

  - block, inline, inline-block, none, flex
  - visibilty: hidden : 공간은 차지하나 안 보임

- position

  - relative
  - absolute : 둥둥 떠있는 느낌, static이 아닌 부모를 기준으로 포지셔닝
  - fixed : 위치 고정 스크롤을 해도 위치 항상 똑같

