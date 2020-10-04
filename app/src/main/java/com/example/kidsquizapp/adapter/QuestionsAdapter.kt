package com.example.kidsquizapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kidsquizapp.R
import com.example.kidsquizapp.callback.SubmitOnClickListener
import com.example.kidsquizapp.data.model.Question
import kotlinx.android.synthetic.main.item_layout.view.*

class QuestionsAdapter constructor(val context:Context,val questionsArray:ArrayList<Question>,val submitOnClickListener: SubmitOnClickListener?) : PagerAdapter(){

    private lateinit var layoutInflater: LayoutInflater
    lateinit var question:TextView
    lateinit var answer1:RadioButton
    lateinit var answer2:RadioButton
    lateinit var answer3:RadioButton
    lateinit var answer4:RadioButton
    lateinit var radioGroup:RadioGroup
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
           answerArrayList[position+1]=view.findViewById<RadioButton>(checkedId).text.toString()
           Log.d("adapter", view.findViewById<RadioButton>(checkedId).text.toString())
           view.findViewById<RadioButton>(checkedId)
       }
        // find the radiobutton by returned id
      Log.d("adapter",radioGroup.getCheckedRadioButtonId().toString())

        submitOnClickListener?.onSubmitClick(answerArrayList)
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
}