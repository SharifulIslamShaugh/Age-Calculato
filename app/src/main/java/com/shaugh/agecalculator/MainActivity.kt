package com.shaugh.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.util.*


abstract class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var birthDate: LocalDate
    lateinit var todayDate: LocalDate
    lateinit var yourAge: Period
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etToday.setOnClickListener(this)
        etBday.setOnClickListener(this)
       /* if ((yourAge != null )){
            ageCalculate()
        }*/

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
                    etToday.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                }, cYear, cMonth, cDay
            )
            todaysDate(cYear, cMonth, cDay)
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
                    etBday.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                }, mYear, mMonth, mDay
            )
            bdaysDate(mYear, mMonth, mDay)
            datePickerDialog.show()
        }
    }

     fun todaysDate(year: Int, month: Int, day: Int){
         todayDate = LocalDate.of(year, Month.of(month), day)
     }
     fun bdaysDate(year: Int, month: Int, day: Int){
         birthDate = LocalDate.of(year, Month.of(month), day)
     }
    fun ageCalculate() {

        val yourAge: Period = Period.between(birthDate, todayDate)
        Log.e("YOU", "Year " + yourAge.getYears().toString() + " - Month " + yourAge.getMonths().toString() + " - Day " + yourAge.getDays().toString())

    }
}
