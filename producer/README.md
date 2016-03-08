Producer
---------

demonstrate dynamic config 

http://localhost:8081/
http://localhost:8081/counter

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
