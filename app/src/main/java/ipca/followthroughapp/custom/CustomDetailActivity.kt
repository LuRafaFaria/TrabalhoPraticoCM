package ipca.followthroughapp.custom

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
import ipca.followthroughapp.R
import ipca.followthroughapp.culinaria.MainCulinaria
import ipca.followthroughapp.culinaria.RecipeRegist

class CustomDetailActivity : AppCompatActivity() {

    companion object {
        const val C_NAME   = "c_name"

    }

    var noteID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_custom)


        val cName = intent.getStringExtra(C_NAME)


        val cNameD = findViewById<TextView>(R.id.cNameD)


        cNameD.text = cName

        val editTextName = findViewById<View>(R.id.cNameD) as EditText

        val intent = intent
        noteID = intent.getIntExtra("noteID", -1)

        if(noteID != -1)
        {
            editTextName.setText(MainCustom.customRegistList[noteID].cName);

            //MainCulinaria.adapter.notifyDataSetChanged()
        }

        else
        {
            MainCustom.customRegistList.add(CustomRegist("Exemplo"))
            noteID = MainCustom.customRegistList.size - 1
            MainCustom.adapter.notifyDataSetChanged()
        }

        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                MainCustom.customRegistList[noteID].cName = s.toString()

                MainCustom.adapter.notifyDataSetChanged()

                val sharedPreferences : SharedPreferences = getSharedPreferences("ipca.followthrough.app", Context.MODE_PRIVATE)

                val editor : SharedPreferences.Editor = sharedPreferences.edit()

                val gson = Gson()

                val json: String = gson.toJson(MainCustom.customRegistList)

                editor.putString("note_added", json)

                editor.apply()


            }

            override fun afterTextChanged(s: Editable) {}
        })



    }

}