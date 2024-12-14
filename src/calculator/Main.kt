package calculator

import calculator.expression.BinaryOperation
import calculator.expression.Literal

fun main(args: Array<String>) {
    val expr = BinaryOperation.Add(
        Literal(1.toBigInteger()),
        BinaryOperation.Mul(
            Literal(2.toBigInteger()),
            Literal(2.toBigInteger())
        )
    )
    val result = expr()
    println(result)
    val str = "1+1+(1+ 3)   / 2/2+2192873198273981723981729832938293-87-9-111(1)+2)"
    val tokens = tokenize(str)
    println(tokens)
}