config-server
------------

Config server need to have the following dependencies
```groovy
@Grab("spring-cloud-config-monitor")
@Grab("spring-cloud-starter-bus-amqp")
```
Config clients need to have the following dependencies
```groovy
@Grab("spring-cloud-starter-bus-amqp")
```


To simulate the github webhook:
```bash
http --json POST :8888/monitor X-Github-Event:"push" commits:='[{"modified": ["eureka.yml"] }]'
```