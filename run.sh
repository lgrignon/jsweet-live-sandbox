#!/bin/bash

mvn clean && mvn -P client generate-sources && mvn package

nohup sh -c 'java -jar target/jsweet-live-sandbox-0.0.1-SNAPSHOT.jar' &
