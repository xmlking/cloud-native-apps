@Grab("spring-cloud-starter-bus-amqp")
@Grab("spring-cloud-starter-turbine")

import org.springframework.cloud.netflix.turbine.EnableTurbine

@CompileStatic
@EnableTurbine
@EnableDiscoveryClient
class Turbine {
}