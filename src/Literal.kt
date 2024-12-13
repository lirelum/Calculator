class Literal(private val value: Int) : Expression {
    override fun invoke(): Int { return value }
}