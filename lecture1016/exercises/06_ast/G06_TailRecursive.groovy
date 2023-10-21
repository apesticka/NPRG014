@groovy.transform.TailRecursive
private def factorial(BigDecimal n, BigDecimal accumulator) {
    if (n < 2) accumulator
    else factorial(n - 1, accumulator * n)
}

def fact(BigDecimal n) {
    return factorial(n, 1)
}

println fact(5)

//TASK Make the function tail recursive so that it can pass the following line
println fact(10000)