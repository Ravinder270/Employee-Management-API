# Project Name

## Employee Management 

## Description
This backend project houses a Spring Boot-based REST API for efficient employee management, offering standard CRUD operations alongside advanced features like retrieving Nth-level managers. The API, equipped with Jakarta Bean Validation, ensures robust input validation and provides detailed error responses. Its support for pagination and sorting enhances scalability, allowing users to tailor data retrieval.
## Table of Contents
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Database Setup](#database-setup)
- [Running the Project](#running-the-project)
- [API Documentation](#api-documentation)
  - [1. Get All Employees](#1-get-all-employees)
  - [2. Get Employee by ID](#2-get-employee-by-id )
  - [3. Update Employee by ID](#3-update-employee-by-id)
  - [4. Add Employee](#4-add-employee)
  - [5. Delete Employee by ID](#5-delete-employee-by-id)
  - [6. Get nth Level Manager of an Employee](#6-get-nth-level-manager-of-an-employee)
  - [7. Get Employees with Pagination and Sorting](#7-get-employees-with-pagination-and-sorting)

## Getting Started

### Prerequisites
- Java (JDK) installed
- Maven installed
- MongoDB installed (if applicable)


## Database Setup

- Update application.properties:
  `spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>/<database>`
- Replace the following placeholders with your actual MongoDB connection details:

  - `<username>`: Your MongoDB username.
  - `<password>`: Your MongoDB password.
  - `<cluster>`: Your MongoDB cluster URI (e.g., cluster0.dyqempr.mongodb.net).
  - `<database>`: Your MongoDB database name.

## Running the Project
1. Run the application: EmployeeManagementApplication from path GlobalGroupware\employee-management\src\main\java\com\global_groupware\employeemanagement\EmployeeManagementApplication.java
2. The application will be accessible at: `http://localhost:8080/api/v1/employees` (adjust the port if necessary)


## API Documentation

### 1. Get All Employees
- **Request Type**: GET 
- **Endpoint**: `employees`
- **Description**: Retrieve a list of all employees.
- **Example Request**: `http://localhost:8080/api/v1/employees`

#### Example Response (Success):

```json
[
  {
    "employeeId": "employee123",
    "employeeName": "John Doe",
    "phoneNumber": "123-456-7890",
    "email": "john.doe@example.com",
    "reportsTo": "managerId",
    "profileImage": "http://example.com/profile.jpg"
  },
  // Additional employee objects...
]

```

### 2. Get Employee by ID
- **Request Type**: GET
- **Endpoint**: `/employees/{employeeId}`
- **Description**: Retrieve an employee by their ID.
- **Parameters**:
  - `employeeId` (path): ID of the employee.
- **Example Request**: `http://localhost:8080/api/v1/employees/{employeeId}`

#### Example Response (Success):

```json
{
  "employeeId": "employee123",
  "employeeName": "John Doe",
  "phoneNumber": "123-456-7890",
  "email": "john.doe@example.com",
  "reportsTo": "managerId",
  "profileImage": "http://example.com/profile.jpg"
}
```

### 3. Update Employee by ID
- **Request Type**: PUT
- **Endpoint**: `/employees/{employeeId}`
- **Description**: Update details of an employee by their ID.
- **Parameters**:
  - `employeeId` (path): ID of the employee.
- **Request Body**: JSON representing the updated employee details.
- **Example Request**: `http://localhost:8080/api/v1/employees/{employeeId}`

#### Example Request Body:

```json
{
  "employeeName": "Updated Name",
  "phoneNumber": "987-654-3210",
  "email": "updated.name@example.com",
  "reportsTo": "updatedManagerId",
  "profileImage": "http://example.com/updated_profile.jpg"
}

```
### 4. Add Employee
- **Request Type**: POST
- **Endpoint**: `/employees`
- **Description**: Add a new employee to the system.
- **Request Body**:
  - `employee` (body, required): The employee details to be added.
- **Example Request**:

  ```json
  {
    "employeeName": "John Doe",
    "phoneNumber": "123-456-7890",
    "email": "john.doe@example.com",
    "reportsTo": "managerId",
    "profileImage": "http://example.com/profile.jpg"
  }
  ```
#### Example Response (Success):

```json
{
  "employeeId": "employee123"
}

```
  

### 5. Delete Employee by ID
- **Request Type**: DELETE
- **Endpoint**: `/employees/{employeeId}`
- **Description**: Delete an employee by their ID.
- **Parameters**:
  - `employeeId` (path): ID of the employee.
- **Example Request**: `http://localhost:8080/api/v1/employees/{employeeId}`

#### Example Response (Success):

```json
{} // An empty JSON response for a successful deletion
```

### 6. Get nth Level Manager of an Employee
- **Request Type**: GET
- **Endpoint**: `/manager`
- **Description**: Retrieve the nth level manager of an employee.
- **Parameters**:
  - `employeeId` (query): ID of the employee.
  - `level` (query): The level of the manager to retrieve.
- **Example Request**: `http://localhost:8080/api/v1/employees/manager?employeeId=employeeId&level=2`

#### Example Response (Success):

```json
{
  "employeeId": "managerId",
  "employeeName": "Manager Name",
  "phoneNumber": "987-654-3210",
  "email": "manager.name@example.com",
  "reportsTo": "grandManagerId",
  "profileImage": "http://example.com/manager_profile.jpg"
}
```

### 7. Get Employees with Pagination and Sorting
- **Request Type**: GET
- **Endpoint**: `/employees`
- **Description**: Retrieve a paginated and sorted list of employees.
- **Parameters**:
  - `page` (query, optional): Page number (default: 1)
  - `pageSize` (query, optional): Page size (default: 10)
  - `sortBy` (query, optional): Sorting criteria (default: "id")
- **Example Request**: `http://localhost:8080/api/v1/employees?page=1&pageSize=10&sortBy=id`

#### Example Response (Success):

```json
[
  {
    "employeeId": "employee123",
    "employeeName": "John Doe",
    "phoneNumber": "123-456-7890",
    "email": "john.doe@example.com",
    "reportsTo": "managerId",
    "profileImage": "http://example.com/profile.jpg"
  },
  // Additional employee objects...
]
```

