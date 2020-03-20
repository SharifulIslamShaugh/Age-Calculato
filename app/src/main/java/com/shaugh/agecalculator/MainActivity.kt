package com.shaugh.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var birthDate: LocalDate
    lateinit var todayDate: LocalDate
    lateinit var yourAge: Period
    lateinit var animation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etToday.setOnClickListener(this)
        etBday.setOnClickListener(this)
        btnCalculate.setOnClickListener(this)
        btnClear.setOnClickListener(this)

        btnCalculate.isClickable = false
        btnClear.isClickable = false
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
                    etToday.text =
                        " " + dayOfMonth.toString() + " - " + (monthOfYear + 1) + " - " + year

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
                    etBday.text =
                        " " + dayOfMonth.toString() + " - " + (monthOfYear + 1) + " - " + year
                    btnCalculate.isClickable = true
                    btnClear.isClickable = true
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        if (v == btnCalculate) {
            ivQuestion.clearAnimation()
            ivQuestion.visibility = View.GONE
            yourAge = Period.between(birthDate, todayDate)
            tvAgeYear.text = yourAge.years.toString()
            tvAgeMonth.text = yourAge.months.toString()
            tvAgeDay.text = yourAge.days.toString()
            when {
                yourAge.years < 21 -> {
                    ivAgeWise.setImageResource(R.drawable.little)
                    animation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
                    ivAgeWise.startAnimation(animation)
                }
                yourAge.years in 21..40 -> {
                    ivAgeWise.setImageResource(R.drawable.young)
                    animation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
                    ivAgeWise.startAnimation(animation)
                }
                else -> {
                    ivAgeWise.setImageResource(R.drawable.old)
                    animation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
                    ivAgeWise.startAnimation(animation)
                }
            }
        }

        if (v == btnClear) {
            tvAgeYear.text = 0.toString()
            tvAgeMonth.text = 0.toString()
            tvAgeDay.text = 0.toString()
            etToday.text = ""
            etBday.text = ""

            ivQuestion.visibility = View.VISIBLE
            ivAgeWise.setImageResource(R.drawable.mr)
            animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
            ivQuestion.startAnimation(animation)

        }
    }

    private fun todaysDate(year: Int, month: Int, day: Int) {
        todayDate = LocalDate.of(year, Month.of(month), day)
    }

    private fun bdaysDate(year: Int, month: Int, day: Int) {
        birthDate = LocalDate.of(year, Month.of(month), day)
    }

}
