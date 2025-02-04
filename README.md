# EventTracking
## Track. Plan. Connect. Your Events, Simplified!

EventTracking is an event management system that is used for app for creating events, managing events, and managing event-user interactions.It resembles various event management apps such as Eventbrite and Google calendar. The tech stack used for the application consists of Java, Spring Boot, PostgreSQL, Redis and Docker.

<p align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="Java" width="60"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="Spring Boot" width="60"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" alt="Redis" width="60"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" alt="Docker" width="60"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" alt="PostgreSQL" width="60"/>
</p>


## Table of Contents
1. [Installation](#installation)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Tech Stack](#-tech-stack)



## Installation


1. Before running the application make sure you have the following installed:
- Docker
- Docker-Compose

2. Clone the repository with the command:
```ruby
git clone https://github.com/cnbrkaydemir/EventTracking.git
```


3. Setup Environmental Variables:

Before running the application, you need to create a .env file and configure the database credentials.
* Create a .env file.

```ruby
touch .env
```
* Open the .env file and set the necessary variables:
```ruby
DB_USER=your_user_name
DB_PASSWORD=your_password
DB_URL_DOCKER=jdbc:postgresql://db:5432/database_name
DB_ENTRY=database_name
```

4. Running the Application with Docker Compose:

Once the .env file is ready, simply run:
```ruby
docker-compose up --build
```
To stop and remove the containers, run:

```ruby
docker-compose down
```

## Features
✨ Features

👤 User Management

✅ User Registration & Login – Users can sign up and log in securely.

✅ Role-Based Access Control – Users are assigned either Admin or User roles.

✅ JWT Authentication – Secure token-based authentication using Spring Security.

✅ Password Hashing – User passwords are encrypted with BCrypt before storage.

📅 Event Management

✅ Create Events – Admins can create new events with a title, description, and date.

✅ Event Participation – Users can join and leave events.

✅ Upcoming Events List – Users can view a list of future events.

✅ Viewing Event Details  – Each event can be viewed from user standpoint.

👥 User & Event Interaction

✅ Invite Users to Events – Admins can add users to events.

✅ Remove Users from Events – Admins can remove participants.


🔒 Security & Authorization

✅ JWT Token-Based Security – Every request is authenticated using JWT.

✅ Spring Security Middleware – Ensures API endpoints are protected.


📊 Database & Architecture

✅ PostgreSQL as RDBMS – Event and user data is managed in a relational database.

✅ Dockerized Deployment – Runs with Docker Compose for easy setup.

# Architecture

![alt text](https://i.imgur.com/uUDOOrq.png)

The layered architecture consisting of repository-service-controller layers is utilized for the project.
For handling event and user entities and serving third party users all the layers work synchronously.
In this architecture repository layer is an ORM tool that maps database entities to POJOs which allows manipulation of database entities.
Service layer associated with models act as the brain of the whole process, creating the business logic required for the application with the help of the repository layer.
Whereas controller layer is the layer that handles HTTP requests coming to the application from different clients with the help of the service layer. 

## 🛠️ Tech Stack

- **Spring Boot**: Provides Inversion of Control (IoC) and Dependency Injection for building scalable applications.
- **Spring Data JPA**: Enables ORM (Object-Relational Mapping) and simplifies database interactions.
- **Spring Security**: Ensures secure API access using JWT-based authentication and password encoding with BCrypt.
- **PostgreSQL**: The relational database management system (RDBMS) used for storing users, events, and authorities.
- **Redis**: Caches database queries to enhance performance and reduce load on PostgreSQL.
- **Docker**: Containerizes the application for easy deployment and scalability using Docker images and Docker Compose.  
