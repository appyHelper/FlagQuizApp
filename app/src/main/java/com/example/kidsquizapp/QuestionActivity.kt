package com.example.kidsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kidsquizapp.adapter.QuestionsAdapter
import com.example.kidsquizapp.repository.QuestionRepository
import kotlinx.android.synthetic.main.activity_question.*
import com.example.kidsquizapp.callback.SubmitOnClickListener

class QuestionActivity : AppCompatActivity() ,SubmitOnClickListener {
 var submitOnClickListener: SubmitOnClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        questionViewPager.adapter=QuestionsAdapter(this,QuestionRepository.getQuestions(),submitOnClickListener)
    }

    override fun onSubmitClick(answer:ArrayList<String>) {
        var correctAnswers=0
       for(i in 0..answer.size-1){
        when(i) {
            0 -> {
                if (answer[0].equals("radioButton2")) {
                    correctAnswers++
                }
            }
            1 -> {
                if (answer[1].equals("radioButton3")) {
                    correctAnswers++
                }
            }
            2 -> {
                if (answer[2].equals("radioButton3")) {
                    correctAnswers++
                }
            }
            3 -> {
                if (answer[3].equals("radioButton3")) {
                    correctAnswers++
                }
            }
            4 -> {
                if (answer[4].equals("radioButton3")) {
                    correctAnswers++
                }
            }
        }

        }

        Log.d("correct answer",correctAnswers.toString())
        startActivity(Intent(this,ResultActivity::class.java).putExtra("score",correctAnswers))
    }
}