import core.Context
import de.gesellix.docker.client.DockerClientImpl
import tasks.DockerTask
import tasks.MavenTask

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by pavel on 16/10/16.
 */
abstract class PipelineScript extends Script {
    Context context

    PipelineScript() {
        def dockerClient = new DockerClientImpl()
        context = new Context(dockerClient: dockerClient)

        def id = System.getenv("HOSTNAME")
        if (id) { //in Docker environment
            println id
            List<String> binds = dockerClient.inspectContainer(id).content.HostConfig.Binds
            String appDir = binds.find { it =~ /.*:\/app$/ } - ':/app'
            context.appDir = appDir
        } else {
            context.appDir = new File("").absolutePath
        }
        if(Files.notExists(Paths.get(context.appDir))){
            throw new RuntimeException("pipeline.groovy file not found in the application root")
        }
    }

    def stage(String name, Closure c) {
        println "Entering stage ${name}"
        c.delegate = this;
        c.resolveStrategy = Closure.DELEGATE_FIRST
        c()
        println "Exiting stage ${name}"
    }

    def docker(Closure c) {
        DockerTask d = new DockerTask(context)
        c.delegate = d
        c.resolveStrategy = Closure.DELEGATE_FIRST
        c.call()
        d.execute()
    }

    def mvn(String command) {
        MavenTask mvn = new MavenTask(context);
        mvn.cmd = "mvn $command";
        mvn.execute();
    }

}
