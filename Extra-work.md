# Extra Work - Optional Team Management

## Optional user story implemented

### 010 Team management optional

As an administrator I can rename and delete teams to adapt them to the projects and structure of the company.

The original project does not include a separate administrator role. Because of that, the optional team management actions have been implemented in the authenticated Teams section of the application.

The extra functionality allows a logged-in user to:

- See the list of teams.
- Create new teams.
- Rename an existing team.
- Delete an existing team.
- See the members of a team.

---

## GitHub repository

https://github.com/KrasMish/Ex3_umh_pairs

---

## Trello board

https://trello.com/b/mQe2rhr3/p3-to-do-list-app-pairs

---

## Docker image

https://hub.docker.com/r/jamesbobbrown/atsd-todolist-james-misha

---

## Methodology followed

The development was carried out using the `develop` branch.

The methodology followed was:

1. Create the optional user story in Trello.
2. Create the GitHub issue `010 Team management optional`.
3. Create the branch `feature/010-team-management` from `develop`.
4. Implement the service methods, controller endpoints, Thymeleaf template and tests.
5. Commit the changes with a clear message.
6. Push the branch to GitHub.
7. Create a pull request into `develop`.
8. Wait for GitHub Actions to run.
9. Merge the pull request when the tests pass.
10. Merge `develop` into `main` for the final delivery.

---

## Implemented classes and methods

### `Equipo`

Path:

```text
src/main/java/todolist/model/Equipo.java
Methodology followed

The development was carried out using the develop branch.

The methodology followed was:

Create the optional user story in Trello.
Create the GitHub issue 010 Team management optional.
Create the branch feature/010-team-management from develop.
Implement the service methods, controller endpoints, Thymeleaf template and tests.
Commit the changes with a clear message.
Push the branch to GitHub.
Create a pull request into develop.
Wait for GitHub Actions to run.
Merge the pull request when the tests pass.
Merge develop into main for the final delivery.

This follows the required workflow:

issue -> branch -> changes -> pull request -> merge
Implemented classes and methods
Equipo

Path:

src/main/java/todolist/model/Equipo.java

Modified method:

Method	Explanation
setNombre(String nombre)	Allows changing the name of an existing team

This method is necessary because the optional story requires renaming teams.

EquipoService

Path:

src/main/java/todolist/service/EquipoService.java

Implemented methods:

Method	Explanation
findById(Long equipoId)	Finds a team by its identifier and returns it as EquipoData
renombrarEquipo(Long equipoId, String nuevoNombre)	Renames an existing team after validating the new name
eliminarEquipo(Long equipoId)	Deletes an existing team from the database

Validation implemented in renombrarEquipo:

The new name cannot be empty.
The new name cannot contain only blank spaces.
The new name cannot be repeated if another team already uses it.
The team must exist before it can be renamed.

Validation implemented in eliminarEquipo:

The team must exist before it can be deleted.
The team is removed from the database.
The relationship between the team and its users is cleaned before deleting the team.
EquipoController

Path:

src/main/java/todolist/controller/EquipoController.java

Implemented methods:

Method	Endpoint	Explanation
editarEquipoForm	GET /equipos/{id}/editar	Shows the form used to rename a team
editarEquipoSubmit	POST /equipos/{id}/editar	Saves the new team name
eliminarEquipo	POST /equipos/{id}/eliminar	Deletes the selected team

Modified methods:

Method	Explanation
listadoEquipos	Shows the list of teams with the new Rename and Delete actions
detalleEquipo	Continues showing the members of the selected team

The controller does not implement business logic directly. It calls the corresponding methods from EquipoService.

Controllers and endpoints

The optional work adds the following endpoints:

Method	Endpoint	Template	Explanation
GET	/equipos/{id}/editar	formEditarEquipo.html	Shows the edit form for renaming a team
POST	/equipos/{id}/editar	Redirects to /equipos	Updates the name of the selected team
POST	/equipos/{id}/eliminar	Redirects to /equipos	Deletes the selected team

Existing endpoints used by this functionality:

Method	Endpoint	Template	Explanation
GET	/equipos	listaEquipos.html	Shows the list of teams and the management buttons
POST	/equipos/nuevo	Redirects to /equipos	Creates a new team
GET	/equipos/{id}	detalleEquipo.html	Shows the members of a selected team
POST	/equipos/{id}/join	Redirects to /equipos	Adds the logged-in user to a team
POST	/equipos/{id}/leave	Redirects to /equipos	Removes the logged-in user from a team
Thymeleaf templates
listaEquipos.html

Path:

src/main/resources/templates/listaEquipos.html

Modified template.

This page now includes a new management column with:

Rename button.
Delete button.
Confirmation message before deleting a team.

The Rename button redirects to:

/equipos/{id}/editar

The Delete button sends a POST request to:

/equipos/{id}/eliminar
formEditarEquipo.html

Path:

src/main/resources/templates/formEditarEquipo.html

New template.

This page contains the form used to rename a team.

It shows the current team name and allows the user to submit a new one.

The form sends the data to:

POST /equipos/{id}/editar
detalleEquipo.html

Path:

src/main/resources/templates/detalleEquipo.html

Existing template.

This template is used to show the members of a selected team.

It is part of the team management functionality because the administrator/user can verify the members of a team before deciding whether the team should be renamed or deleted.

Tests implemented

The following tests were added in:

src/test/java/todolist/service/EquipoServiceTest.java
Test	Explanation
renombrarEquipo	Checks that a team can be renamed correctly
renombrarEquipoConNombreVacioLanzaExcepcion	Checks that an empty team name is rejected
eliminarEquipo	Checks that a team can be deleted correctly

These tests focus on the service layer because the business logic should be implemented in the service, not directly in the controller.

The controller only calls the service methods.

This follows the TDD approach used in Exercise 3.

Manual testing

The feature was also tested manually through the web interface.

Manual test process:

Start the application.
Register a user.
Log in.
Go to the Teams section.
Create a new team.
Click the Rename button.
Change the team name.
Confirm that the new name appears in the team list.
Click the Delete button.
Confirm the deletion.
Confirm that the team no longer appears in the team list.
Database changes

No new database tables were added.

No new columns were added.

The optional functionality uses the existing database structure:

Table	Use
equipos	Stores teams and their names
equipo_usuario	Stores the many-to-many relationship between teams and users
usuarios	Stores users

When a team is renamed, the value of the nombre column in the equipos table is updated.

When a team is deleted, the row is removed from the equipos table.

The existing database screenshot is included in the repository:

docs/database-tables.png

No new database screenshot is required because the database schema was not changed.

Docker commands
Run tests
mvn test
Clean project
mvn clean
Package project
mvn clean package
Build Docker image
docker build -t jamesbobbrown/atsd-todolist-james-misha:extra-work .
Push Docker image
docker push jamesbobbrown/atsd-todolist-james-misha:extra-work
Run Docker image
docker run --rm -p 8081:8080 jamesbobbrown/atsd-todolist-james-misha:extra-work
Final result

The optional story 010 Team management optional has been implemented.

The application now allows team management operations from the Teams section:

Rename teams.
Delete teams.

This improves the application because teams can now be adapted to changes in projects and company structure.

The extra work includes:

Source code changes.
Service methods.
Controller endpoints.
Thymeleaf template changes.
Automated service tests.
Manual testing.
Documentation.
GitHub repository link.
Trello board link.
Docker image link.