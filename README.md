# Squirrel Tracker

A basic Spring Boot + PostgreSQL backend for experimenting with a realistic setup, Flyway migrations, Docker, and database management.

## Overview

- CRUD operations for squirrels, items, stashes, and stash lines
- Database migrations using Flyway
- Basic caching with Spring
- Global CORS configuration
- Docker setup (app + db)

## Tools

- Java
- Spring Boot
- PostgreSQL
- Flyway
- Swagger
- Docker

## Notes

- Back-end only
- Built for education and practice

## Run with Docker

1. Clone the repo: `git clone https://github.com/Josiah4191/squirrel-backend.git`
2. Build and start the containers: `docker compose up --build -d`
3. Access the app: 
   - API: `http://localhost:8080`
   - Database: `localhost:5432`
   - Swagger: `http://localhost:8080/swagger-ui/index.html#/`

## API Summary

### Stash Controller

| Method | Endpoint                         | Description                                   |
|--------|----------------------------------|-----------------------------------------------|
| GET    | /stashes/{stash_id}              | Get a stash by id                             |
| PUT    | /stashes/{stash_id}              | Update a stash location                       |
| POST   | /stashes/{stash_id}              | Add an item and quantity to a stash           |
| DELETE | /stashes/{stash_id}              | Delete a stash by id                          |
| GET    | /squirrels/{squirrel_id}/stashes | Get all stashes for a squirrel by squirrel id |
| POST   | /squirrels/{squirrel_id}/stashes | Create a new stash for a squirrel             |
| GET    | /stashes/{stash_id}/items        | Get all the items in a stash by stash id      |


### Squirrel Controller

| Method | Endpoint                       | Description                                 |
|--------|--------------------------------|---------------------------------------------|
| GET    | /squirrels/{squirrel_id}       | Get a squirrel by id                        |
| PUT    | /squirrels/{squirrel_id}       | Update a squirrel name by id                |
| DELETE | /squirrels/{squirrel_id}       | Delete a squirrel by id                     |
| GET    | /squirrels                     | Get all squirrels                           |
| POST   | /squirrels                     | Create a squirrel                           |
| GET    | /squirrels/{squirrel_id}/items | Get all items for a squirrel by squirrel id |


### Item Controller

| Method | Endpoint         | Description                               |
|--------|------------------|-------------------------------------------|
| GET    | /items/{item_id} | Get item by id                            |
| PUT    | /items/{item_id} | Update an item name and description by id |
| DELETE | /items/{item_id} | Delete an item by id                      |
| GET    | /items           | Get all items                             |
| POST   | /items           | Create a new item                         |
