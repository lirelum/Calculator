package calculator

import Parser
import calculator.expression.BinaryOperation
import calculator.expression.Literal
import calculator.tokenizer.tokenize

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
    val str = "1+1+2-3-234234/12341231"
    val tokens = tokenize(str)
    val parse = Parser(tokens).parse()
    println(parse)
}