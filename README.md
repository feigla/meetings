# Dating App

## Технологический стек

Проект разработан с использованием следующих технологий:

- **Язык программирования:** Java 21
- **Фреймворки и библиотеки:** Spring Boot, Hibernate/JPA
- **Базы данных:** PostgreSQL, PostGIS, H2
- **Тестирование:** JUnit, Integration Tests
- **Коммуникация между микросервисами:** gRPC, Rest

## Архитектура приложения

Приложение построено на **микросервисной архитектуре** и состоит из 5 основных микросервисов:

1. **gateway-api** – шлюз API
2. **auth-service** – сервис аутентификации
3. **profile-service** – сервис управления профилями
4. **location-service** – сервис геолокации
5. **recommendation-service** – сервис рекомендаций

Дополнительно используется отдельная библиотека **proto** для хранения `.proto` файлов. Эти файлы компилируются в Java-код и используются в микросервисах для обеспечения gRPC-коммуникации между ними.

## Описание микросервисов

### 1. Gateway API
**gateway-api** — это центральная точка входа для всех запросов. Он перенаправляет их на соответствующие микросервисы и выполняет проверку **JWT-токенов** на валидность перед обработкой запросов.

### 2. Auth Service
**auth-service** отвечает за:
- Регистрацию пользователей
- Аутентификацию
- Выдачу JWT-токенов

При регистрации пользователя сервис отправляет дополнительный запрос в **profile-service** для создания профиля.

### 3. Location Service
**location-service** отвечает за:
- Хранение местоположения пользователей
- Поиск ближайших пользователей

### 4. Profile Service
**profile-service** используется для:
- Хранения профилей пользователей
- Хранения интересов пользователя

### 5. Recommendation Service
**recommendation-service** формирует список рекомендованных пользователей на основе:
- Географической близости (данные из **location-service**)
- Предпочтений пользователя (данные из **profile-service**)

## Запуск

Запуск приложения возможен через **Makefile**.  

Используется **Docker** для быстрого развертывания приложения.

### Настройка переменных окружения

В файл .env предоставлен возможный вариант названий переменных. 

Рекомендуется изменить AUTH_TOKEN, DB_PASSWORDS, DB_USERS.


### Сборка приложения
```sh
make mvn-build
```

### Запуск приложения
```sh
make start
```
