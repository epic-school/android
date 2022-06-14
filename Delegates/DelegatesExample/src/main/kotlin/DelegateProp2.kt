import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DelegateProp2 {

    var sampleString: String = ""

    var delegateSampleString: String by trimDelegate()
    var strt2: String by trimDelegate()

    fun setParam(string: String) {
        sampleString = string.trim() // " abc " -> "abc"
    }

    fun getParam(): String {
        return sampleString
    }

    fun main() {
        println(delegateSampleString)
        println(strt2)
    }
}

fun trimDelegate() = TrimDelegate()

class TrimDelegate : ReadWriteProperty<Any?, String> {
    private var trimmedValue: String = ""
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return trimmedValue + "123"
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        trimmedValue = value.trim()
    }

}
/*

class MainFragment : Fragment {
    val titleText: String by StringDelegate(R.string.title)

    fun main(){
        print(titleText)
    }
}

class StringDelegate(title: Int) : ReadWriteProperty<Fragment?, String> {
    private var res: String = ""
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return res
    }

    override fun setValue(thisRef: Fragment?, property: KProperty<*>, value: String) {
        res = thisRef.context.getString(title)
    }

}*/
