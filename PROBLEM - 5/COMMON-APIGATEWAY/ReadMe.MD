PURPOSE OF GATEWAY-SERVICE
--------------------------
Zuul is (of course) our gatekeeper to the outside world, 
not allowing any unauthorized external requests pass through. Zulu also provides a well-known entry point to the micro services in the system landscape. Using dynamically allocated ports is convenient to avoid port conflicts and to minimize administration but it makes it of course harder for any given service consumer. Zuul uses Ribbon to look up available services and routes the external request to an appropriate service instance.

ADD-ON for FALLBACK
-------------------
1. Hystrix is a circuit-breaker. Hystrix lets you define a fallback method that gets invoked if your network calls to another microservice fails. 
It reverts back to normal behavior once the service is available again.
2. Ribbon is added for loadbalancing

