package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QuestionFragment.FirstQuestionListener, ShareFragment.ShareListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openQuestionFragment(1, hashMapOf())
        supportActionBar?.hide()
    }

    private fun openQuestionFragment(questionNumber:Int, resultMap: HashMap<String, String>) {
        val transaction = supportFragmentManager.beginTransaction()
        if (questionNumber == 6) {
            val share = ShareFragment.newInstance(resultMap)
            transaction.replace(R.id.container, share)
        } else {
            val firstQuestion = QuestionFragment.newInstance(questionNumber, resultMap)
            transaction.replace(R.id.container, firstQuestion)
        }
        transaction.commit()
    }

    override fun tapPrevious(previousQuestionNumber: Int, resultMap: HashMap<String, String>) {
        openQuestionFragment(previousQuestionNumber, resultMap)
    }

    override fun tapShare(resultMap: HashMap<String, String>, percent: Int) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quiz results")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Your result: " + percent.toString() + " %\n" +
                "\n1) To be, or not to be...\nYour answer: " + resultMap["1"] + "\n" +
                "\n2) Tallest skyscraper:\nYour answer: " + resultMap["2"] + "\n" +
                "\n3) The biggest animal:\nYour answer: " + resultMap["3"] + "\n" +
                "\n4) What's superfluous:\nYour answer: " + resultMap["4"] + "\n" +
                "\n5) The Answer to the Ultimate Question of Life, The Universe, and Everything is:\nYour answer: " + resultMap["5"] + "\n")
        shareIntent.type = "text/plain"

        if (packageManager.resolveActivity(shareIntent, 0) != null) {
            startActivity(Intent.createChooser(shareIntent, "Email"))
        }
    }

    override fun tapReset() {
        openQuestionFragment(1, hashMapOf())
    }

    override fun close() {
        ActivityCompat.finishAffinity(this)
    }

    override fun tapNext(nextQuestionNumber: Int, resultMap: HashMap<String, String>) {
        openQuestionFragment(nextQuestionNumber, resultMap)
    }
}