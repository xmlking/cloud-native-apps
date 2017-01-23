Producer
---------
demonstrate dynamic config 
This API is Hystrix enabled

http://localhost:8081/
http://localhost:8081/counter


Hystrix Dashboard
http://localhost:8989/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8081%2Fhystrix.stream


### Run 

```bash
spring run  .
spring run  .  -- --spring.profiles.active=spanish
JAVA_OPTS=-Xmx1024m spring run .
```

To simulate the github webhook:
```
http --json POST :8888/monitor X-Github-Event:"push" commits:='[{"modified": ["producer.yml"] }]'
```
