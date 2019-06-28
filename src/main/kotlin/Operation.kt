package calculator

sealed class Operation
data class Const(val number: Float) : Operation()
data class Sum(val sum1 : Operation, val sum2 : Operation) : Operation()
data class Div(val div1 : Operation, val div2 : Operation) : Operation()
data class Mul(val mul1 : Operation, val mul2 : Operation) : Operation()
data class Min(val min1 : Operation, val min2 : Operation) : Operation()
object NotANumber : Operation()

