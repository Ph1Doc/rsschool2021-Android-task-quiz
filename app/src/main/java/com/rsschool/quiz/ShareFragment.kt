package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        shareButton?.setOnClickListener{
            listener?.tapShare()
        }
    }

    interface ShareListener {
        fun tapShare()
    }

}