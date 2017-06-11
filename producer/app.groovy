//@Grab("spring-cloud-netflix-hystrix-stream")
@Grab("org.springframework.kafka:spring-kafka:1.1.1.RELEASE")
@Grab("org.springframework.integration:spring-integration-kafka:2.1.0.RELEASE")
@Grab("org.apache.kafka:kafka_2.11:0.10.0.0")
@GrabExclude("org.slf4j:slf4j-log4j12")
@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")
@Grab("spring-cloud-starter-consul-discovery")
@Grab("spring-cloud-starter-consul-config")
@Grab(group='org.springframework.cloud', module='spring-cloud-vault-starter-config', version='1.0.0.M1')
@Grab(group='org.springframework.cloud', module='spring-cloud-vault-config-databases', version='1.0.0.M1')
//@Grab(group='org.springframework.cloud', module='spring-cloud-vault-config-consul', version='1.0.0.M1')
@Grab("spring-cloud-starter-hystrix")
@Grab("spring-boot-starter-jdbc")
@Grab("mysql:mysql-connector-java:5.1.32")



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@Log
@EnableDiscoveryClient
@EnableCircuitBreaker
class Application {

    @Autowired
    Greeter greeter

    @Autowired
    DataSource dataSource;

    int counter = 0

    @RequestMapping(value = "/counter", produces = "application/json")
    String produce() {
        counter++
        log.info("Produced a value: ${counter}")

        "{\"value\": ${counter}}"
    }

    @RequestMapping("/greet")
    String greet() {

        "${greeter.greeting} World!"
    }

    @RequestMapping("/")
    String home() {


       log.info("##########################");

        Connection connection = dataSource.getConnection()
        Statement statement = connection.createStatement()

        ResultSet resultSet = statement.executeQuery("SELECT * from test;");
        resultSet.next();

        String val = resultSet.getString(1);


        resultSet.close();

        log.info("##########################");

        "Connection works with User: " + val
    }

    @RequestMapping(value = '/questions/{questionId}')
    @HystrixCommand(fallbackMethod = "defaultQuestion")
    def question(@PathVariable String questionId) {
        if(Math.random() < 0.5) {
            throw new RuntimeException('random');
        }
        [questionId: questionId]
    }

    def defaultQuestion(String questionId) {
       [questionId: 'defaultQuestion']
    }

}

@Component
@RefreshScope
class Greeter {
    @Value('${greeting}')
    String greeting
}