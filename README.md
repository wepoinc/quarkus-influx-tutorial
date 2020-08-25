# Getting started with Quarkus and InfluxDB to ingest sensor data from a Particle device

This repository contains the code used in the "Getting started with Quarkus and InfluxDB to ingest sensor data from a Particle device" tutorial. The tutorial is divided in two parts:

- [Getting started with Quarkus and InfluxDB to ingest sensor data from a Particle device - Part 1](https://medium.com/wepoinc/getting-started-with-quarkus-and-influxdb-to-ingest-sensor-data-from-a-particle-device-part-1-6816c66ee94?source=friends_link&sk=b04986f369dd34ed83e6128bcfe6e0a8)
- [Getting started with Quarkus and InfluxDB to ingest sensor data from a Particle device - Part 2](https://medium.com/wepoinc/getting-started-with-quarkus-and-influxdb-to-ingest-sensor-data-from-a-particle-device-part-2-d39f8e2a7777?source=friends_link&sk=d417053dcf1f7e24ee921f7601dd39f9)

The code for Part 1 of the tutorial can be found in the branch `part-1`.


## Building and running the application

The application can be built using `./mvnw clean package -DskipTests=true`.
It produces the `tutorial-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.

The application is now runnable using `java -Dfile.encoding=UTF-8 -jar target/tutorial-1.0.0-SNAPSHOT-runner.jar`.
