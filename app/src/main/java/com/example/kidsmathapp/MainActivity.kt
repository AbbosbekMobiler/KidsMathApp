package com.example.kidsmathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_play.*
import java.lang.Exception
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var TimeTextView : TextView ?= null
    var QuestionView : TextView ?= null
    var ScoreTextView : TextView ?= null
    var AlertTextView : TextView ?= null
    var FinalScoreTextView : TextView ?= null
    var btn0 : Button ?= null
    var btn1 : Button ?= null
    var btn2 : Button ?= null
    var btn3 : Button ?= null

    var countDownTimer : CountDownTimer ?= null
    var random : Random = Random
    var a = 0
    var b = 0
    var indexCorrectOfAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestion = 0
    var cals = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calInt = intent.getStringExtra("cals")
        cals = calInt!!
        TimeTextView = findViewById(R.id.TimeTextView)
        QuestionView = findViewById(R.id.QuestionText)
        ScoreTextView = findViewById(R.id.ScoreTextView)
        AlertTextView = findViewById(R.id.AlertTextView)
        btn0 = findViewById(R.id.button0)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)

        start()

    }

    private fun start() {
        NextQuestion(cals)
        countDownTimer = object : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                TimeTextView!!.text = (p0/1000).toString()

            }

            override fun onFinish() {
                TimeTextView!!.text = "Time Up !"
                openDialog()
            }
        }.start()
    }

    private fun openDialog() {
        val inflate = LayoutInflater.from(this)
        val winDialog = inflate.inflate(R.layout.win_layout,null)
        FinalScoreTextView = winDialog.findViewById(R.id.FinalScoreTextView)
        val btnplayAgain = winDialog.findViewById<Button>(R.id.buttonPlayAgain)
        val btnBack = winDialog.findViewById<Button>(R.id.btnBack)
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(winDialog)
        val showDialog = dialog.create()
        FinalScoreTextView!!.text = "$points/$totalQuestion"
        btnplayAgain.setOnClickListener {
            playAgain(it)
            showDialog.hide()
        }
        btnBack.setOnClickListener { onBackPressed() }
        showDialog.show()
    }

    fun optionSelect(view: View) {
        totalQuestion ++
        if (indexCorrectOfAnswer.toString() == view.tag.toString()){
            points++
            AlertTextView!!.text = "Correct"
        }
        else{
            AlertTextView!!.text = "Wrong"

        }
        ScoreTextView!!.text = "$points/$totalQuestion"
        NextQuestion(cals)
    }
    fun playAgain(view: View){
        points = 0
        totalQuestion = 0
        ScoreTextView!!.text = "$points/$totalQuestion"
        countDownTimer!!.start()
    }

    fun NextQuestion(cal:String){
        a = random.nextInt(10)
        b = random.nextInt(10)
        QuestionView!!.text = "$a $cal $b"
        indexCorrectOfAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3){
            if (indexCorrectOfAnswer == i){
                when(cal){
                    "+"->{answers.add(a+b)}
                    "-"->{answers.add(a-b)}
                    "*"->{answers.add(a*b)}
                    "/"->{
                        try {
                            answers.add(a/b)
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                    }
                }
            }
            else{
                var wrongAnswer = random.nextInt(20)
                try {
                    while (
                        wrongAnswer == a+b || wrongAnswer == a-b
                        || wrongAnswer == a*b || wrongAnswer == a/b
                    ){
                        wrongAnswer = random.nextInt(20)
                    }
                    answers.add(wrongAnswer)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
        try {
            btn0!!.text = "${answers[0]}"
            btn1!!.text = "${answers[1]}"
            btn2!!.text = "${answers[2]}"
            btn3!!.text = "${answers[3]}"
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}