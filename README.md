# Quarkus Social API

Este projeto usa Quarkus, o Supersonic Subatomic Java Framework.

## Executando o aplicativo no modo dev

Você pode executar seu aplicativo no modo dev que permite codificação ao vivo usando:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTA:_**  O Quarkus agora vem com uma Dev UI, que está disponível no modo dev apenas em http://localhost:8080/q/dev/.

## Empacotando e executando o aplicativo

O aplicativo pode ser empacotado usando:

```shell script
./mvnw package
```

Ele produz o arquivo `quarkus-run.jar` no diretório `target/quarkus-app/`.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and
  Jakarta Persistence
- JDBC Driver - H2 ([guide](https://quarkus.io/guides/datasource)): Connect to the H2 database via JDBC
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and
  method parameters for your beans (REST, CDI, Jakarta Persistence)
- RESTEasy Classic JSON-B ([guide](https://quarkus.io/guides/rest-json)): JSON-B serialization support for RESTEasy
  Classic
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code
  for Hibernate ORM via the active record or the repository pattern
