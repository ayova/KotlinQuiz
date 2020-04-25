package com.ayova.kotlinquiz.views

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat

import com.ayova.kotlinquiz.R
import com.ayova.kotlinquiz.models.Dataholder
import com.ayova.kotlinquiz.models.Question
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_questions.*
import java.util.*
import kotlin.collections.ArrayList

class QuestionsFragment : Fragment(), View.OnClickListener {
    private var timer: CountDownTimer? = null
    private var currentPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // populate question list
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        questions_tv_question.text = "Lol, works"

        questions_btn_answer1.setOnClickListener(this)
        questions_btn_answer2.setOnClickListener(this)
        questions_btn_answer3.setOnClickListener(this)
        questions_btn_answer4.setOnClickListener(this)

        currentPosition = Dataholder.currentPosition
        val currentQuestion = Dataholder.quizQuestions.allQuestions[currentPosition!!]
        Dataholder.currentPosition++
        questions_tv_question.text = currentQuestion.title

        questions_btn_answer1.text = currentQuestion.answers[0].text
        questions_btn_answer1.tag = currentQuestion.answers[0].isCorrect
        questions_btn_answer2.text = currentQuestion.answers[1].text
        questions_btn_answer2.tag = currentQuestion.answers[1].isCorrect
        questions_btn_answer3.text = currentQuestion.answers[2].text
        questions_btn_answer3.tag = currentQuestion.answers[2].isCorrect
        questions_btn_answer4.text = currentQuestion.answers[3].text
        questions_btn_answer4.tag = currentQuestion.answers[3].isCorrect

        questions_progress_bar.max = 5
        questions_progress_bar.progress = 5
        timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                questions_progress_bar.progress--
            }

            override fun onFinish() {
                Handler().postDelayed({
                    val totalQuestions = Dataholder.quizQuestions.allQuestions.size
                    if (currentPosition == totalQuestions - 1) {
                        activity!!.supportFragmentManager.beginTransaction()?.replace(R.id.main_frame_container,FinishFragment())?.commit()
                    } else {
                        questions_progress_bar.progress = questions_progress_bar.max
                        activity!!.supportFragmentManager.beginTransaction()?.replace(R.id.main_frame_container, QuestionsFragment())?.commit()
                        timer?.cancel()
                    }
                }, 3000)
                //set progress back to full
                //open up new question
            }
        }
        timer!!.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onClick(v: View?) {
        if(v!!.isClickable){
            val btnSelected: MaterialButton = v as MaterialButton

            changeStatusButtons(btnSelected)
//            v.isClickable = false
            val allButtons = arrayListOf<Button>(questions_btn_answer1,questions_btn_answer2,questions_btn_answer3,questions_btn_answer4)
            allButtons.forEach { btn ->
                btn.isClickable = false
            }
        }
    }


    fun pressedButton(btnSelected: MaterialButton) {
        if (btnSelected.tag == true) {
            btnSelected.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorCorrect))
        } else {
            btnSelected.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorWrong))
        }
    }

    fun changeStatusButtons(btnSelected: MaterialButton) {
        val allButtons = arrayListOf<Button>(questions_btn_answer1,questions_btn_answer2,questions_btn_answer3,questions_btn_answer4)

        allButtons.forEach { btn ->
            if (btn == btnSelected) {
                pressedButton(btnSelected)
            } else if (btn.tag == true) {
                val matBtn = btn as MaterialButton
                matBtn.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorTransparent))
                matBtn.strokeWidth = 10
                matBtn.strokeColor = ContextCompat.getColorStateList(context!!, R.color.colorCorrect)
            } else {
                btn.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorTransparent))
            }
        }

    }
}
