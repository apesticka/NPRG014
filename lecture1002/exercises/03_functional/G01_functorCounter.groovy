class Counter<A> {
    A value
    
    //f: Integer -> Integer
    def <B> Counter<B> map(Closure<B> f) {
        //TASK Implement map
        return new Counter<B>(value: f(value))
    }
}
def increment = {it + 1}

def c = new Counter(value: 0)
def r1 = c.map(increment)
println r1.value

def r2 = c.map(increment).map{2*it}
println r2.value

//TASK Make Counter generic, so that it can hold values not only of type Integer, but any type A.
println new Counter(value: "foo").map {it.reverse()}.value