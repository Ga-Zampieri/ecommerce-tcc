# Use a imagem oficial do Maven para compilar a aplicação
FROM maven:3.8.5-openjdk-17 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e o código-fonte para o contêiner
COPY pom.xml .
COPY src ./src

# Compile a aplicação
RUN mvn clean package -DskipTests

# Use uma imagem oficial do JDK para rodar a aplicação
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR compilado do estágio anterior para o diretório de trabalho
COPY --from=build /app/target/*.jar ecommerce-0.0.1-SNAPSHOT.jar

# Exponha a porta em que a aplicação vai rodar
EXPOSE 8080

# Defina o comando padrão para rodar a aplicação
CMD ["java", "-jar", "ecommerce-0.0.1-SNAPSHOT.jar"]