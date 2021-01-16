package app.asahi.nayu.blackboard

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class script : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.script)

        val button2:Button=findViewById(R.id.button2)

        button2.setOnClickListener {
            
        }
}
}