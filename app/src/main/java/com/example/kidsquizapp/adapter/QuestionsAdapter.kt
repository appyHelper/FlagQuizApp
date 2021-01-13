package com.example.kidsquizapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kidsquizapp.R
import com.example.kidsquizapp.SubmitOnClickListener
import com.example.kidsquizapp.data.model.Question
import com.example.kidsquizapp.data.model.Question2
import kotlinx.android.synthetic.main.item_layout.view.*

class QuestionsAdapter constructor(val context:Context, val questionsArray:ArrayList<Question2>, val  submitOnClickListener: SubmitOnClickListener?) : PagerAdapter(){
   var submitOnClickListener1:SubmitOnClickListener1?=null
    var correctAnswerCount= 0
    private lateinit var layoutInflater: LayoutInflater
    lateinit var question:TextView
    lateinit var answer1:RadioButton
    lateinit var answer2:RadioButton
    lateinit var answer3:RadioButton
    lateinit var answer4:RadioButton
    lateinit var radioGroup:RadioGroup
    lateinit var submit:Button
    var answerArrayList=ArrayList<String>(10)
    override fun getCount(): Int {
      return questionsArray.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view= layoutInflater.inflate(R.layout.item_layout,null)
        radioGroup=view.radiogroup
        question=view.textView
        answer1=view.radioButton
        answer2=view.radioButton2
        answer3=view.radioButton3
        answer4=view.radioButton4
        submit=view.submit

        question.text=this.questionsArray[position].question
        answer1.text=this.questionsArray[position].answer1
        answer2.text=this.questionsArray[position].answer2
        answer3.text=this.questionsArray[position].answer3
        answer4.text=this.questionsArray[position].answer4

        if(position == questionsArray.size-1){
            view.submit.visibility= View.VISIBLE
        }
        // get selected radio button from radioGroup
       var selectedId = radioGroup.getCheckedRadioButtonId()
       radioGroup.setOnCheckedChangeListener { group, checkedId ->
           answerArrayList.add(position,view.findViewById<RadioButton>(checkedId).text.toString())
           Log.d("adapter answer", view.findViewById<RadioButton>(checkedId).text.toString())
           Log.d("adapter correct answer", this.questionsArray[position].correctanswer.toString())
          // Log.d("adap correct answer no1",this.questionsArray[position].correctanswer!!.equals(view.findViewById<RadioButton>(checkedId).text.toString()).toString())
           if (this.questionsArray[position].correctanswer!!.equals("answer1")) {
               Log.d("adap correct answer no1", correctAnswerCount.toString())
               if (view.findViewById<RadioButton>(checkedId).text.toString().equals(this.questionsArray[position].answer1)) {
                   correctAnswerCount++
                   Log.d("adap correct answer no1", correctAnswerCount.toString())
                   Log.d("adap correct answer no1", this.questionsArray[position].correctanswer!!.equals(this.questionsArray[position].answer1).toString())

               }
           }
           if (this.questionsArray[position].correctanswer!!.equals("answer2")) {
               Log.d("adap correct answer no2", correctAnswerCount.toString())
               if (view.findViewById<RadioButton>(checkedId).text.toString().equals(this.questionsArray[position].answer2)) {
                   correctAnswerCount++
                   Log.d("adap correct answer no2", correctAnswerCount.toString())
                   Log.d("adap correct answer no2", this.questionsArray[position].correctanswer!!.equals(this.questionsArray[position].answer2).toString())

               }
           }
           if (this.questionsArray[position].correctanswer!!.equals("answer3")) {
               Log.d("adap correct answer no3", correctAnswerCount.toString())
               if (view.findViewById<RadioButton>(checkedId).text.toString().equals(this.questionsArray[position].answer3)) {
                   correctAnswerCount++
                   Log.d("adap correct answer no3", correctAnswerCount.toString())
                   Log.d("adap correct answer no3", this.questionsArray[position].correctanswer!!.equals(this.questionsArray[position].answer3).toString())

               }
           }
           if (this.questionsArray[position].correctanswer!!.equals("answer4")) {
               Log.d("adap correct answer no4", correctAnswerCount.toString())
               if (view.findViewById<RadioButton>(checkedId).text.toString().equals(this.questionsArray[position].answer4)) {
                   correctAnswerCount++
                   Log.d("adap correct answer no4", correctAnswerCount.toString())
                   Log.d("adap correct answer no4", this.questionsArray[position].correctanswer!!.equals(this.questionsArray[position].answer4).toString())

               }
           }
           view.findViewById<RadioButton>(checkedId)

       }
        // find the radiobutton by returned id
      //Log.d("adapter",radioGroup.getCheckedRadioButtonId().toString())
        view.submit.setOnClickListener {
            Log.d("adapter",answerArrayList.toString())

            submitOnClickListener1?.onclick(correctAnswerCount.toString(),questionsArray.size.toString())
        }

        val vp=container as ViewPager
        vp.addView(view)
        return view

    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view ==`object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp=container as ViewPager
        val view=`object` as View
        vp.removeView(view)
    }
    fun setOnClickQuestionListener(submitOnClickListener1: SubmitOnClickListener1){
        this.submitOnClickListener1=submitOnClickListener1
    }

    interface SubmitOnClickListener1{
        fun onclick(answer:String,totalQuestion:String)
    }

}