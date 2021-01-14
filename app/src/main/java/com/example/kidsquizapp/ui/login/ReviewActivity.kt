package com.example.kidsquizapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.example.kidsquizapp.R
import com.example.kidsquizapp.ResultActivity
import com.example.kidsquizapp.SubmitOnClickListener
import com.example.kidsquizapp.adapter.QuestionsAdapter
import com.example.kidsquizapp.data.model.Question2
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.item_layout.*

class ReviewActivity : AppCompatActivity() ,SubmitOnClickListener{
    lateinit var questionsAdapter1: QuestionsAdapter
    var submitOnClickListener: SubmitOnClickListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
//        var questionList=intent.getParcelableArrayListExtra<Question2>("questionList")!!
        var questionList=DataManager.getQuestionList("list")
        Log.d("review",questionList.toString())

        questionsAdapter1= QuestionsAdapter(this@ReviewActivity,questionList,submitOnClickListener,true)
        reviewViewPager.adapter=questionsAdapter1

        questionsAdapter1.setOnClickQuestionListener(object :QuestionsAdapter.SubmitOnClickListener1{
            override fun onclick(answer:String,totalQuestion:String) {
                Log.d("question",answer.toString())
                startActivity(Intent(this@ReviewActivity,LoginActivity::class.java))
                  }
        })

    }

    override fun onSubmitClick() {
        startActivity(Intent(this,LoginActivity::class.java))
    }
}