package calculator.expression

import java.math.BigInteger

enum class Operation {
    ADD,
    SUB,
    MUL,
    DIV,
    REM;

    operator fun invoke(left: BigInteger, right: BigInteger): BigInteger {
        return when (this) {
            ADD -> left.plus(right)
            SUB -> left.minus(right)
            MUL -> left.times(right)
            DIV -> left.div(right)
            REM -> left.rem(right)
        }
    }
}
