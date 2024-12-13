# Clean Architecture Implementation for Cooking Module

This module follows Clean Architecture principles with the following structure:

## Core Layer
- `domain/entities`: Contains the business entities (Order)
- `domain/repositories`: Contains repository interfaces
- `usecases`: Contains use case interfaces and implementations

## Infrastructure Layer
- `persistence`: Contains repository implementations

## Interfaces Layer
- `rest`: Contains REST controllers

This architecture ensures:
1. Independence of frameworks
2. Testability
3. Independence of UI
4. Independence of Database
5. Independence of any external agency

The dependencies point inward, with the domain layer at the center having no dependencies on outer layers.