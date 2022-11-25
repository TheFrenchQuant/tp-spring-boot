#!/bin/sh
#En fait pas vraiment nécessaire

gnome-terminal --"java -jar eureka/target/eureka-0.0.1-SNAPSHOT.jar"

gnome-terminal --"java -jar admin/target/admin-0.0.1-SNAPSHOT.jar"

gnome-terminal --"java -jar product/target/product-0.0.1-SNAPSHOT.jar"

gnome-terminal --"java -jar invoice/target/invoice-0.0.1-SNAPSHOT.jar --server.port=8081"

gnome-terminal --"java -jar invoice/target/invoice-0.0.1-SNAPSHOT.jar --server.port=8082"

gnome-terminal --"java -jar invoice/target/invoice-0.0.1-SNAPSHOT.jar --server.port=8082"

gnome-terminal --"java -jar loadbalancer/target/loadbalancer-0.0.1-SNAPSHOT.jar"