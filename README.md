# ApiException (Application Exception) - Section_2

The `ApiException` class is a custom exception used in the ResumeApp application. It extends `RuntimeException`, allowing the application to throw exceptions related to API errors. This class provides constructors for creating exceptions with custom messages or default error messages, making it easier to handle error scenarios in the application.

## Class: `ApiException`

The `ApiException` class is designed to handle and represent errors that occur within the ResumeApp, specifically when interacting with APIs or when an operation fails due to a business logic constraint.

## Purpose

- **Custom Error Handling**: This class provides a mechanism to throw meaningful exceptions in cases where the default Java exceptions might not be specific enough.
- **API Error Response**: By using this exception, the application can easily manage and return custom error messages to API clients, improving the clarity of error handling.

## Methods

### 1. `ApiException(String message)`
- **Description**: This constructor allows creating an exception with a custom error message.
- **Parameters**:
  - `message`: A string that represents the error message describing what went wrong.
- **Usage**: Use this constructor when you need to throw an exception with a specific error message.

#### Example:

```java
throw new ApiException("User not found in the system");
