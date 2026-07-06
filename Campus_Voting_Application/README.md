# 🗳️ Campus Voting System (Spring Boot Backend API)

A secure and scalable Campus Voting System built using **Java, Spring Boot, Spring Security, JWT, Hibernate, and MySQL**. The system allows students to participate in campus elections while enabling administrators to manage elections, approve candidates, and publish results.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- Student Registration
- Secure Login with JWT
- Role-Based Access Control (ADMIN / STUDENT)
- BCrypt Password Encryption
- Spring Security Integration

### 👨‍🎓 Student Module
- Student registration using preloaded student records
- Secure login
- Candidate application

### 🗳️ Candidate Module
- Apply as a candidate
- Candidate approval by Admin
- Candidate rejection by Admin

### 🏛️ Election Module
- Create Election
- Start Election
- End Election
- Only one active election at a time

### 🗳️ Voting Module
- Cast vote securely
- One vote per student
- Vote only for approved candidates
- Vote only during an active election

### 📊 Result Module
- Live vote counting
- Winner calculation
- Election results

### 📚 API Documentation
- Swagger UI Integration

---

# 🛠️ Tech Stack

| Technology | Used |
|------------|------|
| Java | 22 |
| Spring Boot | 3.5.x |
| Spring Security | ✔ |
| JWT | ✔ |
| Spring Data JPA | ✔ |
| Hibernate | ✔ |
| MySQL | ✔ |
| Maven | ✔ |
| Swagger (OpenAPI) | ✔ |
| Git & GitHub | ✔ |

---

# 📁 Project Structure

```
src/main/java/com/asif/campusvoting

├── auth
├── candidate
├── common
├── election
├── result
├── security
├── student
└── vote
```

---

# 🏗️ Architecture

```
Client
   │
REST API
   │
Controllers
   │
Services
   │
Repositories
   │
MySQL Database
```

---

# 🔐 Authentication

The application uses **JWT (JSON Web Token)** for authentication.

### Login Flow

```
Student/Admin Login
        │
        ▼
Authentication
        │
        ▼
JWT Token Generated
        │
        ▼
Client Stores Token
        │
        ▼
Authorization Header
        │
        ▼
Protected APIs
```

---

# 📌 Main API Endpoints

## Authentication

| Method | Endpoint |
|--------|----------|
| POST | `/api/auth/register` |
| POST | `/api/auth/login` |

---

## Student

| Method | Endpoint |
|--------|----------|
| POST | `/api/students/upload` |

---

## Candidate

| Method | Endpoint |
|--------|----------|
| POST | `/api/candidates/apply` |
| GET | `/api/admin/candidates/pending` |
| PUT | `/api/admin/candidates/{id}/approve` |
| PUT | `/api/admin/candidates/{id}/reject` |

---

## Election

| Method | Endpoint |
|--------|----------|
| POST | `/api/admin/elections` |
| PUT | `/api/admin/elections/{id}/start` |
| PUT | `/api/admin/elections/{id}/end` |

---

## Vote

| Method | Endpoint |
|--------|----------|
| POST | `/api/votes` |

---

## Results

| Method | Endpoint |
|--------|----------|
| GET | `/api/admin/results` |
| GET | `/api/admin/results/winner` |

---

# ⚙️ Installation

## Clone Repository

```bash
git clone https://github.com/AsifSameer536/Campus_Voting_System.git
```

---

## Open Project

Open using **IntelliJ IDEA**

---

## Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/campus_voting
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

## Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📸 Screenshots

Add screenshots of:

- Swagger UI
- Login API
- Candidate Approval
- Vote API
- Results API

---

# 🔮 Future Enhancements

- Email Verification
- Forgot Password
- Refresh Token
- Docker Support
- Unit Testing
- Redis Cache
- React Frontend
- Cloud Deployment

---

# 👨‍💻 Author

**Asif Sameer**

- GitHub: https://github.com/AsifSameer536
- LinkedIn: *(www.linkedin.com/in/asif-sameer)*

---

# ⭐ If you found this project useful, consider giving it a star.