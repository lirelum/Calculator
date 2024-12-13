open class BinaryOperation(
    private val op: (Int, Int) -> Int,
    private val left: Expression,
    private val right: Expression
) : Expression {
    override operator fun invoke(): Int {
        return op(left(), right())
    }
    class Sum(private val left: Expression, private val right: Expression) : BinaryOperation({ a, b -> a + b }, left, right) {}
    class Sub(private val left: Expression, private val right: Expression) : BinaryOperation({ a, b -> a - b }, left, right) {}
    class Mul(private val left: Expression, private val right: Expression) : BinaryOperation({ a, b -> a * b }, left, right) {}
    class Div(private val left: Expression, private val right: Expression) : BinaryOperation({ a, b -> a / b }, left, right) {}
    class Rem(private val left: Expression, private val right: Expression) : BinaryOperation({ a, b -> a % b }, left, right) {}
}