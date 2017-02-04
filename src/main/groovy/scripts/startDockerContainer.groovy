package scripts

import de.gesellix.docker.client.DockerClientImpl

//ocker run --rm -v /Users/pavel/repositories/hakathon-groovy:/app -w /app maven:3-jdk-8 mvn clean install
def rootPath = new File("").absolutePath
println rootPath

def dockerClient = new DockerClientImpl()
def image = "maven:3-jdk-8"
def tag = "3-jdk-8"
def cmds = ["mvn", "info"]
def containerConfig = ["Cmd"       : cmds,
                       //"Volumes"   : ["$rootPath" : "/usr/src/mymaven"],
                       "WorkingDir": "/usr/src/mymaven",
                       "HostConfig": [
                               "Binds": ["$rootPath:/usr/src/mymaven"]
                       ]]
def result = dockerClient.run(image, containerConfig).content
print result
//dockerClient.stop()
