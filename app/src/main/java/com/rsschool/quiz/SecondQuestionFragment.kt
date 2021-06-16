package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SecondQuestionFragment: Fragment() {

    private var previous_button: Button? = null

    private var listener: SecondQuestionListener? = null
    private var next_button: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_second_question, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SecondQuestionListener
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previous_button = view.findViewById(R.id.previous_button)
        next_button = view.findViewById(R.id.next_button)

        previous_button?.setOnClickListener{
            listener?.tapPrevious(1)
        }
        next_button?.setOnClickListener{
            listener?.tapNext(3)
        }
    }

    interface SecondQuestionListener {
        fun tapNext(nextQuestionNumber: Int)
        fun tapPrevious(nextQuestionNumber: Int)
    }
}