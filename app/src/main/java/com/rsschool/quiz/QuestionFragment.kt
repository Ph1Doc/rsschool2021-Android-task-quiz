package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.OnBackPressedCallback
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuestionFragment: Fragment() {

    private var listener: FirstQuestionListener? = null
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val questionNumber = arguments?.getInt(NEXT_QUESTION_NUMBER_KEY) ?: 0
        val answerMap = arguments?.getSerializable(RESULT_MAP_KEY) as HashMap<String, String>

        setupTheme(questionNumber, inflater)

        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        setupQuestionFragment(questionNumber, answerMap)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FirstQuestionListener
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionNumber = arguments?.getInt(NEXT_QUESTION_NUMBER_KEY) ?: 0
        val answerMap = arguments?.getSerializable(RESULT_MAP_KEY) as HashMap<String, String>


        binding.radioGroup.setOnCheckedChangeListener { _, optionId ->
            run {

                when (optionId) {
                    R.id.option_one-> {
                        answerMap[questionNumber.toString()] = binding.optionOne.text.toString()

                    }
                    R.id.option_two-> {
                        answerMap[questionNumber.toString()] = binding.optionTwo.text.toString()
                    }
                    R.id.option_three-> {
                        answerMap[questionNumber.toString()] = binding.optionThree.text.toString()
                    }
                    R.id.option_four-> {
                        answerMap[questionNumber.toString()] = binding.optionFour.text.toString()
                    }
                    R.id.option_five-> {
                        answerMap[questionNumber.toString()] = binding.optionFive.text.toString()
                    }
                }
                binding.nextButton.isEnabled = true
            }
        }

        binding.nextButton.setOnClickListener{
            listener?.tapNext(questionNumber + 1, answerMap)
        }

        binding.previousButton.setOnClickListener{
            listener?.tapPrevious(questionNumber - 1, answerMap)
        }

        binding.toolbar.setNavigationOnClickListener {
            listener?.tapPrevious(
                questionNumber - 1,
                answerMap
            )
        }

        if (questionNumber != 1 ) {
            activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    listener?.tapPrevious(questionNumber - 1, answerMap)
                }
            })
        } else {
            activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    listener?.close()
                }
            })

        }


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        @JvmStatic
        fun newInstance(numberOfQuestion: Int, resultMap: HashMap<String, String>): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()

            args.putInt(NEXT_QUESTION_NUMBER_KEY, numberOfQuestion)
            args.putSerializable(RESULT_MAP_KEY, resultMap)

            fragment.arguments = args
            return fragment
        }

        private const val NEXT_QUESTION_NUMBER_KEY = "NEXT_QUESTION_NUMBER_KEY"
        private const val RESULT_MAP_KEY = "RESULT_MAP_KEY"
    }

    @SuppressLint("SetTextI18n")
    fun setupQuestionFragment(numberOfQuestion: Int, answerMap: HashMap<String, String>) {
        when (numberOfQuestion) {
            1 -> {
                binding.toolbar.title = "Question 1"
                binding.toolbar.navigationIcon = null

                binding.previousButton.isClickable = false
                binding.previousButton.isEnabled = false

                binding.question.text = "To be or not to be..."

                binding.optionOne.text = "To be"
                binding.optionTwo.text = "Not to be"
                binding.optionThree.text = "It's not my problem"
                binding.optionFour.text = "I don't know"
                binding.optionFive.text = "...that is the question"

                if (answerMap["1"]?.isNotEmpty() == true) {
                    for (i in binding.radioGroup) {
                        if ((i as RadioButton).text == answerMap["1"]) {
                            i.isChecked = true
                            binding.nextButton.isEnabled = true
                        }
                    }
                }

            }
            2 -> {
                binding.toolbar.title = "Question 2"

                binding.question.text = "Tallest skyscraper:"

                binding.optionOne.text = "Shanghai Tower, Shanghai"
                binding.optionTwo.text = "Burj Khalifa, Dubai"
                binding.optionThree.text = "Trump International Hotel, Chicago"
                binding.optionFour.text = "Central Park Tower, New York"
                binding.optionFive.text = "Eiffel Tower, Paris"

                if (answerMap["2"]?.isNotEmpty() == true) {
                    for (i in binding.radioGroup) {
                        if ((i as RadioButton).text == answerMap["2"]) {
                            i.isChecked = true
                            binding.nextButton.isEnabled = true
                        }
                    }
                }
            }

            3 -> {
                binding.toolbar.title = "Question 3"

                binding.question.text = "The biggest animal:"

                binding.optionOne.text = "Cat"
                binding.optionTwo.text = "Elephant"
                binding.optionThree.text = "Kitti's hog-nosed bat"
                binding.optionFour.text = "Antarctic blue whale"
                binding.optionFive.text = "Human"

                if (answerMap["3"]?.isNotEmpty() == true) {
                    for (i in binding.radioGroup) {
                        if ((i as RadioButton).text == answerMap["3"]) {
                            i.isChecked = true
                            binding.nextButton.isEnabled = true
                        }
                    }
                }
            }

            4 -> {
                binding.toolbar.title = "Question 4"

                binding.question.text = "What's superfluous:"

                binding.optionOne.text = "Discord"
                binding.optionTwo.text = "Slack"
                binding.optionThree.text = "Kotlin"
                binding.optionFour.text = "MicrosoftTeams"
                binding.optionFive.text = "Telegram"

                if (answerMap["4"]?.isNotEmpty() == true) {
                    for (i in binding.radioGroup) {
                        if ((i as RadioButton).text == answerMap["4"]) {
                            i.isChecked = true
                            binding.nextButton.isEnabled = true
                        }
                    }
                }
            }

            5 -> {
                binding.toolbar.title = "Question 5"
                binding.nextButton.text = "SUBMIT"

                binding.question.text = "The Answer to the Ultimate Question of Life, The Universe, and Everything is:"

                binding.optionOne.text = "33"
                binding.optionTwo.text = "11"
                binding.optionThree.text = "7"
                binding.optionFour.text = "0"
                binding.optionFive.text = "42"

                if (answerMap["5"]?.isNotEmpty() == true) {
                    for (i in binding.radioGroup) {
                        if ((i as RadioButton).text == answerMap["5"]) {
                            i.isChecked = true
                            binding.nextButton.isEnabled = true
                        }
                    }
                }
            }
        }
    }

    private fun setupTheme (numberOfQuestion: Int, inflater: LayoutInflater) {
        when (numberOfQuestion) {
            1 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_First)
            }
            2 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Second)
            }
            3 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Third)
            }
            4 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Fourth)
            }
            5 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Fifth)
            }
        }
    }

    interface FirstQuestionListener {
        fun tapNext(nextQuestionNumber: Int, resultMap: HashMap<String, String>)
        fun tapPrevious(previousQuestionNumber: Int, resultMap: HashMap<String, String>)
        fun close()
    }

}