import java.math.BigInteger

enum class Operation {
    ADD,
    SUB,
    MUL,
    DIV,
    REM;
    fun invoke(left: BigInteger, right: BigInteger): BigInteger {
        when (this) {
            ADD -> return left.plus(right)
            SUB -> return left.minus(right)
            MUL -> return left.times(right)
            DIV -> return left.div(right)
            REM -> return left.rem(right)
        }
    }
}
