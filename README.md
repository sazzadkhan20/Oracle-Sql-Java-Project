# Bangladesh Railway E-Ticket System

A database-focused e-ticketing system for Bangladesh Railway built with Oracle 10g and Java Swing.

## 📋 About

This project is a comprehensive railway ticket booking and management system designed for Bangladesh Railway. The primary focus is on **database design and SQL implementation** using Oracle 10g, with a Java Swing desktop interface for user interaction.

## 🛠️ Technologies Used

- **Database**: Oracle 10g (Primary Focus)
- **Frontend**: Java Swing (Desktop GUI)
- **Language**: Java
- **JDBC Driver**: ojdbc14.jar
- **SQL**: Complex queries, procedures, triggers, views

## 🎯 System Features

### User Module
- User registration and login
- Search trains by route and date
- Check seat availability
- View booking history
- Cancel tickets
- Print e-tickets

### Admin Module
- Manage train schedules
- Add/update/delete trains
- Manage routes and stations
- View all bookings
- Generate reports
- Manage passenger information

### Database Features
- **Tables**: Trains, Routes, Stations, Passengers, Bookings, Payments, Users
- **Complex Queries**: Multi-table joins, subqueries, aggregate functions
- **Constraints**: Primary keys, foreign keys, check constraints

## 📂 Database Schema

### Main Tables

```sql
-- Trains Table
CREATE TABLE trains (
    train_id NUMBER PRIMARY KEY,
    train_name VARCHAR2(100),
    train_type VARCHAR2(50),
    total_seats NUMBER
);

-- Routes Table
CREATE TABLE routes (
    route_id NUMBER PRIMARY KEY,
    train_id NUMBER,
    source_station VARCHAR2(100),
    destination_station VARCHAR2(100),
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP,
    fare NUMBER(10,2),
    FOREIGN KEY (train_id) REFERENCES trains(train_id)
);

-- Passengers Table
CREATE TABLE passengers (
    passenger_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    nid VARCHAR2(20),
    phone VARCHAR2(15),
    email VARCHAR2(100)
);

-- Bookings Table
CREATE TABLE bookings (
    booking_id NUMBER PRIMARY KEY,
    passenger_id NUMBER,
    route_id NUMBER,
    booking_date DATE,
    journey_date DATE,
    seat_number VARCHAR2(10),
    status VARCHAR2(20),
    FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id),
    FOREIGN KEY (route_id) REFERENCES routes(route_id)
);

-- Payments Table
CREATE TABLE payments (
    payment_id NUMBER PRIMARY KEY,
    booking_id NUMBER,
    amount NUMBER(10,2),
    payment_date DATE,
    payment_method VARCHAR2(50),
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);
```

## 🚀 Getting Started

### Prerequisites
- Oracle 10g Database
- JDK 8 or higher
- Oracle JDBC Driver (ojdbc14.jar)
- NetBeans/Eclipse/IntelliJ IDEA

### Installation Steps

1. **Setup Oracle Database**
```sql
-- Run SQL scripts in order
@sql/01_create_tables.sql
@sql/02_insert_sample_data.sql
@sql/03_create_procedures.sql
@sql/04_create_triggers.sql
@sql/05_create_views.sql
```

2. **Configure Database Connection**
```java
// Update DBConnection.java
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String user = "railway_admin";
String password = "your_password";
```

3. **Add JDBC Driver**
- Add ojdbc14.jar to project libraries

4. **Run Application**
- Execute MainFrame.java

### Complex Query Example
```sql
-- Get available trains between stations
SELECT t.train_name, r.departure_time, r.arrival_time, 
       r.fare, r.available_seats
FROM trains t
JOIN routes r ON t.train_id = r.train_id
WHERE r.source_station = 'Dhaka'
  AND r.destination_station = 'Chittagong'
  AND r.journey_date = TO_DATE('2025-01-15', 'YYYY-MM-DD')
  AND r.available_seats > 0
ORDER BY r.departure_time;
```

## 🖥️ GUI Modules

- **Login/Registration Screen**
- **Main Dashboard**
- **Train Search Panel**
- **Booking Form**
- **Ticket Display**
- **Admin Panel**
- **Reports Module**

## 🎓 Database Concepts Implemented

- Entity-Relationship Design
- Normalization (3NF)
- Primary and Foreign Keys
- Complex SQL Queries (Joins, Subqueries)
- Sequences for Auto-increment
- Date/Time handling
- Aggregate Functions

## 📝 Sample Data

System includes sample data for:
- Popular Bangladesh Railway trains (Suborno Express, Turna Nishitha, etc.)
- Major stations (Dhaka, Chittagong, Sylhet, Rajshahi, Khulna)
- Sample routes and schedules
- Test passenger records

## 👨‍💻 Author

## 👥 Authors

| Name | GitHub Profile |
|------|----------------|
| Md. Sazzad Khan | [@sazzadkhan20](https://github.com/sazzadkhan20) |
| Saiful Islam Oni | [@saifulislamoni](https://github.com/saifulislamoni) |
| Md. Ibtihazzaman | [@Sadid19](https://github.com/Sadid19) |

---

**Project Type**: <small>Academic DBMS Project</small>  
**Institution**: <small>American International University-Bangladesh</small>  
**Course**: <small>Database Management System</small>

---

**Note**: This is an academic project focused on database design and SQL implementation using Oracle 10g. Primary emphasis is on backend database operations rather than frontend design.
