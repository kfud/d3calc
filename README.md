# d3calc
Domain Driven Design Calculator Example

# How to Run it

`mvn exec:java -Dexec.mainClass=com.fudcom.d3calc.ApplicationKt`

# How to Use It (via REST)

Make requests on: `localhost:8080`

- Get current number: `GET /`
- Add a number: `POST /add/{number}`
- Substract a number: `POST /sub/{number}`
- Multiply a number: `POST /mul/{number}`
- Divid a number: `POST /div/{number}`
- All clear: `POST /ac`
- Memory Store: `POST /ms`
- Memory Recall: `POST /mr`
