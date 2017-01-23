@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Log
class Application {

    String caption = "All is well"

    @RequestMapping(value = "/caption", produces = "application/json")
    String getCaption() {

        log.info("Caption is : ${caption}")

        "{\"value\": ${caption}}"
    }

}