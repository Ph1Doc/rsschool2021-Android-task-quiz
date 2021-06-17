package com.rsschool.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QuestionFragment.FirstQuestionListener, ShareFragment.ShareListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openQuestionFragment(1, arrayListOf(),0)
    }

    private fun openQuestionFragment(questionNumber:Int, answear: ArrayList<String>, percent: Int) {
        val transaction = getSupportFragmentManager().beginTransaction();
        when(questionNumber) {
            1 -> {
                val firstQuestion = QuestionFragment.newInstance(questionNumber, answear, percent)
                transaction.replace(R.id.container, firstQuestion)
            }
            2 -> {
                val firstQuestion = QuestionFragment.newInstance(questionNumber, answear, percent)
                transaction.replace(R.id.container, firstQuestion)
            }
            3 -> {
                val share = ShareFragment.newInstance(answear, percent)
                transaction.replace(R.id.container, share)
            }
            else -> {
                print("questionNumber is neither 1 nor 2")
            }
        }

        transaction.commit();
    }

    override fun tapPrevious(previousQuestionNumber: Int, answear: ArrayList<String>, percent: Int) {
        openQuestionFragment(previousQuestionNumber, answear, percent)
    }

    override fun tapShare(result: ArrayList<String>, percent: Int) {
        val shareIntent = Intent(Intent.ACTION_SEND)

//        Toast.makeText(this, "percent = " + percent + " result =" + result, Toast.LENGTH_SHORT).show()

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quiz results");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Your result: " + percent.toString() + " %\n" +
                "\n1) To be, or not to be...\nYour answer: " + result[0] + "\n" +
                "\n2)Tallest skyscraper:\nYour answer: " + result[1] + "\n")
        shareIntent.type = "text/plain"

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent, "Email"))

        }
    }

    override fun tapNext(nextQuestionNumber: Int, answear: ArrayList<String>, percent: Int) {
        openQuestionFragment(nextQuestionNumber, answear, percent)
    }
}