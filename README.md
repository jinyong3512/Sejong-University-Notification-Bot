<p align='center' style='font-size:150%'>Sejong-University-Notification-Bot는 자동화 크롬을 통해 고전독서인증 예약을 계속 합니다.</p>

# :star: 특징

## Main.java
>1. [완료] 고전독서인증 예약 계속 시도하기
>2. [예정] 카카오톡을 통해 예약 성공 알리미

### 과정
>1. ID PW 날짜 입력받기
>2. ChromeDriver 설정
>3. URL 접속 후 ID PW 입력 후 로그인
>4. 예약 페이지로 이동하기
>5. 달력 클릭 후 일치하는 날짜 선택
>6. 빈자리 생길 때까지 계속 해당 날짜 검색 
>7. 빈자리 생긴다면 신청 누르기
>8. 과학도서 중 위의 책부터 클릭 가능한 책 생길 시 선택 후 신청 완료
>9. beep 음 10초간 1초마다 재생 후 프로그램 종료


# :white_check_mark: 실행방법

## 1.  Git Bash를 이용하여 clone
```    
$ (/c 에서) git clone https://github.com/jinyong3512/Sejong-University-Notification-Bot.git
```    

## 2.  chromedriver 다운 받기

>본인 컴퓨터의 크롬 버전을 확인한 후 크롬 버전에 맞는 chromedriver.exe를 다운로드합니다.

>크롬 버전 확인 방법: https://blog.naver.com/kiddwannabe/221539689821

>chromedriver.exe 다운: https://chromedriver.chromium.org/downloads

>C드라이브에 있는 Sejong-University-Notification-Bot 안에 chromedriver.exe를 자신이 다운받은 chromedriver.exe로 덮어주세요.


## 3.  실행 도구

### Java
>IDE로 프로젝트를 열고 **Run Main.java**

>(꼭 프로젝트로 Sejong-University-Notification-Bot 열어야함 !)

>(IDE Intellij 추천!)


# :page_with_curl: 라이센스
>APACHE LICENSE, VERSION 2.0
