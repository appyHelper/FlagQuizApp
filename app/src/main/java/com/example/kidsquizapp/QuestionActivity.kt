package com.example.kidsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kidsquizapp.adapter.QuestionsAdapter
import com.example.kidsquizapp.data.model.Question2
import com.example.kidsquizapp.repository.QuestionRepository
import com.example.kidsquizapp.ui.login.DataManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() ,SubmitOnClickListener{
 lateinit var questionsAdapter: QuestionsAdapter
    lateinit var database: DatabaseReference
    lateinit var databaseMarks: DatabaseReference
 var submitOnClickListener: SubmitOnClickListener?=null
    var questionArrayList=ArrayList<Question2>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        var review =intent.getBooleanExtra("review",false)
        var uid =intent.getStringExtra("uid").toString()
        var testName : String= intent.getStringExtra("testName").toString()
        Log.d("question activity",testName)
        database = FirebaseDatabase.getInstance().reference.child("Test Papers").child(testName)
        databaseMarks = FirebaseDatabase.getInstance().reference.child("Student").child(uid)
//
      //  QuestionRepository.database = FirebaseDatabase.getInstance().reference.child("Test Papers").child("GK").child("GK4Test4")
//        var user :JSONObject = database.child("tnpsc-b84c0-default-rtdb").get() as JSONObject
//        Log.d("Login Acitvity","user object $user.toString()")

       database.addListenerForSingleValueEvent(object : ValueEventListener {

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
                DataManager.setQuestionList("list",questionArrayList)
                questionsAdapter= QuestionsAdapter(this@QuestionActivity,questionArrayList,submitOnClickListener,review)
                questionViewPager.adapter=questionsAdapter
                if (review){
                    questionsAdapter.notifyDataSetChanged()
                }
                questionsAdapter.setOnClickQuestionListener(object :QuestionsAdapter.SubmitOnClickListener1{
                    override fun onclick(answer:String,totalQuestion:String) {
                        Log.d("question",answer.toString())
                        var markHashMap = HashMap<String,String>()
                        markHashMap[testName]=answer
                        databaseMarks.child("marks").push().setValue(markHashMap);
                        startActivity(Intent(this@QuestionActivity,ResultActivity::class.java).putExtra("score",answer.toString()).putExtra("total",totalQuestion.toString()))
                    }
                })
            }

        })

//        questionViewPager.setOnClickListener {
//            QuestionRepository.getQuestions(this).get(questionViewPager.currentItem)
//            Log.d("correct answer",QuestionRepository.getQuestions(this).get(questionViewPager.currentItem).toString())
//        }


    }

    override fun onSubmitClick() {
        Log.d("correct answer","sumbit")
        questionArrayList.clear()
        startActivity(Intent(this,ResultActivity::class.java).putExtra("score","hello").putParcelableArrayListExtra("questionList",questionArrayList))
    }


}