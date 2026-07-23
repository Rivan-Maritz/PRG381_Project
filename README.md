# University Cleaning Inventory & Issuance System

A Java desktop application developed for **Programming 3(7)(8)1** at Belgium Campus iTversity, implementing **Track B** of the group project specification: a full-featured inventory and issuance management system for university cleaning materials, suppliers, and cleaners.

---

## 📋 Overview

The system enables university staff to securely manage cleaning materials, suppliers, cleaners, and stock issuances through a desktop interface. It demonstrates core Java, object-oriented design, GUI development, layered architecture, and CRUD operations against a relational database.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Java Swing** | Graphical user interface |
| **Core Java / OOP** | Inheritance, Encapsulation, Polymorphism, Abstraction |
| **JDBC** | Database connectivity |
| **PostgreSQL** | Relational database |
| **jBCrypt** | Password hashing |
| **Maven** | Build and dependency management |

---

## 🏗️ Architecture

The application is split into four layers:

```
ui/            → Swing panels and dialogs. Gather input, call a BusinessLayer
                 service, render the result or show a caught BusinessException.
BusinessLayer/ → One service per entity (UsersService, MaterialsService,
                 SuppliersService, CleanersService, StockIssuanceService,
                 ReportsService). All validation, business rules, and
                 orchestration across DAOs live here. Throws
                 BusinessLayer/exceptions/BusinessException with a message
                 that's already safe to show the user directly.
                 AccessControlService is the one place that decides which
                 role can open which screen.
DAO/           → One class per entity. Pure JDBC persistence - no
                 validation or business rules. Every method opens its own
                 connection via Util/DBConnection.
model/         → Plain entity classes. personModel is an abstract base
                 (getID/getName/getRole) extended by cleanerModel,
                 suppliersModel, and usersModel - the project's OOP
                 inheritance/polymorphism demonstration. materialsModel and
                 stockissuanceModel stand alone since they aren't "people".
Util/          → DBConnection (JDBC connection factory) and PasswordUtil
                 (bcrypt hashing/verification, via jBCrypt).
```

The UI never talks to a DAO directly - every panel goes through a service. This keeps validation and business rules in one place instead of duplicated per screen.

---

## ✅ Implemented Features

- **Authentication** - login, registration (with validation: required fields, password length/match, valid email format, duplicate username/email checks), logout, and session tracking (the logged-in user's ID and role are needed for Stock Issuance's `IssuedBy` column and for role-based access).
- **Role-based access control** - `AccessControlService` is the single source of truth for what each role can open:
  - **Supervisor** - full access to every screen.
  - **Storekeeper** - Dashboard, Cleaners, Stock Issuance, and Reports only. Materials and Suppliers management are off-limits.
  Enforced in two places: `MainFrame.showPanel()` blocks the navigation outright (defense in depth, so it's not just a hidden button), and `DashboardPanel` greys out the Manage Materials / Manage Suppliers buttons for a Storekeeper so the restriction is visible before they even try.
- **Dashboard** - live counts (total materials, low-stock items, total cleaners, total issuances) pulled fresh from the database on every visit.
- **Materials Management** - full CRUD, search by name, Supplier dropdown and Type field (added by hand since the original form only had Name/Stock), reorder level tracking.
- **Suppliers Management** - full CRUD with all contact fields.
- **Cleaners Management** - full CRUD.
- **Stock Issuance** - checks live stock before issuing, blocks issuing more than available, deducts stock, records the transaction, shows a running issuance log.
- **Reports** - Inventory, Low-Stock, Issuance History, and Material Usage (aggregated quantity issued per material).
- **Validation & error handling** - every service throws `BusinessException` with a message that's shown directly in a dialog - no raw stack traces reach the user.

---

## 📦 Project Structure

```
PRG381_Project/
├── pom.xml
├── schema.sql                          # Run this against PostgreSQL to set up the DB
├── src/main/java/com/mycompany/prg381_project/
│   ├── PRG381_Project.java             # main() - launches MainFrame
│   ├── ui/                             # Swing panels + MainFrame + RegisterDialog
│   ├── BusinessLayer/                  # Services + exceptions/BusinessException
│   ├── DAO/                            # JDBC persistence, one class per entity
│   ├── model/                          # Entity classes
│   └── Util/                           # DBConnection, PasswordUtil
```

---

## 🗄️ Database Setup

1. **Install PostgreSQL** if you don't have it, and make sure it's running on the default port `5432`.
2. **Create the database:**
   ```bash
   psql -U postgres
   ```
   ```sql
   CREATE DATABASE cleaning_inventory_db;
   \q
   ```
3. **Run the schema** (creates all 5 tables, constraints, and seeds test data):
   ```bash
   psql -U postgres -d cleaning_inventory_db -f schema.sql
   ```
4. **Set your credentials.** Open `Util/DBConnection.java` and edit the `URL`, `User`, and `Password` constants to match your local Postgres setup:
   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/cleaning_inventory_db";
   private static final String User = "postgres";
   private static final String Password = "yourpassword";
   ```

---

## ▶️ Running the App

**Via Maven:**
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.mycompany.prg381_project.PRG381_Project"
```

**Via NetBeans:** open the project folder, right-click `PRG381_Project.java` → Run File.

### Test login

`schema.sql` seeds 5 accounts, all using the password **`Password123`**:

| Username | Role |
|---|---|
| jdoe | Supervisor |
| mkhumalo | Storekeeper |
| tnkosi | Storekeeper |
| rmaritz | Supervisor |
| lventer | Storekeeper |

Or use the **Register** button on the login screen to create a new account.

---

## 👥 Team & Roles

| Role | Members | Responsibilities |
|---|---|---|
| **Backend Development** | Rivan Maritz, Jared Nicholas Swanepoel, Michiel Coenraad TerBlanche | Database design, JDBC connectivity, DAO classes, business logic, stock issuance rules, validation |
| **Frontend Development** | Tumelo Bapela, Jimmy Junior Baloyi, Tiisetso Keraetswe | Swing UI screens, NetBeans form design, navigation, dashboard, reports display, UI/UX consistency |

All members contributed to integration, testing, and are able to explain their respective contributions to the codebase.

---

## 📄 License

This project was developed for academic purposes as part of the Programming 381 module at Belgium Campus iTversity.
