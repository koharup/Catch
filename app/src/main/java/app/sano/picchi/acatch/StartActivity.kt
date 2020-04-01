package app.sano.picchi.acatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    fun startGame(v:View){
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun startGame2(v:View){
        startActivity(intent)
    }



}
