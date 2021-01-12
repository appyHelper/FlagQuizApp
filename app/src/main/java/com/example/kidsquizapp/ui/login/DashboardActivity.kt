package com.example.kidsquizapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.kidsquizapp.QuestionActivity
import com.example.kidsquizapp.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val list = arrayOf(
            "GKTest1",
            "E1Test1",
            "M1Test2",
            "GK2Test2"
        )
        rvTest.adapter=ArrayAdapter(this,R.layout.item_test_layout,R.id.testId,list)
        rvTest.setOnItemClickListener { parent, view, position, id ->
            startActivity(Intent(this, QuestionActivity::class.java))
        }


    }
}