package ipca.followthroughapp.treinos

import android.os.Bundle
import android.os.CountDownTimer
import android.service.autofill.OnClickAction
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ipca.followthroughapp.R
import java.lang.String


class TreinosInput : AppCompatActivity() {

    companion object {
        const val T_NAME   = "t_name"
        const val TIMER = "timer"
    }

    var noteID = 0
    var counter = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.folder_input_treinos)

        var start : Boolean = false

        val inputName = intent.getStringExtra(T_NAME)
        var inputTimer = intent.getStringExtra(TIMER)
        val tNameI = findViewById<TextView>(R.id.nameI)
        var timerI = findViewById<TextView>(R.id.timerI)
        tNameI.text = inputName
        timerI.text = inputTimer


        val intent = intent
        noteID = intent.getIntExtra("noteID", -1)

        if(noteID != -1)
        {
            tNameI.setText(MainTreinos.treinosRegistList[noteID].tName);
            timerI.setText(MainTreinos.treinosRegistList[noteID].timer);
        }

        //REDUZZIR O INPUTITMER
        val button = findViewById(R.id.startTimer) as Button
        button.setOnClickListener{

            object : CountDownTimer(inputTimer!!.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished : Long) {
                    timerI.text = (millisUntilFinished / 1000).toString();
                    Log.d("bruh2", inputTimer.toString())
                }

                override fun onFinish() {
                    timerI.text = "Finito"
                }
            }.start()

            Log.d("start", inputTimer.toString())
        }


        Log.d("start", start.toString())

    }


}