# Online Food Delivery System

A JavaFX desktop application that connects customers, restaurants, and delivery
riders. Customers can browse a restaurant's menu, add items to a cart, place an
order, and track its status. Riders can log in separately, view pending
orders, and accept deliveries. The system uses a MySQL database (via XAMPP)
for persistent storage of customers, riders, restaurants, menu items, and
orders.

## Major Features

- **Customer flow:** Register → Login → Browse Menu → Add to Cart → Checkout →
  Place Order → Confirmation (with option to cancel).
- **Rider flow:** Register → Login → View Pending Orders → Accept Order.
- **Session management:** Login state is preserved using Java Serialization
  (see below).
- **Database-backed persistence:** All customers, riders, restaurants, menu
  items, and orders are stored in and retrieved from a MySQL database.

## Project Structure

```
com.example.capstone
├── main        → Application entry point (HelloApplication, Launcher)
├── controller  → JavaFX FXML controllers (one per screen)
├── dao         → Data Access Objects for database operations
├── model       → Domain classes (User, Customer, Rider, Order, etc.)
└── util        → Shared utilities (MySQLConnection, SessionManager)
```

## Session Management via Java Serialization

When a customer or rider successfully logs in, the `SessionManager` class
serializes the logged-in `User` object (specifically a `Customer` or `Rider`,
both of which implement `Serializable`) to a local file named `session.dat`
using `ObjectOutputStream`.

- **Creation:** On successful login, `SessionManager.saveSession(user)` writes
  the user object to `session.dat`.
- **Usage:** The session file represents the currently active session while
  the user navigates between screens (Menu, Cart, Confirmation, Rider
  Dashboard, etc.). `SessionManager.loadSession()` can deserialize the file
  using `ObjectInputStream` to check who is currently logged in, and
  `SessionManager.isLoggedIn()` checks for the file's existence.
- **Deletion:** When the user clicks **Logout**, `SessionManager.clearSession()`
  deletes `session.dat` from disk, and the user is redirected back to the
  Login screen.

This demonstrates the full lifecycle of a serialized session file: creation on
login, use throughout the session, and deletion on logout.

## SOLID Principles Applied

### 1. Single Responsibility Principle (SRP)

The `SessionManager` class (in the `util` package) has exactly one
responsibility: managing the session file (saving, loading, and clearing it).
Previously, this logic could easily have been scattered across every
controller that needed to know who was logged in. By centralizing it in
`SessionManager`, each controller (`LoginController`, `MenuController`,
`RiderLoginController`, `RiderDashboardController`) only needs to call a
single method (`saveSession`, `clearSession`) rather than implementing file
I/O itself.

**Benefit:** If the session storage mechanism ever changes (e.g., switching
from a flat file to encrypted storage or a database-backed session), only
`SessionManager` needs to be modified — no controller code changes are
required.

### 2. Dependency Inversion Principle (DIP)

The `User` abstract class acts as a high-level abstraction that `Customer`
and `Rider` depend on, rather than duplicating login/session logic in each
subclass. Additionally, a `UserRepository` interface was introduced in the
`dao` package, decoupling higher-level code from concrete DAO implementations
(`CustomerDAO`, `RiderDAO`). Controllers and services can depend on the
`UserRepository` abstraction instead of a specific database implementation.

**Benefit:** The system can support new user types or swap out the underlying
data source (e.g., moving from MySQL to another database or an API) without
rewriting the controllers that rely on user authentication.

## Requirements

- Java 17+
- Maven
- MySQL (via XAMPP), running on `localhost:3306`, database name `fooddelivery`