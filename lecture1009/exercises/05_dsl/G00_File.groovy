File.metaClass.div = { path ->
     new File(delegate, path) 
}  

String.metaClass.div = { path ->
     new File(delegate, path) 
}  

def file = new File(".")/'test'/'hello'/'file.txt'
println file.exists()

file = "."/'test'/'hello'/'file.txt'
println file.exists()

//TASK Allow for omiting apostrophes for plain file names
file = "."/test/hello/'file.txt'
println file.exists()

def propertyMissing(String name) {
    return name;
}