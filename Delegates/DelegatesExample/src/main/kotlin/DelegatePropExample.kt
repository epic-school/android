import kotlin.properties.Delegates
import kotlin.properties.Delegates.vetoable

class DelegatePropExample {

    private var parameter1: Int by Delegates.notNull()

    private var observ: Int by Delegates.observable(0) { property, oldValue, newValue ->
        print("New value = $newValue")
    }

    private var vetoable: Int by vetoable(10) { property, oldValue, newValue ->
        return@vetoable newValue > oldValue
    }

    private val computedProperty: String by lazy(LazyThreadSafetyMode.NONE) { getString() }

    /**
     *  NONE - никакой блокировки
     *  SYNCHRONIZED -
     *    Thread 1 - (X)
     *    Thread 2 - ()-> getString()
     *    Thread 3 - (X)
     *
     *    PUBLICATION
     *    Thread 1 - 200мс - getString()
     *    Thread 2 - 300мс - getString() - (500мс)
     *    Thread 3 - 320мс - getString()
     */

    fun getString(): String {
        return "123"
    }

    fun main() {
        parameter1 = 100
        parameter1.div(12)

        vetoable = 100
        println(vetoable)
        vetoable = 200
        println(vetoable)
        vetoable = 50
        println(vetoable)
        vetoable = 500
        println(vetoable)

        println(computedProperty)
        println(computedProperty)
    }
}