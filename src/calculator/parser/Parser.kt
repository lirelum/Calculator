import calculator.expression.BinaryOperation
import calculator.expression.Expression
import calculator.expression.Literal
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
    fun parse(): Expression {
        return expression()
    }

    private fun expression(): Expression {
        return expressionP(term())
    }

    private fun expressionP(expr: Expression): Expression {
        return when (currentToken.current) {
            is Token.Add -> {
                currentToken.next()
                BinaryOperation.Add(expr, expressionP(term()))
            }

            is Token.Sub -> {
                currentToken.next()
                BinaryOperation.Sub(expr, expressionP(term()))
            }

            else -> expr
        }
    }

    private fun term(): Expression {
        return termP(factor())
    }

    private fun termP(expr: Expression): Expression {
        return when (currentToken.current) {
            is Token.Mul -> {
                currentToken.next()
                BinaryOperation.Mul(expr, termP(factor()))
            }

            is Token.Div -> {
                currentToken.next()
                BinaryOperation.Div(expr, termP(factor()))
            }

            else -> expr
        }
    }

    private fun factor(): Expression {
        return when (val cur = currentToken.current) {
            is Token.Literal -> {
                currentToken.next()
                Literal(cur.value)
            }

            is Token.LeftParen -> {
                currentToken.next()
                val e = expression()
                val b = currentToken.current is Token.RightParen
                currentToken.next()
                if (b) e else throw Exception("Parse failed, parens")
            }

            else -> throw Exception("Parse failed, unknown")
        }
    }
}