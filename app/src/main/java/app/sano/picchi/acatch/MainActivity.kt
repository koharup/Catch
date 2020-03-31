package app.sano.picchi.acatch

//import sun.jvm.hotspot.utilities.IntArray

//import sun.jvm.hotspot.utilities.IntArray

//import sun.jvm.hotspot.utilities.IntArray

//import sun.jvm.hotspot.utilities.IntArray

import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var boxY: Float = 0f
    private var handler = Handler()
    private var timer = Timer()

    //フレームサイズ
    private var frameHeight : Int = 0
    private var boxSize:Int = 0

    //画面サイズ
    private  var screenWidth = 0f
    private  var screenHeight = 0f

    private var orangeX = 0f
    private var orangeY = 0f
    private var pinkX = 0f
    private var pinkY = 0f
    private var blackX = 0f
    private var blackY = 0f





    //画面に触れているかチェック
    private var action_flg: Boolean = false
    //画面の開始
    private var start_flg: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wm = windowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)

        screenWidth = size.x.toFloat()
        screenHeight = size.y.toFloat()



        //画面開始前だから左上にして見えなくしてる
        orange.x = (-80.0f)
        orange.y = (-80.0f)
        pink.x = (-80.0f)
        pink.y = (-80.0f)
        black.x = (-80.0f)
        black.y = (-80.0f)


    }


    //タッチされたらboxYから20引く(上に移動する)
    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (!start_flg) {
            start_flg = true
            
            frameHeight = frame.height

            boxY = box.y
            boxSize = box.height



            startLabel.visibility = View.GONE

            timer.schedule(object : TimerTask() {
                override fun run() {
                    handler.post { changePos() }
                }
            }, 0, 20)

        } else {

            if (event.action == MotionEvent.ACTION_DOWN) {
                action_flg = true

            } else if (event.action == MotionEvent.ACTION_UP) {
                action_flg = false
            }
        }

        return true
    }


    fun changePos(){

        //orange
        orangeX -= 12
        if(orangeX < 0){
            orangeX = screenWidth + 20
            //orangeY = (float)Math.floor(Math.random() * (frameHeight - orange.height()))

        }







        //box
        if(action_flg){
            boxY -= 20
        }else { //タッチされてない
            boxY += 20
        }
        box.y = boxY


        if (boxY < 0) boxY = 0F


        if (boxY > frameHeight - boxSize) {
            boxY = frameHeight.toFloat() - boxSize.toFloat()
        }



        box.y = boxY
    }



}
