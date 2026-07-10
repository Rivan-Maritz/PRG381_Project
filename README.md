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
- **Encapsulation** - model classes (Material, Supplier, Cleaner, Issuance) with private fields and controlled access
- **Inheritance** - shared base classes for common entity/user behavior
- **Polymorphism** - overridden methods across related model/service classes
- **Abstraction** - DAO interfaces abstracting database implementation details from business logic

---

## 👥 Team & Roles

This project was developed by a team of 5, divided across backend and frontend responsibilities:

| Role | Members | Responsibilities |
|---|---|---|
| **Backend Development** | Rivan Maritz, Jared Nicholas Swanepoel | Database design, JDBC connectivity, DAO classes, business logic, stock issuance rules, validation |
| **Frontend Development** | Tumelo Bapela, Jimmy Junior Baloyi, Tiisetso Keraetswe | Swing UI screens, NetBeans form design, navigation, dashboard, reports display, UI/UX consistency |

All members contributed to integration, testing, and are able to explain their respective contributions to the codebase.

---

## 🗄️ Database Setup (Run This Locally)
 
This project uses **PostgreSQL**. The database itself is **not** stored in this repository - instead, everyone runs the same `schema.sql` script locally to build an identical database with matching test data. Follow these steps exactly to get set up.
 
### 1. Install PostgreSQL
Download and install from [postgresql.org/download](https://www.postgresql.org/download/). During setup:
- Set a password for the `postgres` superuser and **remember it**
- Keep the default port `5432`
- Make sure **pgAdmin 4** and **Command Line Tools** are included in the install
### 2. Create the project database
Open a terminal (Git Bash, Command Prompt, or pgAdmin's Query Tool) and run:
```bash
psql -U postgres
```
Then, inside the `psql` prompt:
```sql
CREATE DATABASE cleaning_inventory_db;
CREATE USER cleaninv_user WITH PASSWORD 'yourpassword';
GRANT ALL PRIVILEGES ON DATABASE cleaning_inventory_db TO cleaninv_user;
\q
```
> Replace `'yourpassword'` with a password of your choice - just remember it, you'll need it in Step 4.
 
### 3. Clone the repo and locate the schema file
```bash
git clone https://github.com/Rivan-Maritz/PRG381_Project.git
cd PRG381_Project/database
```
 
### 4. Run the schema script
This creates all tables, relationships, constraints, and inserts test data (5 users, 10 records in every other table) in one go:
```bash
psql -U cleaninv_user -d cleaning_inventory_db -f schema.sql
```
Enter the password you set in Step 2 when prompted.
 
### 5. Verify it worked
```bash
psql -U cleaninv_user -d cleaning_inventory_db
```
Then inside `psql`:
```sql
\dt
```
You should see 5 tables listed: `users`, `cleaners`, `suppliers`, `materials`, `stockissuance`.
 
Check the test data loaded correctly:
```sql
SELECT * FROM users;
```
Type `\q` to exit.
 
### 6. Configure your local connection in the app
Copy the example config file and fill in your own credentials:
```bash
cp config.properties.example config.properties
```
Edit `config.properties` with:
```
db.url=jdbc:postgresql://localhost:5432/cleaning_inventory_db
db.username=cleaninv_user
db.password=yourpassword
```
> ⚠️ `config.properties` is gitignored — never commit real credentials. Only `config.properties.example` should be pushed to the repo.

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

This project was developed for academic purposes as part of the Programming 381 module at Belgium Campus iTversity.
