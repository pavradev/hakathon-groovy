import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.runtime.InvokerHelper

/**
 * Created by pavel on 02/02/17.
 */
class DslScriptLoader {

    void runScripts(Collection<String> scripts) {
        ClassLoader parentClassLoader = DslScriptLoader.classLoader

        CompilerConfiguration config = createCompilerConfiguration()

        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(parentClassLoader, config)

        try {
            runScriptsWithClassLoader(scripts, groovyClassLoader, config)
        } finally {
            if (groovyClassLoader instanceof Closeable) {
                ((Closeable) groovyClassLoader).close()
            }
        }
    }

    def runScriptsWithClassLoader(Collection<String> scripts, GroovyClassLoader groovyClassLoader, CompilerConfiguration compilerConfiguration) {
        scripts.each { s ->
            String text = new File(s).text
            Class pipeline = groovyClassLoader.parseClass(text, 'script')
            Binding binding = new Binding();
            Script script = InvokerHelper.createScript(pipeline, binding)
            script.run()
        }
    }

    private CompilerConfiguration createCompilerConfiguration() {
        CompilerConfiguration config = new CompilerConfiguration(CompilerConfiguration.DEFAULT)
        config.scriptBaseClass = 'PipelineScript'
        config
    }


    static void main(String[] args) throws Exception {
        if (args.length == 0) {
            println 'Script name is required'
            return
        }

        args.each { String scriptName ->
            String scriptPath = new File(scriptName).absolutePath
            new DslScriptLoader().runScripts([scriptPath])
        }
    }
}
