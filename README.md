ToDo List App — Pairs Project

Spring Boot web application developed as part of the UMH Software Engineering course.

Milestone
Version: 1.2.0
Developers
James
Misha
Project Description

This project is a collaborative ToDo List Web Application built using modern Java backend technologies.

The application allows users to:

create and manage tasks
manage teams
associate users with teams
persist data in relational databases
execute repository and service tests
work with layered Spring Boot architecture
Technologies Used
Technology	Purpose
Java	Backend programming language
Spring Boot	Main application framework
Spring Data JPA	Persistence layer
Hibernate	ORM
Maven	Dependency management
H2 Database	In-memory database
Thymeleaf	Server-side templates
JUnit 5	Testing
ModelMapper	DTO mapping
Project Architecture

The application follows a layered architecture:

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
User creation
User persistence
User retrieval
User-task relationships
Tasks
Task creation
Task persistence
Task assignment
Task modification
Task deletion
Teams
Team entity
Team persistence
Ordered team list
Team retrieval
User-team relationships
Many-to-many association
Service Layer
DTO architecture
Team service
User-team business logic
Ordered queries
Exception handling
Testing

Implemented:

Entity tests
Repository tests
Service tests
Integration tests

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
Application

http://localhost:8080

H2 Database Console

http://localhost:8080/h2-console

H2 Database Credentials
Parameter	Value
JDBC URL	jdbc:h2:mem:testdb
Username	sa
Password	(empty)
Running Tests

Run all tests:

mvn clean test
Git Workflow

The project uses:

feature branches
pull requests
issue tracking
Trello workflow
milestone versioning
Trello Board

https://trello.com/b/mQe2rhr3/p3-to-do-list-app-pairs

GitHub Repository

https://github.com/KrasMish/Ex3_umh_pairs

Current Status
Completed Milestone
1.2.0

Implemented:

entity layer
repository layer
service layer
DTO mapping
relational persistence
business logic
user/team relationships
repository/service tests
Academic Context

Project developed for:

UMH — Software Engineering

Authors

James & Misha
