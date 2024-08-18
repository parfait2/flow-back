# 허용된 IP 접근 제한 설정 프로젝트 - Backend







![image](https://github.com/user-attachments/assets/13748f43-52f3-4bc0-84e5-9644c9f5d775)



이 프로젝트는 Spring Boot를 사용하여 개발된 백엔드 애플리케이션입니다.

**허용된 IP에 대한 접근을 제한**하는 API와 비즈니스 로직을 담당합니다.


📚 [프로젝트 관련 문서 보러 가기](https://yerinpark-dev.notion.site/IP-1dee064d84ac499fb1d462fcd4c35fe9?pvs=4)




## 기술 스택 (Technology Stack)
- Java 17
- Spring Boot 3.3.2
- MySQL
- JPA (Hibernate)
- Lombok
- Gradle

## 프로젝트 설정 (Project Setup)

### 필수 조건 (Prerequisites)
- Java 17 이상
- Gradle 7.x 이상
- MySQL 5.7 이상

### 설정 방법 (Setup Instructions)

1. 저장소를 클론합니다.
    ```bash
    git clone https://github.com/parfait2/flow-back.git
    cd backend
    ```

2. 의존성을 설치합니다.
    ```bash
    ./gradlew clean build
    ```

3. `application.yml` 파일을 설정합니다.
    ```yaml
    server:
      port: 8080
    
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://[Your-DB-Endpoint]:3306/[DB명]?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8
        username: [USERNAME]
        password: [PASSWORD]
    
      jpa:
        properties:
          hibernate:
            jdbc:
              batch_size: 1000
        database: mysql
        database-platform: org.hibernate.dialect.MySQL8Dialect
        open-in-view: false
        show-sql: true
        hibernate:
          ddl-auto: create
          properties:
            hibernate.default_batch_fetch_size: 1000
            hibernate.format_sql: true
    ```

4. 데이터베이스를 생성합니다.
    ```sql
    CREATE DATABASE [DB명];
    ```

5. 애플리케이션을 실행합니다.
    ```bash
    ./gradlew bootRun
    ```
