package app.asahi.nayu.blackboard

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu.*

class MainActivity : AppCompatActivity() {

    val BoardData:List<BoardData> = listOf(
        BoardData("ここタイトル人生","疲れた"),
        BoardData("ここタ略かっぱの","河流れ"),
        BoardData("ここ略出る杭は","打たれる"),
        BoardData("ここ略ロースト","チキン"),
        BoardData("こ略片翼の","セフィロス"),
        BoardData("‥(´・ω・｀)" ,"なんなん"),
        BoardData("‥そろそろ","飽きた"),
        BoardData("‥秋田","山形")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

       val button:Button =findViewById(R.id.button)

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.addAll(BoardData)

        button.setOnClickListener {
            val intent = Intent(this, script::class.java)
            startActivity(intent)
        }

    }
}
