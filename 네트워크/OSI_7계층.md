## 작동 원리



1. OSI 7계층은 응용, 표현, 세션, 전송, 네트워크, 데이터링크, 물리계층으로 나뉨.
2. 전송 시 7계층에서 1계층으로 각각의 층마다 인식할 수 있어야 하는 헤더를 붙임(캡슐화)
3. 수신 시 1계층에서 7계층으로 헤더를 떼어냄(디캡슐화)
4. 출발지에서 데이터가 전송될 때 헤더가 추가되는데 2계층에서만 오류제어를 위해 꼬리부분에 추가됌

![img](OSI_7계층.assets/995EFF355B74179035.jpg)



## 물리 계층



### 물리 계층이란?

- 하드웨어로 표현됨
- 네트워크 장치의 전기적, 기계적인 속성 및 전송하는 수단
- **상위 계층인 데이터 링크 계층의 프레임을 신호로 인코딩하여 네트워크 장치로 전송**



### 장비

**허브(Hub)**  여러 대의 컴퓨터를 연결하여 네트워크로 보내거나, 하나의 네트워크로 수신된 정보를 여러 대의 컴퓨터로 송신하기 위한 장비

**리피터(Repeater)**  디지털 신호를 증폭시켜 주는 역할을 하여 신호가 약해지지 않고 컴퓨터로 수신되도록 하는 장비



## 데이터링크 계층



### 역할

1. 인접한 네트워크 노드들끼리 데이터를 전송하는 역할
2. 물리계층에서 발생하는 오류를 감지하고 수정하는 역할



헤더에는 IP가 아닌 물리적인(?)  주소인 MAC 주소가 담겨있다.

MAC Address : IP는 동적으로 할당받는 주소이며, 고유한 값이 될 수 없다. 그래서 물리적인 NIC를 식별하여 Frame을 보내야 하는데, NIC 고유 식별정보가 바로 MAC 이다. ( Media Access Control)



### 기능

1. 프레임 생성 기능 : 데이터를 프레임 단위로 만듬 앞: 헤더(출발지,목적지 주소, 데이터 내용 정의) 뒤: 트레일러(비트 에러 감지)

2. 회선 제어 기능 :  컴퓨터 신호 간의 충돌을 방지

   - ENQ/ACK 기법 : 송신측에서 Enquiry를 보내어 데이터가 필요한 지 물어보면 수신측은 Acknowledge를 보냄. ACK가 송신측에 전달되면, 데이터를 수신측에 보내고 수신측은 다시 잘 받았다는 신호를 보냄. 마지막으로 송신측이 EOT(End Of Transmission)을 보내고 송수신 종료

   ![데이터 링크 프로토콜: 회선 규칙(line discipline)-조회/확인(ENQuiry/ACKnowledgement) 응답 : 네이버  블로그](OSI_7계층.assets/캡처-16455093792862.PNG)

   - Polling 기법 : 1대 다 전송 기법, Select(송신측이 수신측 선택 전송) 모드와 Poll(송신측이 먼저 수신측한테 데이터 받을 건지 물어봄) 모드가 있다.

   < 셀렉트 모드 >

   ![image-20220222151907120](OSI_7계층.assets/image-20220222151907120.png)

   < 폴 모드 >![image-20220222151935055](OSI_7계층.assets/image-20220222151935055.png)

3. 흐름 제어 기능 : 컴퓨터 마다 성능과 네트워크 환경이 다르기 때문에 데이터를 주고 받을 때 속도 차이가 날 수 있음. 이러한 속도 차이를 해결하기 위해 흐름을 제어.

   - Stop & Wait: 데이터를 송신하고 ACK를 받을 때 까지 기다리는 방식 비효율적 ACK가 오지 않을 경우 프레임 소멸 에러 발생. 이럴 때는 유휴 시간(idle time) 경과 후 프레임을 다시 보냄. 프레임을 다시 보낼 때 수신 측은 시퀀스 넘버를 통해 중복된 프레임인지 확인.
   - Sliding Window: 여러 프레임을 한꺼번에 보낼 수 있음. ACK 응답이 필요 x 윈도우 크기는 송신측과 수신측 프레임이 저장되는 버퍼 크기

   ![Sliding Window Protocol | Set 3 (Selective Repeat) - GeeksforGeeks](OSI_7계층.assets/Sliding-Window-Protocol.jpg)

4. 오류 제어 기능 : 네트워크 상의 문제나 전기적인 문제로 프레임이 소멸되는 등의 오류가 있을 수 있음. 전송 중에 오류나 손실이 발생하면 이를 탐지하고 수정함. 프레임이 손상되어 재전송을 수행하는 과정을 ARQ(Automatic Repeat Request)

   - Stop & Wait ARQ : 프레임이 손상되어 수신측에서 NAK를 전송하거나 주어진 시간 안에 수신 측이 ACK를 안 보내면 프레임 재전송
   - Go Back n ARQ : 송신측이 여러 프레임을 한꺼번에 전송. 프레임 1~3을 한 번에 보내고 프레임 2에 문제가 있는 경우 모든 프레임을 폐기하고 NAK를 보냄. 송신측은 다시 프레임 1~3 보냄
   - Selective Repeat ARQ : 위의 Go Back n ARQ 방식의 문제점을 보완. 위의 예시에서 문제가 생긴 프레임만을 보냄




### 장비

브릿지, 스위치



## 네트워크 계층



### 기능

- 중계 노드를 통하여 전송하는 경우 어떻게 중계할 것인가를 규정
- 라우팅 기능을 맡고 있는 계층으로 목적지까지 가장 안전하고 빠르게 데이터를 보내는 기능을 가지고 있음(최적의 경로를 설정가능)
- 컴퓨터에게 데이터를 전송할 주소를 갖고 있어서 통신가능(iP 주소가 네트워크 계층 헤더에 속함)
- 네트워크 계층에서 데이터 단위는 패킷(Packet)



### 장비

라우터, L3 스위치



## 전송계층



### 기능

- 데이터링크 계층과 기능이 비슷하지만 데이터링크는 미시적(직접 컴퓨터와 연결된 컴퓨터간) 이라면 전송계층은 송수신 양 끝단에 대해 흐름 제어, 오류 점검들의 기능을 한다. 또한 헤더에 포트 번호가 담겨 수신 컴퓨터 내에 해당 데이터를 직접적으로 사용하는 어플리케이션 까지 전송을 담당한다. 전송 계층에서 데이터 단위는 세그먼트(segment)



### 연결형 통신과 비연결형 통신



전송 계층은 **신뢰성/정확성**과 **효율성**으로 구분할 수 있다.

- 신뢰성/정확성: 데이터를 목적지에 문제없이 전달하는 것
- 효율성: 데이터를 빠르고 효율적으로 전달하는 것

신뢰할 수 있고 정확한 데이터를 전달하는 통신: **연결형 통신**,
효율적으로 데이터를 전달하는 통신을 **비연결성 통신**이라고 함.

연결형 통신은 상대편과 확인해 가면서 통신하는 방식이고, 비연결형 통신은 상대편을 확인하지 않고 일방적으로 데이터를 전송하는 방식이다.
ex)

- 연결형: 1) 데이터를 보내도 되나요? 2) 네, 보내도 됩니다. 3) 보냅니다! 4) 받았습니다 5) 확인했습니다.
- 비연결형: 1) 데이터를 보냅니다!

일반적으로 신뢰성과 정확성이 보장되지 않는 통신은 사용하고 싶지 않을 것이다. 하지만 동영상을 볼 때는 비연결형 통신을 사용한다. 동영상은 신뢰할 수 있고 정확한 데이터 전송보다 빠른 전송이 필요하기 때문이다.

두 가지 통신 모두 장단점이 있다.
신뢰할 수 있고 정확한 데이터 전송이 필요한 애플리케이션에는 연결형 통신을 사용하고, 효율적인 데이터 전송이 필요한 애플리케이션에는 비연결형 통신을 사용하면 되는 것이다.

연결형 통신 프로토콜에는 TCP(Transmission Control Protocol)이 사용되고,
비연결형 통신 프로토콜에는 UDP(User Datagram Protocol)가 사용된다.



### TCP의 구조



#### TCP란?

TCP 헤더도 IP 헤더처럼 다양한 정보가 나열되어 있다.

1. 출발지 포트 번호 2. 목적지 포트 번호 3. 일련번호 4. 확인 응답 번호 5. 헤더 길이 6. 예약 영역 7. 코드 비트 8. 윈도우 크기 9. 체크섬 10. 긴급 포인터 11. 옵션

연결형 통신은 꼼꼼하게 상대방을 확인해가면서 데이터를 전송하는데, 데이터 전송 전에 해야하는 작업이 있다.
데이터를 전송하려면 먼저 연결(connection)이라는 **가상의 독점 통신로**를 확보해야 한다.

> 연결(connection, 커넥션)
>
> - TCP 통신에서 정보를 전달하기 위해 사용되는 가상의 통신로로 연결을 확립하고 데이터를 전송한다.



TCP 헤더에서 **7) 코드 비트** 부분은 TCP 헤더의 107번째 비트부터 112번째 비트까지의 6비트로 연결의 제어 정보가 기록되는 곳이다.

- 각 비트의 이름은 순서대로 URG, ACK, PSH, RST, SYN, FIN 이다
- 코드 비트는 각 비트별로 역할이 있는데, 초깃값은 **0**이고, 비트가 활성화되면 **1**이된다.
- 연결을 확립하려면 이 중 **SYN(씬)**과 **ACK(애크)**가 필요하다
- SYN은 **연결 요청**, ACK는 **확인 응답**을 뜻한다.



#### 3-way 핸드셰이크란?



##### SYN과 ACK를 통한 연결 확립

컴퓨터 1: 송신 측, 컴퓨터 2: 수신 측

1. 통신을 하려면 컴퓨터 2에게 허가를 받아야 하므로, 먼저 컴퓨터 1에서 컴퓨터 2로 연결 확립 허가를 받기 위한 요청(SYN)을 보낸다.
   - 000010 (SYN 1로 활성화)
2. 컴퓨터 2는 컴퓨터 1이 보낸 요청을 받은 후에 허가한다는 응답을 회신하기 위해 연결 확립 응답(ACK)을 보낸다. 동시에 컴퓨터 2도 컴퓨터 1에게 데이터 전송 허가를 받기 위해 연결 확립 요청(SYN)을 보낸다.
   - 010010 (ACK와 SYN 1로 활성화)
3. 컴퓨터 2의 요청을 받은 컴퓨터 1은 컴퓨터 2로 허가한다는 응답으로 연결 확립 응답(ACK)을 보낸다.
   - 010000 (ACK 1로 활성화)

이처럼 데이터를 보내기 전에 연결을 확립하기 위해 패킷 요청을 세 번 교환하는 것을 **3-way 핸드셰이크**(three-way handshake)라고 한다.

> 핸드셰이크
>
> - 사람들이 상대방을 확인하고 악수를 하는 것처럼,
>   데이터 통신에서도 확실하게 데이터가 전송되었는지 확인하면서 이루어지는 통신 수단



##### FIN과 ACK를 이용한 연결 종료

1. 컴퓨터 1에서 컴퓨터 2로 연결 종료 요청(FIN)을 보낸다.
   - 000001 (FIN 1로 활성화)
2. 컴퓨터 2에서 컴퓨터 1로 연결 종료 응답(ACK)을 보낸다.
   - 010000 (ACK 1로 활성화)
3. 컴퓨터 2에서도 컴퓨터 1로 연결 종료 요청(FIN)을 보낸다.
   - 000001 (FIN 1로 활성화)
4. 컴퓨터 1에서 컴퓨터 2로 연결 종료 응답(ACK)을 반환한다.
   - 010000 (ACK 1로 활성화)



### 일련번호와 확인 응답 번호의 구조



#### 일련번호와 확인 응답 번호란?

3-way 핸드셰이크가 끝나고 실제 데이터를 보내거나 상대방이 받을 때는 TCP 헤더의 **3) 일련번호**(sequence number)와 **4) 확인 응답 번호**(acknowledgement number)를 사용한다.

TCP는 데이터를 분할해서 보내는데, **일련번호**는 송신 측에서 수신 측에 '이 데이터가 **몇번째 데이터**인지' 알려주는 역할을 한다.
이를 통해 수신자는 원래 데이터의 **몇 번째 데이터**를 받았는지 알 수 있는 것이다.

**확인 응답 번호**는 수신 측이 **몇 번째 데이터**를 수신했는지 송신 측에 알려주는 역할을 한다.
그래서 이 번호는 다음 번호의 데이터를 요청하는 데에도 사용된다.
예를 들어, 10번 데이터를 수신하면 11번 데이터를 송신 측에 요청하는 것이다. 이를 **확인 응답**이라고 한다.



#### 일련번호와 확인 응답 번호를 통한 데이터 전송

일련번호 '3001' 번은 지금 보내는 200바이트 데이터의 첫 번째 바이트의 번호고, 확인 응답 번호는 다음에 보냈으면 하는 데이터의 첫 번째 바이트 번호가 된다.

데이터를 전송하기 전 단계에서 3-way 핸드셰이크로 연결 수립이 이루어질 때, 이번 통신에 사용하는 일련번호인 '3001'번과 확인 응답 번호인 '4001'번이 결정된다.

1. 컴퓨터 1은 컴퓨터 2로 200바이트의 데이터를 전송한다.
   - 일련번호: 3001, 확인 응답 번호: 4001
2. 컴퓨터 2는 200바이트를 수신하고, 다음에 수신하고자 한느 데이터 번호를 확인 응답 번호에 넣는다. 다음에 수신하고자 하는 데이터는 3001 + 200 = 3201이므로 3201번부터 보내 달라고 요청한다.
   - 일련번호: 4001, 확인 응답 번호: 3201
3. 컴퓨터 1은 컴퓨터 2로 3201번부터 200바이트의 데이터를 전송한다.
   - 일련번호: 3201, 확인 응답 번호: 4001
4. 컴퓨터 2는 200바이트를 수신하고 다음에 수신하고자 하는 데이터의 번호를 확인 응답 번호에 넣는다. 다음에 수신하고자 하는 데이터는 3201 + 200 = 3401이므로 3401번부터 보내 달라고 요청한다.
   - 일련번호: 4001, 확인 응답 번호: 3401

1~4의 과정을 데이터 전송이 완료될 때까지 반복한다.

데이터가 항상 올바르게 전달되는 것은 아니므로, 일련 번호와 확인 응답 번호를 사용해서 데이터가 손상되거나 유실된 경우에는 데이터를 재전송하게 되어 있다.

이를 **재전송 제어**라고 한다.
데이터를 전송하는 도중에 오류가 발생하면 일정 시간 대기한 후에 재전송한다.



### 윈도우 크기

위에서 설명한 방식은 세그먼트(데이터) 하나를 보낼 때마다 확인 응답을 한 번 반환하는 통신인데, 이와 같은 통신은 효율이 높지 않다.

하지만 매번 확인 응답을 기다리는 대신 세그먼트를 연속해서 보내고 난 다음에 확인 응답을 반환하면 효율이 높아진다.

(데이터 링크 계층에서도 설명된 내용 Sliding Window)

이렇게 하면 상대방에게 세그먼트가 점점 쌓이긴 하지만, 받은 세그먼트를 일시적으로 보관하는 장소가 있기 때문에 괜찮다.

> **버퍼**(buffer):
>
> - 받은 세그먼트를 일시적으로 보관하는 장소
> - 버퍼 덕분에 세그먼트를 연속해서 보내도 수신 측은 대응할 수 있고, 확인 응답의 효율도 높아진다.

하지만 수신 측이 세그먼트를 대량으로 전송받으면 이를 보관하지 못하고 넘쳐 버리게 되는 경우가 있는데, 이를 **오버플로**(overflow)라고 한다.

때문에 오버플로가 발생하지 않도록 **버퍼의 한계 크기**를 알고 있어야 한다.
이것이 TCP 헤더의 **8)윈도우 크기**(window size) 값에 해당한다.

윈도우 크기는 **얼마나 많은 용량의 데이터를 저장해 둘 수 있는지**를 나타낸다. 즉, 확인 응답을 일일히 하지 않고 연속해서 송수신할 수 있는 데이터 크기다.

이 윈도우 크기의 초깃값은 **3-way-핸드셰이크**를 할 때 판단한다. 연결 확립 요청을 보내고 이에 응답하는 과정에서 윈도우 크기를 함께 보내 서로의 윈도우 크기를 확인하고 있던 것이다.

이제 상대방 버퍼의 한계 값을 알았으니 세그먼트를 오버플로되지 않도록 보내면 되는 것이다. 세그먼트를 연속적으로 전송함으로써 효율적인 데이터 전송이 가능해진다.

TCP 연결 종료는 4-way-핸드셰이크로 한다.



### 포트번호의 구조



#### 포트번호란?

전송 계층에는 전송된 데이터의 목적지가 어떤 애플리케이션인지 구분하는 역할도 있다고 했다.
이를 위해 TCP 헤더의 **1) 출발지 포트 번호**(source port number)와 **2) 목적지 포트 번호**(desination port number)가 필요하다.

포트 번호는 0~65535번을 사용할 수 있다.

**0~1023**번 포트는 주요 프로토콜이 사용하도록 예약되어 있다. 이러한 포트를 **잘 알려진 포트**(well-known ports)라고 한다.
일반적으로 사용되는 서버 측 애플리케이션에서 사용되고 있다.

1024번은 예약되어 있지만 사용되지는 않는 포트고, **1025**번 이상은 **랜덤 포트**라고 해서 클라이언트 측의 송신 포트로 사용된다.

우리는 원룸 건물 전체를 컴퓨터에 비유할 수 있다.
이때 애플리케이션은 각 호실의 주민, 포트 번호를 각 방의 번호로 바꿔서 생각하면 이해하기 쉽다.

동작하는 애플리케이션은 각각 포트 번호가 있어서 다른 애플리케이션과 서로 구분된다. 데이터를 전송할 때는 상대방의 IP 주소가 필요하지만, 어떤 애플리케이션이 사용되고 있는지 구분하려면 TCP는 포트 번호가 필요하다.

그래서 포트 번호를 붙이지 않고 통신하면 컴퓨터에 데이터가 도착하더라도 애플리케이션까지는 도착할 수 없다.

웹 브라우저의 경우 접속할 때 임의의 포트가 **자동으로 할당**된다.
그래서 서버 측에서는 포트 번호를 정해 둬야 하지만 클라이언트 측은 정하지 않아도 괜찮다.



### UDP



#### UDP란?

UDP(User Datagram Protocol)는 **비연결형 통신**이라서 데이터를 전송할 때 TCP처럼 시간이 걸리는 확인 작업을 일일이 하지 않는다.

UDP의 장점은 **데이터를 효율적으로 빠르게 보내는 것**이라서 스트리밍 방식으로 전송하는 동영상 서비스와 같은 곳에 사용된다.
동영상을 TCP 데이터 통신으로 전송하면 수신을 확인하는 데 시간이 너무 오래 걸려서 동영상을 원활하게 볼 수 없다.



#### UDP 헤더란?

UDP에서는 **UDP 헤더**가 붙은 데이터를 **UDP 데이터그램**이라고 한다.
올바른 목적지의 애플리케이션으로 데이터를 전송하기 위해 필요한 정보가 기록되어 있는 UDP 헤더의 구조는 다음과 같다.

- \1) 출발지 포트 번호 2) 목적지 포트 번호 3) 길이 4) 체크섬

UDP는 효율성과 빠른 속도가 중요해서 상대방을 확인하지 않고 연속해서 데이터를 보낸다.

UDP를 사용하면 랜에 있는 컴퓨터나 네트워크 장비에 데이터를 일괄로 보낼 수 있다.
이를 **브로드캐스트**(broadcast)라고 한다.

TCP는 3-way 핸드셰이크와 같이 데이터를 전송할 때도 확인 응답을 하나씩 보내야 하기 때문에 브로드캐스트와 같이 불특정 다수에게 보내는 통신에는 적합하지 않다.

UDP에서 브로드캐스트는 목적지에 관계없이 랜에서 일괄적으로 보내지만, TCP는 목적지를 지정하지 않으면 안 되기 때문에 일괄 통신을 할 수 없다.



## 세션 계층



### 기능

- 어플리케이션들 간의 통신을 주로 관리하며 핵심 역할로 동기화를 담당.
- 어플리케이션들 사이의 접속을 설정하고 유지하며 데이터 전송 시 동기점을 제공하여 **오류 발생 시 데이터를 재전송하거나 복구** 할 수 있게 한다.



## 응용 계층



### 역할

어플리케이션은 서비스를 요청하는 측(사용자 측)에서 사용하는 어플리케이션과 서비스를 제공하는 측의 어플리케이션으로 분류된다.

일반적으로 서비스를 요청하는 측을 **클라이언트**, 서비스를 제공하는 측을 **서버**라고 한다.

웹 브라우저나 메일 프로그램은 사용자 측에서 사용하는 어플리케이션이니 클라이언트에 속한다.
반면, 서비스를 제공하는 측인 서버에는 웹 서버 프로그램과 메일 서버 프로그램 등이 있다. 이런 어플리케이션은 **응용 계층**에서 동작한다.

여기서 응용 계층은 5계층의 **세션 계층**과 6계층의 **표현 계층**을 포함한다.

> **응용 계층**(application layer, 어플리케이션 계층):
>
> OSI 모델의 최상위 계층으로, 다양하게 존재하는 응용 환경에서 공통적으로 필요한 기능을 다룬다. 시스템 간의 응용 처리는 상호 간에 통신하면서 일련의 업무를 처리할 수 있도록 필요한 서비스 기능을 제공한다. 이메일, 파일 전송, 웹 사이트 조회 등 어플리케이션에 대한 서비스를 제공하는 계층이다.

응용 계층에서는 클라이언트의 요청을 전달하기 위해 통신 대상(서버 등)이 이해할 수 있는 메시지(데이터)로 변환하고 전송 계층으로 전달하는 역할을 한다.

또한, 클라이언트 측 어플리케이션(웹 브라우저, 메일 프로그램 등)이 서버 측 어플리케이션(웹 서버 프로그램, 메일 서버 프로그램 등)과 통신하려면 응용 계층의 **프로토콜**을 사용해야 한다.

웹 사이트를 볼 때는 HTTP , 파일을 전송할 때는 FTP, 메일을 보낼 때는 SMTP, 메일을 받을 때는 POP3라는 프로토콜을 사용한다.
또한 네트워크에서 컴퓨터나 네트워크 장비에 붙여진 이름을 기반으로 IP 주소를 알아내는 것을 **이름 해석**(name resolution)이라고 하는데, 이를 위해 DNS를 사용한다.

> **HTTP** (**H**yper**T**ext **T**ransfer **P**rotocol):
> 웹 서비스에서 클라이언트(**웹 브라우저)와 웹 서버 간에 정보를 주고받기** 위해 사용되는 네트워크 프로토콜이다.

> **FTP**(**F**ile **T**ransfer **P**rotocol, 파일 전송 프로토콜):
> 서버와 클라이언트 간에 **파일을 전송**하기 위한 프로토콜이다. 일반적으로 통신 포트는 제어 용도로는 21번을 사용하고 데이터 전송 용도로는 20번 포트를 사용한다.

> **SMTP**(**S**imple **M**ail **T**ransfer **P**rotocol, 단순 메일 전송 프로토콜):
> 인터넷에서 **메일을 송신**하는 데 사용하는 프로토콜이다. 통신 포트는 일반적으로 25번을 사용한다. SMTP를 지원하는 서버를 SMTP 서버라고 한다.

> **POP3**(**P**ost **O**ffice **P**rotocol version **3**):
> 인터넷에서 **메일을 수신**하는 데 사용하는 프로토콜이다. 통신 포트는 일반적으로 110번을 사용한다. POP3를 지원하는 서버를 POP3 서버라고 한다.

> **DNS**(**D**omain **N**ame **S**ystem, 도메인 이름 시스템):
> 네트워크에서 **호스트 이름을 IP 주소로 변환**하는 데 사용하는 시스템(서비스)이다. DNS 서비스가 동작하는 컴퓨터(서버)를 DNS 서버라고 한다.

이 외에도 다른 프로토콜들이 존재한다.

응용 계층은 각각의 어플리케이션에 대응되는 데이터를 전송하는 역할을한다. 그리고 응용 계층에서 보내려는 데이터는 하위 계층인 전송 계층, 네트워크 계층, 데이터 링크 계층, 물리 계층 순서로 처리된다.



| TCP/IP 4계층                       | 역할                                             | 데이타 단위  | 전송 주소 | 예시                                                    | 장비           |
| ---------------------------------- | ------------------------------------------------ | ------------ | --------- | ------------------------------------------------------- | -------------- |
| 응용 계층(Application)             | 응용프로그램 간의 데이타 송수신                  | Data/Message | -         | 파일 전송, 이메일, FTP, HTTP, SSH, Telnet, DNS, SMTP 등 | -              |
| 전송 계층(Transport)               | 호스트 간의 자료 송수신                          | Segment      | Port      | TCP, UDP, RTP, RTCP 등                                  | 게이트웨이     |
| 인터넷 계층(Internet)              | 데이타 전송을 위한 논리적 주소 지정 및 경로 지정 | Packet       | IP        | IP, ARP, ICMP, RARP, OSPF                               | 라우터         |
| 네트워크 연결 계층(Network Access) | 실제 데이타인 프레임을 송수신                    | Frame        | MAC       | Ethernet, PPP, Token Ring 등                            | 브리지, 스위치 |
