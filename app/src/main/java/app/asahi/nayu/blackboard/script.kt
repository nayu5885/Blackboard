package app.asahi.nayu.blackboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

//mport kotlinx.android.synthetic.main.draw.*

class script : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.script)

        val button2:Button=findViewById(R.id.button2)

        val button3:Button=findViewById(R.id.button3)

        button2.setOnClickListener {
           finish()
        }

        button3.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }

    }
}