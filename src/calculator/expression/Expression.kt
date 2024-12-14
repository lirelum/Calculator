package calculator.expression

import java.math.BigInteger

fun interface Expression {
    operator fun invoke(): BigInteger
}