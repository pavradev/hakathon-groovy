# mydsl
Build framework based on docker and groovy

##Building
```
./gradlew oneJar
```
##Create docker image
```
docker build --rm -t "mydsl:latest" .
```
##Launch for your applications
1. Add pipeline.groovy script to the root folder
```
stage("build") {
    mvn "clean install"
}
```
2. Run 'mydsl' docker container:
```
docker run -v $PWD:/app -v /var/run/docker.sock:/var/run/docker.sock mydsl:latest
```
