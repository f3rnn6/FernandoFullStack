# Multi-stage build para optimizar tamaño
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

# Directorio de trabajo
WORKDIR /app

# Copiar archivos necesarios
COPY pom.xml .
COPY src ./src

# Compilar aplicación
RUN mvn clean package -DskipTests

# Imagen final más liviana
FROM eclipse-temurin:17-jre-alpine

# Instalar utilidades necesarias
RUN apk add --no-cache shadow \
 && getent group appuser || groupadd -r appuser \
 && id -u appuser &>/dev/null || useradd -r -g appuser appuser

# Directorio de trabajo
WORKDIR /app

# Copiar JAR desde la etapa de build
COPY --from=build /app/target/serv_usuario-*.jar app.jar

# Asignar permisos al usuario no-root
RUN chown -R appuser:appuser /app

# Cambiar a usuario no-root
USER appuser

# Puerto que expone la aplicación
EXPOSE 8080

# Variables de entorno para JVM
ENV JAVA_OPTS="-Xms256m -Xmx512m"



# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

