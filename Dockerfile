# Use a imagem oficial do Maven para compilar a aplicação
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e o código-fonte para o contêiner
COPY pom.xml .
COPY src ./src

# Compile a aplicação
RUN mvn clean package -DskipTests

# Use uma imagem oficial do JDK para rodar a aplicação
FROM eclipse-temurin:17-jre-alpine

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR compilado do estágio anterior para o diretório de trabalho
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta em que a aplicação vai rodar
EXPOSE 8080

# Defina o comando padrão para rodar a aplicação
CMD ["java", "-jar", "app.jar"]