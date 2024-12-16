package calculator

import Parser
import calculator.tokenizer.tokenize
import java.math.BigInteger

fun main() {
    println("This calculator was written as a learning exercise in recursive descent parsers.")
    println("It will calculate the value of arithmetic expressions consisting of parentheses,")
    println("addition, subtraction, multiplication and division.")
    println("It implements basic operator precedence. Multiplication and division are calculated")
    println("before addition and subtraction, and from left to right.")
    println("Enter 'quit' or 'q' to exit")
    while (true) {
        System.err.flush()
        System.out.print(">> ")
        val expr = readln()
        val result = when (expr) {
            "quit", "q" -> break
            else -> processInput(expr)
        }
        result?.let(::println)
    }
}

fun processInput(expr: String): BigInteger? {
    val tokens = try {
        tokenize(expr)
    }
    catch (e: Exception) {
        System.err.println("Tokenizer error. Did you input a weird character?\n ${e.message}\n")
        null
    }
    return tokens?.let {
        try {
            (Parser(it).parse())()
        }
        catch (e: Exception) {
            System.err.println("Parse error. Perhaps your syntax is incorrect?\n ${e.message}\n")
            null
        }
    }
}