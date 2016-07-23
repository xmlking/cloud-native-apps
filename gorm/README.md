GORM Example
------------
Data API with Hystrix and actuator enabled

Run
```bash
spring run .
# or
spring run *.groovy
```

Test
```bash
spring test  *.groovy
```


Home 
http://localhost:8082/
message
http://localhost:8082/get

Should pass
http://localhost:8082/persons/1
http://localhost:8082/persons/2
Should pass with fallback
http://localhost:8082/persons/3
http://localhost:8082/persons/4
must fail
http://localhost:8082/persons/5
(test if Circuit is open by load generating with postman)

Hystrix Dashboard
http://localhost:8989/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8082%2Fhystrix.stream

Actuator Metrics
http://localhost:8082/info
http://localhost:8082/beans
http://localhost:8082/env
http://localhost:8082/health
http://localhost:8082/metrics
http://localhost:8082/trace
http://localhost:8082/dump
 

