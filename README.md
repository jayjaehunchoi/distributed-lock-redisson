# redisson을 이용하여 분산락 구현

1. 내부적으로 Lua 스크립트를 쓰기 때문에 원자성 보장.
2. 타임아웃이 제공되어 안정적으로 싱글 인스턴스 분산락 구현 가능 (데드락 방지)
3. pub - sub 형태로 락을 잡을 수 있는 시기를 알려줌. (스핀락이 주는 부하 문제 해결)

## 실행 결과
> 쿠폰의 제한 개수는 2개이다.

* 락을 주지 않은 경우

<img width="195" alt="image" src="https://user-images.githubusercontent.com/87312401/196144678-36d85558-a05b-4deb-bb32-d5ec1083706d.png">

* 락을 잡은 경우
  
<img width="316" alt="image" src="https://user-images.githubusercontent.com/87312401/196144884-941b8c34-0fa8-48cf-aef2-2bc8b0f4cb6e.png">

애플리케이션 단에서 Redis를 이용하여 락을 잡을 수 있다. 이슈는 어떤 것을 키로 잡을 것인가? 서버에 부하가 많아지면 어떻게 할 것인가? 클러스터링을 한다면 기준을 무엇으로 할 것인가?
