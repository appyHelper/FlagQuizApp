package com.example.kidsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kidsquizapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var score=intent.getStringExtra("score")
        var total=intent.getStringExtra("total")
       textView3.text="Your Score $score / $total"
        logout.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}