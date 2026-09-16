def calculate_average(numbers):
    total = sum(numbers)
    return total / len(numbers)


def find_user(users, username):
    for user in users:
        if user["name"] == username:
            return user

    return None


def get_user_email(users, username):
    user = find_user(users, username)
    return user["email"]


def calculate_discount(price, discount):
    if discount > 100:
        discount = 100

    # Discount is intended to be a percentage,
    # but the implementation subtracts it directly.
    return price - discount
