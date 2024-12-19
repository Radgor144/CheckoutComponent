# CheckoutComponent

## Table of Contents

- [General Info](#general-info)
- [Requirements](#requirements)
- [How to Run Locally](#how-to-run-locally)
- [Technologies](#technologies)
- [Key Features and Architecture](#key-features-and-architecture)
- [Potential Changes](#potential-changes)

## General Info <a name="general-info"></a>

<a href="https://spring.io/projects/spring-boot" target="blank"> Spring Boot</a> application that implements a Checkout System. The Checkout Component provides a REST API for managing a market checkout system. It allows clients to interact with the service to add, scan, remove, and clear items in a shopping cart. The service is stateful and calculates the total price of items, considering both normal pricing and special pricing rules.

| Method   | URL                                 | Description                      |
|----------|-------------------------------------|----------------------------------|
| `Post`   | `/api/add?idCart={idCart}&item={item}&amount={amount}` | Adds an item to the cart.        |
| `Get`    | `/api/scan?idCart={idCart}`                         | Retrieves the details of a cart. |
| `Delete` | `/api/remove?idCart={idCart}&item={item}&amount={amount}`                       | Removes an item from the cart.   |
| `Delete` | `/api/clear?idCart={idCart}`                        | Clears all items from the cart.  |

## Requirements <a name="requirements"></a>

- <a href="https://spring.io/projects/spring-boot" target="blank"> JDK 21 </a>
- <a href="https://maven.apache.org/" target="blank"> Maven 3.x </a>

## How to Run Locally <a name="how-to-run-locally"></a>
1. Simply run the application, and it will run in memory without requiring a database setup.

## Technologies <a name = "Technologies"></a>

### Project Created with:

- **Java 21** - The core programming language for building the project.
- **Spring boot** - It supports the rapid development of microservices-based applications and includes built-in features for configuration, dependency management, and application bootstrapping.

### Documentation:
- **swagger** - Auto-generate API documentation for better interactivity, comprehension and testing of API features.

### Tests:
- **JUnit** - Unit testing was implemented to check the correctness of individual code fragments in isolation, enabling faster error detection and facilitating refactoring.
- **Mockito** - It allows you to simulate the behaviour of real objects in a controlled test environment, making it easier to test code in isolation and verify interactions between objects.
- **wiremock** - WireMock enables the creation of mock HTTP servers that can be used to test applications that communicate using the HTTP protocol. It is used to create dummy servers that can pretend to be real services. With WireMock, it is possible to create pre-defined responses to specific HTTP requests.

### Build tools:
- **Maven** - Tool for managing dependencies, building, and managing projects.

## Key Features and Architecture <a name = "key-features-and-architecture"></a>

1. **Data Validation**:
    - The application ensures that all incoming data is properly validated using Java's Jakarta Validation annotations and Spring's `@Validated`. For example:
        - Cart ID must be greater than or equal to 1.
        - Item name must not be empty and must be between 1 and 40 characters.
        - Amount must be greater than or equal to 1.

2. **Custom Error Handling**:
    - In case of invalid input or other issues, the application throws custom exceptions with meaningful error messages, improving the API's robustness.

3. **Stateful Shopping Cart**:
    - The cart is managed in memory (for now) using a `CartService`, which stores the items added by users. Each cart is identified by a unique `idCart`.

4. **Pricing Rules**:
    - The application supports special pricing rules for certain items, allowing price adjustments based on conditions like discounts for purchasing multiple items.


## Potential Changes: <a name="potential-changes"></a>

-  **Database Integration**: Currently, the service runs entirely in-memory. It could be extended to support a database (e.g., MySQL, PostgreSQL) for persistent data storage.