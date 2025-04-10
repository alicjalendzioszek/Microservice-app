# Aplikacja Webowa w Architekturze Mikroserwisowej

## Opis projektu

Projekt polegał na stworzeniu aplikacji webowej w architekturze mikroserwisowej. Aplikacja została zaprojektowana z wykorzystaniem **Spring Boot** na backendzie oraz **Angular** na frontendzie. System został podzielony na niezależne mikroserwisy, które zostały skonteneryzowane za pomocą **Dockera** i zarządzane przez **Docker Compose**. Do komunikacji między mikroserwisami oraz zarządzania ruchem wykorzystano **API Gateway**, co pozwoliło na centralne zarządzanie i kontrolowanie żądań.

## Technologie

- **Backend:** Spring Boot
- **Frontend:** Angular
- **Mikroserwisy:** Spring Boot, z wykorzystaniem REST API
- **Baza danych:** MySQL
- **Konteneryzacja:** Docker, Docker Compose
- **API Gateway:** Spring Cloud Gateway

## Informacje

- **Docker** – do konteneryzacji aplikacji
- **Docker Compose** – do zarządzania kontenerami
- **Java 21** – dla backendu
- **Node.js** oraz **npm** – dla frontendowej części aplikacji
- **Maven** – do budowania aplikacji Spring Boot

