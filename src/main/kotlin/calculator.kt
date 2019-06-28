package calculator

fun main() {
    readLine()?.let { print(eval(it)) }
}

fun eval(exp: String): Float {
    var index = 0
    val skipWhile = { cond: (Char) -> Boolean -> while (index < exp.length && cond(exp[index])) index++ }
    val read = { c: Char -> (index < exp.length && exp[index] == c).also { if (it) index++ } }

    val num = {
        skipWhile { it.isWhitespace() }
        val start = index
        skipWhile { it == '-' || it == '+' }
        skipWhile { it.isDigit() || it == '.' }
        exp.substring(start, index).toFloat().also { skipWhile { it.isWhitespace() } }
    }

    fun binary(left: () -> Float, op: Char): List<Float> = mutableListOf(left()).apply {
        while (read(op)) addAll(binary(left, op))
    }

    fun expr(ex : Operation) : Float = when(ex){
        is Const -> ex.number
        is Sum -> expr(ex.sum1) + expr(ex.sum2)
        is Div -> expr(ex.div1) / expr(ex.div2)
        is Mul -> expr(ex.mul1) * expr(ex.mul2)
        else -> Float.NaN
    }

    val div = { binary(num, '/').reduce { a, b -> expr(Div(Const(a), Const(b))) } }
    val mul = { binary(div, '*').reduce { a, b -> a * b } }
    val sub = { binary(mul, '-').reduce { a, b -> a - b } }
    val add = { binary(sub, '+').reduce { a, b -> a + b } }

    return add().also {
        if (index < exp.length) throw IllegalArgumentException("Invalid expression")
    }

}