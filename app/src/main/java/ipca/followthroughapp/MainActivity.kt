package ipca.followthroughapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ipca.followthroughapp.culinaria.MainCulinaria
import ipca.followthroughapp.culinaria.RecipeRegist
import ipca.followthroughapp.custom.CustomRegist
import ipca.followthroughapp.custom.MainCustom
import ipca.followthroughapp.estudos.EstudosRegist
import ipca.followthroughapp.estudos.MainEstudos
import ipca.followthroughapp.treinos.MainTreinos
import ipca.followthroughapp.treinos.TreinosRegist


class MainActivity : AppCompatActivity() {

    var mainRegistList : MutableList<MainRegist> = ArrayList()//<nome da classe>
    lateinit var listView : ListView
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRegistList.add(MainRegist("Culinaria", MainCulinaria::class.java))
        mainRegistList.add(MainRegist("Treinos", MainTreinos::class.java))
        mainRegistList.add(MainRegist("Estudos", MainEstudos::class.java))
        mainRegistList.add(MainRegist("Custom", MainCustom::class.java))

        listView = findViewById(R.id.listView)
        adapter = MainAdapter()
        listView.adapter = adapter


        val sharedPreferencesCul : SharedPreferences = getSharedPreferences("ipca.followthrough.app", MODE_PRIVATE)
        val gsonCul = Gson()
        val jsonCul = sharedPreferencesCul.getString("note_added", null)
        val typeCul = object : TypeToken<ArrayList<RecipeRegist>>() {}.getType()
        MainCulinaria.recipeRegistList = gsonCul.fromJson(jsonCul, typeCul)

        val sharedPreferencesCus : SharedPreferences = getSharedPreferences("ipca.followthrough.app", MODE_PRIVATE)
        val gsonCus = Gson()
        val jsonCus = sharedPreferencesCus.getString("note_added", null)
        val typeCus = object : TypeToken<ArrayList<CustomRegist>>() {}.getType()
        MainCustom.customRegistList = gsonCus.fromJson(jsonCus, typeCus)

        val sharedPreferencesEst : SharedPreferences = getSharedPreferences("ipca.followthrough.app", MODE_PRIVATE)
        val gsonEst = Gson()
        val jsonEst = sharedPreferencesEst.getString("note_added", null)
        val typeEst = object : TypeToken<ArrayList<EstudosRegist>>() {}.getType()
        MainEstudos.estudosRegistList = gsonEst.fromJson(jsonEst, typeEst)

        val sharedPreferencesTr : SharedPreferences = getSharedPreferences("ipca.followthrough.app", MODE_PRIVATE)
        val gsonTr = Gson()
        val jsonTr = sharedPreferencesTr.getString("note_added", null)
        val typeTr = object : TypeToken<ArrayList<TreinosRegist>>() {}.getType()
        MainTreinos.treinosRegistList = gsonTr.fromJson(jsonTr, typeTr)
    }



    inner class MainAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return mainRegistList.size
        }

        override fun getItem(position: Int): Any {
            return  mainRegistList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.activity_source, viewGroup, false)
            val fName = rowView.findViewById<TextView>(R.id.fName)


            fName.text = mainRegistList[position].fName

            rowView.isClickable = true
            rowView.setOnClickListener {
                val choosen : Int
                choosen = mainRegistList.indexOf(position)

                if( mainRegistList[position].fName.equals("Culinaria")){
                    val intent = Intent (this@MainActivity, MainCulinaria::class.java)
                    intent.putExtra(MainDetailActivity.F_NAME, mainRegistList[position].fName)
                    startActivity(intent);
                }
                if(mainRegistList[position].fName.equals("Treinos")){
                    val intent = Intent (this@MainActivity, MainTreinos::class.java)
                    intent.putExtra(MainDetailActivity.F_NAME, mainRegistList[position].fName)
                    startActivity(intent);

                }
                if(mainRegistList[position].fName.equals("Estudos")){
                    val intent = Intent (this@MainActivity, MainEstudos::class.java)
                    intent.putExtra(MainDetailActivity.F_NAME, mainRegistList[position].fName)
                    startActivity(intent);
                }
                if(mainRegistList[position].fName.equals("Custom")){
                    val intent = Intent (this@MainActivity, MainCustom::class.java)
                    intent.putExtra(MainDetailActivity.F_NAME, mainRegistList[position].fName)
                    startActivity(intent);
                }

                //val intent = Intent (this@MainActivity, mainRegistList[position].className)
                //intent.putExtra(MainDetailActivity.F_NAME, mainRegistList[position].fName)


                /*if(intent != null){
                    startActivity(intent);
                }*/
            }
            return rowView
        }
    }
}