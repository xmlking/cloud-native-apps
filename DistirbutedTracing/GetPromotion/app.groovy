@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Log
class Application {

    String promo = "No promo available"

    @RequestMapping(value = "/promo", produces = "application/json")
    String promo() {

        "{\"value\": ${promo}}"
    }

}

