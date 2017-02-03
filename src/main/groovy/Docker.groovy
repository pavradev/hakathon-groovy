import de.gesellix.docker.client.DockerClientImpl

/**
 * Created by pavel on 16/10/16.
 */
class Docker {
    String appDir
    String image
    String cmd

    def execute(){
        println "Executing docker -v ${appDir}:/app ${image} ${cmd}"
    }
}
