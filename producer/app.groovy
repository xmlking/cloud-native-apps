@Grab("spring-cloud-starter-bus-amqp")

@EnableDiscoveryClient
@RestController
@Log
public class Application {

    @Autowired
    Greeter greeter

    int counter = 0

    @RequestMapping(value = "/counter", produces = "application/json")
    String produce() {
        counter++
        log.info("Produced a value: ${counter}")

        "{\"value\": ${counter}}"
    }

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