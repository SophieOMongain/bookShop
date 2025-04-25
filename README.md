# Online Bookshop – Software Patterns CA4

This project is a full-stack Online Bookshop application developed for the Software Design Patterns module. It demonstrates the use of several key design patterns in a practical, backend-focused e-commerce system, with a basic HTML frontend.

## Technologies Used

### Backend:
- Spring Boot
- Spring Security (Authentication and Role-based access control)
- Spring Data JPA
- MySQL (Database)
- RESTful API (JSON responses)

### Frontend:
- HTML

## Features

### User Features
- Register and log in with secure password hashing
- Browse and search for books by title, author, publisher, or category
- Sort search results in ascending or descending order by various attributes
- Add books to a shopping cart, update quantities, remove items
- Checkout process that creates an order and updates stock levels
- View order history
- Submit product reviews with a 1–5 star rating and optional comment

### Admin Features
- View all customer orders and user accounts
- Update book details and manage stock quantities
- Restricted access to admin-only functionality

## Design Patterns Used

| Pattern              | Description                                                 | Key Classes |
|----------------------|-------------------------------------------------------------|-------------|
| Factory Pattern       | Centralized creation of Book objects                        | `BookFactory` |
| Strategy Pattern      | Dynamic discount calculations                               | `DiscountStrategy`, `NoDiscountStrategy`, `PercentDiscountStrategy` |
| Decorator Pattern     | Applying discounts and taxes during price calculation       | `PriceCalculator`, `DiscountDecorator`, `TaxDecorator` |
| Template Method       | Reusable checkout workflow structure                        | `CheckoutTemplate`, `Checkout` |
| Observer Pattern      | Stock change notification after checkout                    | `StockObserver`, `AdminNotificationObserver` |
| MVC Pattern           | Separation of concerns for logic, data, and request handling| Controllers, Services, Repositories, Entities |
| Repository Pattern    | Abstract data access using Spring JPA                       | All `*Repository.java` classes |

## Project Structure
BookShop/ ├── src/ │ └── main/ │ ├── java/ │ │ └── com/onlinebookshop/bookshop/ │ └── resources/ │ └── application.properties ├── frontend/ │ └── index.html ├── pom.xml ├── README.md
