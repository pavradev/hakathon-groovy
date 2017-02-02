/**
 * Created by pavel on 16/10/16.
 */
class Docker {
    String image
    String cmd

    def execute(){
        println "Executing docker ${image} ${cmd}"
    }
}
