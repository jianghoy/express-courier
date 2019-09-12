# Property Definition in Spring Boot

One neat feature of Spring Boot which Spring does not have is a default `application.property` file in `src/main/resources` folder. It can auto inject every configuration.

Take one line as an example:

```property
server.servlet.context-path=/ec
```

This line defines the root path for our project, for example, if we comment out with `#`, the project `Hello World` can be accessed via `localhost:8080/`, which, somehow, may conflict with other projects. So using this line we add a `/ec` as a prefix for **ALL** endpoints.

## further reading

[Property with Spring Boot by Baeldung](https://www.baeldung.com/properties-with-spring)
[Property Source explaned in Spring Doc](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) **THE PROPERTY SOURCE ORDER IS VERY USEFUL**
