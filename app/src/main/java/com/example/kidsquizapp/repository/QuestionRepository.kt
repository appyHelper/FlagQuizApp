package com.example.kidsquizapp.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Switch
import com.example.kidsquizapp.data.model.GKQuestions
import com.example.kidsquizapp.data.model.Question
import com.example.kidsquizapp.data.model.Question1
import com.example.kidsquizapp.data.model.Question2
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.json.JSONObject

object QuestionRepository {

     lateinit var database: DatabaseReference
    fun getQuestions(context: Context) : ArrayList<Question2>{
      //  randomNumberGenerator()
      //  initializeFirebase(context)
       // var questionArrayList=ArrayList<Question2>()
       var questionArrayList=ArrayList<Question2>()
        database = FirebaseDatabase.getInstance().reference.child("Test Papers").child("GK").child("GK4Test4")
//        var user :JSONObject = database.child("tnpsc-b84c0-default-rtdb").get() as JSONObject
//        Log.d("Login Acitvity","user object $user.toString()")

        database.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.d("Login Acitvity",error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (i in 1..10){
                    var randomNubmer =rand(1, 30).toString()
                    Log.d("Login question no","random number ${randomNubmer}")
                    val eachQuestionNO = snapshot.child(randomNubmer).key
                    val eachQuestionAnswer :HashMap<String,String> = snapshot.child(randomNubmer).value as HashMap<String, String>
                    Log.d("Login question no"," ${eachQuestionNO}")
                    Log.d("Login question data"," ${eachQuestionAnswer}")
                    questionArrayList.add(Question2( eachQuestionAnswer.get("answer1"),eachQuestionAnswer.get("answer2"),eachQuestionAnswer.get("answer3"),eachQuestionAnswer.get("answer4"),eachQuestionAnswer.get("correctanswer"),eachQuestionAnswer.get("question")))
                }
                Log.d("Login","question arraylist ${questionArrayList}")

            }

        })
        Log.d("Login","question arraylist ${questionArrayList}")
        Log.d("Login","question return arraylist ${questionArrayList}")

//    var questionArrayList = ArrayList<Question>()
//        questionArrayList.add(Question("1.Who is the first Prime Minister of India?","P.J. Abdul Kalaam","Jawaharlal Nehru","Indira Gandhi","Narendra Modi"))
//        questionArrayList.add(Question("2. Who is the author of “God of Small Things?”","Gabriel Garcia Marquez","Aravind Adiga","Arundhati Roy","Victor Hugo"))
//        questionArrayList.add(Question("3. What is the position of Earth in our Solar system?","First","Second","Third","Fourth"))
//        questionArrayList.add(Question("4. Who found the concept of “Gravitation?","Albert Einstein","Charles Darwin","Issac Newton","V. Raman"))
//        questionArrayList.add(Question("5. What are constellations?","Group of planets","Group of galaxies","Group of stars","Group of meteors"))
       return questionArrayList
    }

//    fun randomNumberGenerator(){
//        for (i in 1..10) {
//            Log.d("login", rand(1, 30).toString())
//        }
//    }
    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (Math.random() * (end - start + 1)).toInt() + start
    }

//    fun initializeFirebase(context: Context) {
//
//        database = FirebaseDatabase.getInstance().reference.child("Test Papers").child("GK").child("GK4Test4")
////        var user :JSONObject = database.child("tnpsc-b84c0-default-rtdb").get() as JSONObject
////        Log.d("Login Acitvity","user object $user.toString()")
//
//        database.addListenerForSingleValueEvent(object : ValueEventListener {
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("Login Acitvity",error.message)
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                for (i in 1..10){
//                    var randomNubmer =rand(1, 30).toString()
//                    Log.d("Login question no","random number ${randomNubmer}")
//                    val eachQuestionNO = snapshot.child(randomNubmer).key
//                    val eachQuestionAnswer :HashMap<String,String> = snapshot.child(randomNubmer).value as HashMap<String, String>
//                    Log.d("Login question no"," ${eachQuestionNO}")
//                    Log.d("Login question data"," ${eachQuestionAnswer}")
//                   questionArrayList.add(Question2( eachQuestionAnswer.get("answer1"),eachQuestionAnswer.get("answer2"),eachQuestionAnswer.get("answer3"),eachQuestionAnswer.get("answer4"),eachQuestionAnswer.get("correctanswer"),eachQuestionAnswer.get("question")))
//                }
//                Log.d("Login","question arraylist ${questionArrayList}")
//
//            }
//
//        })
//    }
}