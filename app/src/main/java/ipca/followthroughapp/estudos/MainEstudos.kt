package ipca.followthroughapp.estudos

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
import ipca.followthroughapp.custom.CustomDetailActivity
import ipca.followthroughapp.custom.MainCustom
import kotlin.collections.ArrayList

class MainEstudos : AppCompatActivity() {

    companion object {
        var estudosRegistList : MutableList<EstudosRegist> = ArrayList()//<nome da classe>
        lateinit var adapter: EstudosAdapter
    }


    lateinit var listViewEst : ListView
    var newEstFolder : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_estudos)

        estudosRegistList.add(EstudosRegist("Exemplo"))

        //var fName = intent.getStringExtra(F_NAME)
        val estFolder = findViewById<TextView>(R.id.estFolder)


        listViewEst = findViewById(R.id.listViewEst)
        adapter = EstudosAdapter()
        listViewEst.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.notes_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.add_note) {
            val intent = Intent(applicationContext, MainEstudos::class.java)
            startActivity(intent)
            return true
        }
        return false
    }

    inner class EstudosAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return estudosRegistList.size
        }

        override fun getItem(position: Int): Any {
            return  estudosRegistList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val estudosView = layoutInflater.inflate(R.layout.estudosmain, viewGroup, false)
            val estFolder = estudosView.findViewById<TextView>(R.id.estFolder)
            val editButton = estudosView.findViewById<Button>(R.id.editButton)
            val deleteButton = estudosView.findViewById<Button>(R.id.deleteButton)
            //1 - add button

            estFolder.text = estudosRegistList[position].eName

            Log.d("bruh", estFolder.text.toString())
            Log.d("bruh", "teste")

            estudosView.isClickable = true
            estudosView.setOnClickListener {
                val intent = Intent (this@MainEstudos, EstudosInput::class.java)
                intent.putExtra(EstudosDetailActivity.E_NAME   , estudosRegistList[position].eName  )
                intent.putExtra("noteID", position)
                startActivity(intent)
                Log.d("bruh", intent.toString())
            }

            editButton.setOnClickListener {
                val intent = Intent (this@MainEstudos, EstudosDetailActivity::class.java)
                intent.putExtra(CustomDetailActivity.C_NAME   , estudosRegistList[position].eName  )
                intent.putExtra("noteID", position) //to tell us which row of listView was tapped
                startActivity(intent)
            }

            deleteButton.setOnClickListener {
                estudosRegistList.removeAt(position)
                adapter.notifyDataSetChanged()
            }
            //2-addbutton setonclicklistener
            //CreateFolder

            /*editButton.setOnClickListener {

            }*/

            return estudosView
        }


        //3- CreateFolder()
        //Fazer outra view ou apresentar caixa de texo a perguntar o nome da receita

        //Dentro do folder da receita ir buscar o nome da receita e atribuir ao rNameD
        //Ao entrar na receita entramos com o editmode (layoutcom edittext)
        //Ao escrever alguma cena depois mandamos o que está escrito para outro layout com textviews que recebem os valores
    }
}