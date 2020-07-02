import org.junit.Test
import org.junit.Assert.assertEquals
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime
import kotlin.time.measureTimedValue


private data class Heap(val name: String, val num: Int)

private class HeapFactory {
    fun getNewHeap() = Heap("name", 5)
}

class HeapFactoryTest {
    @Test
    fun testGetNewHeap() {
        val timeMillis = measureTimeMillis {
            repeat(1000) {
                val newHeap = HeapFactory().getNewHeap()

                assertEquals("name", newHeap.name)
                assertEquals(5, newHeap.num)
            }
        }

        println("HeapFactory: getNewHeap(): ${timeMillis.toDouble()/1000} ms")
    }
}
