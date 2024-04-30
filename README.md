# How run project

## Run project

> [!WARNING]
> Requires to start application:
> 1. mvn 3.6
> 2. java 17

### Build application
```sh
mvn package
```

### Run app
```sh
java -jar target/psychotherapistOffice-0.0.1-SNAPSHOT.jar
```

### Open app in you browser
[Click here](http://localhost:8080/).

## Run project by docker

### How to run the app in development

> [!WARNING]
> Requires to start application:
> 1. docker-compose

### Build app

```sh
docker-compose -f docker/docker-compose-app-builder.yml up
```

### Create .env file
```sh
cp docker/.env.dist docker/.env
```

### Run container with web application

```sh
docker-compose -f docker/docker-compose.yml up -d
```

### Open app in you browser
[Click here](http://localhost:8080/).

### Authentication data:
###### login: admin@example.com
###### password: adminpass

## Description
Customer service for the psychotherapy office

The application has been developed with the clients of a psychotherapy office in order to manage appointments more efficiently.
The client can make contact with the therapist,
check the offer, choose a free and convenient appointment.
The API administrator is given a tool for easier access to the patient,
managing appointments becomes much easier.
Key functionalities:
- adds, searches for patients from the database;
- edits appointments;
- adds, edits therapy types (offer);
- adds, edits work calendar;


In the project I use:
java 17;
spring boot 3.2.0;
View layer using thymeleaf, CSS, java script;
Developer and production profiles using liquibase;
Development database H2, production database postgreSQL;
Logging, password changing is done using libraries:
org.springframework.security.config.annotation.web.builders.HttpSecurity;
org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
org.springframework.security.web.SecurityFilterChain;
Contact mailbox using libraries:
jakarta.mail;
jakarta.mail.internet.InternetAddress;
jakarta.mail.internet.MimeMessage;

I have some more ideas and suggestions, the application will be extended.

DB Schema
![psychotherapistOffice_db_schema.png](https://github.com/GoralTomaszGorski/psychotherapist_office/blob/6ca77b18357403c09c19b23b21effa0e37df7d4c/src/main/resources/static/images/psychotherapistOffice_db_schema.png)