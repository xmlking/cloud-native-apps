@Grab("spring-cloud-starter-bus-kafka")
@Grab("zipkin-server")
@Grab("zipkin-autoconfigure-ui")
@Grab("spring-cloud-sleuth-zipkin-stream")

import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer



@EnableZipkinStreamServer
class ZipKinServer {
}