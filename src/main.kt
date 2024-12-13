fun main() {
    val expr = BinaryOperation.Add(
        Literal(1.toBigInteger()),
        BinaryOperation.Mul(
            Literal(2.toBigInteger()),
            Literal(2.toBigInteger())
        )
    )
    val result = expr()
    println(result)
}