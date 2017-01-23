@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Log
class Application {


    String address = "california usa"

    @RequestMapping(value = "/add", produces = "application/json")
    String getAddress() {

        log.info("Address is : ${address}")

        "{\"value\": ${address}}"
    }
}

