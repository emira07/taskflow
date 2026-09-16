from dataclasses import dataclass


@dataclass
class User:
    username: str
    email: str


class UserRepository:

    def __init__(self):
        self._users = []

    def save(self, user: User) -> None:
        self._users.append(user)

    def find_by_username(self, username: str) -> User | None:
        return next(
            (user for user in self._users if user.username == username),
            None
        )


class UserService:

    def __init__(self, repository: UserRepository):
        self.repository = repository

    def register(self, username: str, email: str) -> User:
        if not username.strip():
            raise ValueError("Username cannot be empty")

        if "@" not in email:
            raise ValueError("Invalid email address")

        user = User(username=username, email=email)
        self.repository.save(user)

        return user
