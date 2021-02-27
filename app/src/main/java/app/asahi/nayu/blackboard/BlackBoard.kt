package app.asahi.nayu.blackboard

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.system.Os.read
import android.widget.Button
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_black_board.*

class BlackBoard : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()

    var paintView: PaintActivity? = null

    val memo: RealmResults<Memo>? =read()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_black_board)

        paintView = findViewById(R.id.paintActivity)
        val saveButton: Button = findViewById(R.id.saveButton)
        val loadButton: Button = findViewById(R.id.loadButton)
        // データの保存にSharedPreferencesを使用．自分で実装するときにはRealmを使おう！
        //val pref: SharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
        if (content !=null){
            paintActivity.setPathColor(memo.content)
        }

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

    fun read(): RealmResults<Memo>? {
        return realm.where(Memo::class.java).findAll()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


}

