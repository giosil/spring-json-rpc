# spring-json-rpc

Implementation of JSON-RPC protocol based on Spring Boot.

## Dependencies

**wcollections**

- `git clone https://github.com/giosil/wcollections.git` 
- `mvn clean install` - this will publish `wcollections-1.0.0.jar` in Maven local repository

## Build

- `git clone https://github.com/giosil/micro-json-rpc.git`
- `mvn clean install`

## Run

- `mvn spring-boot:run`

## Run on Docker

- `docker compose up -d`

## Test

POST `http://localhost:8080/rpc`

Content-Type: application/json

```json
{
  "id": 1,
  "jsonrpc": "2.0",
  "method": "DEMO.hello",
  "params": ["world"]
}
```

```json
{
  "id": 1,
  "jsonrpc": "2.0",
  "method": "DEMO.helloObj",
  "params": ["world"]
}
```

## View OpenAPI documentation

`http://localhost:8080/api-docs`

## License

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Contributors

* [Giorgio Silvestris](https://github.com/giosil)