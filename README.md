# Product Catalog API
Simple REST API for product catalog management built with Spring Boot.

## Overview
This project was created as a technical assessment task.

This is a simple API that performs basic CRUD operations on products in the database. 

## Tech Stack

- Java 21
- Spring Boot 3.5.13
- Liquibase
- PostgreSQL 17.5
- Maven

## Features

- Create product and producer
- Update product and producer
- Delete product and producer
- Get all products and producers
- Get all products with specified attributes
- Search products by their name, producer name or value of attribute

## Data Model

The system has following entities:

- Producer (1:N Products)
- Product (1:N ProductAttributes)
- Attribute (1:N ProductAttributes)
- ProductAttribute (key-value structure)

## API Endpoints

### Products
- GET /products
- GET /products/filtered
- GET /products/search
- POST /products
- PUT /products/{id}
- DELETE /products/{id}
### Producers
- GET /producers/{id}
- GET /producers
- POST /producers
- PUT /producers
- DELETE /producers

## Design Decisions

- Used DTO and mapper layer to separate API from persistence
- Implemented custom JPQL queries for filtering and searching products
- Designed flexible attribute system using key-value pairs

## How to run

### Requirements

- Java 21
- Maven

### Clone Repository

git clone https://github.com/KubaBrzezinskiDev/product-catalog-api.git

cd product-catalog-api

### Run application 

mvn spring-boot:run

### Application URL

http://localhost:8080

### Database

Configure PostgreSQL connection in application.properties



