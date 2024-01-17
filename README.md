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
