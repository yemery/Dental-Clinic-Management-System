# Dental Clinic Management System

## Description
This project is a desktop application developed in **Java** with **Java Swing** for the graphical user interface and **JSON files** for data storage. It is designed to streamline and simplify the management of dental clinics, providing a comprehensive solution for patient records, appointments, consultations, invoices, and prescriptions. The application is tailored for small clinics, offering an affordable and efficient system to computerize their operations.

## Features
- **Patient Management**: Record and manage patient details, including medical history.
- **Appointment Scheduling**: Plan, modify, and track appointments with status updates.
- **Consultations Management**: Record consultation details and generate medical certificates.
- **Invoice Management**: Create and manage invoices, including payment tracking.
- **Prescription Management**: Manage prescriptions and associated medications.
- **User Roles**: Authentication system with role-based access for dentists and assistants.
- **Financial Management** , **Prescription Management** , **Medical Acts and Interventions** , **Medical Acts and Interventions** ...
- **Data Storage**: JSON files for storing patient records, appointments, and other data.

## Tools and Technologies
- **Java 21+**: Core programming language.
- **Java Swing**: For creating a user-friendly graphical user interface.
- **Jackson Library**:
    - **Databind**: Serialization and deserialization of JSON data.
    - **Annotations**: Customizing JSON processing.
    - **Core**: Low-level JSON parsing and generation.
- **IntelliJ IDEA**: Integrated Development Environment (IDE).
- **Git & GitHub**: Version control and project hosting.

## Architecture
The application follows a **three-tier architecture**:
1. **Presentation Layer**: User interface created with Swing.
2. **Business Logic Layer**: Controllers and services manage the application's logic.
3. **Data Layer**: JSON-based storage accessed through DAO classes.

## Limitations
- **Data Storage**: JSON files are used instead of a relational database, limiting advanced data management.
- **Security**: Absence of encryption for sensitive data.
- **Accessibility**: Desktop-based solution limits usage to compatible systems.

## Future Improvements
- Integrating a relational database (e.g., MySQL, PostgreSQL) for enhanced data handling.
- Implementing data encryption for improved security.
- Developing a web or mobile version to increase accessibility.

## Installation
1. Clone the repository:
   ```bash
   git@github.com:yemery/java-4iir-s1.git
2. Open the project in IntelliJ IDEA or another Java IDE.
3. Ensure Java 21+ is installed on your system.
4. Run the application via the main class.
## Usage

1. Start the application.
2. Log in using the appropriate credentials (Doctor or Assistant).
3. Use the menu to navigate between patient management, appointment scheduling, and other features.
4. Login credentials:
    - **Doctor**: Username: `DOCTOR` | Password: `DOCTOR`
    - **Assistant**: Username: `ASSISTANT` | Password: `ASSISTANT`