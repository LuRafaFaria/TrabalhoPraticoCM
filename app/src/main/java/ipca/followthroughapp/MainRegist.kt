package ipca.followthroughapp

class MainRegist {

    var fName      : String? = null
    var className  : Class<*>? = null


    constructor(fName: String?, className  : Class<*>?) {
        this.fName = fName
        this.className = className
    }
}