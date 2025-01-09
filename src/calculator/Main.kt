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
        print(">> ")
        when (val expr = readln()) {
            "quit", "q" -> break
            else -> processInput(expr)
        }?.let(::println)
    }
}

fun processInput(expr: String): BigInteger? {
    return try {
        tokenize(expr)
    }
    catch (e: Exception) {
        System.err.println("Tokenizer error. Did you input a weird character?\n ${e.message}\n")
        null
    }?.let {
        try {
            (Parser(it).parse())()
        }
        catch (e: Exception) {
            System.err.println("Parse error. Perhaps your syntax is incorrect?\n ${e.message}\n")
            null
        }
    }
}