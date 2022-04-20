# WARNING - this branch purposefully adds vulnerabilities to the application (SQL Injection, XSS, probably more).

# IMPORTANT - Do NOT use this code in production and never attempt hacking techniques on applications for which you don't have explicit permissions to do so.

# simple-spring-boot-app
A simple application made with Spring Boot for educational purposes.

## Important URLs:

### Access the h2 console
* GET [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* user: sa
* password: NotSafeForProduction

### Show portfolio page
* GET [http://localhost:8080/portfolio](http://localhost:8080/portfolio)

### Create comment (using the form / form encoded)
* POST [http://localhost:8080/portfolio](http://localhost:8080/portfolio)

### Get all comments using the REST service
* GET [http://localhost:8080/api/portfolio/comments](http://localhost:8080/api/portfolio/comments)

### Insert comment using the REST service
* POST [http://localhost:8080/api/portfolio/comments](http://localhost:8080/api/portfolio/comments)
* Json Payload: { "name": "John Doe", "message": "Wow" }
