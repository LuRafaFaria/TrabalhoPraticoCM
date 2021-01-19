package ipca.followthroughapp.culinaria

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ipca.followthroughapp.R
import kotlinx.android.synthetic.main.recipe_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONArray
import org.json.JSONObject

class RecipeListView : AppCompatActivity() {


    companion object {
        const val S_RECIPE   = "s_recipe"
    }


    var searchedRecipes : MutableList<SearchedRecipe> = ArrayList()
    var sAdaptor = SAdaptor()
    var sRecipeD : String? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.searched_recipe_result)


        sRecipeD = intent.getStringExtra(S_RECIPE)

        val listViewSearch = findViewById<ListView>(R.id.listViewSearch)
        listViewSearch.adapter = sAdaptor



        GlobalScope.async {


            var result = RecipeAPIBackend.getSearchedRecipes(sRecipeD.toString())

            if (result == "Sem Internet")
            {
                runOnUiThread {
                    Toast.makeText(this@RecipeListView, "Sem Internet!", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                val jsonObjectSearched = JSONObject(result)
                var jsonArraySearched : JSONArray = jsonObjectSearched.getJSONArray("searchedRecipes")


                for (index in 0 until jsonArraySearched.length())
                {
                    val jsonArticle = jsonArraySearched.getJSONObject(index)
                    val article = SearchedRecipe.fromJson(jsonArticle)

                    searchedRecipes.add(article)
                }


                runOnUiThread {
                    sAdaptor.notifyDataSetChanged()
                }
            }
        }
    }

    inner class SAdaptor : BaseAdapter()
    {
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View
        {

            val rowView = layoutInflater.inflate(R.layout.recipe_list, viewGroup, false)

            val textViewTitle = rowView.findViewById<TextView>(R.id.textViewTitle)
            val textViewPic = rowView.findViewById<ImageView>(R.id.imageViewRecipe)

            textViewTitle.text = searchedRecipes[position].title
            println(textViewTitle.text)

            if ((searchedRecipes[position].urlToImage?:"").contains("http"))
            {
                RecipeAPIBackend.getBitmapFromURL(searchedRecipes[position].urlToImage!!)
                {
                    imageViewRecipe.setImageBitmap(it)
                }
            }

            rowView.isClickable = true
            //rowView.setOnClickListener {
            //    val intent = Intent (this@RecipeListView, RecipeFromID::class.java)
            //    intent.putExtra(RecipeFromID.RECIPE_ID, searchedRecipes[position].id)
            //
            //
            //    startActivity(intent)
            //}

            return rowView
        }

        override fun getItem(position: Int): Any
        {
            return searchedRecipes[position]
        }

        override fun getItemId(position: Int): Long
        {
            return 0
        }

        override fun getCount(): Int
        {
            return searchedRecipes.size
        }

    }


}