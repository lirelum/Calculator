package calculator.tokenizer

import java.math.BigInteger

sealed class RawToken {
    data class Literal(val value: String) : RawToken() {
        override fun invoke(): Token {
            return Token.Literal(BigInteger(value))
        }
    }

    data class Operator(val value: String) : RawToken() {
        override fun invoke(): Token {
            return when (value) {
                "+" -> Token.Add
                "-" -> Token.Sub
                "*" -> Token.Mul
                "/" -> Token.Div
                "%" -> Token.Rem
                else -> throw IllegalArgumentException("Unknown operator: $value")
            }
        }
    }

    data object LeftParen : RawToken() {
        override fun invoke(): Token {
            return Token.LeftParen
        }
    }

    data object RightParen : RawToken() {
        override fun invoke(): Token {
            return Token.RightParen
        }
    }

    abstract operator fun invoke(): Token
}