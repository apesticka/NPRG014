//TASK Define the unless (aka if not) method

def unless(boolean condition, Closure code) {
    if (!condition) code()
}

unless(1 > 5) {
    println "Condition not satisfied!"
    def value = 10
    println "Value is $value"
}

println 'done'