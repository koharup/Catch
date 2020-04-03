package app.sano.picchi.acatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

    var level = 0

class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }



    fun lowStart(v:View){
        level = 0
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun normalStart(v:View){
        level = 1
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun highStart(v:View){
        level = 2
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }





}
