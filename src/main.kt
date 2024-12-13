fun main() {
    val expr = add(Literal(1), mul(Literal(2), Literal(2)))
    val result = expr()
    println(result)
    val str = "(1+2)/3"
    val tokens = tokenize(str)
    println(tokens)
}