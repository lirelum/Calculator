package calculator

import calculator.expression.Operation
import java.math.BigInteger

sealed class RawToken {
    data class Literal(val value: String) : RawToken() {
        override fun eval(): Token {
            return Token.Literal(BigInteger(value))
        }
    }
    data class Operator(val value: String) : RawToken() {
        override fun eval(): Token {
           return when (value) {
               "+" -> Token.Operator(Operation.ADD)
               "-" -> Token.Operator(Operation.SUB)
               "*" -> Token.Operator(Operation.MUL)
               "/" -> Token.Operator(Operation.DIV)
               "%" -> Token.Operator(Operation.REM)
               else -> throw IllegalArgumentException("Unknown operator: $value")
           }
        }
    }

    data object LeftParen : RawToken() {
        override fun eval(): Token {
           return Token.LeftParen
        }
    }

    data object RightParen : RawToken() {
        override fun eval(): Token {
           return Token.RightParen
        }
    }

    abstract fun eval() : Token
    operator fun invoke() : Token { return this.eval() }
}