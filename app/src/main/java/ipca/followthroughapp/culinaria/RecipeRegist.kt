package ipca.followthroughapp.culinaria

class RecipeRegist {

    var rName      : String? = null
    var ings       : String? = null
    var rPrep      : String? = null
    //var className  : Class<*>? = null

    constructor(rName: String?, ings: String?, rPrep: String?) {
        this.rName = rName
        this.ings = ings
        this.rPrep = rPrep
    }
}