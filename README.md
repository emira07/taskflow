# TaskFlow API

TaskFlow is a collaborative task management backend for small and medium-sized development teams.

## 1. Project Overview

TaskFlow allows users to create projects, organize work into tasks, assign tasks to project members, track task progress, and receive notifications.

The backend exposes a REST API and follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

The project uses Java, Spring Boot, PostgreSQL, JPA/Hibernate, JUnit, and Mockito.

## 2. Domain Model

### User

A user represents an account in TaskFlow.

Important properties:
- `id`
- `username`
- `email`
- `password`
- `active`

Rules:
- Email addresses must be unique.
- Usernames cannot be empty.
- Inactive users cannot perform authenticated operations.
- Passwords must never be stored in plain text.
- Passwords must never be returned by API endpoints.

### Project

A project represents a workspace containing tasks.

Important properties:
- `id`
- `name`
- `description`
- `ownerId`

Rules:
- Every project has exactly one owner.
- The owner is automatically a project member.
- Only project members can access project tasks.
- Only the owner can delete a project.
- A user cannot be added to the same project more than once.

### Task

A task belongs to exactly one project.

Important properties:
- `id`
- `title`
- `description`
- `status`
- `priority`
- `projectId`
- `assigneeId`

Statuses:
- `TODO`
- `IN_PROGRESS`
- `DONE`
- `CANCELLED`

Priorities:
- `LOW`
- `MEDIUM`
- `HIGH`
- `CRITICAL`

Task rules:
1. Every task belongs to a project.
2. Only a project member can be assigned to a task.
3. A `DONE` task cannot return to `TODO`.
4. A `CANCELLED` task cannot be assigned.
5. Task titles cannot be empty.
6. A `CRITICAL` task must have an assignee before moving to `IN_PROGRESS`.

## 3. Architecture

### Controller Layer

Controllers receive HTTP requests, validate request parameters, call services, and return HTTP responses.

Controllers must not contain business logic or direct database operations.

### Service Layer

Services implement business rules and coordinate repositories and external services.

Services should not:
- Execute SQL directly.
- Format HTTP responses.
- Create database connections.
- Contain unrelated responsibilities.

### Repository Layer

Repositories are responsible for persistence.

Database-specific operations should remain in repositories. The service layer should communicate with persistence through repository abstractions.

## 4. Notifications

Notifications are generated when:
- A user is assigned to a task.
- A task changes status.
- A critical task is created.
- A project member is removed.

Notification functionality should be separated from task-management business logic.

Task services should depend on a notification abstraction rather than directly constructing a concrete email implementation.

## 5. Payments

TaskFlow supports premium projects.

Supported payment providers:
- Credit card
- PayPal

Payment-provider implementations should remain isolated from project-management logic.

Adding another payment provider should not require changing the core project-management service.

## 6. Security

The following requirements apply throughout the application:

- Passwords must be hashed before storage.
- Passwords must never appear in logs.
- Authentication tokens must not appear in error messages.
- Authorization must be checked before accessing project resources.
- Users must only access projects for which they have permission.

## 7. Error Handling

The API uses:

| Status | Meaning |
|---|---|
| 200 | Successful operation |
| 201 | Resource created |
| 400 | Invalid request |
| 401 | Authentication required |
| 403 | Operation not permitted |
| 404 | Resource not found |
| 409 | Resource conflict |
| 500 | Internal server error |

Exceptions must not be silently ignored.

Generic `Exception` handling should be avoided when a more specific exception can be used.

Error messages should provide meaningful information without exposing sensitive data.

## 8. Coding Conventions

Classes use PascalCase:

```text
UserService
TaskRepository
NotificationService
```

Methods and variables use camelCase:

```text
findUserById()
calculateTotal()
projectOwnerId
```

Avoid unclear names such as `x`, `data`, `temp`, or `manager` when a descriptive name is possible.

Methods should have one clear responsibility.

Classes should have one coherent responsibility.

Avoid:
- Duplicated business logic
- Deep nesting
- Very long methods
- Very large classes
- Magic numbers
- Dead code
- Empty catch blocks
- Unnecessary coupling

## 9. Dependency Injection

TaskFlow uses dependency injection.

Services should depend on abstractions when a component can have multiple implementations.

For example:

```text
TaskService
    ↓
NotificationSender
    ↓
EmailNotificationSender
```

`TaskService` should not directly instantiate `EmailNotificationSender`.

The same principle applies to payment providers and other replaceable external components.

## 10. Task Lifecycle

The normal task lifecycle is:

```text
TODO
  ↓
IN_PROGRESS
  ↓
DONE
```

Cancellation is possible from `TODO` or `IN_PROGRESS`:

```text
TODO ──────────→ CANCELLED
IN_PROGRESS ───→ CANCELLED
```

The following transition is invalid:

```text
DONE → TODO
```

Invalid state transitions must be rejected.

## 11. Maintainability

Correctness, security, maintainability, readability, and testability are more important than premature optimization.

When the same business rule appears in several locations, it should preferably be centralized.

## 12. Testing

Business-critical services should have automated tests covering:
- Valid operations
- Invalid input
- Authorization failures
- Missing resources
- Business-rule violations
- Error conditions

## 13. Developer Context

When reviewing or modifying TaskFlow, both the architectural constraints and the business rules in this document must be respected.

A piece of code can compile correctly and still be incorrect if it violates a TaskFlow business rule.

For example, assigning a user to a task is only valid after verifying that the user is a member of the task's project.

Likewise, payment-provider-specific code should not be embedded inside project-management services because payment providers are replaceable components.
