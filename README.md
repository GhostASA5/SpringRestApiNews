# Новостной сервис

## Описание проекта
Проект реализует новостной сервис. Вы можете просмотреть, создать, изменить или
удалить разных пользователей, категории новостей, новости и
комментарии к новостям.

Более подробную информацию по REST запросам вы можете найти по ссылке:
http://localhost:8080/swagger-ui/index.html

Проект реализован вместе со Spring Security. Для аутенфикации используется
Basic Auth. При создании пользователя указывается username, password и role.
У пользователя могут быть роли USER, MODERATOR, ADMIN.

## Используемые технологии

- Spring Boot 3
- Gradle 8.7
- Lombok
- JDK 17

## Запуск проекта
Проект можно запустить двумя способами:
1. Запустить SpringRestApiApplication.java по расположению src/main/java/com/example/SpringRestAPI/SpringRestApiApplication.java

2. Запустить docker-compose.yaml. Для этого перейдите в папку docker и запустите
   команду:
```shell
docker-compose up
```