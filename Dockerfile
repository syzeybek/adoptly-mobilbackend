# 1. AŞAMA: Uygulamayı paketle (Mutfak Hazırlığı)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. AŞAMA: Uygulamayı çalıştır (Güncel ve Resmi Kutu)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]