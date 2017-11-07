#!/bin/bash

mkdir target || echo "Already there"
cd target

docker network create demo || echo "Already exists"

build() {
	echo "Building $1/$2:$3"
	if [ ! -d "$2" ]; then
	 	git clone https://github.com/$1/$2
	fi
	cd $2
	git pull
	docker build -t $1/$2:$3 .
	cd ..
}

build loraneo docker-os 7.3a
build loraneo docker-java 8u144a

build loraneo docker-zookeeper 3.4.10a
docker rm -f zookeeper
docker run -d --name zookeeper \
	--network demo \
	-p 2181:2181 \
	loraneo/docker-zookeeper:3.4.10a

build loraneo docker-kafka-broker 1.0.0a
docker rm -f kafka
docker run -d --name kafka \
	--network demo \
	-p 9092:9092 \
	loraneo/docker-kafka-broker:1.0.0a

build loraneo docker-postgres 9.6a
docker rm -f database-server
docker run -d --name database-server \
	--network demo \
	-p 5432:5432 \
	loraneo/docker-postgres:9.6a

build loraneo docker-debezium-connect 1.0.0a
docker rm -f kafka-connect
docker run -d --name kafka-connect \
	--network demo \
	-p 8083:8083 \
	loraneo/docker-debezium-connect:1.0.0a
	
build loraneo docker-debezium-logstash 5.6.3a
docker rm -f debezium-logstash	
docker run -d --name debezium-logstash \
	--network demo \
	loraneo/docker-debezium-logstash:5.6.3a


cd ..
until $(curl --output /dev/null --silent --fail -X POST -H "Content-Type: application/json" --data "@debezium-postgres.json" 127.0.0.1:8083/connectors); do
    printf '.'
    sleep 5
done
	

