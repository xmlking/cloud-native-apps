@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Log
class Application {


    int credit = 700

    @RequestMapping(value = "/credit", produces = "application/json")
    String getCredit() {
        credit = 1000 * Math.random()
        log.info("Produced a value: ${credit}")

        "{\"value\": ${credit}}"
    }
}