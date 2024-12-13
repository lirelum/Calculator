import java.math.BigInteger

sealed class Token {
    class Literal(val value: BigInteger) : Token()
    class Operator(val operator: Operation) : Token()
    data object LeftBrace : Token()
    data object RightBrace : Token()
}