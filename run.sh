#!/bin/bash

export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/
mvn clean && mvn -P client generate-sources && mvn package

nohup sh -c "$JAVA_HOME/bin/java -jar target/jsweet-live-sandbox-3.0.0-jar-with-dependencies.jar" &
