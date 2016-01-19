Zuul
-----
**Zuul** implements *API gateway* pattern to handles all incoming requests to backend services. 
It is used in combination with other Spring Cloud components like **Ribbon** and **Hystrix** to provide a flexible and 
resilient routing tier for backend services.

You can configure filters dynamically loaded into Zuul to perform the following functions:

* Authentication and security: Identifying authentication requirements for each resource and rejecting requests that do not satisfy them.
* Insights and monitoring: Tracking meaningful data and statistics at the edge in order to give us an accurate view of production.
* Dynamic routing: Dynamically routing requests to different back-end clusters as needed.
* Stress testing: Gradually increasing the traffic to a cluster in order to gauge performance.
* Load shedding: Allocating capacity for each type of request and dropping requests that go over the limit.
* Static response handling: Building some responses directly at the edge instead of forwarding them to an internal cluster.
* Multiregion resiliency: Routing requests across AWS regions in order to diversify our ELB usage and move our edge closer to our members.

![Alt text](../deckset/images/api-gateway-pattern-fig7.png)

```
http://localhost:8765/health

http://localhost:8765/producer
```