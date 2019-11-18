package com.example.gamebacklog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import java.text.SimpleDateFormat
import java.util.*


const val EXTRA_GAME = "EXTRA_GAME"

class AddGameActivity : AppCompatActivity() {
    private lateinit var error: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        setSupportActionBar(toolbar)

        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { addGame() }
    }

    private fun addGame() {
        if(isGameCardValid()) {
            val game = Game(
                title = etTitle.text.toString(),
                platform = etPlaftform.text.toString(),
                date = createDate(etDay.text.toString() + etMonth.text.toString() + etYear.text.toString())
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_GAME, game)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

    }

    private fun isGameCardValid(): Boolean {
        return when {
            etTitle.text!!.isBlank() -> {
                error = "Title must not be empty"
                false
            }
            etPlaftform.text!!.isBlank() -> {
                error = "Platform must not be empty"
                false
            }
            (etDay.text!!.count() != 2 || etDay.text!!.isBlank()
                    || etDay.text!!.toString().toInt() > 31 || etDay.text!!.toString().toInt() < 0 ) -> {
                error = "Fill in a valid day"
                false
            }
            (etMonth.text!!.count() != 2 || etMonth.text!!.isBlank()
                    || etMonth.text!!.toString().toInt() > 12 || etMonth.text!!.toString().toInt() < 0)
            -> {
                error = "Fill in a valid month"
                false
            }
            (etYear.text!!.count() != 4 || etYear.text!!.isBlank())
            -> {
                error = "Fill in a valid year"
                false
            }
            else -> true
        }

    }

    private fun createDate(date: String): Date {
        val format = SimpleDateFormat("ddMMyyyy", Locale.US )

        return format.parse(date)!!
    }

}
