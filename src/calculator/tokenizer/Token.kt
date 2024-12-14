package calculator.tokenizer

import calculator.expression.Operation
import java.math.BigInteger

sealed class Token {
    data class Literal(val value: BigInteger) : Token()
    data object Add : Token()
    data object Sub : Token()
    data object Mul : Token()
    data object Div : Token()
    data object Rem : Token()
    data object LeftParen : Token()
    data object RightParen : Token()
}