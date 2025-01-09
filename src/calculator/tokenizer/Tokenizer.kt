package calculator.tokenizer

class TokenizerException(msg: String) : Exception(msg)

fun tokenize(string: String): List<Token> {
    return scan(string).map(RawToken::invoke)
}

private val operators = listOf('+', '-', '*', '/');
private val digits = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
private val whitespace = listOf(' ', '\n', '\r', '\t', '\n');

fun scan(string: String): List<RawToken> {
    return when (string.firstOrNull()) {
        in operators -> operator(string)
        in digits -> digit(string)
        in whitespace -> scan(string.drop(1))
        '(', ')' -> paren(string)
        else -> emptyList()
    }
}

fun operator(string: String): List<RawToken> {
    return when (val c = string.firstOrNull()) {
        in operators -> listOf(RawToken.Operator(c.toString())) + scan(string.drop(1))
        else -> scan(string)
    }
}

fun digit(string: String): List<RawToken> {
    return _digit("", string)
}

fun _digit(buf: String, string: String): List<RawToken> {
    return when (val c = string.firstOrNull()) {
        in digits -> {_digit(buf + c, string.drop(1))}
        else -> (listOf(RawToken.Literal(buf)) + scan(string))
    }
}

fun paren(string: String): List<RawToken> {
    return when (string.firstOrNull()) {
        '(' -> {listOf(RawToken.LeftParen) + scan(string.drop(1))}
        ')' -> {listOf(RawToken.RightParen) + scan(string.drop(1))}
        else -> scan(string)
    }
}
