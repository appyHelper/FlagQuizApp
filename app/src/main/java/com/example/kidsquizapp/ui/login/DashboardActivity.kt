package com.example.kidsquizapp.ui.login

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.kidsquizapp.QuestionActivity
import com.example.kidsquizapp.R
import com.example.kidsquizapp.adapter.QuestionsAdapter
import com.example.kidsquizapp.data.model.Question2
import com.example.kidsquizapp.repository.QuestionRepository
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_question.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DashboardActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        var uid =intent.getStringExtra("uid")
        database = FirebaseDatabase.getInstance().reference.child("Student").child(uid!!)

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            var questionArrayList=ArrayList<Question2>()
            override fun onCancelled(error: DatabaseError) {
                Log.d("Dashboard Acitvity",error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var testListForAdapter = arrayListOf<String>()

                    var testList =
                        snapshot.child("Test to be taken").value as HashMap<String, String>
                    Log.d("Dashboard Acitvity", testList.toString())
                    testListForAdapter.clear()
                    for (i in testList) {
                        Log.d("Dashboard Acitvity", (i.value >= getCurrentDate()).toString())
                        if (i.value >= getCurrentDate())
                            testListForAdapter.add(i.key)
                    }
                if(testListForAdapter.size>0) {
                    val testListAdapter = ArrayAdapter(
                        this@DashboardActivity,
                        R.layout.item_test_layout,
                        R.id.testId,
                        testListForAdapter
                    )
                    rvTest.adapter = testListAdapter
                    rvTest.setOnItemClickListener { parent, view, position, id ->
                        Log.d("Dashboard Acitvity ", testListForAdapter[position].toString())
                        var testName: String = testListForAdapter[position]
                        testListForAdapter.remove(testName)
                        testListAdapter.notifyDataSetChanged()
                        database.child("Test to be taken").child(testName).removeValue()
                        startActivity(
                            Intent(
                                this@DashboardActivity,
                                QuestionActivity::class.java
                            ).putExtra("testName", testName).putExtra("uid",uid).putExtra("review",false)
                        )
                    }
                }else{
                    val dialogBuilder = AlertDialog.Builder(this@DashboardActivity)

                    // set message of alert dialog
                    dialogBuilder.setMessage("No Test found under your profile. Please contact Admin")
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("OK", DialogInterface.OnClickListener {
                                dialog, id -> finish()
                            startActivity(
                                Intent(
                                    this@DashboardActivity,
                                    LoginActivity::class.java
                                ).putExtra("uid",uid).putExtra("review",false)
                            )

                        })
                        // negative button text and action


                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box

                    // show alert dialog
                    alert.show()
                }
            }

        })

//        val list = arrayOf(
//            "GKTest1",
//            "E1Test1",
//            "M1Test2",
//            "GK2Test2"
//        )

//        rvTest.setOnItemClickListener { parent, view, position, id ->
//            startActivity(Intent(this, QuestionActivity::class.java))
//        }


    }

    @SuppressLint("NewApi")
    fun getCurrentDate() : String{

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted = current.format(formatter)
        Log.d("Dashboard Acitvity ",formatted)
        return formatted
    }
}