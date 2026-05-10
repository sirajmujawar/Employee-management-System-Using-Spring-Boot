# 🏢 Employee Management System

A full-stack **Employee Management System** built with **Spring Boot** (backend) and **React + Vite** (frontend). This application provides a complete CRUD (Create, Read, Update, Delete) interface for managing employee records, featuring a RESTful API backend with database persistence and a modern single-page application frontend.

---

## 📑 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Backend Details](#backend-details)
  - [Entity Layer](#entity-layer)
  - [Repository Layer](#repository-layer)
  - [DAO Layer](#dao-layer)
  - [Service Layer](#service-layer)
  - [Controller Layer](#controller-layer)
  - [Exception Handling](#exception-handling)
  - [CORS Configuration](#cors-configuration)
  - [Response Structure](#response-structure)
- [Frontend Details](#frontend-details)
  - [Components](#components)
  - [Routing](#routing)
  - [Styling](#styling)
- [API Endpoints](#api-endpoints)
- [Database Configuration](#database-configuration)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Swagger API Documentation](#swagger-api-documentation)
- [Screenshots](#screenshots)

---

## Overview

The Employee Management System is a web-based application designed to streamline the process of managing employee data within an organization. It allows administrators to:

- **Add** new employees with details like name, email, salary, department, and location.
- **View** all employee records in a tabular format with pagination and sorting.
- **Update** existing employee information.
- **Delete** employees by their unique ID.
- **Search** employees by location using pattern matching.

The project follows a **layered architecture** pattern separating concerns across Controller → Service → DAO → Repository layers for clean, maintainable code.

---

## Features

| Feature                   | Description                                                                 |
| ------------------------- | --------------------------------------------------------------------------- |
| ➕ Add Employee            | Create a new employee record with full validation                          |
| 📋 View Records           | Display all employees in a responsive, sortable table                      |
| ✏️ Update Employee        | Modify employee details by specifying the employee ID                      |
| 🗑️ Delete Employee       | Remove an employee record by ID                                            |
| 📄 Pagination & Sorting   | Server-side pagination with sorting by name (descending)                   |
| 🔍 Search by Location     | Find employees by location using case-insensitive pattern matching         |
| 📑 Bulk Insert            | Save multiple employee records in a single API call                        |
| ⚠️ Exception Handling     | Global exception handler with custom `IdValidationException`              |
| 📝 Swagger UI             | Interactive API documentation via SpringDoc OpenAPI                        |
| 🕐 Audit Timestamps       | Automatic creation and update timestamps on every record                   |
| 🌐 CORS Enabled           | Cross-origin support configured for frontend-backend communication         |
| 📱 Responsive Design      | Mobile-friendly UI with responsive CSS                                     |

---

## Tech Stack

### Backend
| Technology              | Version  | Purpose                                      |
| ----------------------- | -------- | -------------------------------------------- |
| Java                    | 17       | Programming language                         |
| Spring Boot             | 3.2.5    | Application framework                        |
| Spring Data JPA         | —        | ORM and database abstraction                 |
| Spring Web              | —        | REST API development                         |
| H2 Database             | —        | In-memory database (active profile)          |
| PostgreSQL              | —        | Production database (commented out profile)  |
| Lombok                  | —        | Boilerplate code reduction                   |
| SpringDoc OpenAPI       | 2.5.0    | Swagger UI / API documentation               |
| Spring Boot DevTools    | —        | Hot reload during development                |
| Maven                   | —        | Build tool and dependency management         |

### Frontend
| Technology              | Version  | Purpose                                      |
| ----------------------- | -------- | -------------------------------------------- |
| React                   | 19.2.4   | UI library                                   |
| React DOM               | 19.2.4   | DOM rendering                                |
| React Router DOM        | 7.14.0   | Client-side routing                          |
| Vite                    | 8.0.4    | Build tool and dev server                    |
| ESLint                  | 9.39.4   | Code linting                                 |
| Vanilla CSS             | —        | Styling                                      |

---

## Architecture

The project follows a **multi-tier layered architecture**:

```
┌──────────────────────────────────────────────────────────────────────┐
│                        FRONTEND (React + Vite)                       │
│                         http://localhost:5173                         │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐  │
│  │  Navbar   │ │   Add    │ │  Update  │ │  Delete  │ │ Records  │  │
│  │          │ │ Employee │ │ Employee │ │ Employee │ │ Employee │  │
│  └──────────┘ └──────────┘ └──────────┘ └──────────┘ └──────────┘  │
└────────────────────────────┬─────────────────────────────────────────┘
                             │  HTTP (REST API)
                             ▼
┌──────────────────────────────────────────────────────────────────────┐
│                      BACKEND (Spring Boot)                           │
│                       http://localhost:8080                           │
│                                                                      │
│  ┌─────────────────────────────────────────────────────────────────┐ │
│  │  Controller Layer  (EmployeeController.java)                    │ │
│  │  - Handles HTTP requests (GET, POST, PUT, DELETE)               │ │
│  │  - Maps endpoints to service methods                            │ │
│  │  - Returns ResponseEntity with ResponseStructure                │ │
│  └─────────────────────────┬───────────────────────────────────────┘ │
│                             │                                        │
│  ┌─────────────────────────▼───────────────────────────────────────┐ │
│  │  Service Layer  (EmployeeService.java)                          │ │
│  │  - Business logic and validations                               │ │
│  │  - ID validation (throws IdValidationException for negative)    │ │
│  │  - Delegates to DAO layer                                       │ │
│  └─────────────────────────┬───────────────────────────────────────┘ │
│                             │                                        │
│  ┌─────────────────────────▼───────────────────────────────────────┐ │
│  │  DAO Layer  (EmployeeDao.java)                                  │ │
│  │  - Data access operations                                       │ │
│  │  - Pagination & sorting logic                                   │ │
│  │  - Uses EmployeeRepository                                      │ │
│  └─────────────────────────┬───────────────────────────────────────┘ │
│                             │                                        │
│  ┌─────────────────────────▼───────────────────────────────────────┐ │
│  │  Repository Layer  (EmployeeRepository.java)                    │ │
│  │  - Extends JpaRepository<Employee, Integer>                     │ │
│  │  - Custom query: findByLocationContainingIgnoringCase()         │ │
│  └─────────────────────────┬───────────────────────────────────────┘ │
│                             │                                        │
└─────────────────────────────┼────────────────────────────────────────┘
                              │  JPA / Hibernate
                              ▼
                  ┌───────────────────────┐
                  │   H2 Database          │
                  │   (In-Memory)          │
                  │   jdbc:h2:mem:testdb   │
                  └───────────────────────┘
```

---

## Project Structure

```
Employee-mangement-spring-boot-project/
│
├── Employee-management-System/              # 🔧 BACKEND (Spring Boot)
│   ├── pom.xml                              # Maven dependencies & build config
│   ├── mvnw / mvnw.cmd                      # Maven wrapper scripts
│   └── src/
│       ├── main/
│       │   ├── java/com/qsp/Employee_management_System/
│       │   │   ├── EmployeeManagementSystemApplication.java   # Main entry point
│       │   │   ├── config/
│       │   │   │   └── CrossConfig.java                       # CORS configuration
│       │   │   ├── controller/
│       │   │   │   └── EmployeeController.java                # REST API endpoints
│       │   │   ├── dao/
│       │   │   │   └── EmployeeDao.java                       # Data access object
│       │   │   ├── entitylayer/
│       │   │   │   └── Employee.java                          # JPA entity model
│       │   │   ├── exception/
│       │   │   │   ├── GlobalExceptionHandler.java            # Global error handler
│       │   │   │   └── IdValidationException.java             # Custom exception
│       │   │   ├── repository/
│       │   │   │   └── EmployeeRepository.java                # JPA repository
│       │   │   ├── responsestructure/
│       │   │   │   └── ResponseStructure.java                 # Generic API response
│       │   │   └── service/
│       │   │       └── EmployeeService.java                   # Business logic
│       │   └── resources/
│       │       └── application.properties                     # App configuration
│       └── test/                                               # Unit tests
│
└── ems-react/Employee-Management-System/    # 🎨 FRONTEND (React + Vite)
    ├── index.html                           # HTML entry point
    ├── package.json                         # NPM dependencies & scripts
    ├── vite.config.js                       # Vite configuration
    ├── eslint.config.js                     # ESLint configuration
    └── src/
        ├── main.jsx                         # React entry point (BrowserRouter)
        ├── App.jsx                          # Root component with routes
        ├── Component/
        │   ├── Navbar.jsx                   # Navigation bar component
        │   ├── AddEmployee.jsx              # Add employee form
        │   ├── UpdateEmp.jsx                # Update employee form
        │   ├── DeleteEmp.jsx                # Delete employee form
        │   └── RecordEmp.jsx                # Employee records table
        └── css/
            └── App.css                      # Application styles
```

---

## Backend Details

### Entity Layer

**File:** `Employee.java`

The `Employee` entity represents the database table and defines the following fields:

| Field        | Type             | Description                                   |
| ------------ | ---------------- | --------------------------------------------- |
| `empid`      | `int`            | Primary key (auto-generated via sequence starting at 1234) |
| `name`       | `String`         | Employee's full name                          |
| `email`      | `String`         | Employee's email address                      |
| `salary`     | `double`         | Employee's salary                             |
| `department` | `String`         | Department (e.g., HR, IT, Finance)            |
| `location`   | `String`         | Employee's work location                      |
| `creation`   | `LocalDateTime`  | Auto-set timestamp when record is created (`@CreationTimestamp`) |
| `updation`   | `LocalDateTime`  | Auto-updated timestamp on record modification (`@UpdateTimestamp`) |

Key annotations used:
- `@Entity` — Marks the class as a JPA entity.
- `@Id` + `@GeneratedValue` — Auto-generates the primary key using a sequence (`my_seq`) starting at `1234` with an allocation size of `1`.
- `@CreationTimestamp` / `@UpdateTimestamp` — Hibernate annotations that automatically track creation and modification times.

---

### Repository Layer

**File:** `EmployeeRepository.java`

Extends `JpaRepository<Employee, Integer>`, which provides built-in methods such as:
- `save()`, `findById()`, `findAll()`, `deleteById()`, `existsById()`, `saveAll()`

**Custom Query Method:**
```java
List<Employee> findByLocationContainingIgnoringCase(String address);
```
This uses Spring Data JPA's **derived query method** naming convention to search employees whose location contains the given substring (case-insensitive).

---

### DAO Layer

**File:** `EmployeeDao.java`

The Data Access Object (DAO) layer acts as an abstraction over the repository. It contains:

| Method                                 | Description                                              |
| -------------------------------------- | -------------------------------------------------------- |
| `saveEmployeedao(Employee)`            | Saves a single employee record                           |
| `getEmployeeByIdDao(int)`              | Finds an employee by ID, returns `null` if not found     |
| `deleteEmployeeByIdDao(int)`           | Deletes employee if exists, returns `true`/`false`       |
| `updateEmployeedao(Employee)`          | Updates an existing employee record                      |
| `saveListEmployeeDao(List<Employee>)`  | Bulk saves a list of employees                           |
| `getEmployeeusingPaginationandSorting(int, int)` | Returns paginated results sorted by name (descending) |
| `getEmployeeByAddressDao(String)`      | Searches employees by location (case-insensitive)       |

---

### Service Layer

**File:** `EmployeeService.java`

The service layer contains **business logic** and **validations**:

- Validates employee IDs — throws `IdValidationException` if a negative ID is provided.
- Delegates all data operations to the DAO layer.
- Handles pagination parameters (page number and page size).

---

### Controller Layer

**File:** `EmployeeController.java`

The REST controller exposes the following API endpoints (see [API Endpoints](#api-endpoints) for full details). Every response is wrapped in a generic `ResponseStructure<T>` object for consistent API responses.

---

### Exception Handling

| File                          | Purpose                                                      |
| ----------------------------- | ------------------------------------------------------------ |
| `IdValidationException.java`  | Custom `RuntimeException` thrown when an invalid (negative) employee ID is provided |
| `GlobalExceptionHandler.java` | `@RestControllerAdvice` that catches `IdValidationException` and returns a structured `400 BAD REQUEST` response |

**Example error response:**
```json
{
  "statusCode": 400,
  "message": "given id is negative",
  "data": null
}
```

---

### CORS Configuration

**File:** `CrossConfig.java`

Enables **Cross-Origin Resource Sharing** so the React frontend (running on `http://localhost:5173`) can communicate with the Spring Boot backend (running on `http://localhost:8080`).

```java
registry.addMapping("/**")
    .allowedOrigins("http://localhost:5173")
    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    .allowedHeaders("*");
```

---

### Response Structure

**File:** `ResponseStructure.java`

A generic wrapper class used to standardize all API responses:

```json
{
  "statusCode": 201,
  "message": "Data is successfully inserted...",
  "data": { /* Employee object or other data */ }
}
```

| Field        | Type     | Description                        |
| ------------ | -------- | ---------------------------------- |
| `statusCode` | `int`    | HTTP status code                   |
| `message`    | `String` | Descriptive message                |
| `data`       | `T`      | Generic payload (Employee, List, Boolean, etc.) |

---

## Frontend Details

### Components

| Component           | Route       | Description                                                                                   |
| ------------------- | ----------- | --------------------------------------------------------------------------------------------- |
| **Navbar**          | —           | Top navigation bar with links to all pages. Displays on every page.                           |
| **AddEmployee**     | `/`         | Form with fields for Name, Email, Salary, Department (dropdown), and Location. Sends a `POST` request to create a new employee. Includes client-side validation. |
| **UpdateEmp**       | `/update`   | Form similar to Add but also requires the Employee ID. Sends a `PUT` request to update the employee with the given ID. |
| **DeleteEmp**       | `/delete`   | Simple form with an Employee ID field. Sends a `DELETE` request to remove the employee.        |
| **RecordEmp**       | `/records`  | Fetches and displays all employees in a table. Includes inline **Edit** and **Delete** buttons per row. |

### Routing

The app uses **React Router DOM v7** with `BrowserRouter` for client-side navigation:

```jsx
<BrowserRouter>
  <Navbar />
  <Routes>
    <Route path="/"        element={<AddEmployee />} />
    <Route path="/update"  element={<UpdateEmp />}   />
    <Route path="/delete"  element={<DeleteEmp />}   />
    <Route path="/records" element={<RecordEmp />}    />
  </Routes>
</BrowserRouter>
```

### Styling

The application uses **vanilla CSS** (`src/css/App.css`) with:

- **Dark navbar** (`#0f172a`) with sky-blue hover effects (`#38bdf8`).
- **Card-style forms** with white backgrounds, border-radius, and box shadows.
- **Responsive table** with zebra striping, hover effects, and action buttons.
- **Color-coded buttons**: Blue for Edit (`#2196F3`), Red for Delete (`#f44336`), Dark for Submit (`#0f172a`).
- **Mobile responsive** layout with media queries for screens ≤ 400px.
- **Focus effects** on inputs with blue glow animations.

---

## API Endpoints

### Employee CRUD Operations

| Method   | Endpoint                         | Description                                  | Request Body        | Response                              |
| -------- | -------------------------------- | -------------------------------------------- | ------------------- | ------------------------------------- |
| `POST`   | `/employees`                     | Create a new employee                        | `Employee` JSON     | `201 Created` — `ResponseStructure<Employee>` |
| `GET`    | `/employees/{empid}`             | Get employee by ID                           | —                   | `200 OK` or `404 Not Found`          |
| `PUT`    | `/employees`                     | Update an existing employee                  | `Employee` JSON     | `201 Created` — `ResponseStructure<Employee>` |
| `DELETE` | `/employees/{empid}`             | Delete employee by ID                        | —                   | `200 OK` or `404 Not Found`          |
| `POST`   | `/employees/all`                 | Bulk create multiple employees               | `List<Employee>` JSON | `201 Created` — `ResponseStructure<List<Employee>>` |
| `GET`    | `/employees?page=0&size=10`      | Get paginated & sorted employees             | —                   | `Page<Employee>`                     |
| `GET`    | `/employees/location/{location}` | Search employees by location (case-insensitive) | —               | `List<Employee>`                     |

### Sample Request Body

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "salary": 75000,
  "department": "IT",
  "location": "Bangalore"
}
```

### Sample Response

```json
{
  "statusCode": 201,
  "message": "Data is successfully inserted...",
  "data": {
    "empid": 1234,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "salary": 75000.0,
    "department": "IT",
    "location": "Bangalore",
    "creation": "2026-05-11T00:00:00",
    "updation": "2026-05-11T00:00:00"
  }
}
```

---

## Database Configuration

The project supports **two database profiles**:

### Active — H2 (In-Memory Database)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
> **Note:** H2 is an in-memory database, so all data is lost when the application restarts. This is ideal for development and testing.

### Alternative — PostgreSQL (Commented Out)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/spring-boot-ems
spring.datasource.username=postgres
spring.datasource.password=<your-password>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
```
> To switch to PostgreSQL, uncomment these lines in `application.properties` and comment out the H2 configuration. Make sure PostgreSQL is installed and a database named `spring-boot-ems` exists.

---

## Prerequisites

Before running the project, ensure you have the following installed:

| Software    | Version  | Download Link                                     |
| ----------- | -------- | ------------------------------------------------- |
| Java JDK    | 17+      | [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/) |
| Node.js     | 18+      | [nodejs.org](https://nodejs.org/)                |
| npm         | 9+       | Included with Node.js                            |
| Maven       | 3.8+     | Included via Maven Wrapper (`mvnw`)              |
| Git         | Latest   | [git-scm.com](https://git-scm.com/)             |
| PostgreSQL  | 15+ *(optional)* | [postgresql.org](https://www.postgresql.org/) |

---

## Getting Started

### Backend Setup

1. **Navigate to the backend directory:**
   ```bash
   cd Employee-management-System
   ```

2. **Run the Spring Boot application:**
   ```bash
   # Using Maven Wrapper (recommended)
   ./mvnw spring-boot:run

   # Or on Windows
   mvnw.cmd spring-boot:run
   ```

3. **The backend server starts at:** `http://localhost:8080`

4. **Access the H2 Console (optional):**
   Navigate to `http://localhost:8080/h2-console` and use:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: `password`

### Frontend Setup

1. **Navigate to the frontend directory:**
   ```bash
   cd ems-react/Employee-Management-System\ \(1\)/Employee-Management-System
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

3. **Start the development server:**
   ```bash
   npm run dev
   ```

4. **The frontend runs at:** `http://localhost:5173`

### Available Frontend Scripts

| Command          | Description                              |
| ---------------- | ---------------------------------------- |
| `npm run dev`    | Start Vite dev server with HMR           |
| `npm run build`  | Create production build                  |
| `npm run lint`   | Run ESLint for code quality checks       |
| `npm run preview`| Preview the production build locally     |

---

## Swagger API Documentation

This project integrates **SpringDoc OpenAPI** for interactive API documentation.

Once the backend is running, access Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

This provides a visual interface to explore, test, and interact with all the REST API endpoints without needing an external tool like Postman.

---

## Screenshots

After running both the backend and frontend servers:

| Page              | URL                         | Description                         |
| ----------------- | --------------------------- | ----------------------------------- |
| Add Employee      | `http://localhost:5173/`    | Form to create new employees        |
| Update Employee   | `http://localhost:5173/update` | Form to update existing employees |
| Delete Employee   | `http://localhost:5173/delete` | Form to delete employees by ID   |
| Employee Records  | `http://localhost:5173/records` | Table showing all employees      |
| Swagger API Docs  | `http://localhost:8080/swagger-ui/index.html` | Interactive API docs |

---

## 🛠️ Built With

- **Spring Boot** — Backend REST API framework
- **React** — Frontend UI library
- **Vite** — Lightning-fast frontend build tool
- **Spring Data JPA** — Database ORM layer
- **H2 / PostgreSQL** — Database engines
- **SpringDoc OpenAPI** — API documentation

---

## 📄 License

This project is open-source and available for educational purposes.

---

> **💡 Tip:** Always start the **backend server first** before launching the frontend, so that the API is available when the React app makes HTTP requests.
