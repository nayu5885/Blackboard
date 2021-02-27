package app.asahi.nayu.blackboard

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.system.Os.read
import android.widget.Button
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_black_board.*

class Main2Activity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()

    val button4: Button = findViewById(R.id.button4)

    val BoardData: List<BoardData> = listOf(
       // BoardData("ここタイトル人生", "疲れた"),
       // BoardData("ここタ略かっぱの", "河流れ"),
      //  BoardData("ここ略出る杭は", "打たれる"),
      //  BoardData("ここ略ロースト", "チキン"),
      //  BoardData("こ略片翼の", "セフィロス"),
      //  BoardData("‥(´・ω・｀)", "なんなん"),
       // BoardData("‥そろそろ", "飽きた"),
       // BoardData("‥秋田", "山形")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button4.setOnClickListener {
            val intent = Intent(this, script::class.java)
            startActivity(intent)
        }

     //   val memo: RealmResults<Memo>? =read()

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


}
