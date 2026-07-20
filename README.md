# CAPSTONE - Online Food Delivery System

## Overview
This is a JavaFX-based online food delivery application that allows customers to browse restaurants, place orders, and rate their experience, while riders can accept and manage deliveries. The system supports multiple user roles (Customer, Rider, Admin) and manages user sessions using Java Serialization.

## Major Features
- *User Registration & Login* — separate flows for Customers and Riders, with credential validation handled by the User class hierarchy.
- *Customer Dashboard* — browse restaurants, place orders, view order history, and leave reviews.
- *Rider Dashboard* — view pending orders, accept deliveries, and update delivery status.
- *Session Management* — persistent login sessions using Java's built-in serialization mechanism.
- *Data Persistence* — order, restaurant, and user data managed through DAO classes backed by a MySQL database.

## Serialization Mechanism
User session management is implemented through *Java Serialization*, handled entirely by the SessionManager class (com.example.capstone.util.SessionManager).

- *On login:* once a user's credentials are validated, SessionManager.saveSession(User user) serializes the logged-in User object (a Customer or Rider) to a file named session.dat using ObjectOutputStream.
- *During navigation:* the application checks SessionManager.isLoggedIn() and SessionManager.loadSession() to read the serialized User object back with ObjectInputStream, keeping the user's session active as they move between screens.
- *On logout:* SessionManager.clearSession() deletes session.dat from disk, and the application redirects the user back to the login screen.

To support this, User, Customer, and Rider all implement java.io.Serializable and declare an explicit serialVersionUID to ensure consistent deserialization across versions of the class.

## SOLID Principles Applied

### 1. Dependency Inversion Principle (DIP) — User.java
The abstract User class serves as the base abstraction that all concrete user types (Customer, Rider, Admin) depend on, rather than depending on each other directly. Controllers and the session manager work with the User abstraction instead of concrete subclasses, so new user types can be added without modifying existing code that depends on User.

*Benefit:* Reduces coupling between high-level modules (controllers, session handling) and low-level user-type implementations, making the system easier to extend with new roles in the future.

### 2. Single Responsibility Principle (SRP) — SessionManager.java
The SessionManager class has exactly one responsibility: managing the user session file (creating, reading, and deleting session.dat). It does not handle authentication logic, UI navigation, or database access — those responsibilities live in the respective controller and DAO classes.

*Benefit:* Keeps session-handling logic isolated and easy to test or modify independently, without risk of breaking login validation or UI behavior.

## Tech Stack
- Java / JavaFX
- MySQL (via JDBC)
- Maven

## Author
Kenji
