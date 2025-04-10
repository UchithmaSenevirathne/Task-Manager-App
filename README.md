# Task Manager Web Application
This is a full-stack task manager application built using Angular for the front end and Spring Boot for the back end. Users can manage tasks by performing CRUD operations (Create, Read, Update, Delete), with tasks stored in a MySQL database.

## Features
### Backend (Spring Boot)
* RESTful API that handles tasks.

* #### Operations:

  * Create, read, update, and delete tasks via the API.

  * Store tasks in MySQL.

  * Use Spring Data JPA for database interaction.
 
### Frontend (Angular)
* Task List Page: Displays all tasks in a table or card format with basic filtering by status (e.g., TO_DO, IN_PROGRESS, DONE).

* Task Form Page: Allows adding or editing tasks using Reactive or Template-driven forms.

* Task Details Page: Shows detailed information about a selected task.

### Authentication
* JWT-based Authentication for secure API access.

* Protects routes that require a valid JWT token for access.

### Dockerization
* Docker Compose setup for backend, frontend, and MySQL.

## Technology Stack
Frontend: Angular, Angular Material(Icons), Bootstrap

Backend: Spring Boot, Spring Data JPA, Spring Security, JWT

Database: MySQL

Authentication: JWT

Containerization: Docker, Docker Compose

## Installation & Setup
### Running the Backend
1. Clone the repository:
    * git clone https://github.com/UchithmaSenevirathne/Task-Manager-App.git
    * cd Task-Manager-App
    
2. Navigate to the backend directory:
    * cd backend
    
3. Build the project:
    * mvn clean install

4. Configure the database connection in src/main/resources/application.properties:
    * spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
    * spring.datasource.username=your_username
    * spring.datasource.password=your_password

5. Run the Backend:
    * mvn spring-boot:run

### Running the Frontend
1. Navigate to the frontend directory:
    * cd frontend

2. Install the dependencies:
    * npm install

3. Run the Frontend:
    * ng serve
  
## Database Setup
* Ensure you have MySQL installed and running.

* Create a new database named task_manager:
  * CREATE DATABASE task_manager;

## API Documentation
https://documenter.getpostman.com/view/35385637/2sB2cXAN4h

## Application UI
![img1](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20150341.png)
![img2](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20150411.png)
![img3](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20150631.png)
![img4](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20154800.png)
![img5](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20154837.png)
![img6](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20155034.png)
![img7](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20155110.png)
![img8](https://github.com/UchithmaSenevirathne/Task-Manager-App/blob/main/Screenshots/Screenshot%202025-04-10%20165211.png)
