import java.math.BigInteger

open class BinaryOperation(
    private val op: (BigInteger, BigInteger) -> BigInteger,
    private val left: Expression,
    private val right: Expression
) : Expression {
    override operator fun invoke(): BigInteger {
        return op(left(), right())
    }
    companion object {
        fun Add(left: Expression, right: Expression): BinaryOperation {
            return BinaryOperation(Operation.ADD::invoke, left, right)
        }
        fun Sub(left: Expression, right: Expression): BinaryOperation {
            return BinaryOperation(Operation.SUB::invoke, left, right)
        }
        fun Mul(left: Expression, right: Expression): BinaryOperation {
            return BinaryOperation(Operation.MUL::invoke, left, right)
        }
        fun Div(left: Expression, right: Expression): BinaryOperation {
            return BinaryOperation(Operation.DIV::invoke, left, right)
        }
        fun Mod(left: Expression, right: Expression): BinaryOperation {
            return BinaryOperation(Operation.REM::invoke, left, right)
        }
    }
}