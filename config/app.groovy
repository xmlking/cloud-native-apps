@Grab("spring-cloud-config-monitor")
@Grab("spring-cloud-starter-bus-kafka")
@groovy.transform.CompileStatic
@EnableConfigServer
@EnableDiscoveryClient
class ConfigServer {
}
