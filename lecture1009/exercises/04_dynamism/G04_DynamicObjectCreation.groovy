interface Calculator {
    def add(a, b)

    def subtract(a, b)

    def multiply(a, b)

    def divide(a, b)

    def increment(a)
}

final data = [
        add: {a, b -> a + b},
        multiply: {a, b -> a * b},
        increment: {add(it, 1)},
        subtract: {a, b -> add(a, -b)}
]

data.increment.delegate = data
data.subtract.delegate = data

final myCalculator = data as Calculator

assert 10 == myCalculator.add(3, 7)
assert 6 == myCalculator.multiply(2, 3)
assert 6 == myCalculator.increment(5)

//TASK uncomment and see the reported exception
//TASK add the subtract method so that the following line passes
assert 3 == myCalculator.subtract(4, 1)

//TASK Re-implement the increment() method so that it reuses the code of add()

println 'done'