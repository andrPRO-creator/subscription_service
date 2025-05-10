# Subscription microservice
Микросервис управления пользователями и их подписками на сервисы

## Функциональность 
1. Пользователи. Добавление пользователей с параметрами:

- ID

- Username (уникальное)


2. Подписки. Добавление подписки с параметрами:

- ID

- Название (уникальное)


## Стек
- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- Docker

## API Endpoints


### Пользователи 
&nbsp;&nbsp;&nbsp;GET
- /users/{userId} &nbsp;&nbsp;&nbsp; получение пользователя по id
  
&nbsp;&nbsp;&nbsp;POST
- /users &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;создание пользователя

&nbsp;&nbsp;&nbsp;PATCH
- /users/{userId} &nbsp;&nbsp;&nbsp; изменение параметров пользователя

&nbsp;&nbsp;&nbsp;DELETE
- /users/{userId} &nbsp;&nbsp;&nbsp;удаление пользователя



### Подписки
&nbsp;&nbsp;&nbsp;GET
- /foods/{id}&nbsp;&nbsp;&nbsp;получение подписки по id
- /subscriptions/top3 &nbsp;&nbsp;&nbsp;получение топ 3 подписок подключенных пользователям
  
&nbsp;&nbsp;&nbsp;POST
- /subscriptions &nbsp;&nbsp;&nbsp;создание подписки
- /subscriptions/userId/subId &nbsp;&nbsp;&nbsp;добавление подписки пользователю

&nbsp;&nbsp;&nbsp;DELETE
- /subscriptions/{id}&nbsp;&nbsp;&nbsp;удаление подписки
- /subscriptions/{userId}/{subId}&nbsp;&nbsp;&nbsp;удаление подписки у пользователя

