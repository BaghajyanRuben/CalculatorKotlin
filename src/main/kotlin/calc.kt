package calculator

fun main() {

    val mathRegex = "([0-9]*\\.?[0-9]+[-+/*])+([-+]?[0-9]*\\.?[0-9]+)".toRegex()

    val input = readLine()

    var operations = "-+/*"
    var result = if (input.isNullOrEmpty()) "" else input

    val match = mathRegex.matchEntire(result)?.value
    if (match.isNullOrEmpty()){
        println("invalid input")
        return
    }

    while (operations != "-+") {
        val regex = operations.substring(0, operations.length - 1)
        val operation = operations.substring(operations.length - 1, operations.length)

        result = calculate(result, regex, operation)
        operations = regex
    }

    println(sum(result))
}

fun calculate(calcText: String, regex: String, operation: String): String {

    var r = ""

    if (regex != "") {
        val reg = "(?<=[$regex])|(?=[$regex])"
        val list = calcText.split(reg.toRegex())
        list.forEach { s ->
            r += doHighOperation(s, operation)
        }
    } else {
        r = doHighOperation(calcText, operation)
    }

    return r
}

fun doHighOperation(s: String, operation: String): String {
    return when (operation) {
        in s -> {
            val opRegex = "[$operation]"
            s.split(opRegex.toRegex())
                .map { it.toDouble() }
                .reduce { a, b ->
                    when (operation) {
                        "*" -> a * b
                        "/" -> a / b
                        else -> a + b
                    }
                }
                .toString()
        }
        else -> s
    }
}

fun sum(input: String): Double {
    return input.split("(?=[-+])".toRegex())
        .map {
            when {
                it.startsWith("+") -> it.substring(1, it.length).toDouble()
                it.startsWith("-") -> it.substring(1, it.length).toDouble() * -1
                else -> it.toDouble()
            }
        }
        .reduce { a, b -> a + b }
        .format(2)
}

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this).toDouble()



