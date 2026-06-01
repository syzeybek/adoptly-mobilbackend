# 1. AŞAMA: Uygulamayı paketle (Mutfak Hazırlığı)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Testleri atlayarak projeyi derliyoruz
RUN mvn clean package -DskipTests

# 2. AŞAMA: Uygulamayı çalıştır (Sunuma Hazır)
FROM openjdk:17-jdk-slim
WORKDIR /app
# İlk aşamada oluşan .jar dosyasını bu aşamaya kopyalıyoruz
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]