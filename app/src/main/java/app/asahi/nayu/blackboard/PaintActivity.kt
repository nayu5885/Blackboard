package app.asahi.nayu.blackboard

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.widget.Button

class PaintActivity : AppCompatActivity() {

    var paintView: PaintView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)

        paintView = findViewById(R.id.paintView)
        val saveButton: Button = findViewById(R.id.saveButton)
        val loadButton: Button = findViewById(R.id.loadButton)
        // データの保存にSharedPreferencesを使用．自分で実装するときにはRealmを使おう！
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
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        paintView?.setSize(paintView!!.width, paintView!!.height)
    }

    }

