# ToDo List App — Pairs Project

Spring Boot web application developed as part of the UMH Software Engineering course.

## Milestone

**Version:** 1.2.0

## Developers

- James
- Misha

## Project Description

This project is a collaborative ToDo List Web Application built using modern Java backend technologies.

The application allows users to:

- Create and manage tasks.
- Manage teams.
- Associate users with teams.
- Persist data in relational databases.
- Execute repository and service tests.
- Work with layered Spring Boot architecture.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming language |
| Spring Boot | Main application framework |
| Spring Data JPA | Persistence layer |
| Hibernate | ORM |
| Maven | Dependency management |
| H2 Database | In-memory database |
| PostgreSQL | Production/integration database |
| Docker | Containerization |
| GitHub Actions | Continuous integration |
| Thymeleaf | Server-side templates |
| JUnit 5 | Testing |
| ModelMapper | DTO mapping |

## Project Architecture

The application follows a layered architecture:

```txt
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database Layer
Project Structure
src
├── main
│   ├── java
│   │   └── todolist
│   │       ├── controller
│   │       ├── dto
│   │       ├── model
│   │       ├── repository
│   │       ├── service
│   │       └── config
│   │
│   └── resources
│
├── test
│   ├── java
│   │   └── todolist
│   │       ├── repository
│   │       ├── service
│   │       └── controller
│   │
│   └── resources
Implemented Features
Users
User creation.
User persistence.
User retrieval.
User-task relationships.
Tasks
Task creation.
Task persistence.
Task assignment.
Task modification.
Task deletion.
Teams
Team entity.
Team persistence.
Ordered team list.
Team retrieval.
User-team relationships.
Many-to-many association.
Team creation.
Join team functionality.
Leave team functionality.
Service Layer
DTO architecture.
Team service.
User-team business logic.
Ordered queries.
Exception handling.
Validation of duplicated teams.
Validation of empty team names.
Validation of duplicated team membership.
Testing

Implemented:

Entity tests.
Repository tests.
Service tests.
Controller tests.
Integration tests.

Run all tests with:

mvn clean test
Database Relationships
User ↔ Task

One User → Many Tasks

Implemented using:

@OneToMany
User ↔ Team

Many Users ↔ Many Teams

Implemented using:

@ManyToMany

Intermediate relational table:

equipo_usuario
Running the Project
Clone Repository
git clone https://github.com/KrasMish/Ex3_umh_pairs.git
Enter Project Folder
cd Ex3_umh_pairs
Run Application
mvn spring-boot:run
Local Access

Application:

http://localhost:8080

If port 8080 is busy:

mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"

Then open:

http://localhost:8081
H2 Database Console
http://localhost:8080/h2-console
H2 Database Credentials
Parameter	Value
JDBC URL	jdbc:h2:mem:testdb
Username	sa
Password	empty
PostgreSQL Execution

Start PostgreSQL with Docker:

docker run -d -p 5432:5432 --name postgres-develop -e POSTGRES_USER=atsd -e POSTGRES_PASSWORD=atsd -e POSTGRES_DB=atsd postgres:13

Run the application with the PostgreSQL profile:

mvn spring-boot:run "-Dspring-boot.run.profiles=postgres"

If port 8080 is busy:

mvn spring-boot:run "-Dspring-boot.run.profiles=postgres" "-Dspring-boot.run.arguments=--server.port=8081"
Running Tests

Run all tests:

mvn clean test

Run tests with PostgreSQL profile:

mvn test -Dspring.profiles.active=postgres
Docker

Build the project:

mvn clean package

Build the Docker image:

docker build -t YOUR_DOCKERHUB_USERNAME/atsd-todolist-james-misha:1.2.0 .

Run the Docker image on port 8081:

docker run --rm -p 8081:8080 --name todolist-app -e SPRING_PROFILES_ACTIVE=postgres -e POSTGRES_HOST=host.docker.internal YOUR_DOCKERHUB_USERNAME/atsd-todolist-james-misha:1.2.0

Push the Docker image:

docker push YOUR_DOCKERHUB_USERNAME/atsd-todolist-james-misha:1.2.0
Git Workflow

The project uses:

Feature branches.
Pull requests.
Issue tracking.
Trello workflow.
Milestone versioning.
Small commits.
Test-driven development.
GitHub Actions.
Trello Board
https://trello.com/b/mQe2rhr3/p3-to-do-list-app-pairs
GitHub Repository
https://github.com/KrasMish/Ex3_umh_pairs
Current Status

Completed Milestone:

1.2.0

Implemented:

Entity layer.
Repository layer.
Service layer.
Controller layer.
DTO mapping.
Relational persistence.
Business logic.
User/team relationships.
Team membership management.
Repository tests.
Service tests.
Controller tests.
GitHub Actions.
Docker image.

Optional user story 010 was not implemented.

Academic Context

Project developed for:

UMH — Software Engineering
Authors

James & Misha
