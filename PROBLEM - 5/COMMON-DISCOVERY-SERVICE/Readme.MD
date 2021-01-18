PURPOSE OF CONFIGURATION-SERVICE
--------------------------
Eureka server acts as the registry to which other services register themselves. 
When one microservice needs to communicate with another, 
it can query Eureka to find the active instance of that microservice.

ADD-ON for FALLBACK
-------------------
1. Hystrix is a circuit-breaker. Hystrix lets you define a fallback method that gets invoked if your network calls to another microservice fails. 
It reverts back to normal behavior once the service is available again.
2. Ribbon is added for loadbalancing


PS D:\MY FOLDER\SOURCE CODE\BAGUS_POC\DOCKER\latest_source\COMMON-DISCOVERY-SERVICE> ls


    Directory: D:\MY FOLDER\SOURCE CODE\BAGUS_POC\DOCKER\latest_source\COMMON-DISCOVERY-SERVICE


Mode                LastWriteTime         Length Name
----                -------------         ------ ----
d-----       15-04-2020  04.10 PM                src
d-----       22-04-2020  01.44 AM                target
-a----       30-10-2019  11.30 AM           2017 .classpath
-a----       21-01-2019  04.38 AM            268 .gitignore
-a----       30-09-2019  09.52 AM           1461 .project
-a----       22-04-2020  08.09 AM           6925 docker-compose-2.yml
-a----       22-04-2020  08.13 AM           2867 docker-compose.yml
-a----       13-04-2020  12.17 PM            119 Dockerfile-redis.yml
-a----       22-04-2020  01.53 AM            199 Dockerfile.yml
-a----       21-01-2019  04.38 AM           9113 mvnw
-a----       21-01-2019  04.38 AM           5810 mvnw.cmd
-a----       22-04-2020  01.43 AM           2873 pom.xml


PS D:\MY FOLDER\SOURCE CODE\BAGUS_POC\DOCKER\latest_source\COMMON-DISCOVERY-SERVICE> docker-compose -f docker-compose.yml up
ERROR: Service 'configserver' depends on service 'serviceregistry' which is undefined.
PS D:\MY FOLDER\SOURCE CODE\BAGUS_POC\DOCKER\latest_source\COMMON-DISCOVERY-SERVICE> docker-compose -f docker-compose.yml up
ERROR: A service can not link to itself: configserver
PS D:\MY FOLDER\SOURCE CODE\BAGUS_POC\DOCKER\latest_source\COMMON-DISCOVERY-SERVICE> docker-compose -f docker-compose.yml up

