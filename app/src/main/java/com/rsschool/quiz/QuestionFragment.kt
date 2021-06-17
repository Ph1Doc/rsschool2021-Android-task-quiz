package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionFragment: Fragment() {

    private var listener: FirstQuestionListener? = null

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var questionNumber = arguments?.getInt(NEXT_QUESTION_NUMBER_KEY) ?: 0
        setupTheme(questionNumber, inflater)

        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        setupQuestionFragment(questionNumber)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FirstQuestionListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var percent = arguments?.getInt(PERCENT_KEY) ?: 0
        var questionNumber = arguments?.getInt(NEXT_QUESTION_NUMBER_KEY) ?: 0
        var result = arguments?.getStringArrayList(RESULT_KEY)?.toMutableList()

        binding.radioGroup.setOnCheckedChangeListener { _, optionId ->
            run {
                when (optionId) {
                    R.id.option_one-> {
                        result?.add(binding.optionOne.text.toString())
                        percent += 10
                    }
                    R.id.option_two-> {
                        result?.add(binding.optionTwo.text.toString())
                    }
                }
            }
        }

        binding.nextButton.setOnClickListener{
            listener?.tapNext(questionNumber + 1, result as ArrayList<String>, percent)
        }

        binding.previousButton.setOnClickListener{
            listener?.tapPrevious(questionNumber - 1, result as ArrayList<String>, percent)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        @JvmStatic
        fun newInstance(numberOfQuestion: Int, answear: ArrayList<String>, percent: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()

            args.putInt(NEXT_QUESTION_NUMBER_KEY, numberOfQuestion)
            args.putInt(PERCENT_KEY, percent)
            args.putStringArrayList(RESULT_KEY, answear)

            fragment.arguments = args
            return fragment
        }

        private const val RESULT_KEY = "RESULT_KEY"
        private const val PERCENT_KEY = "PERCENT_KEY"
        private const val NEXT_QUESTION_NUMBER_KEY = "NEXT_QUESTION_NUMBER_KEY"
    }

    fun setupQuestionFragment(numberOfQuestion: Int) {
        when (numberOfQuestion) {
            1 -> {
                binding.toolbar.navigationIcon = null

                binding.previousButton.isClickable = false
                binding.previousButton.isEnabled = false

                binding.question.text = "To be or not to be..."

                binding.optionOne.text = "To be"
                binding.optionTwo.text = "Not to be"
                binding.optionThree.text = "It's not my problem"
                binding.optionFour.text = "I don't know"
                binding.optionFive.text = "...that is the question"
            }
            2 -> {
                binding.toolbar.title = "Question 2"

                binding.question.text = "Tallest skyscraper:"

                binding.optionOne.text = "Shanghai Tower, Shanghai"
                binding.optionTwo.text = "Burj Khalifa, Dubai"
                binding.optionThree.text = "Trump International Hotel, Chicago"
                binding.optionFour.text = "Central Park Tower, New York"
                binding.optionFive.text = "Eiffel Tower, Paris"
            }
        }
    }

    fun setupTheme (numberOfQuestion: Int, inflater: LayoutInflater) {
        when (numberOfQuestion) {
            1 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_First)
            }
            2 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Second)
            }
        }
    }

    interface FirstQuestionListener {
        fun tapNext(nextQuestionNumber: Int, answear: ArrayList<String>, percent: Int)
        fun tapPrevious(previousQuestionNumber: Int, answear: ArrayList<String>, percent: Int)
    }

}