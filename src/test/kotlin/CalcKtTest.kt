import calculator.calculate
import calculator.doHighOperation
import calculator.sum
import junit.framework.TestCase

class CalcKtTest : TestCase() {

    fun testCalculate() {
        val inputDivide = "48/2+2*5-4/2"
        val outputDivide = "24.0+2*5-2.0"
        val inputMultiply = calculate(inputDivide, "-+*","/")
        assertEquals(outputDivide, inputMultiply)
        assertEquals(calculate(inputMultiply, "-+","*"), "24.0+10.0-2.0")

    }

    fun testDoOperation() {
        val inputDivide = "48/2"
        val inputMultiply = "4*2"

        assertEquals("24.0", doHighOperation(inputDivide, "/"))
        assertEquals("8.0", doHighOperation(inputMultiply, "*"))

    }

    fun testSum() {
        val input = "48+5-2+10"
        assertTrue(sum(input) == 61.0)
    }
}