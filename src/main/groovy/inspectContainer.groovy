import de.gesellix.docker.client.DockerClientImpl

/**
 * Created by pavel on 03/02/17.
 */
def dockerClient = new DockerClientImpl()
List<String> binds = dockerClient.inspectContainer("090").content.HostConfig.Binds
println binds
String appBind = binds.find {it =~ /.*:\/app$/} - ':/app'
println appBind