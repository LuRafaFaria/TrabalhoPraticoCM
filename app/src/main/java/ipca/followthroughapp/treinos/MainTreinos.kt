package ipca.followthroughapp.treinos

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
import ipca.followthroughapp.custom.CustomDetailActivity
import ipca.followthroughapp.estudos.EstudosDetailActivity
import ipca.followthroughapp.estudos.EstudosRegist
import ipca.followthroughapp.estudos.MainEstudos

class MainTreinos : AppCompatActivity() {

    companion object{
        var treinosRegistList : MutableList<TreinosRegist> = ArrayList()//<nome da classe>
        lateinit var adapter: TreinosAdapter

    }


    lateinit var listViewTreinos : ListView
    var newTreinosFolder : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_treinos)

        treinosRegistList.add(TreinosRegist("Exemplo", "0"))

        //var fName = intent.getStringExtra(F_NAME)
        val treinosFolder = findViewById<TextView>(R.id.treinosFolder)


        listViewTreinos = findViewById(R.id.listViewTreinos)
        adapter = TreinosAdapter()
        listViewTreinos.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.notes_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.add_note) {
            val intent = Intent(applicationContext, MainTreinos::class.java)
            startActivity(intent)
            return true
        }
        return false
    }



    inner class TreinosAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return treinosRegistList.size
        }

        override fun getItem(position: Int): Any {
            return  treinosRegistList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val treinosView = layoutInflater.inflate(R.layout.treinosmain, viewGroup, false)
            val treinosFolder = treinosView.findViewById<TextView>(R.id.treinosFolder)
            val editButton = treinosView.findViewById<Button>(R.id.editButton)
            val deleteButton = treinosView.findViewById<Button>(R.id.deleteButton)
            //1 - add button

            treinosFolder.text = treinosRegistList[position].tName

            Log.d("bruh", treinosFolder.text.toString())
            Log.d("bruh", "teste")

            treinosView.isClickable = true
            treinosView.setOnClickListener {
                val intent = Intent (this@MainTreinos, TreinosInput::class.java)
                intent.putExtra(TreinosDetailActivity.T_NAME   , treinosRegistList[position].tName  )
                intent.putExtra("noteID", position) //to tell us which row of listView was tapped
                intent.putExtra(TreinosDetailActivity.TIMER, treinosRegistList[position].timer) //to tell us which row of listView was tapped
                startActivity(intent)
                Log.d("bruh", intent.toString())

            }

            editButton.setOnClickListener {
                val intent = Intent (this@MainTreinos, TreinosDetailActivity::class.java)
                intent.putExtra(TreinosDetailActivity.T_NAME   , treinosRegistList[position].tName  )
                intent.putExtra("noteID", position) //to tell us which row of listView was tapped
                intent.putExtra("timer", treinosRegistList[position].timer) //to tell us which row of listView was tapped
                startActivity(intent)
            }

            deleteButton.setOnClickListener {
                treinosRegistList.removeAt(position)
                adapter.notifyDataSetChanged()
            }


            return treinosView
        }


        //3- CreateFolder()
        //Fazer outra view ou apresentar caixa de texo a perguntar o nome da receita

        //Dentro do folder da receita ir buscar o nome da receita e atribuir ao rNameD
        //Ao entrar na receita entramos com o editmode (layoutcom edittext)
        //Ao escrever alguma cena depois mandamos o que está escrito para outro layout com textviews que recebem os valores
    }
}