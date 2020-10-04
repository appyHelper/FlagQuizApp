package com.example.kidsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kidsquizapp.adapter.QuestionsAdapter
import com.example.kidsquizapp.repository.QuestionRepository
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() ,SubmitOnClickListener{
 lateinit var questionsAdapter: QuestionsAdapter
 var submitOnClickListener: SubmitOnClickListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
 questionsAdapter= QuestionsAdapter(this,QuestionRepository.getQuestions(),submitOnClickListener)
        questionViewPager.adapter=questionsAdapter
        questionViewPager.setOnClickListener {
            QuestionRepository.getQuestions().get(questionViewPager.currentItem)
            Log.d("correct answer",QuestionRepository.getQuestions().get(questionViewPager.currentItem).toString())
        }
        questionsAdapter.setOnClickQuestionListener(object :QuestionsAdapter.SubmitOnClickListener1{
            override fun onclick(answer:ArrayList<String>) {
              Log.d("adapter1","activity")
                var correctAnswers=0
              for(i in 0..answer.size-1){
                  when(i) {
                      0 -> {
                          if (answer[0].equals("Jawaharlal Nehru")) {
                              correctAnswers++
                          }
                      }
                      1 -> {
                          if (answer[1].equals("Arundhati Roy")) {
                              correctAnswers++
                          }
                      }
                      2 -> {
                          if (answer[2].equals("Third")) {
                              correctAnswers++
                          }
                      }
                      3 -> {
                          if (answer[3].equals("Issac Newton")) {
                              correctAnswers++
                          }
                      }
                      4 -> {
                          if (answer[4].equals("Group of stars")) {
                              correctAnswers++
                          }
                      }
                  }

              }

              Log.d("correct answer",correctAnswers.toString())
          startActivity(Intent(this@QuestionActivity,ResultActivity::class.java).putExtra("score",correctAnswers.toString()))
            }
        })
//      questionsAdapter.setOnClickQuestionListener(object :QuestionsAdapter.SubmitOnClickListener{
//          override fun onSubmitClick(answer:ArrayList<String>) {
//              var correctAnswers=0
//              for(i in 0..answer.size-1){
//                  when(i) {
//                      0 -> {
//                          if (answer[0].equals("Jawaharlal Nehru")) {
//                              correctAnswers++
//                          }
//                      }
//                      1 -> {
//                          if (answer[1].equals("Arundhati Roy")) {
//                              correctAnswers++
//                          }
//                      }
//                      2 -> {
//                          if (answer[2].equals("Third")) {
//                              correctAnswers++
//                          }
//                      }
//                      3 -> {
//                          if (answer[3].equals("Issac Newton")) {
//                              correctAnswers++
//                          }
//                      }
//                      4 -> {
//                          if (answer[4].equals("Group of stars")) {
//                              correctAnswers++
//                          }
//                      }
//                  }
//
//              }
//
//              Log.d("correct answer",correctAnswers.toString())
//              //startActivity(Intent(QuestionActivity::class.java,ResultActivity::class.java).putExtra("score",correctAnswers))
//          }
//      })
    }

    override fun onSubmitClick() {
        Log.d("correct answer","sumbit")
       // startActivity(Intent(this,ResultActivity::class.java).putExtra("score","hello"))
    }


}