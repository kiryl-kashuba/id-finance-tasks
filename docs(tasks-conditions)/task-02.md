# Task 02: WebFlux

Create domain object `User` with the next data:

```
id: Long
firstName: String
lastName: String
```

Create service to return data about users in JSON format. Service should contain two REST endpoints to handle requests:

```
GET /user/{id} -- returns data about user by `id`
GET /user      -- returns list of all users
```

Implement endpoints using controller and router.

Use `in-memory` storage to keep data about users.

Use `spring-webflux` and dependencies:

```
//I have deleted information that potentially falls under "trade secrets"//
implementation lib('io.projectreactor:reactor-core')
```

## Documentation

https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html  \
https://www.baeldung.com/spring-webflux  \
just type 'spring webflux' in Google and read any article
