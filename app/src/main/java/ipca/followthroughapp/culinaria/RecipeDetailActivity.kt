package ipca.followthroughapp.culinaria

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ipca.followthroughapp.R
import java.lang.reflect.Type


class RecipeDetailActivity : AppCompatActivity() {

   companion object {
       const val R_NAME   = "r_name"
       const val INGS     = "ings"
       const val R_PREP  = "r_prep"

   }



   var noteID = 0

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.detail_recipe)





       val rName = intent.getStringExtra(R_NAME)
       val ings = intent.getStringExtra(INGS)
       val rPrep = intent.getStringExtra(R_PREP)

       val rNameD = findViewById<TextView>(R.id.rNameD)
       val ingsD = findViewById<TextView>(R.id.ingsD)
       val rPrepD = findViewById<TextView>(R.id.rPrepD)

       rNameD.text = rName
       ingsD.text = ings
       rPrepD.text = rPrep



       val editTextName = findViewById<View>(R.id.rNameD) as EditText
       val editTextIngs = findViewById<View>(R.id.ingsD) as EditText
       val editTextPrep = findViewById<View>(R.id.rPrepD) as EditText

       val intent = intent
       noteID = intent.getIntExtra("noteID", -1)

       if(noteID != -1)
       {
           editTextName.setText(MainCulinaria.recipeRegistList[noteID].rName)
           editTextIngs.setText(MainCulinaria.recipeRegistList[noteID].ings)
           editTextPrep.setText(MainCulinaria.recipeRegistList[noteID].rPrep)

       }
       else
       {
           MainCulinaria.recipeRegistList.add(RecipeRegist("Exemplo", "Exemplo", "Exemplo"))
           noteID = MainCulinaria.recipeRegistList.size - 1
           MainCulinaria.adapter.notifyDataSetChanged()
       }

       editTextName.addTextChangedListener(object : TextWatcher {
           override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
           override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

               MainCulinaria.recipeRegistList[noteID].rName = s.toString()

               MainCulinaria.adapter.notifyDataSetChanged()

               val sharedPreferences : SharedPreferences = getSharedPreferences("ipca.followthrough.app", Context.MODE_PRIVATE)

               val editor : SharedPreferences.Editor = sharedPreferences.edit()

               val gson = Gson()

               val json: String = gson.toJson(MainCulinaria.recipeRegistList)

               editor.putString("note_added", json)

               editor.apply()


           }

           override fun afterTextChanged(s: Editable) {}
       })

       editTextIngs.addTextChangedListener(object : TextWatcher {
           override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
           override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

               MainCulinaria.recipeRegistList[noteID].ings = s.toString()

               MainCulinaria.adapter.notifyDataSetChanged()
           }

           override fun afterTextChanged(s: Editable) {}
       })

       editTextPrep.addTextChangedListener(object : TextWatcher {
           override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
           override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

               MainCulinaria.recipeRegistList[noteID].rPrep = s.toString()

               //MainCulinaria.adapter.notifyDataSetChanged()
           }

           override fun afterTextChanged(s: Editable) {}
       })

   }




}