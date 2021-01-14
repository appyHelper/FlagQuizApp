package com.example.kidsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kidsquizapp.data.model.Question2
import com.example.kidsquizapp.ui.login.LoginActivity
import com.example.kidsquizapp.ui.login.ReviewActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var questionList=intent.getParcelableArrayListExtra<Question2>("questionList")
        setContentView(R.layout.activity_result)
        var score=intent.getStringExtra("score")
        var total=intent.getStringExtra("total")
       textView3.text="Your Score $score / $total"
        logout.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        review.setOnClickListener {
            Log.d("result",questionList.toString())
            startActivity(Intent(this,ReviewActivity::class.java).putExtra("review",true))
        }
    }
}