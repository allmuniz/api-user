# Usando a imagem base com Java 21
FROM openjdk:21-jdk-slim as build

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando os arquivos necessários para o container
COPY . /app/

# Garantindo que o gradlew tenha permissões de execução
RUN chmod +x gradlew

# Rodando o Gradle para compilar o projeto e gerar o JAR
RUN ./gradlew build -x test

# Copiando o arquivo JAR para o container
COPY build/libs/api-user-*.jar /app/api-user.jar

# Expondo a porta que o Spring Boot irá utilizar (padrão 8080)
EXPOSE 8080

# Comando para rodar o aplicativo no container
ENTRYPOINT ["java", "-jar", "/app/api-user.jar"]
