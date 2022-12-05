#!/bin/sh
#En fait pas vraiment nécessaire

taskset -c 0 java -jar eureka/target/eureka-0.0.1-SNAPSHOT.jar

taskset -c 1 java -jar admin/target/admin-0.0.1-SNAPSHOT.jar

taskset -c 2 java -jar product/product-service/target/product-service-0.0.1-SNAPSHOT.jar

taskset -c 3 java -jar invoice/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar --server.port=8081

taskset -c 4 java -jar invoice/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar --server.port=8082

taskset -c 5 java -jar invoice/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar --server.port=8083

taskset -c 6 java -jar loadbalancer/target/loadbalancer-0.0.1-SNAPSHOT.jar

