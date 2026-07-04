# Campus Voting System

A secure backend application for conducting college elections using Spring Boot.

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (In Progress)
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Postman

## Features

### Authentication
- Student Registration
- Student Login
- BCrypt Password Encryption
- Global Exception Handling

### Student Module
- Upload Students using CSV
- Validate Student Records

### Upcoming Features
- JWT Authentication
- Email Verification
- Candidate Management
- Election Management
- Voting System
- Result Generation

## Project Structure

```
src
 ├── auth
 ├── student
 ├── security
 ├── common
 └── config
```

## API Endpoints

### Authentication

- POST `/api/auth/register`
- POST `/api/auth/login`

### Student

- POST `/api/students/upload`

## Author

**Asif Sameer**
