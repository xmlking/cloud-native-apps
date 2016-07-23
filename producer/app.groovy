//@Grab("spring-cloud-netflix-hystrix-stream")
@Grab("spring-cloud-starter-bus-kafka")
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Log
class Application {

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