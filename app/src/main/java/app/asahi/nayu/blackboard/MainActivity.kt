package app.asahi.nayu.blackboard

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu.*

class MainActivity : AppCompatActivity() {

    var paintView: PaintView? = null

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

        paintView = findViewById(R.id.paintView)

       val button:Button =findViewById(R.id.button)
        val saveButton: Button = findViewById(R.id.saveButton)
        val loadButton: Button = findViewById(R.id.loadButton)

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.addAll(BoardData)

        val pref: SharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)

        // 保存処理
        saveButton.setOnClickListener {
            // PaintViewで描いた画像を文字列にして取得．nullなら何もせずタップ時の処理を終える
            val strImage = paintView?.getImageStr() ?: return@setOnClickListener

            // Base64の文字列から画像を作成するために必要なデータ（縦横のサイズとBase64形式の画像データ）を保存
            pref.edit().apply {
                putInt("sizeX", paintView?.sizeX ?: 0)
                putInt("sizeY", paintView?.sizeY ?: 0)
                putString("image", strImage)
                apply()
            }
        }

        // 読み込み処理
        loadButton.setOnClickListener {
            // 保存したデータから縦横のサイズとBase64形式の画像データを取得
            val sizeX: Int = pref.getInt("sizeX", 0)
            val sizeY: Int = pref.getInt("sizeY", 0)
            val image: String = pref.getString("image", null) ?: return@setOnClickListener

            // PaintViewのsetImageメソッドに取得したデータを渡す
            paintView?.setImage(sizeX, sizeY, image)
        }

        button.setOnClickListener {
            val intent = Intent(this, script::class.java)
            startActivity(intent)
        }

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        paintView?.setSize(paintView!!.width, paintView!!.height)
    }

    }


