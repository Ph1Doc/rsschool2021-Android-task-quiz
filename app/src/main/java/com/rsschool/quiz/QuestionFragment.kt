package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.get
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

            val bundle = bundleOf(
                Pair(NEXT_QUESTION_NUMBER_KEY, numberOfQuestion),
                Pair(RESULT_MAP_KEY, resultMap)
            )

            fragment.arguments = bundle
            return fragment
        }

        private const val NEXT_QUESTION_NUMBER_KEY = "NEXT_QUESTION_NUMBER_KEY"
        private const val RESULT_MAP_KEY = "RESULT_MAP_KEY"
    }

    @SuppressLint("SetTextI18n")
    fun setupQuestionFragment(numberOfQuestion: Int, answerMap: HashMap<String, String>) {
        when (numberOfQuestion) {
            1 -> {
                binding.toolbar.navigationIcon = null
                binding.previousButton.isClickable = false
                binding.previousButton.isEnabled = false
                binding.question.text = "To be or not to be..."
                val arrayButtonsText = arrayListOf(
                    "To be",
                    "Not to be",
                    "It's not my problem",
                    "I don't know",
                    "...that is the question")
                setButtonText(numberOfQuestion, arrayButtonsText, answerMap)

                if (isDarkTheme()) {
                    binding.toolbar.setBackgroundResource(R.color.deep_orange_100_dark)
                }
            }
            2 -> {
                binding.question.text = "Tallest skyscraper:"
                val arrayButtonsText = arrayListOf(
                    "Shanghai Tower, Shanghai",
                    "Burj Khalifa, Dubai",
                    "Trump International Hotel, Chicago",
                    "Central Park Tower, New York",
                    "Eiffel Tower, Paris")
                setButtonText(numberOfQuestion, arrayButtonsText, answerMap)
                if (isDarkTheme()) {
                    binding.toolbar.setBackgroundResource(R.color.yellow_100_dark)
                }
            }

            3 -> {
                binding.question.text = "The biggest animal:"
                val arrayButtonsText = arrayListOf(
                    "Cat",
                    "Elephant",
                    "Kitti's hog-nosed bat",
                    "Antarctic blue whale",
                    "Human")
                setButtonText(numberOfQuestion, arrayButtonsText, answerMap)
                if (isDarkTheme()) {
                    binding.toolbar.setBackgroundResource(R.color.light_green_100_dark)
                }
            }

            4 -> {
                binding.question.text = "What's superfluous:"
                val arrayButtonsText = arrayListOf(
                    "Discord",
                    "Slack",
                    "Kotlin",
                    "MicrosoftTeams",
                    "Telegram")
                setButtonText(numberOfQuestion, arrayButtonsText, answerMap)
                if (isDarkTheme()) {
                    binding.toolbar.setBackgroundResource(R.color.cyan_100_dark)
                }
            }

            5 -> {
                binding.nextButton.text = "SUBMIT"
                binding.question.text = "The Answer to the Ultimate Question of Life, The Universe, and Everything is:"
                val arrayButtonsText = arrayListOf(
                    "33",
                    "11",
                    "7",
                    "0",
                    "42")
                setButtonText(numberOfQuestion, arrayButtonsText, answerMap)
                if (isDarkTheme()) {
                    binding.toolbar.setBackgroundResource(R.color.deep_purple_100_dark)
                }
            }
        }
    }

    private fun setupTheme (numberOfQuestion: Int, inflater: LayoutInflater) {
        when (numberOfQuestion) {
            1 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_First)
                requireActivity().window.statusBarColor = ContextCompat.getColor(context as Context, R.color.deep_orange_100_dark)
            }
            2 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Second)
                requireActivity().window.statusBarColor = ContextCompat.getColor(context as Context, R.color.yellow_100_dark)
            }
            3 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Third)
                requireActivity().window.statusBarColor = ContextCompat.getColor(context as Context, R.color.light_green_100_dark)
            }
            4 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Fourth)
                requireActivity().window.statusBarColor = ContextCompat.getColor(context as Context, R.color.cyan_100_dark)
            }
            5 -> {
                inflater.context.setTheme(R.style.Theme_Quiz_Fifth)
                requireActivity().window.statusBarColor = ContextCompat.getColor(context as Context, R.color.deep_purple_100_dark)
            }
        }
    }

    private fun isDarkTheme(): Boolean {
        return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    private fun setButtonText(numberOfQuestion: Int, buttonsText: ArrayList<String>, answerMap: HashMap<String, String>) {

        binding.toolbar.title = "Question $numberOfQuestion"

        for (i in buttonsText) {
            (binding.radioGroup[buttonsText.indexOf(i)] as RadioButton).text = i
        }

        if (answerMap[numberOfQuestion.toString()]?.isNotEmpty() == true) {
            for (i in binding.radioGroup) {
                if ((i as RadioButton).text == answerMap[numberOfQuestion.toString()]) {
                    i.isChecked = true
                    binding.nextButton.isEnabled = true
                }
            }
        }
    }

    interface FirstQuestionListener {
        fun tapNext(nextQuestionNumber: Int, resultMap: HashMap<String, String>)
        fun tapPrevious(previousQuestionNumber: Int, resultMap: HashMap<String, String>)
        fun close()
    }
}