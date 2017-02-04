package tasks

import core.Context

/**
 * Created by pavel on 16/10/16.
 */
class DockerTask implements Task{
    Context context
    String image
    String cmd

    DockerTask(Context context) {
        this.context = context;
    }

    def execute() {
        println "Executing: docker run --rm -v ${context.appDir}:/app -w /app ${image} ${cmd}"
        def containerConfig = ["Cmd"       : cmd.split(),
                               "WorkingDir": "/app",
                               "HostConfig": [
                                       "Binds": ["$context.appDir:/app"]
                               ]]
        context.dockerClient.run(image, containerConfig)
    }
}
