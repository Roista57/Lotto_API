# Lotto_API

Spring Boot와 Vue3를 이용해서 간단한 로또 번호 추첨 웹페이지 제작

# Spring Boot

### Reference Documentation

For further reference, please consider the following sections:

-   [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
-   [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.6/maven-plugin/reference/html/)
-   [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.6/maven-plugin/reference/html/#build-image)
-   [Spring Batch](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#howto.batch)
-   [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#using.devtools)
-   [JDBC API](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#data.sql)
-   [Java Mail Sender](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#io.email)
-   [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
-   [OAuth2 Authorization Server](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#web.security.oauth2.authorization-server)
-   [OAuth2 Resource Server](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#web.security.oauth2.server)
-   [Spring Security](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#web.security)
-   [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#web)
-   [WebSocket](https://docs.spring.io/spring-boot/docs/3.2.6/reference/htmlsingle/index.html#messaging.websockets)

### Guides

The following guides illustrate how to use some features concretely:

-   [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
-   [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
-   [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
-   [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
-   [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
-   [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
-   [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
-   [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
-   [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
-   [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
-   [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
-   [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)

## 예정

-   Spring Boot Version 3.2.6 (MyBatis Framework를 사용하기 위해서)
-   Spring Boot DevTools
-   Spring Batch - 매주 복권 당첨 번호를 자동으로 가져와 갱신하기 위해 사용 예정
-   JDBC API - 데이터베이스 인터페이스 (데이터베이스를 변경하더라도 호환 문제가 발생하지 않기 위해 사용)
-   MyBatis Framework ( 데이터베이스의 쿼리문을 Mybatis를 이용하여 작성)
-   MySQL Driver ( 사용할 MySQL 드라이버 )

## 기능 테스트 용

-   Java Mail Sender ( 간단한 이메일을 전송할 수 있는 기능을 담고 있음 )
-   Spring Security ( 보안과 관련된 역할, JWT )
-   OAuth2 Authorization Server
-   OAuth2 Resource Server
-   WebSocket ( 동시성을 보여주기 위해 사용 예정 )
-   Spring Web ( Restfull API )

# Vue

기본적인 Sing Page Application 을 제작하는 용도

### Reference Documentation

-   BootStrap5
-   axios
-   Router
-   pinia
-   [vue3-cookies](https://www.npmjs.com/package/vue3-cookies)

# 로또 번호 추첨 기능

### 1. 자바의 Collections.shuffle을 사용한 랜덤 (45개의 숫자를 뽑을 확률이 모두 동일)

### 2. LSTM으로 구성된 랜덤

-   "딥러닝이 예측한 로또 번호는 당첨이 잘될까?" 라는 영상과 첨부된 코드를 사용  
    [ 참고 영상 ]https://www.youtube.com/watch?v=3G3zExNItj0&t=622s&ab_channel=%EC%A1%B0%EC%BD%94%EB%94%A9JoCoding  
    [ 원본 블로그 ] https://tykimos.github.io/2020/01/25/keras_lstm_lotto_v895/  
    [ 참고 코드 ] https://github.com/youtube-jocoding/lotto-deeplearning
-   기존에 Tensorflow로 작성된 코드를 GPT-4를 이용하여 Pytorch로 변환
-   1회차부터 현재 회차까지의 로또 정보를 자동으로 CSV 파일로 구성해줌.( 파이썬 )
-   기존 코드의 매번 모델을 새로 만드는 방법에서 1주일에 한번 업데이트를 할 때 모델을 새로 만들어 저장하고 랜덤한 번호를 요청할 때는 모델을 불러오는 방식으로 변경

# 초기 화면

(디자인 보다는 백엔드에 중점적으로 진행할 예정)
![lotto-frist](https://github.com/Roista57/Lotto_API/assets/91594792/cc9d7fa4-d640-4a49-940a-8db1cacb1eb6)
