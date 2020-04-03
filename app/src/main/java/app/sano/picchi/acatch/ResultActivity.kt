package app.sano.picchi.acatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.scoreLabel
import kotlinx.android.synthetic.main.activity_result.*


//import sun.jvm.hotspot.utilities.IntArray


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var score = intent.getIntExtra("SCORE",0)
        scoreLabel.text = "score: $score"


        var sharedPreferences = getSharedPreferences("GAME_DATA",Context.MODE_PRIVATE)
        var highScore = sharedPreferences.getInt("HIGH_SCORE",0)


        if (score > highScore){
            highScoreLabel.text = "HighScore: $score"

            var editor = sharedPreferences.edit()
            editor.putInt("HIGH_SCORE",score)
            editor.apply()

        }else {
            highScoreLabel.text = "HighScore: $highScore"
        }

        }

    fun tryAgain(v:View){
        intent = Intent(this,StartActivity::class.java)
        startActivity(intent)

    }





    }

