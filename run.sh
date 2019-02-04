#!/bin/bash

mvn clean && mvn -P client generate-sources && mvn package

nohup java target/jsweet-live-sandbox-0.0.1-SNAPSHOT-shaded.jar &