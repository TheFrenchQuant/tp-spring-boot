#!/bin/sh

gnome-terminal -e "taskset -c 0 java -jar eureka/target/eureka-0.0.1-SNAPSHOT.jar"

gnome-terminal -e "taskset -c 0 java -jar admin/target/admin-0.0.1-SNAPSHOT.jar"

gnome-terminal -e "taskset -c 1,2  java -jar product/product-service/target/product-service-0.0.1-SNAPSHOT.jar"

gnome-terminal -e "taskset -c 3 java -jar invoice/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar --server.port=8081"

gnome-terminal -e "taskset -c 4,5 java -jar loadbalancer/target/loadbalancer-0.0.1-SNAPSHOT.jar"

gnome-terminal -e "/home/franck/dev/apache-jmeter-5.5/bin/jmeter"