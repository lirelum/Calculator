package calculator.tokenizer

class TokenizerException(msg: String) : Exception(msg)

fun tokenize(string: String): List<Token> {
    return scan(string).map(RawToken::eval)
}
private enum class State {
    LITERAL,
    OPERATOR,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
}
private fun scan(string: String): List<RawToken> {
    val numerals = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val whitespace = setOf(' ', '\n', '\r', '\t')
    val operators = setOf('+', '-', '*', '/', '%')
    val tokens = mutableListOf<RawToken>()
    var state = State.LITERAL
    var literalBuf = ""
    var operatorBuf = ""
    for (char in string) {
       when (char) {
           in numerals -> {
               when (state) {
                   State.LITERAL -> {
                       literalBuf += char
                   }
                   State.OPERATOR -> {
                       tokens.add(RawToken.Operator(operatorBuf))
                       operatorBuf = ""
                       state = State.LITERAL
                       literalBuf += char
                   }
                   State.LEFT_PARENTHESIS -> {
                       state = State.LITERAL
                       literalBuf += char
                   }
                   State.RIGHT_PARENTHESIS -> throw TokenizerException("Unexpected character '$char'")
               }
           }
           in whitespace -> {}
           in operators -> {
               when (state) {
                   State.LITERAL -> {
                       tokens.add(RawToken.Literal(literalBuf))
                       literalBuf = ""
                       state = State.OPERATOR
                       operatorBuf += char
                   }
                   State.OPERATOR -> throw TokenizerException("Unexpected character '$char'")
                   State.LEFT_PARENTHESIS -> throw TokenizerException("Unexpected character '$char'")
                   State.RIGHT_PARENTHESIS -> {
                       state = State.OPERATOR
                       operatorBuf += char
                   }
               }
           }
           '(' -> {
               when (state) {
                   State.LITERAL -> {
                       tokens.add(RawToken.Literal(literalBuf))
                       literalBuf = ""
                       tokens.add(RawToken.LeftParen)
                       State.LEFT_PARENTHESIS
                   }
                   State.OPERATOR -> {
                       tokens.add(RawToken.Operator(operatorBuf))
                       operatorBuf = ""
                       tokens.add(RawToken.LeftParen)
                       state = State.LEFT_PARENTHESIS
                   }
                   State.RIGHT_PARENTHESIS -> throw TokenizerException("Unexpected character '$char'")
                   State.LEFT_PARENTHESIS -> throw TokenizerException("Unexpected character '$char'")
               }
           }
           ')' -> {
               when (state) {
                   State.LITERAL -> {
                       tokens.add(RawToken.Literal(literalBuf))
                       literalBuf = ""
                       tokens.add(RawToken.RightParen)
                       state = State.RIGHT_PARENTHESIS
                   }
                   State.OPERATOR -> {
                       tokens.add(RawToken.Operator(operatorBuf))
                       operatorBuf = ""
                       tokens.add(RawToken.RightParen)
                       state = State.RIGHT_PARENTHESIS
                   }
                   State.LEFT_PARENTHESIS -> throw TokenizerException("Unexpected character '$char'")
                   State.RIGHT_PARENTHESIS -> throw TokenizerException("Unexpected character '$char'")
               }
           }
           else -> throw TokenizerException("Unexpected character '$char'")
       }
    }
    if (state == State.LITERAL && literalBuf.isNotEmpty()) {tokens.add(RawToken.Literal(literalBuf))}
    if (state == State.OPERATOR && operatorBuf.isNotEmpty()) {tokens.add(RawToken.Operator(operatorBuf))}
    return tokens
}
