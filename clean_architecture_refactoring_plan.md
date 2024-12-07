# Clean Architecture Refactoring Plan for Cooking Module

## Current Structure (Hexagonal Architecture)
- **Web Layer**: CookingController (Adapter)
- **Application Layer**: CookingService (Port), CookingServiceImpl (Implementation)
- **Domain Layer**: Cooking, CookingEntity
- **Infrastructure Layer**: CookingRepository (Adapter)

## Clean Architecture Structure
1. **Entities** (Enterprise Business Rules)
   - Will contain core business entities
   - Move domain models here

2. **Use Cases** (Application Business Rules)
   - Will contain business logic
   - Service implementations will move here
   - Will define repository interfaces

3. **Interface Adapters**
   - Controllers
   - Repository implementations
   - Data models and mappers

4. **Frameworks & Drivers**
   - External frameworks
   - Database implementations
   - Web frameworks

## Refactoring Steps
1. Create new package structure
2. Move and refactor domain entities
3. Create use case interfaces and implementations
4. Adjust interface adapters
5. Update dependency injection
6. Clean up and remove old structure

## New Package Structure
```
com.snackbar.cooking
├── domain (entities)
├── usecase
│   ├── port
│   └── impl
├── adapter
│   ├── web
│   ├── persistence
│   └── mapper
└── infrastructure
```