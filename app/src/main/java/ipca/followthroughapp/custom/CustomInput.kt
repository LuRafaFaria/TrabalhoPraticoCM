package ipca.followthroughapp.custom

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ipca.followthroughapp.R
import ipca.followthroughapp.culinaria.MainCulinaria

class CustomInput : AppCompatActivity() {

    companion object {
        const val C_NAME   = "c_name"

    }

    var noteID = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.folder_input_rest)


        val inputName = intent.getStringExtra(C_NAME)


        val cNameI = findViewById<TextView>(R.id.nameI)


        cNameI.text = inputName



        val intent = intent
        noteID = intent.getIntExtra("noteID", -1)

        if(noteID != -1)
        {
            cNameI.setText(MainCustom.customRegistList[noteID].cName);
        }

    }
}