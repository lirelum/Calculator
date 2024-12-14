import calculator.tokenizer.Token

class Parser(private val tokens: List<Token>) {
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
                true
            }
            is Token.Sub -> {
                currentToken.next()
                true
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
                true
            }
            
        }
    }
}