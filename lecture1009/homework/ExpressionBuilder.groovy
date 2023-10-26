// 2022/2023
// TASK The MarkupBuilder in Groovy can transform a hierarchy of method calls and nested closures into a valid XML document.
// Create a NumericExpressionBuilder builder that will read a user-specified hierarchy of simple math expressions and build a tree representation of it.
// The basic arithmetics operations as well as the power (aka '^') operation must be supported.
// It will feature a toString() method that will pretty-print the expression tree into a string with the same semantics, as verified by the assert on the last line.
// This means that parentheses must be placed where necessary with respect to the mathematical operator priorities.
// Change or add to the code in the script. Reuse the infrastructure code at the bottom of the script.
class NumericExpressionBuilder extends BuilderSupport {

    Item root

    @Override
    String toString() {
        root
    }

    @Override
    protected void setParent(Object parent, Object child) {
        if (parent as OperatorItem == null) throw new Exception("Only operator nodes can have child nodes")
        root ?= parent
        parent.addChild(child)
    }

    @Override
    protected Object createNode(Object name) {
        return createNode(name, null, null)
    }

    @Override
    protected Object createNode(Object name, Object value) {
        createNode(name, null, value)
    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        createNode(name, attributes, null)
    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        Item node = switch (name) {
            case "+" -> new AdditionOperator()
            case "-" -> new SubtractionOperator()
            case "*" -> new MultiplicationOperator()
            case "/" -> new DivisionOperator()
            case "power" -> new ExponentiationOperator()
            case "number" -> new NumberItem(value: attributes.value)
            case "variable" -> new VariableItem(name: attributes.value)
            default -> throw new Exception("$name is not a valid node name")
        }

        return node
    }
}

abstract class Item {
    abstract String toString();
    abstract int getPriority();
}

class NumberItem extends Item {
    def value;

    @Override String toString() { return value }
    @Override int getPriority() { return Integer.MAX_VALUE }
}

class VariableItem extends Item {
    String name

    @Override String toString() { return name }
    @Override int getPriority() { return Integer.MAX_VALUE }
}

abstract class OperatorItem extends Item {
    abstract void addChild(Item child);
}

abstract class BinaryOperator extends OperatorItem {
    Item left
    Item right

    abstract String getSymbol();

    String printAsChild(Item child) {
        child.priority < priority ? "($child)" : "$child"
    }

    @Override
    String toString() {
        return "${printAsChild(left)} $symbol ${printAsChild(right)}"
    }

    @Override
    void addChild(Item child) {
        if (left == null) left = child
        else if (right == null) right = child
        else throw new Exception("A binary operator can only have 2 child nodes")
    }
}

class AdditionOperator extends BinaryOperator {
    @Override int getPriority() { 0 }
    @Override String getSymbol() { "+" }
}

class SubtractionOperator extends BinaryOperator {
    @Override int getPriority() { 0 }
    @Override String getSymbol() { "-" }
}

class MultiplicationOperator extends BinaryOperator {
    @Override int getPriority() { 1 }
    @Override String getSymbol() { "*" }
}

class DivisionOperator extends BinaryOperator {
    @Override int getPriority() { 1 }
    @Override String getSymbol() { "/" }
}

class ExponentiationOperator extends BinaryOperator {
    @Override int getPriority() { 2 }
    @Override String getSymbol() { "^" }
}

//------------------------- Do not modify beyond this point!

def build(builder, String specification) {
    def binding = new Binding()
    binding['builder'] = builder
    new GroovyShell(binding).evaluate(specification)
}

//Custom expression to display. It should be eventually pretty-printed as 10 + x * (2 - 3) / 8 ^ (9 - 5)
String description = '''
builder.'+' {
    number(value: 10)
    '*' {
        variable(value: 'x')
        '/' {
            '-' {
                number(value: 2)
                number(value: 3)
            }
            power {
                number(value: 8)
                '-' {
                    number(value: 9)
                    number(value: 5)
                }
            }
        }
    }
}
'''

//XML builder building an XML document
build(new groovy.xml.MarkupBuilder(), description)

//NumericExpressionBuilder building a hierarchy of Items to represent the expression
def expressionBuilder = new NumericExpressionBuilder()
build(expressionBuilder, description)
def expression = expressionBuilder.rootItem()
println (expression.toString())
assert '10 + x * (2 - 3) / 8 ^ (9 - 5)' == expression.toString()