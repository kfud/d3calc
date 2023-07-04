# d3calc
Domain Driven Design Calculator Example

# How to RUN it

`mvn exec:java -Dexec.mainClass=com.fudcom.d3calc.ApplicationKt`

# How to USE It (via REST)

Make requests on: `localhost:8080`

- Get current number: `GET /`
- Add a number: `POST /add/{number}`
- Substract a number: `POST /sub/{number}`
- Multiply a number: `POST /mul/{number}`
- Divid a number: `POST /div/{number}`
- All clear: `POST /ac`
- Memory Store: `POST /ms`
- Memory Recall: `POST /mr`

# How to TEST

`mvn test`

This is still work in progress, but the idea is to:
1. Unit-test the business rules core package.
2. Then test the adapters (REST) with mocking.