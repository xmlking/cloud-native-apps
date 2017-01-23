@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Log
class Application {


    String reco = "Shawshank Redemption"

    @RequestMapping(value = "/reco", produces = "application/json")
    String reco() {

        "{\"value\": ${reco}}"
    }

}