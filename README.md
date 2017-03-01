cloud-native-apps
=================

Step-by-step guide to build [12-Factor](http://12factor.net/) cloud native apps with *Spring Cloud*, *Netflix OSS*, 
*Grails* and *AngularJS*

### Features
Spring Cloud focuses on providing good out of box experience for typical use cases and extensibility mechanism to cover others.

* Distributed/versioned configuration
* Service registration and discovery
* Routing
* Service-to-service calls
* Load balancing
* Circuit Breakers
* Global locks
* Leadership election and cluster state
* Distributed messaging

### Infrastructure Services 

* **Consul:** Service registry & discovery
* **Netflix Hystrix:** Resiliency - Circuit breaker, Fallback, Concurrency, RxJava 
* **Netflix Hystrix Dashboard:** Monitoring and metrics dashboard
* **Netflix Turbine:** Aggregate hystrix streams
* **Netflix Ribbon:** Client-side load-balancing
* **Netflix Zuul:** Reverse proxy for API gateway
* **Spring Cloud Consul Config:** Centralized configuration
* **Spring Cloud Vault:** Managing secrets
* **Spring Cloud Bus:** PubSub messaging over Kafka
* **Zipkin:** Distributed Tracing
* **Spring Cloud Security:** SSO for APIs - OAuth/JWT -- coming soon
* **Logging:** Centralized logging - Logstash, ES, Kibana (LEK) -- coming soon
* **Swagger:** API Docs -- coming soon


![](./presentation/images/system-landscape.png)
### Application Services 

#### Getting Started
1. [Prerequisites](./Prerequisites.md)
2. [Setup](./Setup.md)


#### Start on Local Machine

Starting  Order
> zk -> Kafka -> Consul -> Git2Consul -> Vault -> zipking -> (Producer,  ...), -> (Hystrix Dashboard)

#### Quickstart examples
> start following two apps, one at a time as they use same port.

Starting Tweetable App
```
cd tweetable
spring run .
```

Starting Mongo Example
```
cd mongo
spring run .
```

#### Full-stack cloud native examples 


Starting Zipkin Service
```
cd Zipkin
spring run .
```

Start up Producer Service
```
cd producer
spring run .

Start other services under Distributed Tracing folder
```

Starting Monitoring App
```
cd hystrix-dashboard
spring run .
```

Starting mybatis Service

> Follow instructions [mybatis](./mybatis/)


Starting Aggregate Service
 
> Follow instructions [aggregator](./aggregator/)




