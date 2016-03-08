//@Grab("org.springframework.amqp:spring-amqp:1.5.4.BUILD-SNAPSHOT")
@Grab("spring-cloud-config-monitor")
@Grab("spring-cloud-starter-bus-amqp")

@groovy.transform.CompileStatic
@EnableConfigServer
@EnableDiscoveryClient
class ConfigServer {
}
