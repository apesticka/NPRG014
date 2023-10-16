String.metaClass.backToFront = {->
    delegate[-1..0]
}

println 'cimanyd si yvoorG'.backToFront()



//TASK define a starTrim() method to surround the original trimmed string with '*' 

def str = '   core   '
str.metaClass.starTrim = {->
    return '*' + delegate.trim() + '*'
}

println str.starTrim()
assert '*core*' == '   core   '.starTrim()

println 'done'


















