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
Psychotherapy office client services

The application has been prepared for clients of a psychotherapy office 
to manage appointments more efficiently. 
The client can check the working time of the office, 
choose a free and convenient date for an appointment.
API administrator gets a tool for easier access to the patient, 
appointment management becomes much easier.

Actually I prepare functions:
1. Login and authorization of client
2. changing therm hours - for admin
3. approving, deleting meetings - for admin
4. Contacting box
5. Tab about me

DB Schema
![Psychotherapy_office_DB_Schema .png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2FPsychotherapy_office_DB_Schema%20.png)