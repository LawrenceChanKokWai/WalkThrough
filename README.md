# Auditable Entity - Handling User Context in Application. Section 4

This document focuses on how the `Auditable` class in the ResumeApp leverages the `RequestContext` to handle user context, specifically when performing operations such as persisting and updating entities.

## Overview of `var user_id = RequestContext.getUserId();`

The line `var user_id = RequestContext.getUserId();` retrieves the user ID from the `RequestContext` to ensure that the current user performing an operation (e.g., creating or updating an entity) is correctly tracked. This is a critical aspect of auditing operations, where every change to an entity must be associated with the user responsible for the action.

### Purpose

- **Track Changes by User**: By retrieving the current user ID from `RequestContext`, the system ensures that the `createdBy` and `updatedBy` fields are populated with the correct user performing the operation.
- **Error Handling**: The system throws an `ApiException` if the user ID is not found in the `RequestContext`, preventing the operation from being completed without valid user information.

## Usage in `Auditable` Class

### 1. **In `@PrePersist` Method**

The `beforePersist()` method is annotated with `@PrePersist`, meaning it is called before a new entity is saved to the database.

```java
@PrePersist
public void beforePersist() {
    var user_id = RequestContext.getUserId();
    if (user_id == null) {
        throw new ApiException("Cannot persist entity without user id in request context for this thread");
    }
    setCreatedAt(LocalDateTime.now());
    setCreatedBy(user_id);
    setUpdatedBy(user_id);
    setUpdatedAt(LocalDateTime.now());
}

```


### 2. **In `@PreUpdate` Method**
The `preUpdate()` method is annotated with `@PreUpdate`, meaning it is called before a new entity is updated to the database.

```java
@PreUpdate
    public void beforeUpdate() {
        var user_id = RequestContext.getUserId();
        if( user_id == null )
        {
            throw new ApiException( "Cannot persists entity without user id in request context for this thread" );
        }
        setUpdatedAt( now() );
        setUpdatedBy( user_id );
    }
```
