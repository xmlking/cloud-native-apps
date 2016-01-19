Producer
---------

demonstrate dynamic config 

http://localhost:8081/
http://localhost:8081/counter

```
spring run  .
spring run  .  -- --spring.profiles.active=spanish
```

To simulate the github webhook:
```
http --json POST :8888/monitor X-Github-Event:"push" commits:='[{"modified": ["producer.yml"] }]'
```
