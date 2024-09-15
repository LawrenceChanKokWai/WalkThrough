# RequestContext - section 4

The `RequestContext` class provides a thread-safe way to store and retrieve the user ID within the current thread context. It uses `ThreadLocal` to ensure that each thread has its own isolated copy of the user ID, which is useful in multi-threaded environments such as web applications.

## Purpose

The `RequestContext` class is designed to store the user ID for the duration of a request in a thread-local variable. This user ID can then be accessed throughout the application's processing to identify the current user making the request. This is particularly useful for actions like auditing, where it is important to know which user is performing certain operations (e.g., creating or updating entities).

## How It Works

### ThreadLocal

- The class relies on `ThreadLocal<Long>` to store the user ID for the current thread.
- `ThreadLocal` provides a way to keep variables isolated between threads, ensuring that each thread has its own unique copy of a variable. This is useful in scenarios like handling HTTP requests, where multiple threads could be handling different requests concurrently.

## Methods

### `start()`
- This method is used to clear the `USER_ID` from the current thread by calling `USER_ID.remove()`. It ensures that no stale user ID remains in the context when starting a new request.
- **Usage**: Typically called at the beginning of a new request or operation to reset the `ThreadLocal` storage.

### `getUserId()`
- Retrieves the user ID for the current thread.
- **Returns**: The `Long` value of the user ID stored in the thread context, or `null` if no user ID is set.

### `setUserId(Long user_id)`
- Stores the user ID in the current thread's context.
- **Parameters**:
  - `user_id`: The ID of the user to be stored in the thread-local variable.
- **Usage**: This method is used to set the user ID when starting a new operation that needs to be tied to a specific user (e.g., during authentication or when creating a new entity).

## Usage Example

Here’s how you might use the `RequestContext` class in a typical application workflow:

```java
public void handleRequest(Long userId) {
    // Start the request context
    RequestContext.start();
    
    // Set the user ID for the current request thread
    RequestContext.setUserId(userId);
    
    // Perform operations that require the user ID
    Long currentUserId = RequestContext.getUserId();
    
    // Use the user ID for auditing or other purposes
    System.out.println("Processing request for user ID: " + currentUserId);
    
    // At the end of the request, the context will be cleared
    RequestContext.start();
}
