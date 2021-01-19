package ipca.followthroughapp.treinos

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import ipca.followthroughapp.InputFilterMinMax
import ipca.followthroughapp.R


class TreinosDetailActivity : AppCompatActivity() {

    companion object {
        const val T_NAME   = "t_name"
        const val TIMER = "timer"

    }

    var noteID = 0
    var timer = "0"
    var min = 0
    var max = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_treinos)

        val switchTimer = findViewById<View>(R.id.switchTimer) as Switch
        val timeText = findViewById<View>(R.id.editTimerSec)

        timeText.isEnabled = false
        timeText.isVisible = false


        switchTimer.setOnClickListener {
            if (switchTimer.isChecked())
            {
                timeText.isEnabled = true
                timeText.isVisible = true
                MainTreinos.adapter.notifyDataSetChanged()
            }
            else
            {
                timeText.isEnabled = false
                timeText.isVisible = false
            }
        }

        val tName = intent.getStringExtra(TreinosDetailActivity.T_NAME)
        val timerInput = intent.getStringExtra(TIMER)

        val tNameD = findViewById<TextView>(R.id.tNameD)
        val timerDetail = findViewById<TextView>(R.id.editTimerSec)


        tNameD.text = tName
        timerDetail.text = timerInput

        val editTextName = findViewById<View>(R.id.tNameD) as EditText
        val editTimer = findViewById<View>(R.id.editTimerSec) as EditText

        //editTimer.filters = arrayOf<InputFilter>(InputFilterMinMax("1", "3600"))

        if (editTimer.text.toString().compareTo("") > 500 )
        {
            Log.d("britoDentro", (editTimer.text.toString().compareTo("") > 500).toString() )
        }
        Log.d("britoFora", (editTimer.text.toString().compareTo("") > 500).toString()  )





        val intent = intent

        noteID = intent.getIntExtra("noteID", -1)

        if(noteID != -1)
        {
            editTextName.setText(MainTreinos.treinosRegistList[noteID].tName);
            editTimer.setText(MainTreinos.treinosRegistList[noteID].timer)


        }

        else
        {
            MainTreinos.treinosRegistList.add(TreinosRegist("Exemplo", timer))
            noteID = MainTreinos.treinosRegistList.size - 1
            MainTreinos.adapter.notifyDataSetChanged()
        }

        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                MainTreinos.treinosRegistList[noteID].tName = s.toString()

                MainTreinos.adapter.notifyDataSetChanged()

                val sharedPreferences : SharedPreferences = getSharedPreferences("ipca.followthrough.app", Context.MODE_PRIVATE)

                val editor : SharedPreferences.Editor = sharedPreferences.edit()

                val gson = Gson()

                val json: String = gson.toJson(MainTreinos.treinosRegistList)

                editor.putString("note_added", json)

                editor.apply()


            }

            override fun afterTextChanged(s: Editable) {}
        })

        editTimer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


                if (editTimer.text.toString().compareTo("3600") > 0)
                {
                    Toast.makeText(this@TreinosDetailActivity, "Max Timer 1 hour", Toast.LENGTH_LONG).show()
                }
                else
                {
                    MainTreinos.treinosRegistList[noteID].timer = s.toString()

                    MainTreinos.adapter.notifyDataSetChanged()

                    val sharedPreferences : SharedPreferences = getSharedPreferences("ipca.followthrough.app", Context.MODE_PRIVATE)

                    val editor : SharedPreferences.Editor = sharedPreferences.edit()

                    val gson = Gson()

                    val json: String = gson.toJson(MainTreinos.treinosRegistList)

                    editor.putString("note_added", json)

                    editor.apply()
                }



            }

            override fun afterTextChanged(s: Editable) {}
        })


    }



}