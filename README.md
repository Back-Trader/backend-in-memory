BackTrader java backend development using Springboot and PostgreSQL.

After downloading project...

1. to run, in terminal

    `./mvnw spring-boot:run`

2. to install newly added dependencies and clear cache

    `./mvnw clean install`

3. to test endpoints open the following file in the Postman application

    `//api/*collection.json`

4. view the 'in-memory' database via browser gui, navigate to

    `http://localhost:8080/h2-console`

    `JDBC URL: jdbc:h2:mem:backend`

