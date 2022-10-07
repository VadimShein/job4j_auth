[![Build Status](https://app.travis-ci.com/VadimShein/job4j_auth.svg?branch=master)](https://app.travis-ci.com/VadimShein/job4j_auth)

# <p align="center">auth</p>
Проект для ознакомления с технологией Rest API.
Класс PersonController обрабатывает CRUD запросы, сохраняя, удаляя, изменяя объекты Person в БД. 
Класс EmployeeController, используя RestTemplate создаёт запросы для PersonController.


## Используемые технологии:
* Java 13, Rest API, RestTemplate
* Spring Boot, Spring Data
* PostgreSQL, Hibernate
* JUnit, Mockito
* Maven


## Сборка проекта:
```
mvn clean package -DskipTests
```

## Запуск
1. Скопировать проект 
```
git clone https://github.com/VadimShein/job4j_auth
```

2. Создать базу данных auth и таблицы. Из файла src/main/resources/db/update.sql

3. Выполнить команду 
``` 
mvn install
```

4. Запустить приложение, адрес по умолчанию  [http://localhost:8080/](http://localhost:8080/)
```
mvn spring-boot:run
```

## Контакты:
[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/SheinVadim)
[![alt-text](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:shein.v94@mail.ru)
