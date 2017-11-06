#!/bin/bash
set -e

mvn clean package
docker rm -f demo-backend || echo "Not yet running" 
docker build -t loraneo/demo-backend demo-backend/
docker run -p 8080:8080 --name demo-backend --network demo loraneo/demo-backend