# Medieval Market API
# RM556972 GABRIEL DUARTE
# RM556089 VINICIUS SOUZA

A RESTful API for a medieval fantasy marketplace built with Spring Boot.

## Overview

This API provides a complete backend for managing characters and items in a medieval fantasy setting. It includes:

- **Character API**: CRUD operations for managing characters with validation for name, class, level, and coins.
- **Item API**: Full CRUD functionality for magical items with owner relationship to characters.
- **Search API**: Advanced filtering capabilities for both characters and items.


## API Endpoints

### Character API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/characters` | Get all characters (paginated) |
| GET | `/api/characters/{id}` | Get character by ID |
| POST | `/api/characters` | Create a new character |
| PUT | `/api/characters/{id}` | Update an existing character |
| DELETE | `/api/characters/{id}` | Delete a character |
| GET | `/api/characters/search/name?name={name}` | Search characters by name |
| GET | `/api/characters/search/class?characterClass={class}` | Search characters by class |

### Item API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/items` | Get all items (paginated) |
| GET | `/api/items/{id}` | Get item by ID |
| POST | `/api/items` | Create a new item |
| PUT | `/api/items/{id}` | Update an existing item |
| DELETE | `/api/items/{id}` | Delete an item |
| GET | `/api/items/search/name?name={name}` | Search items by name |
| GET | `/api/items/search/type?type={type}` | Search items by type |
| GET | `/api/items/search/rarity?rarity={rarity}` | Search items by rarity |
| GET | `/api/items/search/price?minPrice={min}&maxPrice={max}` | Search items by price range |
| GET | `/api/items/owner/{ownerId}` | Get all items owned by a character |

## Data Models

### Character

- **id**: Long (auto-generated)
- **name**: String (required)
- **characterClass**: Enum (WARRIOR, MAGE, ARCHER)
- **level**: int (1-99)
- **coins**: int (≥ 0)
- **items**: List of Items (one-to-many relationship)

### Item

- **id**: Long (auto-generated)
- **name**: String (required)
- **type**: Enum (WEAPON, ARMOR, POTION, ACCESSORY)
- **rarity**: Enum (COMMON, RARE, EPIC, LEGENDARY)
- **price**: int (≥ 0)
- **owner**: Character (many-to-one relationship)

## Getting Started

1. Clone the repository
2. Build the project: `mvn clean install`
3. Run the application: `mvn spring-boot:run`
4. Access the H2 console at: `http://localhost:8080/h2-console`
5. API will be available at: `http://localhost:8080/api/`

## Technologies Used

- Java 17
- Spring Boot 3.1.0
- Spring Data JPA
- H2 Database
- Lombok
- Maven
