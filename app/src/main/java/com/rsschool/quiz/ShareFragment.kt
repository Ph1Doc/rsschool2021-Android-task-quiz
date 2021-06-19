package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentShareBinding

class ShareFragment: Fragment() {

    private var listener: ShareListener? = null
    private var _binding: FragmentShareBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareBinding.inflate(inflater, container, false)
        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            requireActivity().window.statusBarColor = 0
        } else {
            requireActivity().window.statusBarColor = ContextCompat.getColor(context as Context, R.color.deep_purple_100_dark)
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ShareListener
    }

    @SuppressLint("SetTextI18n")
    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val percent = calculatePercent()
        val answerMap = arguments?.getSerializable(RESULT_MAP_KEY) as HashMap<String, String>

        binding.result.text = "Your result: $percent %"
        binding.result.textSize = 26f

        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            binding.reset.setBackgroundColor(0)
            binding.share.setBackgroundColor(0)
            binding.close.setBackgroundColor(0)
        }

        binding.share.setOnClickListener{
            listener?.tapShare(answerMap, percent)
        }

        binding.reset.setOnClickListener{
            listener?.tapReset()
        }

        binding.close.setOnClickListener{
            listener?.close()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(resultMap: HashMap<String, String>): ShareFragment {
            val fragment = ShareFragment()

            val bundle = bundleOf(
                Pair(RESULT_MAP_KEY, resultMap)
            )

            fragment.arguments = bundle
            return fragment
        }

        private const val RESULT_MAP_KEY = "RESULT_MAP_KEY"
    }

    @Suppress("UNCHECKED_CAST")
    fun calculatePercent(): Int {
        var percent = 0
        val answerMap = arguments?.getSerializable(RESULT_MAP_KEY) as HashMap<String, String>
        if (answerMap["1"] == "To be") {
            percent += 20
        }
        if (answerMap["2"] == "Burj Khalifa, Dubai") {
            percent += 20
        }
        if (answerMap["3"] == "Antarctic blue whale") {
            percent += 20
        }
        if (answerMap["4"] == "Kotlin") {
            percent += 20
        }
        if (answerMap["5"] == "42") {
            percent += 20
        }
        return percent
    }

    interface ShareListener {
        fun tapShare(resultMap: HashMap<String, String>, percent: Int)
        fun tapReset()
        fun close()
    }
}