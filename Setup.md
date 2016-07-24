cloud-native-apps
------------------



### Prerequisites 
Follow instructions [here](./PREREQUISITES.md)

create-app MyApp --profile=web-micro

INFRASTRUCTURE SERVERS
1. API Gateway - gatekeeper
1. API Security (JWT) 
2. Load Balancer
2. Cloud Config
2. Service Discovery 
2. Metrics 
2. Monitoring - Hystrix Dashboard + Turbine    
2. Logging   - Logstash, ES, Kibana (LEK)
2. API Docs 
3. Resiliency 
4. Reactive Programming  

Features 
Distributed/versioned configuration
Service registration and discovery
Routing
Service-to-service calls
Load balancing
Circuit Breakers
Global locks
Leadership election and cluster state
Distributed messaging



### Typically microservices may want to go one of two ways:
1. Use Eureka as a hub and find the configuration services
2. Use Configuration services and find Eureka
I personally prefer the Eureka first approach

Start the eureka app first:
```bash
cd eureka
spring run .
```
Then start the service (in a separate shell):

```bash
cd service
spring run .
```

To simulate the github webhook:
```
http --json POST :8888/monitor X-Github-Event:"push" commits:='[{"modified": ["dynamic-config-resource.yml"] }]'
```

passing args
```
spring run eureka-server.groovy -- --spring.application.name=eureka-server
spring run  .  -- --spring.profiles.active=spanish
JAVA_OPTS=-Xmx1024m spring run hello.groovy
```

Using the embedded shell
```
spring shell
```
 

Ref:
http://www.slideshare.net/mstine/dist-sys-wspringclouddeckset
https://github.com/spring-projects/spring-boot/tree/master/spring-boot-cli/samples
http://projects.spring.io/spring-cloud/spring-cloud.html
http://callistaenterprise.se/blogg/teknik/2015/04/10/building-microservices-with-spring-cloud-and-netflix-oss-part-1/
http://callistaenterprise.se/blogg/teknik/2015/04/15/building-microservices-with-spring-cloud-and-netflix-oss-part-2/
http://www.kennybastani.com/2015/07/spring-cloud-docker-microservices.html
http://www.infoworld.com/article/2925047/application-development/build-self-healing-distributed-systems-with-spring-cloud.html
http://spencer.gibb.us/blog/2015/09/24/spring-cloud-config-push-notifications/
Using Netflix Zuul to Proxy your Microservices
https://blog.heroku.com/archives/2016/3/2/using_netflix_zuul_to_proxy_your_microservices

examples
https://github.com/kbastani/spring-cloud-microservice-example
https://github.com/habuma/spring-cloud-cli-examples
https://github.com/bijukunjummen/sample-spring-hystrix
Circuit Breaker
https://docs.pivotal.io/spring-cloud-services/circuit-breaker/writing-client-applications.html

Creating colorful Banners for Spring Boot Applications
http://patorjk.com/software/taag/#p=display&f=Jacky&t=SUMANTH
curl -F "image=@/my/img.jpg"  -H "Content-Type: multipart/form-data"  bootiful-banners.cfapps.io/banner


grails create-app bookstore --profile web --features mongodb,asset-pipeline
grails create-app bookstore --profile rest-api --features hibernate,security,json-views