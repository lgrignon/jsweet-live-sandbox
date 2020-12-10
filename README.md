# jsweet-live-sandbox
Online playground for JSweet. Ideal to try JSweet without having to install anything.

## Compile
mvn clean && mvn -P client generate-sources && mvn package assembly:single

## Run
java -jar target/jsweet-live-sandbox-3.0.0-jar-with-dependencies.jar &

## Browser
Go to www directory with your browser
(Note /transpile must proxy to localhost:18580
