package ipca.followthroughapp.culinaria

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ipca.followthroughapp.R


class RecipeInput : AppCompatActivity() {

    companion object {
        const val R_NAME   = "r_name"
        const val INGS     = "ings"
        const val R_PREP  = "r_prep"
    }

    var noteID = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.folder_input)


        val inputName = intent.getStringExtra(R_NAME)
        val inputIngs = intent.getStringExtra(INGS)
        val inputPrep = intent.getStringExtra(R_PREP)

        val rNameI = findViewById<TextView>(R.id.rNameI)
        val rIngsI = findViewById<TextView>(R.id.rIngsI)
        val rPrepI = findViewById<TextView>(R.id.rPrepI)



        rNameI.text = inputName
        rIngsI.text = inputIngs
        rPrepI.text = inputPrep


        val editText = findViewById<View>(R.id.rNameD)
        val intent = intent
        noteID = intent.getIntExtra("noteID", -1)

        if(noteID != -1)
        {
            rNameI.setText(MainCulinaria.recipeRegistList[noteID].rName);
        }

    }

}