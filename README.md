# publisher
This microservice provides an API for working with a queue of reports and scenarios.
The program makes it possible to add scenarios to a scenarios queue or reports from a reports queue.
[You can also see worker microservice here, which executes scenarios and generates reports.](https://github.com/MorgothGorthaur/worker_microservice)

you can run microservices using docker compose and see additional documentation  [here](https://github.com/victortarasov1/executor-service-docker-compose)

## Flow

### the scenario execution and generating reports flow:
![Flow Diagram](img/Flow.png)



## Publisher's API

- `PUT: /executor/publisher/scenario` - add new scenario
- `DELETE: /executor/publisher/scenario/?scenarioId=` - delete scenario by id
- `PATCH: /executor/publisher/scenario` - update scenario
- `POST: /executor/publisher/scenario/?scenarioId=` - execute scenario by id
- `GET: /executor/publisher/scenario/?scenarioId=` - get scenario by id
- `GET: /executor/publisher/scenario/all` - get all scenarios
- `GET: /executor/publisher/reports/?scenarioId=` - get report by id
- `GET: /executor/publisher/reports/all` - get all reports

## JSON Examples



### Proxy Example
```json
{
  "id": "74ce8215-e6b0-4e43-9f5c-481fb3f40d42",
  "proxyNetworkConfig": {
    "id": "f89664ee-6286-4275-807d-d5ab0f85841d",
    "hostname": "45.65.137.218",
    "port": 999
  },
  "proxyCredentials": {
    "username": "",
    "password": ""
  },
  "createdAt": "2024-07-03T15:08:35.890147Z"
}
```


## Used Technologies
### Back-end:
- Spring Web
- Spring Data JPA
- Spring Actuator
- Spring Cloud Config
- Spring Cloud Eureka
- Spring Cloud OpenFeign
- Spring Cloud Circuit breaker
- Spring Cloud Stream
- AspectJ
- AssertJ
- Jakarta Validation
- postgresql
- Mockito
### Server build:
- gradle
- google jib

## Requirements
- java 17
- gradle