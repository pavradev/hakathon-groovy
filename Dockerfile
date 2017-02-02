FROM groovy:latest

COPY . /mydsl

CMD ["/root/.sdkman/candidates/groovy/current/bin/groovy", "-cp", "/mydsl/src/main/groovy/", "/mydsl/src/main/groovy/scriptLoader.groovy"]

