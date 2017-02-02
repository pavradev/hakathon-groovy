import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.runtime.InvokerHelper

/**
 * Created by pavel on 17/10/16.
 */

ClassLoader classLoader = this.class.classLoader

CompilerConfiguration config = new CompilerConfiguration(CompilerConfiguration.DEFAULT)
config.scriptBaseClass = 'PipelineScript'

GroovyClassLoader groovyClassLoader = new GroovyClassLoader(classLoader, config)

//String path = "/Users/pavel/repositories/cucumber-demo/pipeline"
//String path = "/mydsl/src/main/groovy/pipeline.groovy"
//String path = "/Users/pavel/repositories/mydsl/src/main/groovy/pipeline.groovy"
String path = "/workspace/pipeline"
String text = new File(path).text

Class pipeline = groovyClassLoader.parseClass(text, 'script')

Binding binding = new Binding();
Script script = InvokerHelper.createScript(pipeline, binding)
script.run()