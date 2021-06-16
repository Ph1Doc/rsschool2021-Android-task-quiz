package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class FirstQuestionFragment: Fragment() {

    private var toolbar: androidx.appcompat.widget.Toolbar? = null
    private var previous_button: Button? = null
    private var next_button: Button? = null
    private var questionText: TextView? = null
    private var radioGroup: RadioGroup? = null
    private var radioButtonFirstQuestion: RadioButton? = null
    private var radioButtonSecondQuestion: RadioButton? = null
    private var radioButtonThirdQuestion: RadioButton? = null
    private var radioButtonFourthQuestion: RadioButton? = null
    private var radioButtonFifthQuestion: RadioButton? = null


    private var listener: FirstQuestionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FirstQuestionListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar?.navigationIcon = null

        previous_button = view.findViewById(R.id.previous_button)
        previous_button?.isClickable = false
        previous_button?.isEnabled = false

        next_button = view.findViewById(R.id.next_button)

        questionText = view.findViewById(R.id.question)
        questionText?.text = "To be or not to be..."

        radioGroup = view.findViewById(R.id.radio_group)
        radioButtonFirstQuestion = view.findViewById(R.id.option_one)
        radioButtonSecondQuestion = view.findViewById(R.id.option_two)
        radioButtonThirdQuestion = view.findViewById(R.id.option_three)
        radioButtonFourthQuestion = view.findViewById(R.id.option_four)
        radioButtonFifthQuestion = view.findViewById(R.id.option_five)
        radioButtonFirstQuestion?.text = "To be"
        radioButtonSecondQuestion?.text = "Not to be"
        radioButtonThirdQuestion?.text = "It's not my problem"
        radioButtonFourthQuestion?.text = "I don't know"
        radioButtonFifthQuestion?.text = "...that is the question"


//        radioGroup?.addView(radioButtonFirstQuestion)

        next_button?.setOnClickListener{
            listener?.tapNext(2)
        }

    }

    interface FirstQuestionListener {
        fun tapNext(nextQuestionNumber: Int)
    }

}