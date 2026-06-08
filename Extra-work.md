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

https://hub.docker.com/repository/docker/jamesbobbrown/atsd-todolist-james-misha/general

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