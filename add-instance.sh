#!/bin/sh

gnome-terminal -e "taskset -c 6 java -jar invoice/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar --server.port=8082"

#gnome-terminal -e "taskset -c 7 java -jar invoice/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar --server.port=8083"


