package calculator

import Parser
import calculator.tokenizer.tokenize

fun main() {
    val expr2 = "(1)"
    val parse = Parser(tokenize(expr2)).parse()
    println(parse())
}