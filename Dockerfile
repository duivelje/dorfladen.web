FROM openjdk:21
# COPY deploy/*.jar dorfladenApp.jar

# EXPOSE 8082
# ENTRYPOINT ["java", "-jar", "dorfladenApp.jar"]


WORKDIR /usr/src/myapp
RUN javac DorfladenBackend.java
CMD ["java", "Main"]