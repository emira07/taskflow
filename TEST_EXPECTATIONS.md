# Test Expectations

This file is for benchmark/evaluation use and is not project documentation.

## UserService.java
Potential findings:
- Password stored/handled as plain text
- Multiple responsibilities
- Console output in service
- Validation/business logic concerns

## TaskService.java
Potential findings:
- Missing project-membership validation in assignTask
- Business rule violation
- Service responsibility/coupling considerations

## NotificationService.java
Potential findings:
- Empty catch block
- Swallowed exception
- Generic exception handling

## PaymentService.java
Potential findings:
- Dependency Inversion Principle violation
- Tight coupling to concrete payment implementation

## clean_code_violations.py
Potential findings:
- Poor naming
- Too many parameters
- Deep nesting
- Magic numbers
- Complex logic

## bugs.py
Potential findings:
- Division by zero
- Possible None dereference
- Incorrect percentage discount calculation

## good_code.py
Expected: no major violations.
