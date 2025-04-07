FROM openjdk:17

#컨테이너 내 작업 디렉토리 /app으로 지정
WORKDIR /app

#jar 파일을 app.jar로 /app 디렉토리에 복사
COPY . .

# gradlew에 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 빌드 수행 (캐시 방지 위해 --no-daemon)
RUN ./gradlew build --no-daemon

# 빌드된 JAR 파일을 app.jar로 복사
RUN cp build/libs/*.jar app.jar

EXPOSE 8080
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "app.jar"]