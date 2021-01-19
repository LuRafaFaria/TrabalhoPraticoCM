package ipca.followthroughapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainDetailActivity : AppCompatActivity(){

    companion object {
        const val F_NAME   = "f_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_recipe)


        val fName = intent.getStringExtra(F_NAME)

        val fNameD = findViewById<TextView>(R.id.fName)

        fNameD.text = fName
    }
}