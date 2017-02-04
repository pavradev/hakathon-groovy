package tasks

import core.Context

class MavenTask extends DockerTask{
    MavenTask(Context context) {
        super(context)
        image = 'maven:3-jdk-8'
    }
}
