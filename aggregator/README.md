Aggregator
==========

Aggregator Service: Example of using feign with eureka
Cross-Origin Resource Sharing [(CORS)](https://spring.io/understanding/CORS) enabled. 
 
* Aggregator
* Aggregator 2


### Build

```bash
# gradle aggregator:installShadow
gradle aggregator:build
```

### Run

> Assumes eureka is running on http://localhost:8761

#### mybatis Service

Start [mybatis](../mybatis/) service.  

```bash
gradle  mybatis:bootRun 
# java -jar mybatis/build/libs/mybatis-0.1.0-SNAPSHOT.jar
```

verify it is functioning at [http://localhost:8083](http://localhost:8083)

You should see `MyBatis: mybatis:myhostname:8083`


#### Aggregator Service

```bash
gradle aggregator:bootRun
```

verify it is functioning at [http://localhost:8085](http://localhost:8085)

You should see `MyBatis: mybatis:myhostname:8083`

#### See round robin load balancing in action

Start [mybatis](../mybatis/) service on port 8084

```bash
gradle 8084  mybatis:bootRun 
# java -jar mybatis/build/libs/mybatis-0.1.0-SNAPSHOT.jar  --server.port=8084
```

Go back to [http://localhost:7211](http://localhost:8085) and you should see both ports `8083` and `8085` in the output after a minute or two as you keep refreshing.

### Test
http://localhost:8085/
http://localhost:8085/members
http://localhost:8085/home