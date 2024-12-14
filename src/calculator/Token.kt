package calculator

import calculator.expression.Operation
import java.math.BigInteger

sealed class Token {
    data class Literal(val value: BigInteger) : Token()
    data class Operator(val operator: Operation) : Token()
    data object LeftParen : Token()
    data object RightParen : Token()
}