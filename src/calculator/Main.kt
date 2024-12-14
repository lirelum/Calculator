package calculator

import Parser
import calculator.expression.BinaryOperation
import calculator.expression.Literal
import calculator.tokenizer.tokenize

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
    val str = "1+1+1+1+1+1+1"
    val tokens = tokenize(str)
    val parse = Parser(tokens).parse()
    println(parse)
}