package com.shaugh.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var birth = 0
    var today = 0
    lateinit var birthDate: LocalDate
    lateinit var todayDate: LocalDate
    lateinit var yourAge: Period
    lateinit var animation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etToday.setOnClickListener(this)
        etBday.setOnClickListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        ivQuestion.startAnimation(animation)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        if (v == etToday) {
            // Get Current Date
            val c: Calendar = Calendar.getInstance()
            val cYear = c.get(Calendar.YEAR)
            val cMonth = c.get(Calendar.MONTH)
            val cDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    todaysDate(year, monthOfYear, dayOfMonth)
                    etToday.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    today = 1
                }, cYear, cMonth, cDay
            )

            datePickerDialog.show()
        }
        if (v == etBday) {
            // Get Birth Date
            val c: Calendar = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    bdaysDate(year, monthOfYear, dayOfMonth)
                    etBday.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    birth = 1
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
        if (today != 0 && birth !=0){
            btnCalculate.isClickable = true
            if (v == btnCalculate){
                yourAge = Period.between(birthDate, todayDate)
                Log.e("YOU", "Year " + yourAge.getYears().toString() + " - Month " + yourAge.getMonths().toString() + " - Day " + yourAge.getDays().toString())
            }
        }
        if (today != 0 && birth !=0){
            btnCalculate.isClickable = true
            if (v == btnClear){
                today = 0
                birth = 0
                etToday.text = ""
                tvBday.text = ""
            }
        }
    }

     fun todaysDate(year: Int, month: Int, day: Int){
         todayDate = LocalDate.of(year, Month.of(month), day)
     }
     fun bdaysDate(year: Int, month: Int, day: Int){
         birthDate = LocalDate.of(year, Month.of(month), day)
     }

}
