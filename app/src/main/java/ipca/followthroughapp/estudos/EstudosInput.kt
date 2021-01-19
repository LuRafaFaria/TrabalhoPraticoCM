package ipca.followthroughapp.estudos

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ipca.followthroughapp.R
import ipca.followthroughapp.custom.MainCustom
import kotlin.math.E

class EstudosInput : AppCompatActivity() {

    companion object {
        const val E_NAME   = "e_name"

    }

    var noteID = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.folder_input_estudos)

        //val inputname comp object
        //val cNameI = viewid nameI
        //rNameI = inputName
        //edittext cNameD

        val inputName = intent.getStringExtra(E_NAME)


        val eNameI = findViewById<TextView>(R.id.nameI)


        eNameI.text = inputName

        val intent = intent
        noteID = intent.getIntExtra("noteID", -1)

        if(noteID != -1)
        {
            eNameI.setText(MainEstudos.estudosRegistList[noteID].eName);
        }

    }
}