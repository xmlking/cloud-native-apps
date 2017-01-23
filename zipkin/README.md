Zipkin Server
-------------

To start zipkin server

cd zipkin
spring run .

http://localhost:8988

To experiment with zipkin

Start the below apps in the specified order

zookeeper > kafka > eureka > config > zipkin > producer > creditinfo >
getaddress > promo > reco > hystrix > mybatis > aggregator

p.s find projects under Distributed tracing folder