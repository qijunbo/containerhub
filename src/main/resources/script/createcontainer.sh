#!/bin/sh
name=$1
docker container prune -f
docker run --name $name -d -P sunway/lims:1

#docker run --name $name -d -P -v ~/docker/webapp:/root/webapp qijunbo/java:8
