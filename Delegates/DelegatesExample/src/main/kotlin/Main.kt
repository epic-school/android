fun main(args: Array<String>) {

    /**
     *  Делегаты классов
     *  Делегаты свойств
     *
     */
    /*  val superService = SuperService()
      superService.start()
      superService.stop()
      superService.restart()*/
    //DelegatePropExample().main()
    DelegateProp2().main()
}

class SuperService : MyInterface by Service() {
    override fun start() {
        print("Start SuperService")
    }
}

class Service : MyInterface {
    override fun start() {
        print("start")
    }

    override fun stop() {
        print("stop")
    }

    override fun restart() {
        print("restart")
    }
}

interface MyInterface {

    fun start()

    fun stop()

    fun restart()

}