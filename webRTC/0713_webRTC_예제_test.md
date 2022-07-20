## WebRTC kurento 예제 테스트



#### kurento 공식 github



```
git clone https://github.com/kurento/kurento-tutorial-java.git
```



해당 소스 코드를 복제하여 가져온다.



#### Docker에서 Kurento Media Server 실행시키기



```
docker pull kurento/kurento-media-server:latest
```



도커에 kms 이미지를 가져오고



```
docker run -d --name kms --network host \kurento/kurento-media-server:latest
```



도커를 실행시킨다.



```
The command 'docker' could not be found in this WSL 2 distro.
We recommend to activate the WSL integration in Docker Desktop settings.

See https://docs.docker.com/docker-for-windows/wsl/ for details.
```



위와 같은 에러가 발생해 Ubuntu에서 wsl의 버전을 2로 cmd에서 변경해주었다.



```
> wsl --set-version Ubuntu 2
```



쿠렌토의 TCP 8888번 포트와 UDP 포트범위[5000, 5050]를 연결해주는 과정이 필요

```
docker run --rm \-p 8888:8888/tcp \-p 5000-5050:5000-5050/udp \-e KMS_MIN_PORT=5000 \-e KMS_MAX_PORT=5050 \kurento/kurento-media-server:latest
```

![img](https://blog.kakaocdn.net/dn/bVQR2M/btrbdZJWQ8o/FKNYfd89E7kjZh26jkc30K/img.png)

 

에러 해결 방법을 찾아본 결과 도커를 재시작하면 해결된다는 글이 많았으나 아직까지 해결이 되지 않는다.