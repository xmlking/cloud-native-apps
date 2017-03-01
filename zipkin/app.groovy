@Grab("org.springframework.kafka:spring-kafka:1.1.1.RELEASE")
@Grab("org.springframework.integration:spring-integration-kafka:2.1.0.RELEASE")
@Grab("org.apache.kafka:kafka_2.11:0.10.0.0")
@GrabExclude("org.slf4j:slf4j-log4j12")
@Grab("spring-cloud-starter-bus-kafka")
@Grab("zipkin-server")
@Grab("zipkin-autoconfigure-ui")
@Grab("spring-cloud-sleuth-zipkin-stream")

import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer



@EnableZipkinStreamServer
class ZipKinServer {
}