package app.asahi.nayu.blackboard

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.widget.Button
import java.nio.ByteBuffer
import java.util.*

class PaintActivity : AppCompatActivity(context: Context, attrs: AttributeSet? = null): View(context, attrs) {
    // 外枠と格子の色や太さなどを表す
    private val gridPaint = Paint()
    // ユーザが使う線の色や太さなどを表す
    private val paint = Paint()
    // ユーザがなぞった座標を持つ
    private val path = Path()
    // ユーザが描くものの描画を行う
    var canvas = Canvas()
    // このViewの横の大きさ
    var sizeX: Int? = null
    // このViewの縦の大きさ
    var sizeY: Int? = null
    // 外枠と格子を表示するための画像
    private var gridGuid: BitmapDrawable? = null
    // 絵を描く画像
    var bmp: Bitmap? = null

    init {
        // ユーザが引く線の色などを設定
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 5f

        // 外枠と格子の色などを設定
        gridPaint.color = Color.LTGRAY
        gridPaint.style = Paint.Style.STROKE
        gridPaint.strokeJoin = Paint.Join.ROUND
        gridPaint.strokeCap = Paint.Cap.ROUND
        gridPaint.strokeWidth = 10f
    }

    // PaintViewの大きさを設定し，格子を描画するメソッド
    fun setSize(x:Int, y:Int){
        // 受け取った大きさを上述の変数に代入
        sizeX = x
        sizeY = y

        // 外枠と格子を表示するBitmap画像を生成
        val gridBmp = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888)
        // 格子と外枠の描画を行うCanvasをBitmap画像を元に作製
        val gridCanvas = Canvas(gridBmp)
        // 外枠を描画
        gridCanvas.drawRect(0f, 0f, x.toFloat(), y.toFloat(), gridPaint)

        // 格子を描画するため線の太さを変更
        gridPaint.strokeWidth = 2f

        // 格子を描画
        var lineX = 0f
        var lineY = 0f
        val width = x / 10f
        val height = y / 10f

        for(i in 1..9){
            lineX += height
            lineY += width
            gridCanvas.drawLine(0f, lineX, x.toFloat(), lineX, gridPaint)
            gridCanvas.drawLine(lineY, 0f, lineY, y.toFloat(), gridPaint)
        }

        // 出来上がった画像を元にgridGuidのインスタンスを作製
        gridGuid = BitmapDrawable(resources, gridBmp)
        // PaintViewの前景に設定
        this.foreground = gridGuid

        // ユーザが描画する画像を作って白く塗りつぶす
        bmp = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bmp)
        canvas.drawColor(Color.WHITE)
    }

    // 触られたときの処理
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event == null) return true
        val x = event.x
        val y  = event.y

        when(event.action){
            // タップされたらpathを初期化し，タップされた場所を原点として設定
            MotionEvent.ACTION_DOWN ->{
                path.reset()
                path.moveTo(x, y)
            }
            // タップされたまま動かした場合，動かした先の座標まで線を引く
            MotionEvent.ACTION_MOVE ->{
                path.lineTo(x, y)
            }
            // 指が離れた場合，離れた座標まで線を引く
            MotionEvent.ACTION_UP ->{
                path.lineTo(x, y)
            }
        }

        // 描画する
        draw()
        return true
    }

    private fun draw(){
        // pathに保持された線を元に，paintの設定に基づいて線を描画する
        canvas.drawPath(path, paint)
        // PaintViewの背景として設定
        this.background = BitmapDrawable(resources, bmp)
    }

    // 線の色を変更するときに呼ぶメソッド
    fun setPathColor(color:Int){
        paint.color = color
    }

    // 線の太さを変更するときに呼ぶメソッド
    fun setPathWidth(width:Float){
        paint.strokeWidth = width
    }

    // 描いた画像をBase64でエンコードして取得するときに呼ぶメソッド
    fun getImageStr():String?{
        if(bmp == null) return null

        val byteBuffer = ByteBuffer.allocate(bmp!!.byteCount)
        bmp!!.copyPixelsToBuffer(byteBuffer)
        return Base64.encodeToString(byteBuffer.array(), Base64.DEFAULT)
    }

    // 既存のBitmap画像を設定するときに呼ぶメソッド
    fun setImage(bitmap: Bitmap){
        canvas.setBitmap(bitmap)
        bmp = bitmap
        this.background = BitmapDrawable(resources, bmp)
    }

    // Base64でエンコードした画像データの文字列と縦横のサイズからBitmap画像を作成し，設定するときに呼ぶメソッド
    fun setImage(sizeX: Int, sizeY: Int, strImage: String){
        val bitmap = Bitmap.createBitmap(sizeX, sizeY, Bitmap.Config.ARGB_8888)
        bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(Base64.decode(strImage, Base64.DEFAULT)))

        setImage(bitmap)
    }

  }




