# ToDo App – Agile Development Assignment

## Developer

- Krasin Mykhailo

---

# Project Description

This project is a ToDo application developed using Spring Boot and Agile development practices.

The application includes:

- User authentication
- Task management
- User listing page
- User description page
- About page
- Docker containerization
- Unit and Web tests
- Agile workflow with GitHub and Trello

---

# Technologies Used

- Java 17
- Spring Boot
- Spring MVC
- Thymeleaf
- Maven
- H2 Database
- Bootstrap
- JUnit 5
- Mockito
- Docker

---

# GitHub Repository

- [GitHub Repository](https://github.com/KrasMish/Exercise2_umh)

---

# Trello Board

- [Trello Agile Board](https://trello.com/invite/b/69f06a9879340109d2d579be/ATTIf5eb0e9595eb83c7d6c1ae43b6b637e0F3A91E25/todo-app-agile-board)

---

# Docker Hub

- [Docker Hub Image](https://hub.docker.com/r/kramish/todo-app)

---

# Implemented Features

## Required Features

### Menu Bar

Navigation menu implemented using Thymeleaf fragments.

Includes:
- Users page
- Logout
- About page

---

### Users List Page

Endpoint:

```text
/usuarios
```

Displays:
- all registered users
- clickable user links

---

### User Description Page

Endpoint:

```text
/usuarios/{id}
```

Displays:
- user information
- user tasks

---

## Optional Features

### About Page

Endpoint:

```text
/about
```

Displays:
- development team
- application version
- release date

---

# Localhost URLs

## Application

- [http://localhost:8080](http://localhost:8080)

---

## Login

- [http://localhost:8080/login](http://localhost:8080/login)

---

## Users Page

- [http://localhost:8080/usuarios](http://localhost:8080/usuarios)

---

## User Description

- [http://localhost:8080/usuarios/1](http://localhost:8080/usuarios/1)

---

## About Page

- [http://localhost:8080/about](http://localhost:8080/about)

---

# Docker

## Pull Docker Image

```bash
docker pull kramish/todo-app
```

---

## Run Docker Container

```bash
docker run -p 8080:8080 kramish/todo-app
```

---

# Running the Project

## Run with Maven

```bash
mvn spring-boot:run
```

---

# Build Project

```bash
mvn clean package
```

---

# Run Tests

```bash
mvn test
```

---

# Tests Implemented

## Service Tests

- UsuarioServiceTest
- TareaServiceTest

---

## Web Tests

- UsuarioWebTest
- TareaWebTest

---

# Agile Workflow

The project was developed following Agile workflow practices:

- Feature branches
- Pull requests
- GitHub issues
- Labels
- Milestones
- Trello Kanban board

---

# Branches Used

- feature/menu-bar
- feature/users-list
- feature/user-description
- feature/about-page

---

# Labels Used

- enhancement
- backend
- frontend
- technical

---

# Milestones

- v1.0.0
- v1.1.0

---

# Dockerfile

```dockerfile
FROM openjdk:21-ea-1-jdk
COPY target/*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]
```

---

# Final Features Checklist

- Menu bar
- Users list
- User description page
- About page
- Docker support
- Unit testing
- Web testing
- GitHub workflow
- Trello workflow
