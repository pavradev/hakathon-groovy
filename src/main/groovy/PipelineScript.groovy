/**
 * Created by pavel on 16/10/16.
 */
abstract class PipelineScript extends Script{
    List<Docker> commands = []

    def stage(String name, Closure c){
        println "Entering stage ${name}"
        c.delegate = this;
        c.resolveStrategy = Closure.DELEGATE_FIRST
        c()
        println "Exiting stage ${name}"
    }

    def docker(Closure c){
        Docker d = new Docker()
        c.delegate = d
        c.resolveStrategy = Closure.DELEGATE_FIRST
        c.call()
        d.execute()
    }

    def mvn(String command){
        docker {
            image = 'maven:3-jdk-8'
            cmd = command
        }
    }

    def shout(String s){
        println s
    }

}
