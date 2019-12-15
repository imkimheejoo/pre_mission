## 카카오페이 사전 과제
**[주택 금융 서비스 API 개발]**

## 개발프레임워크
[Back-End]
--- 
- Spring Boot
- JPA

[DataBase]
--- 
- H2

## 요구사항
- 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발
- 주택금융 공급 금융기관(은행) 목록을 출력하는 API 를 개발하세요.
- 년도별 각 금융기관의 지원금액 합계를 출력하는 API 를 개발하세요.
- 각년도별 각기관의 전체지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API개발 
    - 예를들어, 2005 년 ~2017 년 중에 2010 년 국민은행의 전체
  지원금액(1 월~12 월 지원 합계)이 가장 높았다면 { “year": “2010” , "bank": “국민은행”}을 결과로 출력합니다.
- 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API 개발
    - 예를들어, 2005 년 ~ 2016 년 외환은행의 평균 지원금액 (매년 12 달의 지원금액 평균값)을 계산하여 가장 작은 값과 큰 값을 출력합니다. 소수점 이하는 반올림해서 계산하세요.

## 문제해결전략
---
### 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발

**[요청 URI]**

- GET [http://localhost:8080/api/load](http://localhost:8080/api/load)

**[응답]**

- HTTP/1.1 200
Content-Length: 0
Date: Sun, 15 Dec 2019 14:38:43 GMT
Keep-Alive: timeout=60
Connection: keep-alive

### 주택금융 공급 금융기관(은행) 목록을 출력하는 API 를 개발하세요.

**[요청 URI]**

GET [http://localhost:8080/api/find/institutions](http://localhost:8080/api/find/institutions)

**[응답]**

- HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 14:27:54 GMT
Keep-Alive: timeout=60
Connection: keep-alive
- Body

    //[출력예제] 
    {
    "list": [
    {
    "name": "주택도시기금"
    },
    {
    "name": "국민은행"
    },
    {
    "name": "우리은행"
    },
    {
    "name": "신한은행"
    },
    {
    "name": "한국시티은행"
    },
    {
    "name": "하나은행"
    },
    {
    "name": "농협은행/수협은행"
    },
    {
    "name": "외환은행"
    },
    {
    "name": "기타은행"
    }
    ]
    }

### 각년도별 각기관의 전체지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API개발

**[요청 URI]**

- GET [http://localhost:8080/api/find/status](http://localhost:8080/api/find/status)
- HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 14:32:08 GMT
Keep-Alive: timeout=60
Connection: keep-alive
- Body
```
    {
    "name": "주택금융 공급현황",
    "amountsByYear": [
    {
    "year": "2016 년",
    "totalAmount": 400971,
    "detailAmount": {
    "하나은행": 45485,
    "농협은행/수협은행": 23913,
    "우리은행": 45461,
    "국민은행": 61380,
    "신한은행": 36767,
    "주택도시기금": 91017,
    "외환은행": 5977,
    "한국시티은행": 46,
    "기타은행": 90925
    }
    },...
```
### 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API 개발

**[요청 URI]**

- GET [http://localhost:8080/api/find/max/institution](http://localhost:8080/api/find/max/institution)

**[응답]**

- HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 14:33:41 GMT
Keep-Alive: timeout=60
Connection: keep-alive
- Body
```
    {
    "year": 2014,
    "bank": "주택도시기금"
    }
```
### 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API 개발
**[요청 URI]**

- GET [http://localhost:8080/api/find/statistic/keb](http://localhost:8080/api/find/statistic/keb)

**[응답]**

- HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 15 Dec 2019 14:35:37 GMT
Keep-Alive: timeout=60
Connection: keep-alive
- Body
```
    {
    "bank": "외환은행",
    "support_amount": [
    {
    "year": 2017,
    "amount": 0
    },
    {
    "year": 2015,
    "amount": 1702
    }
    ]
    }
```
## 빌드 및 실행방법
- Build : Gradle
