# README TDD - Exercise 3

## Implemented user stories

### 009 Manage Team Membership

Explain:
- The user can create a new team.
- The user can join a team.
- The user can leave a team.
- A user can belong to more than one team.
- A team can contain many users.

## Trello / GitHub methodology

For this exercise we followed an agile workflow using Trello and GitHub. The objective was to organize the development of version `1.2.0` of the application in small tasks, connect each task with a GitHub issue, develop each change in a separate branch, and finally merge the work through pull requests after the tests passed successfully.

First, the work was divided into user stories. Each user story represented a functionality that the user should be able to use in the application. For example, the team functionality was divided into stories such as listing teams, managing team membership, and optionally managing teams as an administrator. These user stories were added to the Trello board so that the development progress could be followed visually.

The Trello board was organized with several columns: **To Do**, **Doing**, **In Pull Request**, and **Done**. At the beginning, each task or user story was placed in the **To Do** column. When we started working on one of them, the card was moved to **Doing**. Once the implementation was finished and a pull request was created on GitHub, the Trello card was moved to **In Pull Request**. Finally, after the pull request was reviewed, the tests passed, and the branch was merged into `main`, the card was moved to **Done**.

Each Trello card was related to a GitHub issue. The issues were used to describe the technical work needed to complete each user story. The issue title started with a number, following the methodology required in the exercise. For example, an issue could be called `008 Service and model teams listing` or `009 Manage team membership`. This made it easier to identify which user story each issue belonged to.

For every issue, a new Git branch was created. The branch name was related to the functionality being implemented. For example, for the team listing functionality, a branch such as `service-teams` or `view-controller-teams` could be created. This allowed us to keep the work separated from the stable `main` branch. The `main` branch was only updated when the functionality was finished and the pull request had passed the required tests.

The development process followed this order:

1. Create or choose a Trello card for the user story.
2. Create a GitHub issue linked to that user story.
3. Assign the issue to milestone `1.2.0`.
4. Create a new branch from `main`.
5. Implement the necessary changes in the code.
6. Commit the changes with a clear message.
7. Push the branch to GitHub.
8. Open a pull request from the branch into `main`.
9. Link the pull request to the corresponding issue.
10. Wait for GitHub Actions to run the tests.
11. If the developer tests and integration tests passed, merge the pull request.
12. Move the Trello card to **Done**.

The repository used two GitHub Actions workflows. The first one was the **Developer tests** workflow. This workflow launched the Maven tests using the normal development configuration. The second one was the **Integration tests** workflow. This workflow launched the tests using a PostgreSQL database container, so it verified that the application also worked correctly with the real database configuration.

Before merging a pull request, both workflows had to pass successfully. This ensured that the new code did not break the existing functionality. If any test failed, the pull request was not merged until the error was fixed.

The pull requests were also associated with the `1.2.0` milestone. This milestone represented the release version required for the exercise. By assigning issues and pull requests to the milestone, it was possible to see which tasks belonged to the final version `1.2.0`.

For the team functionality, the methodology was applied in different layers of the application. First, the model and repository layer was implemented using tests. This included creating the `Equipo` entity, the `EquipoRepository`, and the many-to-many relationship between `Equipo` and `Usuario`. Then, the service layer was implemented, adding the business logic needed to create teams, list teams, join a team, leave a team, and obtain the users of a team. Finally, the controller and view layer was implemented, adding the endpoints and Thymeleaf templates needed to use the functionality from the web interface.

This methodology allowed the project to be developed in a controlled way. Trello was used to organize the work visually, GitHub issues were used to describe each technical task, branches were used to isolate changes, pull requests were used to review and integrate the code, and GitHub Actions was used to automatically check that the application continued working correctly before merging into `main`.

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/equipos` | Shows the team list |
| GET | `/equipos/{id}` | Shows the users of a team |
| POST | `/equipos/nuevo` | Creates a new team |
| POST | `/equipos/{id}/join` | Adds the logged user to a team |
| POST | `/equipos/{id}/leave` | Removes the logged user from a team |

## Classes added or modified

| Class | Explanation |
|---|---|
| `Equipo` | Entity that represents a team |
| `Usuario` | Modified to include the many-to-many relationship with teams |
| `EquipoRepository` | Repository for saving and searching teams |
| `EquipoService` | Business logic for creating teams and managing membership |
| `EquipoController` | Controller for team pages and membership actions |
| `EquipoData` | DTO used to send team data to the views |

## Database tables

The application uses a relational database to store users, tasks and teams. For this exercise, the most important new tables are related to the team functionality.

### `usuarios`

The `usuarios` table stores the users of the application. Each user has an identifier and information such as their email and name. Users are necessary because the team functionality allows each logged-in user to join or leave different teams.

### `tareas`

The `tareas` table stores the tasks created by users. This table already existed in the original ToDoList application. Each task is associated with a user, so the application can show the tasks that belong to the logged-in user.

### `equipos`

The `equipos` table stores the teams created in the application. Each team has an identifier and a name. This table was added for the team management functionality. It allows the application to save the list of available teams and later show them ordered alphabetically in the teams page.

### `equipo_usuario`

The `equipo_usuario` table is an intermediate table used to represent the many-to-many relationship between users and teams. A user can belong to more than one team, and a team can contain many users. Because of that, the relationship cannot be stored only inside the `usuarios` table or only inside the `equipos` table.

This table stores pairs of identifiers: one column references the team and the other column references the user. For example, if user `1` belongs to team `3`, the table stores a row connecting that user with that team.

This structure makes it possible to:

* list all teams of a user;
* list all users of a team;
* add a user to a team;
* remove a user from a team;
* allow the same user to participate in several teams.

The database tables were generated automatically by JPA/Hibernate from the entity classes and their annotations. The `Equipo` entity created the `equipos` table, the `Usuario` entity created the `usuarios` table, and the many-to-many relationship between both entities created the `equipo_usuario` table.


## Tests explained


The project includes developer tests and integration tests. The developer tests are executed with Maven during local development, while the integration tests are executed with GitHub Actions using a PostgreSQL database container. This allows the project to check that the code works both in the normal development environment and with the real PostgreSQL database configuration.

### Equipo entity test

The first test checks the creation of the `Equipo` class. The objective is to verify that a team can be created with a name and that the name can be recovered correctly using the getter method. This is the first step of the TDD process because the test is written before implementing the minimum code needed in the `Equipo` class.

### Equipo repository test

The repository test checks that a team can be saved in the database and later recovered. In this test, a new `Equipo` object is created and saved using `EquipoRepository`. After saving it, the test checks that the team has received an identifier and that it can be found again using `findById`.

This test verifies that the `Equipo` entity is correctly connected to JPA and that the `equipos` table is generated and used correctly.

### Equality test between teams

Another test checks the `equals` and `hashCode` methods of the `Equipo` class. This is important because teams may be compared in collections or when working with relationships. The equality logic checks the team identifier when it exists. If the identifier does not exist yet, the comparison is based on the team name.

This avoids problems when comparing teams that are already stored in the database and teams that are still temporary objects.

### Many-to-many relationship test

The many-to-many relationship test checks the relationship between `Equipo` and `Usuario`. The test creates a team and a user, saves them in the database, and then adds the user to the team.

After that, the test verifies that:

* the team contains the user;
* the user contains the team;
* the relationship is correctly updated on both sides.

This test is important because the application needs to allow users to join teams and leave teams. It also verifies that the intermediate table `equipo_usuario` works correctly.

### EquipoService tests

The service tests check the business logic of the team functionality. These tests are used to verify that the service layer works correctly before connecting it to the controller and views.

The service tests check operations such as:

* creating a new team;
* listing teams;
* ordering teams alphabetically;
* adding a user to a team;
* removing a user from a team;
* obtaining the users that belong to a team;
* detecting error cases, such as trying to join a team that does not exist or trying to remove a user from a team incorrectly.

These tests are important because the controller should not contain complex logic. The controller should only call the service methods. Therefore, most of the important functionality is tested in the service layer.

### Web/controller tests

The web tests check that the controller endpoints work correctly. They verify that the correct pages are loaded, that the model contains the necessary data, and that the user can access the team functionality from the web interface.

For the team functionality, the web layer includes endpoints such as:

* showing the list of teams;
* showing the users of a selected team;
* creating a new team;
* joining a team;
* leaving a team.


SCREENSHOT DATABASE

### GitHub Actions tests

The repository contains two GitHub Actions workflows. The first one runs the developer tests. The second one runs the integration tests with PostgreSQL.

The integration workflow starts a PostgreSQL Docker container and runs the Maven tests using the `postgres` profile. This checks that the application can connect to PostgreSQL and that the database configuration works correctly.

Before merging the pull request into `main`, both workflows must pass. This ensures that the new changes do not break the application.

## Thymeleaf templates

| Template | Explanation |
|---|---|
| `listaEquipos.html` | Shows all teams and allows joining/leaving |
| `detalleEquipo.html` | Shows the users of a selected team |
| `fragments.html` | Contains the navbar with the Teams option |

## Docker commands

```bash
docker pull postgres:13
docker run -d -p 5432:5432 --name postgres-develop -e POSTGRES_USER=atsd -e POSTGRES_PASSWORD=atsd -e POSTGRES_DB=atsd postgres:13
docker run -d -p 5432:5432 --name postgres-test -e POSTGRES_USER=atsd -e POSTGRES_PASSWORD=atsd -e POSTGRES_DB=atsd_test postgres:13
docker container ls -a
docker container stop postgres-develop
docker container start postgres-develop
docker container logs postgres-develop
```
```md
## Docker image

Docker Hub image:

`jamesbobbrown/atsd-todolist-james-misha:1.2.0`

Commands used:

```bash
docker build -t jamesbobbrown/atsd-todolist-james-misha:1.2.0 .
docker push jamesbobbrown/atsd-todolist-james-misha:1.2.0
docker run --rm -p 8081:8080 --name todolist-app -e SPRING_PROFILES_ACTIVE=postgres -e POSTGRES_HOST=host.docker.internal jamesbobbrown/atsd-todolist-james-misha:1.2.0
```