package com.example.kidsquizapp.repository

import com.example.kidsquizapp.data.model.Question

object QuestionRepository {

    fun getQuestions() : ArrayList<Question>{
    var questionArrayList = ArrayList<Question>()
        questionArrayList.add(Question("1.Who is the first Prime Minister of India?","P.J. Abdul Kalaam","Jawaharlal Nehru","Indira Gandhi","Narendra Modi"))
        questionArrayList.add(Question("2. Who is the author of “God of Small Things?”","Gabriel Garcia Marquez","Aravind Adiga","Arundhati Roy","Victor Hugo"))
        questionArrayList.add(Question("3. What is the position of Earth in our Solar system?","First","Second","Third","Fourth"))
        questionArrayList.add(Question("4. Who found the concept of “Gravitation?","Albert Einstein","Charles Darwin","Issac Newton","V. Raman"))
        questionArrayList.add(Question("5. What are constellations?","Group of planets","Group of galaxies","Group of stars","Group of meteors"))
        return questionArrayList



    }
}