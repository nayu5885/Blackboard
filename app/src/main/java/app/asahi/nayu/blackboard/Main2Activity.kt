package app.asahi.nayu.blackboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm

class Main2Activity : AppCompatActivity() {

    private val realm by lazy {
        Realm.getDefaultInstance()
    }

    //var adapter: RecyclerViewAdapter? = null


    val BoardData: List<BoardData> = listOf(
        BoardData("ここタイトル人生", "疲れた"),
        BoardData("ここタ略かっぱの", "河流れ"),
       BoardData("ここ略出る杭は", "打たれる"),
        BoardData("ここ略ロースト", "チキン"),
        BoardData("こ略片翼の", "セフィロス"),
        BoardData("‥(´・ω・｀)", "なんなん"),
        BoardData("‥そろそろ", "飽きた"),
        BoardData("‥秋田", "山形")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val button4: Button = findViewById(R.id.button4)


        if(realm.where(Memo::class.java).findAll().isNullOrEmpty()) createInitList()

       // adapter = RecyclerViewAdapter(this, object: RecyclerViewAdapter.OnItemClickListner{
          //  override fun onItemClick(item: BoardData) {
                // SecondActivityに遷移するためのIntent
             //   val intent = Intent(applicationContext, BoardData::class.java)
                // RecyclerViewの要素をタップするとintentによりSecondActivityに遷移する
                // また，要素のidをSecondActivityに渡す
              ///  intent.putExtra("id", item.id)
              //  startActivity(intent)
           // }
       // })

       // val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
       // recyclerView.setHasFixedSize(true)
       // recyclerView.layoutManager = LinearLayoutManager(this)
       // recyclerView.adapter = adapter


       // val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
      //  recyclerView.setHasFixedSize(true)
       // recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = adapter

       // findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            // SecondActivityに遷移するためのIntent
          //  val intent = Intent(applicationContext,BoardData::class.java)
            // SecondActivityに遷移
         //   startActivity(intent)
       // }



        button4.setOnClickListener {
            val intent = Intent(this, script::class.java)
            startActivity(intent)
        }
    }

    private fun createInitList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


}
