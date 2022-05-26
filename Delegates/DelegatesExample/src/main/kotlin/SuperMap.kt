class SuperMap(private val map: HashMap<String, Int> = hashMapOf()) : MutableMap<String, Int> by map {
    override fun get(key: String): Int? {
        return map.remove(key)
    }
}

fun testMap() {
    val map = SuperMap()

    map["key1"] = 2

    val result = map.get("key1")
    val result2 = map["key1"]
}