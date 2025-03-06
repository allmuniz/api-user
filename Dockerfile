# Usando a imagem base com Java 21
FROM openjdk:21-jdk-slim as build

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando o arquivo JAR para o container
COPY build/libs/api-user-*.jar /app/api-user.jar

# Expondo a porta que o Spring Boot irá utilizar (padrão 8080)
EXPOSE 8080

# Comando para rodar o aplicativo no container
ENTRYPOINT ["java", "-jar", "/app/api-user.jar"]
