import groovy.json.JsonBuilder
import groovy.json.JsonOutput


def rowBuilder = {String measurement ->
    query 'some complex query'
    name measurement
}

def specialRow = rowBuilder >> {String measurement ->
    name "special-$measurement"
}

def simple = {Collection measurements ->
    measurements.collect {
        rowBuilder.curry(it)
    }
}

def jvmMeasurements = ['m1', 'm2', 'm3']

JsonBuilder builder = new JsonBuilder()
builder.call {
    name 'Dashboard'
    type 'Simple'
    rows jvmMeasurements, specialRow
}


String json = JsonOutput.prettyPrint(builder.toString())
println json