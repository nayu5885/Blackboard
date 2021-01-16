package app.asahi.nayu.blackboard

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu.*

class MainActivity : AppCompatActivity() {

    val BoardData:List<BoardData> = listOf(
        BoardData("人生","疲れた"),
        BoardData("かっぱの","河流れ"),
        BoardData("出る杭は","打たれる"),
        BoardData("ロースト","チキン"),
        BoardData("片翼の","セフィロス"),
        BoardData("(´・ω・｀)" ,"なんなん"),
        BoardData("そろそろ","飽きた"),
        BoardData("秋田","山形")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.addAll(BoardData)

    }
}
