footer: © 2016 Sumanth Chinthagunta
slidenumbers: true

# [fit] Building Distributed Systems with
# [fit] Netflix OSS
# [fit] and
# [fit] Spring Cloud
![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)

---

![left](./images/sumanth.png)
# Me

Sumanth Chinthagunta ([@xmlking](http://twitter.com/xmlking))
[xmlking@gmail.com](mailto:xmlking@gmail.com)

---

# There Seems to Be Some Hype...
![](./images/unicorn.jpg)

---

# haracteristics of Cloud Ntive applications
* Shared State is evil
    * Statelessness leads to scalability
    * Coordination of shared state across peers impacts performance
* Configuration via environment or via an external configuration server (github/Zookeeper/etcd)
* Inject external dependency connection and credential information via services
* Console based logging
* Monitoring via services and frameworks
* Local development, cloud production
* Execute application as one or more stateless processes enabling scale-out via a process model
* Explicitly declare and isolate dependencies
* One codebase tracked in revision control, multiple deploys
* Fast startup and graceful shutdown
* Make no distinction between local and third party services
* Treat logs as event streams
* Immutable code with instant rollback
* Asynchronous Integration of Services with Events
    * Event Collaboration
    * Event Sourcing
* Functional Reactive Pogramming 

---
# Reactive Applications 
A new architectural style called Reactive applications has emerged to allow developers to build systems that are event-driven, scalable, responsive and resilient. 
The key building blocks for event driven reactive applications are asynchronous sending of events, non-blocking, decoupling of event generation and processing, isolation, observable models, event streams and stateful clients. 
The entire solution needs to be asynchronous from the top(browser) to the bottom (web layer  & service components).

# Reactive Streams 
is an initiative to provide a standard for asynchronous stream processing with non-blocking back pressure on the JVM.
The main goal of Reactive Streams is to govern the exchange of stream data across an asynchronous boundary—think passing elements on to another thread or thread-pool—while ensuring that the receiving side is not forced to buffer arbitrary amounts of data. 
In other words, backpressure is an integral part of this model in order to allow the queues which mediate between threads to be bounded. 
The benefits of asynchronous processing would be negated if the communication of backpressure were synchronous.

---

# Components 

* Spring Cloud Config 
    External configuration management backed by a git repository
    Refreshable configuration. Covered extensively in 9 Configuration - Comes from the environment[p]
* Spring Cloud Bus
    Event bus implemented as a lightweight AMQP message broker used to broadcast state changes or other management instructions. The bus currently supports sending messages to all nodes listening or all nodes for a particular service that satisfy a particular criteria.  There are currently two endpoints implemented /bus/env and /bus/refresh.
* Spring Cloud Security 
    Set of primitives for building secure applications. If you deploy apps on Cloud Foundry that use HTTP Basic Security, then the best way to configure security is through service credentials, e. g. in the URI of the user provided service, since then it doesn’t even need to be in a config file. 
---
# Define: Microservice
> Loosely coupled service oriented architecture with bounded contexts...
-- Adrian Cockcroft

---

# [fit] Spring Boot
# [fit] A Microframework for Microservices
![](https://raw.githubusercontent.com/spring-projects/spring-boot/gh-pages/img/project-icon-large.png)

---

# It Can Get Pretty Small...

```java
@RestController
class ThisWillActuallyRun {
  @RequestMapping("/")
  String home() {
    "Hello World!"
  }
}
```

---

# [fit] DEMO

---

# With Spring Data REST!

```java
@Entity
@Table(name = "city")
public class City implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String county;

  //...

}
```

---

# With Spring Data REST!

```java
@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
public interface CityRepository extends PagingAndSortingRepository<City, Long> {}
```

---

# With Spring Data REST!

```java
@SpringBootApplication
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```

---

# With Spring Data REST!
```
{
  "_links" : {
    "next" : {
      "href" : "http://localhost:8080/cities?page=1&size=20"
    },
    "self" : {
      "href" : "http://localhost:8080/cities{?page,size,sort}",
      "templated" : true
    }
  },
  "_embedded" : {
    "cities" : [ {
      "name" : "HOLTSVILLE",
      "county" : "SUFFOLK",
      "stateCode" : "NY",
      "postalCode" : "00501",
      "latitude" : "+40.922326",
      "longitude" : "-072.637078",
```

---

# [fit] DEMO

---

# [fit] Writing a Single Service is
# [fit] Nice...

---

# [fit] But No Microservice
# [fit] is an Island
![](./images/island-house.jpg)

---

# Challenges of Distributed Systems

* Configuration Management
* Service Registration & Discovery
* Routing & Load Balancing
* Fault Tolerance (Circuit Breakers!)
* Monitoring
* Concurrent API Aggregation & Transformation

---

![](./images/netflix_oss.jpeg)

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Spring Cloud
# [fit] Distributed System Patterns FTW!

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Configuration
# [fit] Management

---

# Spring Environment

* Properties
* Profiles

---

# `app.groovy`

```java
@RestController
class BasicConfig {

  @Value('${greeting}')
  String greeting

  @RequestMapping("/")
  String home() {
    "${greeting} World!"
  }
}
```

---

# `application.yml`

```
greeting: Hello
```

---

# [fit] DEMO

---

# Boot Priority

1. Command Line Args
1. JNDI
1. Java System Properties
1. OS Environment Variables
1. Properties Files
1. `@PropertySource`
1. Defaults

---

# [fit] DEMO

^ Show GREETING=Ohai spring run app.groovy

---

# [fit] Profiles

---

# `application.yml`

```
greeting: Hello

---

spring:
  profiles: spanish
greeting: Hola
```

---

# [fit] DEMO

^ Show SPRING_PROFILES_ACTIVE=spanish spring run app.groovy

^ Also show GREETING=Ohai SPRING_PROFILES_ACTIVE=spanish spring run app.groovy

---

# [fit] Distributed?

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Config
# [fit] Server!

---

# Config Server `app.groovy`

```java
@Grab("org.springframework.cloud:spring-cloud-starter-bus-amqp:1.0.0.RC1")
@Configuration
@EnableAutoConfiguration
@EnableConfigServer
class ConfigServer {
}
```

---

# Config Server `application.yml`

```
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/xmlking/cloud-config.git
```

---

![](./images/github.jpeg)

# `https://github.com/xmlking/cloud-config/blob/master/producer.yml`

```
greeting: Bonjour
```

---

# Config Client `app.groovy`

```java
@Grab("org.springframework.cloud:spring-cloud-starter-bus-amqp:1.0.0.RC1")
@RestController
class BasicConfig {

  @Autowired
  Greeter greeter

  @RequestMapping("/")
  String home() {
    "${greeter.greeting} World!"
  }
}

@Component
@RefreshScope
class Greeter {

  @Value('${greeting}')
  String greeting

}
```

---

# Config Client `bootstrap.yml`

```
spring:
  application:
    name: demo
```

---

# [fit] DEMO

---

![right fit](./images/rabbitmq.png)
# [fit] Cloud
# [fit] Bus!

---

# [fit] `curl -X POST http://localhost:8888/bus/refresh`

---

# [fit] DEMO

^ Change greeting in demo.yml to Howdy

^ git commit/push

^ Show greeting

^ Trigger refresh

^ Show greeting again!

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Service
# [fit] Registration &
# [fit] Discovery

---

# [fit] Eureka
![](./images/netflix_oss.jpeg)

---

# [fit] Producer
# [fit] Consumer

---

# Eureka Service Registry

```java
@GrabExclude("ch.qos.logback:logback-classic")
@EnableEurekaServer
class Eureka {
}
```

---

# Producer

```java
@EnableDiscoveryClient
@RestController
public class Application {

  int counter = 0

  @RequestMapping("/")
  String produce() {
    "{\"value\": ${counter++}}"
  }
}
```

---

# Consumer

```java
@EnableDiscoveryClient
@RestController
public class Application {

  @Autowired
  DiscoveryClient discoveryClient

  @RequestMapping("/")
  String consume() {
    InstanceInfo instance = discoveryClient.getNextServerFromEureka("PRODUCER", false)

    RestTemplate restTemplate = new RestTemplate()
    ProducerResponse response = restTemplate.getForObject(instance.homePageUrl, ProducerResponse.class)

    "{\"value\": ${response.value}}"
  }
}

public class ProducerResponse {
  Integer value
}
```

---

# [fit] DEMO

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Routing &
# [fit] Load Balancing

---

# [fit] Ribbon
![](./images/netflix_oss.jpeg)

---

# Consumer with Load Balancer

```java
@Autowired
LoadBalancerClient loadBalancer

@RequestMapping("/")
String consume() {
  ServiceInstance instance = loadBalancer.choose("producer")
  URI producerUri = URI.create("http://${instance.host}:${instance.port}");

  RestTemplate restTemplate = new RestTemplate()
  ProducerResponse response = restTemplate.getForObject(producerUri, ProducerResponse.class)

  "{\"value\": ${response.value}}"
}
```

---

# [fit] DEMO

---

# Consumer with Ribbon-enabled `RestTemplate`

```java
@Autowired
RestTemplate restTemplate

@RequestMapping("/")
String consume() {
  ProducerResponse response = restTemplate.getForObject("http://producer", ProducerResponse.class)

  "{\"value\": ${response.value}}"
}
```

---

# [fit] DEMO

---

# Feign Client

```java
@FeignClient("producer")
public interface ProducerClient {

  @RequestMapping(method = RequestMethod.GET, value = "/")
  ProducerResponse getValue();
}
```

---

# Consumer with Feign Client

```java
@SpringBootApplication
@FeignClientScan
@EnableDiscoveryClient
@RestController
public class Application {

  @Autowired
  ProducerClient client;

  @RequestMapping("/")
  String consume() {
    ProducerResponse response = client.getValue();

    return "{\"value\": " + response.getValue() + "}";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```

---

# [fit] Demo

---


![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Fault
# [fit] Tolerance

---

# [fit] Hystrix
![](./images/netflix_oss.jpeg)

---

# Circuit Breaker
![inline](./images/circuit-breaker.gif)

---

# Consumer `app.groovy`

```java
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
public class Application {

  @Autowired
  ProducerClient client

  @RequestMapping("/")
  String consume() {
    ProducerResponse response = client.getProducerResponse()

    "{\"value\": ${response.value}}"
  }

}
```

---

# Producer Client

```java
@Component
public class ProducerClient {

  @Autowired
  RestTemplate restTemplate

  @HystrixCommand(fallbackMethod = "getProducerFallback")
  ProducerResponse getProducerResponse() {
    restTemplate.getForObject("http://producer", ProducerResponse.class)
  }

  ProducerResponse getProducerFallback() {
    new ProducerResponse(value: 42)
  }
}
```

---

# [fit] Demo

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Monitoring

---

# [fit] DEMO
# [fit] `http://localhost:8082/hystrix.stream`

---

# [fit] Hystrix
# [fit] Dashboard
![](./images/netflix_oss.jpeg)

---

# Hystrix Dashboard

![inline](./images/hystrix-dashboard.png)

---

# Hystrix Dashboard

```java
@Grab("org.springframework.cloud:spring-cloud-starter-hystrix-dashboard:1.0.0.RC1")

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard

@EnableHystrixDashboard
class HystrixDashboard {
}
```

---

# [fit] Demo

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Concurrent
# [fit] API
# [fit] Aggregation &
# [fit] Transformation

---

# API Gateway Pattern
![inline](./images/API_Gateway.png)

---

# [fit] RxJava
![](./images/netflix_oss.jpeg)

---

# Movie Catalog Service

```java
@RequestMapping(value = "/catalog/movies/{mlId}", method = RequestMethod.GET)
public Movie movie(@PathVariable String mlId) {
  return movieRepository.findByMlId(mlId);
}
```

---

# Movie Catalog Service

```
{
  id: 1001,
  title: "GoldenEye (1995)",
  mlId: "2",
  genres: [
  {
    id: 1001,
    mlId: "1",
    name: "Action"
  },
  {
    id: 1002,
    mlId: "2",
    name: "Adventure"
  },
  {
    id: 1016,
    mlId: "16",
    name: "Thriller"
  }
  ]
}
```

---

# Movie Review Service

```java
@RequestMapping(value = "/reviews/reviews/{mlId}", method = RequestMethod.GET)
public Iterable<Review> reviews(@PathVariable String mlId) {
  return reviewRepository.findByMlId(mlId);
}
```

---

# Movie Review Service

```
[
{
  id: "54b85cbe004e0464177e90e4",
  mlId: "2",
  userName: "schintha",
  title: "GoldenEye (1995)",
  review: "Pretty good...",
  rating: 3
},
{
  id: "54b85cbe004e0464177e90e5",
  mlId: "2",
  userName: "starbuxman",
  title: "GoldenEye (1995)",
  review: "BOND BOND BOND!",
  rating: 5
},
{
  id: "54b85cbf004e0464177e90e8",
  mlId: "2",
  userName: "littleidea",
  title: "GoldenEye (1995)",
  review: "Good show!",
  rating: 4
}
]
```

---

# Movie Recommendations Service

```java
public interface MovieRepository extends GraphRepository<Movie> {
  Movie findByMlId(String mlId);

  @Query("MATCH (movie:Movie) WHERE movie.mlId = {0} MATCH movie<-[:LIKES]-slm-[:LIKES]->recommendations " +
  "RETURN distinct recommendations")
  Iterable<Movie> moviesLikedByPeopleWhoLiked(String mlId);
}
```

---

# Movie Recommendations Service

```java
@RequestMapping(value = "/recommendations/forMovie/{mlId}", method = RequestMethod.GET)
public Iterable<Movie> recommendedMoviesForMovie(@PathVariable String mlId) {
  return movieRepository.moviesLikedByPeopleWhoLiked(mlId);
}
```

---

# Movie Recommendations Service

```java
@RequestMapping(value = "/recommendations/forMovie/{mlId}", method = RequestMethod.GET)
public Iterable<Movie> recommendedMoviesForMovie(@PathVariable String mlId) {
  return movieRepository.moviesLikedByPeopleWhoLiked(mlId);
}
```

---

# Movie Recommendations Service

```
[
{
  id: 6,
  mlId: "1",
  title: "Toy Story (1995)"
},
{
  id: 1,
  mlId: "4",
  title: "Get Shorty (1995)"
},
{
  id: 2,
  mlId: "5",
  title: "Copycat (1995)"
},
{
  id: 0,
  mlId: "3",
  title: "Four Rooms (1995)"
}
]
```

---

# [fit] API
# [fit] Gateway

---

# Catalog Integration Service

```java
@Service
public class CatalogIntegrationService {

  @Autowired
  RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "stubMovie")
  public Observable<Movie> getMovie(final String mlId) {
    return new ObservableResult<Movie>() {
      @Override
      public Movie invoke() {
        return restTemplate.getForObject("http://catalog-service/catalog/movies/{mlId}", Movie.class, mlId);
      }
    };
  }

  private Movie stubMovie(final String mlId) {
    Movie stub = new Movie();
    stub.setMlId(mlId);
    stub.setTitle("Interesting...the wrong title. Sssshhhh!");
    return stub;
  }
}
```

---

# Reviews Integration Service

```java
@Service
public class ReviewsIntegrationService {

  @Autowired
  RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "stubReviews")
  public Observable<List<Review>> reviewsFor(String mlId) {
    return new ObservableResult<List<Review>>() {
      @Override
      public List<Review> invoke() {
        ParameterizedTypeReference<List<Review>> responseType = new ParameterizedTypeReference<List<Review>>() {
        };
        return restTemplate.exchange("http://reviews-service/reviews/reviews/{mlId}", HttpMethod.GET, null, responseType, mlId).getBody();
      }
    };
  }

  private List<Review> stubReviews(String mlId) {
    Review review = new Review();
    review.setMlId(mlId);
    review.setRating(4);
    review.setTitle("Interesting...the wrong title. Sssshhhh!");
    review.setReview("Awesome sauce!");
    review.setUserName("joeblow");
    return Arrays.asList(review);
  }

}
```

---

# Recommendations Integration Service

```java
@Service
public class RecommendationsIntegrationService {
  @Autowired
  RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "stubRecommendations")
  public Observable<List<Movie>> getRecommendations(final String mlId) {
    return new ObservableResult<List<Movie>>() {
      @Override
      public List<Movie> invoke() {
        ParameterizedTypeReference<List<Movie>> responseType = new ParameterizedTypeReference<List<Movie>>() {
        };
        return restTemplate.exchange("http://recommendations-service/recommendations/forMovie/{mlId}", HttpMethod.GET, null, responseType, mlId).getBody();
      }
    };
  }

  private List<Movie> stubRecommendations(final String mlId) {
    Movie one = new Movie();
    one.setMlId("25");
    one.setMlId("A movie which doesn't exist");
    Movie two = new Movie();
    two.setMlId("26");
    two.setMlId("A movie about nothing");
    return Arrays.asList(one, two);
  }
}
```

---

# Concurrently Aggregate and Transform

```java
@RequestMapping("/movie/{mlId}")
public DeferredResult<MovieDetails> movieDetails(@PathVariable String mlId) {
  Observable<MovieDetails> details = Observable.zip(

  catalogIntegrationService.getMovie(mlId),
  reviewsIntegrationService.reviewsFor(mlId),
  recommendationsIntegrationService.getRecommendations(mlId),

  (movie, reviews, recommendations) -> {
    MovieDetails movieDetails = new MovieDetails();
    movieDetails.setMlId(movie.getMlId());
    movieDetails.setTitle(movie.getTitle());
    movieDetails.setReviews(reviews);
    movieDetails.setRecommendations(recommendations);
    return movieDetails;
  }

  );
  return toDeferredResult(details);
}
```

---

# [fit] Demo

---


# Thanks!

Sumanth Chinthagunta ([@xmlking](http://twitter.com/xmlking))

* Spring Cloud: http://cloud.spring.io
* This Presentation: https://github.com/xmlking/cloud-native-apps/

---

# Image Credits

* http://i.imgur.com/atz81.jpg
* http://theroomermill.net/wp-content/uploads/2014/06/island-house.jpg
