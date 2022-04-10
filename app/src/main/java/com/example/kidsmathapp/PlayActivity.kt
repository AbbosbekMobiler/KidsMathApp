package com.example.kidsmathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        addtion.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("cals","+")
            startActivity(intent)
        }

        subtraction.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("cals","-")
            startActivity(intent)
        }

        multi.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("cals","*")
            startActivity(intent)
        }

        divie.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("cals","/")
            startActivity(intent)
        }
    }
}