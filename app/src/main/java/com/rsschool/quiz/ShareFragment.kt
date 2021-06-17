package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment

class ShareFragment: Fragment() {

    private var backButton: AppCompatImageButton? = null
    private var shareButton: AppCompatImageButton? = null
    private var closeButton: AppCompatImageButton? = null

    private var listener: ShareListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.share_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ShareListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton = view.findViewById(R.id.back)
        shareButton = view.findViewById(R.id.share)
        closeButton = view.findViewById(R.id.close)

        var percent = arguments?.getInt(PERCENT_KEY) ?: 0
        var result = arguments?.getStringArrayList(RESULT_KEY)

//        Toast.makeText(context, "percent = " + " result =" + result, Toast.LENGTH_SHORT).show()

        shareButton?.setOnClickListener{
            listener?.tapShare(result as ArrayList<String>, percent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(answear: ArrayList<String>, percent: Int): ShareFragment {
            val fragment = ShareFragment()
            val args = Bundle()

            args.putInt(PERCENT_KEY, percent)
            args.putStringArrayList(RESULT_KEY, answear)

            fragment.arguments = args
            return fragment
        }

        private const val RESULT_KEY = "RESULT_KEY"
        private const val PERCENT_KEY = "PERCENT_KEY"
    }

    interface ShareListener {
        fun tapShare(result: ArrayList<String>, percent: Int)
    }

}