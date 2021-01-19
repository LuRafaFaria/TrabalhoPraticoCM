package ipca.followthroughapp.culinaria

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recipe_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ipca.followthroughapp.R
import org.json.JSONArray
import org.json.JSONObject

class RecipeAPI : AppCompatActivity() {


    var recipes : MutableList<Recipe> = ArrayList()
    var rAdapter = RAdaptor()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.search_recipe)


        val listViewArticles = findViewById<ListView>(R.id.listViewArticle)
        listViewArticles.adapter = rAdapter


        GlobalScope.async {

            var result = RecipeAPIBackend.getLatestRecipes()
            if (result == "Sem Internet")
            {
                runOnUiThread {
                    Toast.makeText(this@RecipeAPI, "Sem Internet!", Toast.LENGTH_LONG).show()
                }
            }
            else
            {


                val jsonObject = JSONObject(result)
                if (jsonObject.get("status").equals("ok"))
                {
                    var jsonArray : JSONArray = jsonObject.getJSONArray("recipes")
                    for (index in 0 until jsonArray.length())
                    {
                        val jsonArticle = jsonArray.getJSONObject(index)
                        val article = Recipe.fromJson(jsonArticle)
                        recipes.add(article)
                    }
                    runOnUiThread {
                        rAdapter.notifyDataSetChanged()

                    }
                }


            }
        }

    }

    inner class RAdaptor : BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View
        {
            val rowView = layoutInflater.inflate(R.layout.recipe_list, viewGroup, false)

            val textViewTitle = rowView.findViewById<TextView>(R.id.textViewTitle)
            val textViewDescription = rowView.findViewById<TextView>(R.id.textViewTitle)
            val textViewPic = rowView.findViewById<ImageView>(R.id.imageViewRecipe)

            textViewTitle.text = recipes[position].title
            textViewDescription.text = recipes[position].summary

            if ((recipes[position].urlToImage?:"").contains("http"))
            {
                RecipeAPIBackend.getBitmapFromURL(recipes[position].urlToImage!!)
                {
                    imageViewRecipe.setImageBitmap(it)
                }
            }
            rowView.isClickable = true
            rowView.setOnClickListener {
                val intent = Intent (this@RecipeAPI, RecipeAPIDetailActivity::class.java)
                intent.putExtra(RecipeAPIDetailActivity.ARTICLE_URL, recipes[position].sourceUrl)
                intent.putExtra(RecipeAPIDetailActivity.ARTICLE_TITLE, recipes[position].title)


                startActivity(intent)
            }

            return rowView
        }

        override fun getItem(position: Int): Any
        {
            return recipes[position]
        }

        override fun getItemId(position: Int): Long
        {
            return 0
        }

        override fun getCount(): Int
        {
            return recipes.size
        }

    }

}


