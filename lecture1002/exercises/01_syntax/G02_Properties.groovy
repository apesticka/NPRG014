class City {
    String name
    int size
    boolean capital = false
    
    static def create(String n, int v, boolean e = false) {
        return new City(name: n, size: v, capital: e)
    }
    
    @Override String toString() {
        return "${capital?"Capital c":"C"}ity of ${name}, population: ${size}"
    }
}

println City.create("Brno", 400000).dump()
println City.create("Praha", 1300000, true).dump()

City pisek = new City(name: 'P�sek', size: 25000, capital: false)
City tabor = new City(size: 35000, capital: false, name: 'T�bor')
def praha = City.create("Praha", 1300000, true)

println pisek.dump()
pisek.size = 25001
println pisek.dump()

println tabor
//TASK Provide a customized toString() method to print the name and the population
assert 'City of P�sek, population: 25001' == pisek.toString()
assert 'Capital city of Praha, population: 1300000' == praha.toString()