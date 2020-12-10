# jsweet-live-sandbox ![CI](https://github.com/lgrignon/jsweet-live-sandbox/workflows/CI/badge.svg)

Online playground for JSweet. Ideal to try JSweet without having to install anything.

## Compile
```
mvn clean && mvn -P client generate-sources && mvn package assembly:single
```

## Run
```
java -jar target/jsweet-live-sandbox-3.0.0-jar-with-dependencies.jar &
```

## Local dev: browser
Browse `www` using a local web server
(Note `/transpile` must proxy to `localhost:18580`)
