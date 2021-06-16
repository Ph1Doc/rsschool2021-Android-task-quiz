package com.rsschool.quiz

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), FirstQuestionFragment.FirstQuestionListener,
    SecondQuestionFragment.SecondQuestionListener, ShareFragment.ShareListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openQuestionFragment(1)
    }

    private fun openQuestionFragment(questionNumber:Int) {
        val transaction = getSupportFragmentManager().beginTransaction();
        when(questionNumber) {
            1 -> {
                val firstQuestion = FirstQuestionFragment()
                transaction.replace(R.id.container, firstQuestion)
            }
            2 -> {
                val secondQuestion = SecondQuestionFragment()
                transaction.replace(R.id.container, secondQuestion)
            }
            3 -> {
                val share = ShareFragment()
                transaction.replace(R.id.container, share)
            }
            else -> {
                print("questionNumber is neither 1 nor 2")
            }
        }

        transaction.commit();
    }

    override fun tapNext(nextQuestionNumber: Int) {
        openQuestionFragment(nextQuestionNumber)
    }

    override fun tapPrevious(nextQuestionNumber: Int) {
        openQuestionFragment(nextQuestionNumber)
    }

    override fun tapShare() {
        val shareIntent = Intent(Intent.ACTION_SEND)

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quiz result");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello")
        shareIntent.type = "text/plain"

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            Toast.makeText(this, "Enter correct data for Min and Max", Toast.LENGTH_SHORT).show()
            startActivity(Intent.createChooser(shareIntent, "Email"))

        }
    }
}