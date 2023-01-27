## Mini Autorizador
Uma Api rest que simula um motor de regras para autorizar transações de cartões.

### Tecnologias
- Java 17;
- Spring Boot Web, Data JPA, Test, Validation;
- JUnit;
- Swagger;
- MySql;
- Lombok;
- H2;

### Funcionalidades
- Criação de cartões;
- Obtenção do saldo do cartão;
- Autorização de transação;

### Execução da aplicação
- Clone o projeto ```git clone https://github.com/marinholucasit/mini-autorizador.git```
- Na raiz do projeto rode o comando ```docker compose up -d ```
- Suba a aplicação usando ```./gradlew bootRun```
- Verificar a saúde a aplicação http://localhost:8080/actuator/health
- Documentação http://localhost:8080/swagger-ui/index.html#