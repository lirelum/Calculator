import calculator.tokenizer.Token

class Parser(tokens: List<Token>) {
    private class CurrentToken(private val tokens: Iterator<Token>) {
         var current = if (tokens.hasNext()) (tokens.next()) else null
             private set
        fun next() {
            current = if (tokens.hasNext()) (tokens.next()) else null
        }
    }
    private val currentToken = CurrentToken(tokens.iterator())
    fun parse(): Boolean {
        return expression()
    }
   private fun expression(): Boolean {
       return term() && expressionP()
   }
    private fun expressionP(): Boolean {
        return when(currentToken.current) {
            is Token.Add -> {
                currentToken.next()
                term() && expressionP()
            }
            is Token.Sub -> {
                currentToken.next()
                term() && expressionP()
            }
            else -> true
        }
    }
    private fun term(): Boolean {
        return factor() && termP()
    }
    private fun termP(): Boolean {
        return when(currentToken.current) {
            is Token.Mul -> {
                currentToken.next()
                factor() && termP()
            }
            is Token.Div -> {
                currentToken.next()
                factor() && termP()
            }
            else -> true
        }
    }
    private fun factor(): Boolean {
        return when(currentToken.current) {
            is Token.Literal -> {
                currentToken.next()
                true
            }
            is Token.LeftParen -> {
                currentToken.next()
                val e = expression()
                val b = currentToken.current is Token.RightParen
                currentToken.next()
                e && b
            }
            else -> false
        }
    }
}