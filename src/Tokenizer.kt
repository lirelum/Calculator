import java.math.BigInteger

class TokenizerException : Exception()

sealed class Token {
    data class Literal(val value: BigInteger) : Token()
    data object Sum : Token()
    data object Sub : Token()
    data object Mul : Token()
    data object Div : Token()
    data object Rem : Token()
    data object LeftBrace : Token()
    data object RightBrace : Token()
}

fun tokenize(string: String): List<Token> {
    val (tokens, _) = tokenizeInternal(string)
    return tokens
}

private fun tokenizeInternal(string: String): Pair<List<Token>, String> {
    if (string.isBlank()) {
        return Pair(listOf<Token>(), string)
    }
    val c = string.first()
    var str = string.drop(1)
    val token = when (c) {
        '+' -> Token.Sum
        '-' -> Token.Sub
        '*' -> Token.Mul
        '/' -> Token.Div
        '%' -> Token.Rem
        '(' -> Token.LeftBrace
        ')' -> Token.RightBrace
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
            val (num, ss) = literal(string)
            str = ss
            Token.Literal(BigInteger(num))
        }
        else -> throw TokenizerException()
    }
    val (tokens, ss) = tokenizeInternal(str)
    return Pair(listOf<Token>(token) + tokens, ss)
}
private fun literal(string: String): Pair<String, String> {
    if (string.isBlank()) {return Pair("", string)}
    val c = string.first()
    return when(c) {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
            val (nums, rest) = literal(string.drop(1))
            Pair(c + nums, rest)
        }
        else -> Pair("", string)
    }
}
