# University Cleaning Inventory & Issuance System

A Java desktop application developed for **Programming 3(7)(8)1** at Belgium Campus iTversity, implementing **Track B** of the group project specification: a full-featured inventory and issuance management system for university cleaning materials, suppliers, and cleaners.

---

## 📋 Overview

The system enables university staff to securely manage cleaning materials, suppliers, cleaners, and stock issuances through an intuitive desktop interface. It demonstrates core Java programming, object-oriented design, GUI development, and CRUD operations against a relational database.

---

## 🛠️ Tech Stack (Track B Requirements)

| Technology | Purpose |
|---|---|
| **Java Swing** | Graphical user interface |
| **Core Java** | Application logic |
| **Object-Oriented Programming (OOP)** | Inheritance, Encapsulation, Polymorphism, Abstraction |
| **JDBC** | Database connectivity |
| **NetBeans GUI Builder** | Form/UI design |
| **PostgreSQL / Derby (JavaDB)** | Relational database (database still to be decided between these 2)|

---

## ✅ Core Features

### Authentication
- Staff registration and login
- Password validation
- User authentication and exception handling on invalid credentials

### Dashboard
- Total materials in stock
- Low-stock item alerts
- Total registered cleaners
- Recent stock issuances summary

### Materials Management (CRUD)
- Add, view, update, and delete cleaning materials
- Track available quantities and reorder levels
- Search and filter materials

### Suppliers Management (CRUD)
- Add, view, update, and delete supplier records
- Maintain supplier contact details

### Cleaners Management (CRUD)
- Add, view, update, and delete cleaner records
- Optional assignment of cleaners to departments

### Stock Issuance Management (CRUD)
- Issue materials to cleaners
- Automatic stock deduction on issuance
- Prevents issuing more stock than available
- Maintains full issuance history

### Reports
- Inventory report
- Low-stock report
- Issuance history report
- Material usage report

### Validation & Business Rules
- Prevents duplicate usernames and email addresses
- Validates all required fields
- Prevents negative stock values
- Displays meaningful, user-friendly error messages
- Enforces role-based permissions where applicable

---

## 🏗️ Architecture

The application follows a layered architecture to promote separation of concerns and maintainability:

```
├── UI Layer (Swing Forms)         → NetBeans-generated GUI screens
├── Business Logic Layer           → Validation, business rules, stock logic
├── Data Access Layer (DAO/JDBC)   → Database queries and connections
└── Database Layer                 → PostgreSQL / Derby relational database
```

Object-oriented principles are demonstrated throughout:
- **Encapsulation** — model classes (Material, Supplier, Cleaner, Issuance) with private fields and controlled access
- **Inheritance** — shared base classes for common entity/user behavior
- **Polymorphism** — overridden methods across related model/service classes
- **Abstraction** — DAO interfaces abstracting database implementation details from business logic

---

## 👥 Team & Roles

This project was developed by a team of 5, divided across backend and frontend responsibilities:

| Role | Members | Responsibilities |
|---|---|---|
| **Backend Development** | Rivan Maritz, Jared Nicholas Swanepoel | Database design, JDBC connectivity, DAO classes, business logic, stock issuance rules, validation |
| **Frontend Development** | Tumelo Bapela, Jimmy Junior Baloyi, Tiisetso Keraetswe | Swing UI screens, NetBeans form design, navigation, dashboard, reports display, UI/UX consistency |

All members contributed to integration, testing, and are able to explain their respective contributions to the codebase.

---

## 🗄️ Database Design

The relational database includes the following core tables:

- `users` — staff accounts, roles, and credentials
- `materials` — cleaning materials, quantities, and reorder levels
- `suppliers` — supplier details and contact information
- `cleaners` — cleaner records and department assignments
- `issuances` — stock issuance transactions and history

Relationships, constraints, and normalization have been applied to ensure data integrity and prevent duplication.

---

## ⚙️ Setup & Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- NetBeans IDE (recommended for GUI Builder support)
- database will be decided later
- PostgreSQL (or Apache Derby / JavaDB) installed and running
- JDBC driver for your chosen database

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-org>/<repo-name>.git
   ```

2. **Open the project in NetBeans**
   - File → Open Project → select the cloned project folder

3. **Configure the database connection**
   - Create the database using the provided SQL schema (see `/database` folder)
   - Update your JDBC connection details in the configuration file (see `config.properties.example`)

4. **Build and run**
   - Clean and Build the project in NetBeans
   - Run the application from the main entry class

---

## 🔒 Environment Configuration

Database credentials are **not** committed to this repository. Copy the example configuration file and populate it with your local database details:

```bash
cp config.properties.example config.properties
```

---

## 📦 Project Structure

this is to be changed later:
```
├── src/
│   ├── ui/              # Swing forms and panels
│   ├── model/           # Entity classes (Material, Supplier, Cleaner, Issuance, User)
│   ├── dao/             # Data Access Objects (JDBC layer)
│   ├── service/         # Business logic and validation
│   └── util/            # Helper/utility classes
├── database/            # SQL schema and setup scripts
├── config.properties.example
├── .gitignore
└── README.md
```

---

## 📄 License

This project was developed for academic purposes as part of the Programming 3(7)(8)1 module at Belgium Campus iTversity.
