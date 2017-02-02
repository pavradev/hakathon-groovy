import de.gesellix.docker.client.DockerClientImpl

def dockerClient = new DockerClientImpl()
def image = "ekino/wiremock"
def tag = "2.1.11"
def containerConfig = ["ExposedPorts": ["8080/tcp": [:]],
                           "HostConfig"  : ["PortBindings": [
                                   "8080/tcp": [
                                           ["HostIp"  : "0.0.0.0",
                                            "HostPort": "4712"]]
                           ]]]
def result = dockerClient.run(image, containerConfig, tag).content
