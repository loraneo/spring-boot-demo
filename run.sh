#!/bin/bash

docker network create demo || echo "Already exists"
docker rm -f zookeeper
docker rm -f kafka
docker rm -f postgres
docker rm -f kafka-connect
docker rm -f debezium-logstash


docker run -d --name zookeeper \
	--network demo \
	-p 2181:2181 \
	loraneo/docker-zookeeper:3.4.10a

docker run -d --name kafka \
	--network demo \
	-p 9092:9092 \
	loraneo/docker-kafka-broker:1.0.0a


docker run -d --name postgres \
	--network demo \
	-p 5432:5432 \
	loraneo/docker-postgres:9.6a


docker run -d --name kafka-connect \
	--network demo \
	-p 8083:8083 \
	loraneo/docker-kafka-connect:1.0.0a
	
docker rm -f debezium-logstash	
docker run -d --name debezium-logstash \
	--network demo \
	loraneo/docker-debezium-logstash:5.6.3a


until $(curl --output /dev/null --silent --fail -X POST -H "Content-Type: application/json" --data "@debezium-postgres.json" 127.0.0.1:8083/connectors); do
    printf '.'
    sleep 5
done

	
