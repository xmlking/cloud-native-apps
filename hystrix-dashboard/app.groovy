@Grab("org.springframework.kafka:spring-kafka:1.1.1.RELEASE")
@Grab("org.springframework.integration:spring-integration-kafka:2.1.0.RELEASE")
@Grab("org.apache.kafka:kafka_2.11:0.10.0.0")
@GrabExclude("org.slf4j:slf4j-log4j12")
@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-sleuth-stream")
@Grab("spring-cloud-starter-consul-discovery")
@Grab("spring-cloud-starter-hystrix")
//@Grab("spring-cloud-starter-consul-config")
@Grab("spring-cloud-starter-hystrix-dashboard")
@groovy.transform.CompileStatic
@EnableDiscoveryClient
@EnableHystrixDashboard
class HystrixDashboard {
}
