package com.example.kidsquizapp.ui.login

import com.example.kidsquizapp.data.model.Question2

object DataManager {
    var dataHashMap =HashMap<String,ArrayList<Question2>>()

    fun getQuestionList(listKey:String):ArrayList<Question2>{
        return dataHashMap[listKey]!!
    }
    fun setQuestionList(listKey:String,questionList:ArrayList<Question2>){
        dataHashMap[listKey]=questionList
    }
}