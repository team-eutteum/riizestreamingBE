FROM openjdk:17

#컨테이너 내 작업 디렉토리 /app으로 지정
WORKDIR /app

#jar 파일을 app.jar로 /app 디렉토리에 복사
COPY build/libs/*.jar app.jar

EXPOSE 8080
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "app.jar"]