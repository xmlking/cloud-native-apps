@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-cloud-starter-hystrix-dashboard")
@groovy.transform.CompileStatic
@EnableDiscoveryClient
@EnableHystrixDashboard
class HystrixDashboard {
}
