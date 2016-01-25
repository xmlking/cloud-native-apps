@Grab("spring-cloud-config-monitor")
@Grab("spring-cloud-starter-bus-amqp")

@CompileStatic
@EnableConfigServer
@EnableDiscoveryClient
class ConfigServer {
}
