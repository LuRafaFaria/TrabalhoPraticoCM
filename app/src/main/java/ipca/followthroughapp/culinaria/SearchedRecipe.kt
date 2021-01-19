package ipca.followthroughapp.culinaria

import org.json.JSONObject

class SearchedRecipe {

    var id          : Double? = null
    var title       : String? = null
    var urlToImage       : String? = null

    constructor(title: String?, urlToImage: String?) {
        this.title = title
        this.urlToImage = urlToImage
    }

    constructor()
    {

    }

    companion object
    {
        fun fromJson(jsonObjectSearched: JSONObject) : SearchedRecipe
        {
            var article = SearchedRecipe()
            article.id          = jsonObjectSearched.getDouble("id")
            article.title       = jsonObjectSearched.getString("title")
            article.urlToImage       = jsonObjectSearched.getString("image")
            return article
        }
    }
}