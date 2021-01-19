package ipca.followthroughapp.culinaria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipca.followthroughapp.R


class MainCulinaria : AppCompatActivity() {


    companion object{
        var recipeRegistList : MutableList<RecipeRegist> = ArrayList()
        lateinit var adapter: RecipeAdapter
    }


    lateinit var listViewCul : ListView


    var num : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_culinaria)


        Log.d("bruh", num.toString())


        recipeRegistList.add(RecipeRegist("Nome da Receita", "Ingredientes", "Preparação"))



        //var fName = intent.getStringExtra(F_NAME)
        val culFolder = findViewById<TextView>(R.id.culFolder)


        listViewCul = findViewById(R.id.listViewCul)
        adapter = RecipeAdapter()
        listViewCul.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.notes_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.add_note) {
            val intent = Intent(this@MainCulinaria, MainCulinaria::class.java)
            startActivity(intent)
            return true
        }

        else if (item.itemId == R.id.search_recipe) {
            val intent = Intent(this@MainCulinaria, SearchedRecipeDetailActivity::class.java)
            startActivity(intent)
            return true
        }
        return false
    }

    inner class RecipeAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return recipeRegistList.size
        }

        override fun getItem(position: Int): Any {
            return  recipeRegistList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val culinariaView = layoutInflater.inflate(R.layout.culinariamain, viewGroup, false)
            val culFolder = culinariaView.findViewById<TextView>(R.id.culFolder)
            val editButton = culinariaView.findViewById<Button>(R.id.editButton)
            //val addButton = culinariaView.findViewById<FloatingActionButton>(R.id.addButton)
            val deleteButton = culinariaView.findViewById<Button>(R.id.deleteButton)

            culFolder.text = recipeRegistList[position].rName


            culinariaView.isClickable = true
            culinariaView.setOnClickListener{
                val intent = Intent (this@MainCulinaria, RecipeInput::class.java)
                intent.putExtra(RecipeInput.R_NAME   , recipeRegistList[position].rName  )
                intent.putExtra(RecipeInput.INGS         , recipeRegistList[position].ings )
                intent.putExtra(RecipeInput.R_PREP , recipeRegistList[position].rPrep)
                intent.putExtra("noteID", position)
                startActivity(intent)
            }

            editButton.setOnClickListener {
                val intent = Intent (this@MainCulinaria, RecipeDetailActivity::class.java)
                intent.putExtra(RecipeDetailActivity.R_NAME   , recipeRegistList[position].rName  )
                intent.putExtra(RecipeDetailActivity.INGS         , recipeRegistList[position].ings )
                intent.putExtra(RecipeDetailActivity.R_PREP , recipeRegistList[position].rPrep)
                intent.putExtra("noteID", position) //to tell us which row of listView was tapped
                startActivity(intent)
            }

            //detail add edit
            //input click

            deleteButton.setOnClickListener {
                recipeRegistList.removeAt(position)
                adapter.notifyDataSetChanged()
            }



            //CreateFolder

            /*editButton.setOnClickListener {

            }*/

            return culinariaView
        }


        //3- CreateFolder()

        //Fazer outra view ou apresentar caixa de texo a perguntar o nome da receita

        //Dentro do folder da receita ir buscar o nome da receita e atribuir ao rNameD
        //Ao entrar na receita entramos com o editmode (layoutcom edittext)
        //Ao escrever alguma cena depois mandamos o que está escrito para outro layout com textviews que recebem os valores
    }
}