package calculator.expression

import java.math.BigInteger

class Literal(private val value: BigInteger) : Expression {
    override fun invoke(): BigInteger { return value }
}