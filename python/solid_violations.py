class EmployeeManager:
    """Intentionally combines multiple responsibilities."""

    def __init__(self):
        self.employees = []

    def add_employee(self, name, salary):
        self.employees.append({"name": name, "salary": salary})

    def calculate_salary(self, employee):
        return employee["salary"] * 1.15

    def save_to_database(self, employee):
        print("Connecting to database...")
        print("INSERT INTO employees VALUES (...)")

    def send_email(self, employee):
        print("Sending email to", employee["name"])

    def generate_report(self):
        print("Generating employee report")

    def export_to_pdf(self):
        print("Exporting report to PDF")
