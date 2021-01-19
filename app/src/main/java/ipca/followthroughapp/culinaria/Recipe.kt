package ipca.followthroughapp.culinaria

import org.json.JSONObject

class Recipe
{
    var id          : Double? = null
    var title       : String? = null
    var sourceUrl         : String? = null
    var urlToImage       : String? = null
    var summary : String? = null

    constructor(title: String?, sourceUrl: String?, urlToImage: String?, summary : String?) {
        this.title = title
        this.sourceUrl = sourceUrl
        this.urlToImage = urlToImage
        this.summary = summary
    }

    constructor()
    {

    }

    companion object
    {
        fun fromJson(jsonObject: JSONObject) : Recipe
        {
            var article = Recipe()
            article.id          = jsonObject.getDouble("id")
            article.title       = jsonObject.getString("title")
            article.sourceUrl         = jsonObject.getString("sourceUrl")
            article.urlToImage       = jsonObject.getString("image")
            article.summary = jsonObject.getString("summary")
            return article
        }
    }

}