# RGT 과제 제출
## 테이블 주문 플랫폼 개요

### 사용자 접속 및 인증
- 손님 사용자는 식당테이블에서 각자의 스마트폰으로 플랫폼에 접속
- 플랫폼에 로그인 후 매장과 식당테이블 번호 선택으로 주문 진행 가능
- 로그인은 필수 (비로그인 상태에서는 진행 불가)

### 메뉴 확인
- 손님 사용자는 모바일 브라우저에서 매장의 메뉴 리스트 확인 가능
- 메뉴 리스트: 메뉴명, 가격 표시
- 메뉴 상세 정보 확인 가능: 메뉴명, 가격, 설명 포함

### 장바구니 기능
- 메뉴 상세 정보에서 장바구니에 담기 가능
- 장바구니에 담을 때 개수 설정 가능
- 장바구니 화면에서 담은 메뉴 확인 가능
- 장바구니 내 메뉴 개수 수정 가능
- 장바구니 내 메뉴 삭제 가능

### 주문 처리
- 장바구니 내 메뉴 주문 가능
- 주문은 식당테이블 단위로 처리
- 각 사용자의 주문은 테이블별로 합산
- 상품 결제 과정은 생략

### 주문 확인
- 손님 사용자: 주문 내역 확인 가능
  - 주문 내역은 테이블 단위로 합산하여 표시
  - 테이블의 최신 confirm 상태 주문만 표시
- 사장님 사용자: 테이블별 주문 내역 확인 가능

### 주문 상태 관리
- 진행 중인 주문은 테이블당 한 건만 존재 가능
- 주문 상태:
  1. confirm (수락됨)
  2. cancel (취소)
  3. complete (완료)
- 새로 생성된 주문의 초기 상태는 confirm
- 주문 상태를 완료 또는 취소로 변경 가능
</br>

## 전체 흐름 구상
![image](https://github.com/user-attachments/assets/44b7c16f-4b06-4e5a-b54c-a352ba873808)


## API 명세서
http://43.201.0.0:8080/swagger-ui/index.html#/
![image](https://github.com/user-attachments/assets/8c0e038d-1249-4051-b984-5ae005a6f2a9)
![image](https://github.com/user-attachments/assets/7e7842a7-cf52-41bf-858b-b685625332f2)
</br>

## 서버 주소
http://43.201.0.0:8080/greeting
</br>

## erd
https://www.erdcloud.com/d/wLjCv9Lpy8J7TRJTv
</br>

![image](https://github.com/user-attachments/assets/43bb4284-e4c7-48d6-82ec-d403f0171215)
- mysql
- redis - cart
    - cafeId : 1
    - tableNumber : 1-4
    - 1번 테이블  -  menuId : 1 , quantity : 2

![image](https://github.com/user-attachments/assets/82f87fa2-cdc0-4085-8412-edd6def0ccf0)
</br>

## 전체 구현 과정
[https://tundra-pedestrian-277.notion.site/RGT-9e8e9cd3a72b4c60b4e7db8e835a54d7?pvs=4](https://www.notion.so/RGT-9e8e9cd3a72b4c60b4e7db8e835a54d7?pvs=21)
</br>

## 파일 구조도
```
│  RgtApplication.java
│
├─config
│  │  EncoderConfig.java
│  │  JwtConfig.java
│  │  RedisConfig.java
│  │  SecurityConfig.java
│  │  SwaggerConfig.java
│  │
│  └─filter
│          JwtAuthorizationFilter.java
│
├─constants
│      Authority.java
│      ExceptionMessage.java
│      OrderState.java
│
├─contorller
│      CafeController.java
│      CartController.java
│      MenuController.java
│      OrderController.java
│      TestController.java
│      UserController.java
│
├─dto
│  ├─request
│  │      CafeTableReqDto.java
│  │      MenuReqDtoForCart.java
│  │      OrderReqDto.java
│  │      UserReqDto.java
│  │
│  └─response
│          CartRespDto.java
│          MenuRespDto.java
│          OrderRespDto.java
│
├─entity
│      Cafe.java
│      Menu.java
│      User.java
│      UserOrder.java
│      UserOrderDetail.java
│
├─exception
│      AdditionalQuantityRequiredException.java
│      GlobalExceptionHandler.java
│      InvalidOrderStatusModificationException.java
│      InvalidTableNumberException.java
│      OrderAlreadyConfirmedException.java
│
├─repository
│      CafeRepository.java
│      MenuRepository.java
│      OrderDetailRepository.java
│      OrderRepository.java
│      UserRepository.java
│
└─service
        CafeService.java
        CartService.java
        MenuService.java
        OrderService.java
        UserService.java
```

