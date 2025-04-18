# Dating App

## Технологический стек

Проект разработан с использованием следующих технологий:

- **Язык программирования:** Java 21
- **Фреймворки и библиотеки:** Spring Boot, Spring Security, Hibernate/JPA
- **Базы данных:** PostgreSQL, PostGIS, H2, Redis
- **Тестирование:** JUnit, Integration Tests
- **Коммуникация между микросервисами:** gRPC, Rest, Kafka
- **Развертывание проекта:** Docker, Makefile

## Архитектура приложения

Приложение построено на **микросервисной архитектуре** и состоит из таких микросервисов:

1. **gateway-api** – шлюз API
2. **auth-service** – сервис аутентификации
3. **profile-service** – сервис управления профилями
4. **location-service** – сервис геолокации
5. **recommendation-service** – сервис рекомендаций
6. **matcher-service** – мэтчинг-сервис

Дополнительно используется:

1. **proto** – для хранения `.proto` файлов. Эти файлы компилируются в Java-код и используются в микросервисах для обеспечения gRPC-коммуникации между ними.
2. **kafka-library** – для сериализации и десериализации POJO объектов при обмене сообщениями через **Kafka**.
3. **redis-library** - для хранения конфигураций, чтобы подключиться к redis, 
а также кастомный сервис для добавления refresh токена в черный список с ttl,
когда пользователь выходит из аккаунта.

## Схема приложения
![Схема](./meetings.drawio.png)

## Описание микросервисов

### 1. Gateway API
**gateway-api** — это центральная точка входа для всех запросов. 
Он перенаправляет их на соответствующие микросервисы, выполняет проверку **access токена** на валидность 
и проверяет, что **refresh токен** не находится в черном списке.

### 2. Auth Service
**auth-service** отвечает за:
- Регистрацию пользователей
- Аутентификацию
- Выдачу refresh и access токенов
- Смена пароля
- Обновления токенов
- Выход пользователя из аккаунта, refresh токен добавляется в черный список.

### 3. Location Service
**location-service** отвечает за:
- Хранение местоположения пользователей
- Поиск ближайших пользователей

### 4. Profile Service
**profile-service** используется для:
- Хранения профилей пользователей
- Хранения интересов пользователя
- Активации и Деактивации пользователя

### 5. Recommendation Service
**recommendation-service** формирует список рекомендованных пользователей на основе:
- Географической близости (данные из **location-service**)
- Предпочтений пользователя (данные из **profile-service**)

### 6. Matcher Service
**matcher-service** хранит информацию, кто кому поставил лайк. 
Если два пользователя друг другу поставили лайк, 
то состоялся мэтчинг.
## Запуск

Запуск приложения возможен через **Makefile**.  

Используется **Docker** для быстрого развертывания приложения.

### Настройка переменных окружения

В файле .env предоставлен возможный вариант названий переменных. 

Рекомендуется изменить AUTH_TOKEN, DB_PASSWORDS, DB_USERS. 
Также всe переменные, которые должны быть засекречены.


### Сборка приложения
```sh
make mvn-build
```

### Запуск приложения
```sh
make start
```

## Api функции

### Auth-service
**Регистрация пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/auth/sign-up \
    -H "Content-Type: application/json" \ 
    -d '{"username":"username", "password":"password"}'
  ```
- _Описание запроса:_
  - _username_ допускается от 5 до 20 символов, не null
  - _password_ допускается от 8 до 255 символов, не null
- _Ответ от сервера:_
  ```json
  {
    "refresh_token":"refresh_token",
    "access_token": "access_token"
  }
  ```
---
**Авторизация пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/auth/sign-in \
    -H "Content-Type: application/json" \ 
    -d '{"username":"username", "password":"password"}'
  ```
- _Описание запроса:_
  - _username_ допускается от 5 до 20 символов, не null
  - _password_ допускается от 8 до 255 символов, не null
- _Ответ от сервера:_
  ```json
  {
    "refresh_token":"refresh_token",
    "access_token": "access_token"
  }
  ```
---
**Обновление токен пользователю:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/auth/refresh-tokens \
    -H "Content-Type: application/json" \
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer access_token"
  ```
- _Описание запроса:_
  - Обновить токен можно только в том случае, если _access_token_ не валиден.
- _Ответ от сервера:_
  ```json
  {
    "refresh_token":"refresh_token",
    "access_token": "access_token"
  }
  ```
---
**Обновление пароля:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/auth/reset-password \
    -H "Content-Type: application/json" \
    -d '{"username":"username", "old_password":"old_password", "new_password":"new_password"}'
  ```
- _Описание запроса:_
  - _username_ допускается от 5 до 20 символов, не null
  - _old_password_ допускается от 8 до 255 символов, не null
  - _new_password_ допускается от 8 до 255 символов, не null
- _Ответ от сервера:_
  ```json
  {
    "refresh_token":"refresh_token",
    "access_token": "access_token"
  }
  ```
---
**Выход пользователя из аккаунта:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/auth/logout \
    -H "Content-Type: application/json" \
    -H "Cookie: refresh_token=refresh_token"
  ```
- _Ответ от сервера:_
  - HttpStatus.NO_CONTENT
---
### Profile-service
**Заполнение информации о себе:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/bios \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token" \
    -d '{"name":"name", "age":18, "description":"description", "gender":"gender"}'
  ```
- _Описание запроса:_
  - _name_ допускается от 1 до 45 символов, не null
  - _age_ может быть минимум 18 и максимум 80, не null
  - _gender_ может быть MALE или FEMALE, не null
  - _description_ может быть максимально 255 символов, не null
- _Ответ от сервера:_
  ```json
  {
    "id": 1,
    "status": "status",
    "name": "name",
    "age": 18,
    "description": "description",
    "gender": "gender"
  }
  ```
---
**Обновление информации о себе:**
- _Запрос на сервер:_
  ```sh
  curl -X PUT http://localhost:80/bios/{id} \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token" \
    -d '{"name":"name", "age":18, "description":"description", "gender":"gender"}'
  ```
- _Описание запроса:_
  - _name_ допускается от 1 до 45 символов, не null
  - _age_ может быть минимум 18 и максимум 80, не null
  - _gender_ может быть MALE или FEMALE, не null
  - _description_ может быть максимально 255 символов, не null
- _Ответ от сервера:_
  - _HttpStatus.NO_CONTENT_
---
**Получение информации о себе:**
- _Запрос на сервер:_
  ```sh
  curl -X GET http://localhost:80/bios/{id} \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  ```json
  {
    "id": 1,
    "status": "status",
    "name": "name",
    "age": 18,
    "description": "description",
    "gender": "gender"
  }
  ```
---
**Обновление статуса пользователя (Активен/Неактивен):**
- _Запрос на сервер:_
  ```sh
  curl -X PUT http://localhost:80/bios/{id}/status?active=false \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  ```json
  {
    "message": "Запрос в обработке"
  }
  ```
---
**Получение статуса пользователя (Активен/Неактивен):**
- _Запрос на сервер:_
  ```sh
  curl -X GET http://localhost:80/bios/{id}/status \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  ```json
  {
    "status": "status"
  }
  ```
---
**Заполнение предпочтений пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/preferences \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token" \
    -d '{"age_lower_bound":18, "age_upper_bound":30, "gender":"gender"}'
  ```
- _Описание запроса:_
  - _age_lower_bound_ и age_upper_bound могут быть минимум 18 и максимум 80, не null
  - _age_lower_bound_ < age_upper_bound
  - _gender_ может быть MALE или FEMALE, не null
- _Ответ от сервера:_
  ```json
  {
    "id": 1,
    "age_lower_bound": 18,
    "age_upper_bound": 30,
    "gender": "gender"
  }
  ```
---
**Обновление предпочтений пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X PUT http://localhost:80/preferences/{id} \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token" \
    -d '{"age_lower_bound":18, "age_upper_bound":30, "gender":"gender"}'
  ```
- _Описание запроса:_
  - _age_lower_bound_ и age_upper_bound могут быть минимум 18 и максимум 80, не null
  - _age_lower_bound_ < age_upper_bound
  - _gender_ может быть MALE или FEMALE, не null
- _Ответ от сервера:_
  - HttpStatus.NO_CONTENT
---
**Получение предпочтений пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X GET http://localhost:80/preferences/{id} \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  ```json
  {
    "id": 1,
    "age_lower_bound": 18,
    "age_upper_bound": 30,
    "gender": "gender"
  }
  ```
---
### Location-service
**Сохранение местоположения пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/locations \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token" \
    -d '{"latitude":18, "longitude":30}'
  ```
- _Описание запроса:_
  - latitude от 0 до 90, не null
  - longitude от 0 до 180, не null
- _Ответ от сервера:_
  ```json
  {
    "id": 1,
    "latitude": 18,
    "longitude": 30
  }
  ```
---
**Обновление местоположения пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X PUT http://localhost:80/locations/{id} \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token" \
    -d '{"latitude":18, "longitude":30}'
  ```
- _Описание запроса:_
  - latitude от 0 до 90, не null
  - longitude от 0 до 180, не null
- _Ответ от сервера:_
  - HttpStatus.NO_CONTENT
---
### Recommendation-service
**Получение списка рекомендаций для конкретного пользователя:**
- _Запрос на сервер:_
  ```sh
  curl -X GET http://localhost:80/recommendations \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  ```json
  [
    {
      "id": 1,
      "name": "name",
      "age": 18,
      "gender": "gender",
      "description": "description",
      "dist": 666.66
    }
  ]
- _Описание ответа от сервера:_
  - _dist_ определяет расстояние от конкретного пользователя до рекомендованного в метрах
  - Запрос может вернуть за раз максимум 5 пользователей
---
### Matcher-service
**Лайкнуть пользователя с liked_id:**
- _Запрос на сервер:_
  ```sh
  curl -X POST http://localhost:80/likes?liked_id=2 \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  ```json
  {
    "id": 1,
    "liked_id": 2
  }
  ```
---
**Удаление лайка пользователю с liked_id:**
- _Запрос на сервер:_
  ```sh
  curl -X DELETE http://localhost:80/likes/2 \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  - HttpStatus.NO_CONTENT
---
**Получение информации, поставил ли лайк пользователю с liked_id (True/False):**
- _Запрос на сервер:_
  ```sh
  curl -X GET http://localhost:80/likes/2 \
    -H "Content-Type: application/json" \ 
    -H "Cookie: refresh_token=refresh_token" \
    -H "Authorization: Bearer token"
  ```
- _Ответ от сервера:_
  ```json
  true
  ```
