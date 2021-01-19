package ipca.followthroughapp.culinaria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ipca.followthroughapp.MainActivity
import ipca.followthroughapp.R
import kotlinx.android.synthetic.main.recipe_list.*
import kotlinx.android.synthetic.main.search_recipe.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONArray
import org.json.JSONObject

class SearchedRecipeDetailActivity : AppCompatActivity() {

    //detailSearchRecipe
    companion object {
        const val S_RECIPE   = "s_recipe"

    }



    var noteID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_recipe)



        val sRecipe = intent.getStringExtra(S_RECIPE)

        val sRecipeD = findViewById<TextView>(R.id.detailSearchRecipe)
        val searchButton = findViewById<Button>(R.id.searchButton)



        searchButton.setOnClickListener{

            val intent = Intent (this@SearchedRecipeDetailActivity, RecipeListView::class.java)
            intent.putExtra(RecipeListView.S_RECIPE   , sRecipeD.text)


            startActivity(intent)

        }


    }



}