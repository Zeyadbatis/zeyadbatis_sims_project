# SIMS – Student Information Management System

A desktop Java Swing application for managing student and staff records, backed by an embedded Apache Derby database. Staff members log in to register, search for, and update student records, register new staff, and change their password.

## Features

- **Staff login** – authenticate against staff credentials stored in the database
- **Register new student** – add a student's name, email, major, and gender
- **Search students** – look up students by any combination of first name, last name, email, major, or gender
- **Update student** – edit an existing student's email or major
- **Register new staff** – add a new staff user account
- **Change password** – update the logged-in staff member's password

## Tech Stack

- **Java 11**, Swing (GUI)
- **Apache Derby 10.14.2.0** (embedded database, bundled in `lib/`)
- **Ant** build, managed as a **NetBeans** Java (J2SE) project

## Project Structure

```
src/
├── Main/
│   ├── Main.java            # Application entry point
│   ├── Panel.java           # Main Swing UI (all screens/panels)
│   ├── Person.java          # Abstract base class (firstName, lastName)
│   ├── Student.java         # Student model
│   ├── Staff.java           # Staff model
│   ├── RegisterStudent.java # Insert new student records
│   ├── SearchStudent.java   # Dynamic student search queries
│   ├── UpdateStudent.java   # Update student records
│   └── StaffLogic.java      # Staff login, registration, password logic
└── DataBase/
    ├── DBManager.java       # Derby connection, queries, updates
    └── CreateTable.java     # Creates Student/Staff tables on first run

resources/
├── student.txt              # Sample student data
├── staff.txt                # Sample staff data
└── aut.png                  # UI image asset

lib/                         # Bundled Derby driver jars
dist/                        # Prebuilt runnable JAR (SIMS_project1_Zeyad.jar)
```

## Getting Started

### Prerequisites

- JDK 11 or later
- (Optional) NetBeans IDE, for the easiest way to open/build/run the project

### Run from NetBeans

1. Open the project folder in NetBeans (`File → Open Project`).
2. Run the project (`Main.java` is the entry point, set as `main.class` in `nbproject/project.properties`).

### Run from the command line

Build with Ant (from the project root, where `build.xml` lives):

```bash
ant jar
java -jar dist/SIMS_project1_Zeyad.jar
```

Or, using the prebuilt jar already in `dist/`:

```bash
java -jar dist/SIMS_project1_Zeyad.jar
```

The Derby driver jars needed at runtime are on the classpath via the JAR manifest, so no extra `-cp` flag is required.

### Database

The app uses an **embedded** Derby database — no separate database server is required. On first run, `DBManager` connects to (and creates, if missing) a Derby database named `projectD_Ebd` in the working directory, and `CreateTable` creates the `Student` and `Staff` tables automatically if they don't already exist.

> **Note:** `DataBase/DBManager.java` currently has a hardcoded database username/password. Since Derby's embedded mode runs in-process and isn't exposed over the network, this isn't a real access-control risk, but it's worth moving to a config file or environment variables if the project grows or the credentials are ever reused elsewhere.

## Known Issues

- `CreateTable.createStaffTable()` is missing commas between the `Username`, `Password`, `FirstName`, and `LastName` column definitions in its `CREATE TABLE` SQL, which will cause that statement to fail — the `Staff` table generation needs a fix.
- `DBManager` lowercases stored values (names, email, major) on insert; searches should account for case when comparing against user-entered input.

## Author

Zeyad
