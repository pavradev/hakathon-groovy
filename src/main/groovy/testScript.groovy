/**
 * Created by pavel on 02/02/17.
 */
stage("build") {
    stage("step1") {
        mvn "clean install"
    }
    stage("step2") {
        docker {
            image = "python"
            cmd = "myscript.py"
        }
    }
}

docker {
    image = "java:8"
    cmd = "compile"
}