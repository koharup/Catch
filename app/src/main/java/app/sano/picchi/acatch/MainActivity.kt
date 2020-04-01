package app.sano.picchi.acatch

//import sun.jvm.hotspot.utilities.IntArray

//import sun.jvm.hotspot.utilities.IntArray

//import sun.jvm.hotspot.utilities.IntArray

//import sun.jvm.hotspot.utilities.IntArray

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    //private val soundPlayer = SoundPlayer(this)

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

    private var score :Int = 0








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


        scoreLabel.text = "score : 0"



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

        hitCheck()

        //orange
        orangeX -= 12
        if(orangeX < 0){
            orangeX = screenWidth + 20
            orangeY = (Math.random() * (frameHeight - orange.height)).toFloat()
        }

        orange.x = orangeX
        orange.y = orangeY


        //togetoge
        blackX -= 16
        if(blackX < 0){
            blackX = screenWidth + 10
            blackY = (Math.random() * (frameHeight - black.height)).toFloat()
        }

        black.x = blackX
        black.y = blackY


        //pink
        pinkX -= 20
        if (pinkX < 0){
            pinkX = screenWidth + 5000
            pinkY = (Math.random() * (frameHeight - pink.height)).toFloat()
        }
        pink.x = pinkX
        pink.y = pinkY

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

        scoreLabel.text = "Score : $score "
    }

    fun hitCheck() {


        //orange
        var orangeCenterX = orangeX + orange.width / 2
        var orangeCenterY = orangeY + orange.height / 2

        if (0 <= orangeCenterX && orangeCenterX <= boxSize && boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize) {
            orangeX = -10.0f
            score += 10

            //soundPlayer.playHitSound()
            //Log.d("music",soundPlayer.toString())

        }

        //pink
        var pinkCenterX = pinkX + pink.width / 2
        var pinkCenterY = pinkX + pink.width / 2

        if (0 <= pinkCenterX && pinkCenterX <= boxSize && boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize) {
            pinkX = -10.0f
            score += 30

            //soundPlayer.playHitSound()
           // Log.d("music",soundPlayer.toString())

        }


        //togetoge
        var blackCenterX = blackX + black.width / 2
        var blackCenterY = blackY + black.height / 2

        if (0 <= blackCenterX && blackCenterX <= boxSize && boxY <= blackCenterY && blackCenterY <= boxY + boxSize) {

            //soundPlayer.playOverSound()

            if (timer != null){
                timer.cancel()


                var intent = Intent(this,ResultActivity::class.java)
                intent.putExtra("SCORE",score)
                startActivity(intent)
            }


        }






    }


}
