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

Explain:
- User story created in Trello.
- Issues created with numbers.
- Branch created from the issue.
- Changes implemented.
- Pull request opened.
- Pull request linked to the issue.
- Issue closed after merging.

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

Explain:
- `equipos`
- `usuarios`
- `equipo_usuario`

Add a screenshot of the database tables here.

## Tests explained

Explain:
- Entity test for `Equipo`.
- Repository test for saving and finding teams.
- Equality test for teams.
- Many-to-many relationship test.
- Service tests for creating teams, listing teams, joining teams and leaving teams.
- Integration tests run with PostgreSQL using GitHub Actions.

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