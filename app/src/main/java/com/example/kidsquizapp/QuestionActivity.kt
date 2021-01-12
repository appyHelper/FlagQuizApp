package com.example.kidsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kidsquizapp.adapter.QuestionsAdapter
import com.example.kidsquizapp.data.model.Question2
import com.example.kidsquizapp.repository.QuestionRepository
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() ,SubmitOnClickListener{
 lateinit var questionsAdapter: QuestionsAdapter
    lateinit var database: DatabaseReference
 var submitOnClickListener: SubmitOnClickListener?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
       // Log.d("question activity",QuestionRepository.getQuestions(this).toString())
        database = FirebaseDatabase.getInstance().reference.child("Test Papers").child("GK").child("GK4Test4")
//
      //  QuestionRepository.database = FirebaseDatabase.getInstance().reference.child("Test Papers").child("GK").child("GK4Test4")
//        var user :JSONObject = database.child("tnpsc-b84c0-default-rtdb").get() as JSONObject
//        Log.d("Login Acitvity","user object $user.toString()")

       database.addListenerForSingleValueEvent(object : ValueEventListener {
           var questionArrayList=ArrayList<Question2>()
            override fun onCancelled(error: DatabaseError) {
                Log.d("Login Acitvity",error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {


                for (i in 1..10){
                    var randomNubmer = QuestionRepository.rand(1, 30).toString()
                    Log.d("Login question no","random number ${randomNubmer}")
                    val eachQuestionNO = snapshot.child(randomNubmer).key
                    val eachQuestionAnswer :HashMap<String,String> = snapshot.child(randomNubmer).value as HashMap<String, String>
                    Log.d("Login question no"," ${eachQuestionNO}")
                    Log.d("Login question data"," ${eachQuestionAnswer}")
                  questionArrayList.add(Question2( eachQuestionAnswer.get("answer1"),eachQuestionAnswer.get("answer2"),eachQuestionAnswer.get("answer3"),eachQuestionAnswer.get("answer4"),eachQuestionAnswer.get("correctanswer"),eachQuestionAnswer.get("question")))
                }
                Log.d("Login","question arraylist ${questionArrayList}")
                questionsAdapter= QuestionsAdapter(this@QuestionActivity,questionArrayList,submitOnClickListener)
                questionViewPager.adapter=questionsAdapter
            }

        })

//        questionViewPager.setOnClickListener {
//            QuestionRepository.getQuestions(this).get(questionViewPager.currentItem)
//            Log.d("correct answer",QuestionRepository.getQuestions(this).get(questionViewPager.currentItem).toString())
//        }
//        questionsAdapter.setOnClickQuestionListener(object :QuestionsAdapter.SubmitOnClickListener1{
//            override fun onclick(answer:ArrayList<String>) {
//              Log.d("adapter1","activity")
//                var correctAnswers=0
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
//          startActivity(Intent(this@QuestionActivity,ResultActivity::class.java).putExtra("score",correctAnswers.toString()))
//            }
//        })

    }

    override fun onSubmitClick() {
        Log.d("correct answer","sumbit")
       // startActivity(Intent(this,ResultActivity::class.java).putExtra("score","hello"))
    }


}