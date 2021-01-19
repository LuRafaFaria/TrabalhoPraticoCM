package ipca.followthroughapp.custom

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
import ipca.followthroughapp.R
import ipca.followthroughapp.culinaria.MainCulinaria
import ipca.followthroughapp.culinaria.RecipeDetailActivity
import ipca.followthroughapp.estudos.EstudosDetailActivity
import ipca.followthroughapp.estudos.EstudosRegist

class MainCustom : AppCompatActivity() {

    companion object {
        var customRegistList : MutableList<CustomRegist> = ArrayList()//<nome da classe>
        lateinit var adapter: CustomAdapter
    }


    lateinit var listViewCustom : ListView

    var newCustomFolder : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_custom)

        customRegistList.add(CustomRegist("Exemplo"))

        //var fName = intent.getStringExtra(F_NAME)
        val customFolder = findViewById<TextView>(R.id.customFolder)


        listViewCustom = findViewById(R.id.listViewCustom)
        adapter = CustomAdapter()
        listViewCustom.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.notes_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.add_note) {
            val intent = Intent(applicationContext, MainCustom::class.java)
            startActivity(intent)
            return true
        }
        return false
    }

    inner class CustomAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return customRegistList.size
        }

        override fun getItem(position: Int): Any {
            return  customRegistList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val customView = layoutInflater.inflate(R.layout.custommain, viewGroup, false)
            val customFolder = customView.findViewById<TextView>(R.id.customFolder)
            val editButton = customView.findViewById<Button>(R.id.editButton)
            val deleteButton = customView.findViewById<Button>(R.id.deleteButton)
            //1 - add button

            customFolder.text = customRegistList[position].cName

            Log.d("bruh", customFolder.text.toString())
            Log.d("bruh", "teste")

            customView.isClickable = true
            customView.setOnClickListener {
                val intent = Intent (this@MainCustom, CustomInput::class.java)
                intent.putExtra(CustomDetailActivity.C_NAME, customRegistList[position].cName  )
                intent.putExtra("noteID", position)
                startActivity(intent)
            }


            editButton.setOnClickListener {
                val intent = Intent (this@MainCustom, CustomDetailActivity::class.java)
                intent.putExtra(CustomDetailActivity.C_NAME   , customRegistList[position].cName  )
                intent.putExtra("noteID", position) //to tell us which row of listView was tapped
                startActivity(intent)
            }

            deleteButton.setOnClickListener {
                customRegistList.removeAt(position)
                adapter.notifyDataSetChanged()
            }


            //2-addbutton setonclicklistener
            //CreateFolder

            /*editButton.setOnClickListener {

            }*/

            return customView
        }


        //3- CreateFolder()
        //Fazer outra view ou apresentar caixa de texo a perguntar o nome da receita

        //Dentro do folder da receita ir buscar o nome da receita e atribuir ao rNameD
        //Ao entrar na receita entramos com o editmode (layoutcom edittext)
        //Ao escrever alguma cena depois mandamos o que está escrito para outro layout com textviews que recebem os valores
    }

}