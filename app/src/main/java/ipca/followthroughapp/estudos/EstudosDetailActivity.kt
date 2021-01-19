package ipca.followthroughapp.estudos

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
import ipca.followthroughapp.custom.CustomDetailActivity
import ipca.followthroughapp.custom.CustomRegist
import ipca.followthroughapp.custom.MainCustom

class EstudosDetailActivity : AppCompatActivity() {

    companion object {
        const val E_NAME   = "e_name"

    }

    var noteID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_estudos)


        val eName = intent.getStringExtra(EstudosDetailActivity.E_NAME)


        val eNameD = findViewById<TextView>(R.id.eNameD)


        eNameD.text = eName

        val editTextName = findViewById<View>(R.id.eNameD) as EditText

        val intent = intent
        noteID = intent.getIntExtra("noteID", -1)

        if(noteID != -1)
        {
            editTextName.setText(MainEstudos.estudosRegistList[noteID].eName);

            //MainCulinaria.adapter.notifyDataSetChanged()
        }

        else
        {
            MainEstudos.estudosRegistList.add(EstudosRegist("Exemplo"))
            noteID = MainEstudos.estudosRegistList.size - 1
            MainEstudos.adapter.notifyDataSetChanged()
        }

        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                MainEstudos.estudosRegistList[noteID].eName = s.toString()

                MainEstudos.adapter.notifyDataSetChanged()

                val sharedPreferences : SharedPreferences = getSharedPreferences("ipca.followthrough.app", Context.MODE_PRIVATE)

                val editor : SharedPreferences.Editor = sharedPreferences.edit()

                val gson = Gson()

                val json: String = gson.toJson(MainEstudos.estudosRegistList)

                editor.putString("note_added", json)

                editor.apply()


            }

            override fun afterTextChanged(s: Editable) {}
        })

    }
}