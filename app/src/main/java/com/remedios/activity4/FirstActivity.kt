package com.remedios.activity4

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.remedios.activity4.databinding.FirstActivityBinding
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

open class FirstActivity : AppCompatActivity() {
    private lateinit var binding:FirstActivityBinding
    private lateinit var questionList :ArrayList<QuestionModel>
    lateinit var questionModel:QuestionModel
    private var index:Int = 0
    var correctAnswerCount = 0
    var wrongAnswerCount = 0
    var backPressedTime:Long = 0
    var backToast:Toast? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        questionList = arrayListOf()
        questionList.add(QuestionModel("What is the capital of Philippines?", "NCR", "Manila", "P", "MCR", "NCR"))
        questionList.add(QuestionModel("What is the currency of Philippines?", "Dollar", "Yuan", "Rubbles", "Peso", "Peso"))
        questionList.add(QuestionModel("What is the name of the first popular graphical web browser?", "Chome", "Mosaic", "Internet Explorer", "WorldWideWeb", "Mosaic"))
        questionList.add(QuestionModel("What continent is your country belong?", "Australia", "Pangea", "Asean", "Asia", "Asia"))
        questionList.add(QuestionModel("Who is the father of modern computer?", "Charles Cabbage", "Charles Babbage", "Charles Darwin", "Charles Garrage", "Charles Babbage"))

        questionModel = questionList[index]
        showQuestion()
        countDown()


    }

    private fun countDown(){
        var duration:Long = TimeUnit.SECONDS.toMillis(5)

        object :CountDownTimer(duration, 1000){
            override fun onTick(millisUntilFinished: Long) {
                var sDuration:String = String().format(Locale.ENGLISH,
                    "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                binding.countdown.text = sDuration
            }

            override fun onFinish() {
                index++
                if(index<questionList.size){

                    questionModel = questionList[index]
                    showQuestion()
                    resetBackground()
                    enableButton()
                    countDown()
                }
                else{
                    gameResult()
                }
            }

        }.start()

    }
    private fun correctAns(option: Button){
        option.background = getDrawable(R.drawable.right_bg)
        correctAnswerCount++
    }
    private fun wrongAns(option: Button){
        option.background = resources.getDrawable(R.drawable.wrong_bg)
        wrongAnswerCount++
    }

    private fun gameResult(){
        val intent = Intent(this, SecondActivity::class.java)
        val name = intent.getStringExtra("name")
        intent.putExtra("correct", correctAnswerCount.toString())
        intent.putExtra("total", questionList.size.toString())
        intent.putExtra("yourName", name.toString())
        startActivity(intent)
    }

    private fun showQuestion() {

        binding.tvQuestions.text = questionModel.questions
        binding.option1.text = questionModel.option1
        binding.option2.text = questionModel.option2
        binding.option3.text = questionModel.option3
        binding.option4.text = questionModel.option4

    }

    private fun enableButton(){
        binding.option1.isClickable=true
        binding.option2.isClickable=true
        binding.option3.isClickable=true
        binding.option4.isClickable=true

    }
    private fun disableButton(){
        binding.option1.isClickable=false
        binding.option2.isClickable=false
        binding.option3.isClickable=false
        binding.option4.isClickable=false

    }

    private fun resetBackground() {
        binding.option1.background = resources.getDrawable(R.drawable.option_bg)
        binding.option2.background = resources.getDrawable(R.drawable.option_bg)
        binding.option3.background = resources.getDrawable(R.drawable.option_bg)
        binding.option4.background = resources.getDrawable(R.drawable.option_bg)
    }


    fun option1Clicked(view:View){
        disableButton()
        if (questionModel.option1 == questionModel.correntAnswer){
            binding.option1.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(binding.option1)
        }
        else{
            wrongAns(binding.option1)
        }
    }

    fun option2Clicked(view:View){
        disableButton()
        if (questionModel.option2 == questionModel.correntAnswer){
            binding.option2.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(binding.option2)
        }
        else{
            wrongAns(binding.option2)
        }
    }
    fun option3Clicked(view:View){
        disableButton()
        if (questionModel.option3 == questionModel.correntAnswer){
            binding.option3.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(binding.option3)
        }
        else{
            wrongAns(binding.option3)
        }
    }
    fun option4Clicked(view:View){
        disableButton()
        if (questionModel.option4 == questionModel.correntAnswer){
            binding.option4.background = resources.getDrawable(R.drawable.right_bg)
            correctAns(binding.option4)
        }
        else{
            wrongAns(binding.option4)
        }
    }

    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast?.cancel()
            finish()
        }
        else{
            backToast = Toast.makeText(baseContext, "Double Press to Quit Game", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }



}

