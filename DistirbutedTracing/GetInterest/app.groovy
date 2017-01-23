@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")


@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Log
class Application {

    String interests = "Cricket, Movies, Music"

    @RequestMapping(value = "/getInterest", produces = "application/json")
    String getInterest() {

        log.info("Interests are: ${interests}")

        "{\"value\": ${interests}}"
    }
}

