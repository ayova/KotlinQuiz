package com.ayova.kotlinquiz.models

data class QuestionList(val allQuestions: ArrayList<Question>)
data class Question(val title: String, val category: String, val answers: ArrayList<Answer>)
data class Answer(val text: String, val isCorrect: Boolean)

/**
 * Originally created objects. Using Andrey's objects for better json interaction
 */
//data class AllQuestions(val questions: ArrayList<Question>)
//
//data class Question(val question: String, val answers: ArrayList<Answer>) {}
//
//data class Answer(val answer: String, val isRight: Boolean)
