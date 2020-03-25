package com.shaugh.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.time.temporal.ChronoUnit
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var birthDate: LocalDate
    lateinit var todayDate: LocalDate
    lateinit var yourAge: Period
    lateinit var animation: Animation
    lateinit var animation2: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etToday.setOnClickListener(this)
        etBday.setOnClickListener(this)
        btnCalculate.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnMore.setOnClickListener(this)
        btnLess.setOnClickListener(this)

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
                    todaysDate(year, monthOfYear+1, dayOfMonth)
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
                    bdaysDate(year, monthOfYear+1, dayOfMonth)
                    etBday.text =
                        " " + dayOfMonth.toString() + " - " + (monthOfYear + 1) + " - " + year
                    btnCalculate.isClickable = true
                    btnClear.isClickable = true
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        if (v == btnCalculate) {
            btnMore.visibility = View.VISIBLE
            ivQuestion.clearAnimation()
            ivQuestion.visibility = View.INVISIBLE
            yourAge = Period.between(birthDate, todayDate)
            tvAgeYear.text = yourAge.years.toString()
            tvAgeMonth.text = yourAge.months.toString()
            tvAgeDay.text = yourAge.days.toString()
            when {
                yourAge.days < 0 || yourAge.months < 0 || yourAge.years < 0 -> {
                    ivAgeWise.setImageResource(R.drawable.negetive)
                    animation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
                    ivAgeWise.startAnimation(animation)
                }
                yourAge.years in 0..20 -> {
                    ivAgeWise.setImageResource(R.drawable.little)
                    animation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
                    ivAgeWise.startAnimation(animation)
                }
                yourAge.years in 21..40 -> {
                    ivAgeWise.setImageResource(R.drawable.young)
                    animation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
                    ivAgeWise.startAnimation(animation)
                }
                yourAge.years > 40 -> {
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
            lln5.visibility = View.GONE
            tvTotalYear.text = 0.toString()
            tvTotalMonth.text = 0.toString()
            tvTotalDay.text = 0.toString()
            ivQuestion.visibility = View.VISIBLE
            btnMore.visibility = View.GONE
            ivAgeWise.setImageResource(R.drawable.mr)
            animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
            ivQuestion.startAnimation(animation)

        }
        if (v == btnMore){
            ivQuestion.visibility = View.GONE
            btnLess.visibility = View.VISIBLE
            lln5.visibility = View.VISIBLE
            ivAgeWise.visibility = View.GONE
            btnMore.visibility = View.GONE
            val years: Long = ChronoUnit.YEARS.between(birthDate, todayDate)
            val months: Long = ChronoUnit.MONTHS.between(birthDate, todayDate)
            val days: Long = ChronoUnit.DAYS.between(birthDate, todayDate)

            tvTotalYear.text = years.toString()
            tvTotalMonth.text = months.toString()
            tvTotalDay.text = days.toString()
            animation = AnimationUtils.loadAnimation(this, R.anim.move_anim)
            lln5.startAnimation(animation)
        }
        if (v == btnLess){
            animation = AnimationUtils.loadAnimation(this, R.anim.slid_down_anim)
            animation2 = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
            btnMore.visibility = View.VISIBLE
            btnLess.visibility = View.GONE
            lln5.visibility = View.GONE
            lln5.startAnimation(animation)
            ivQuestion.visibility = View.INVISIBLE
            ivAgeWise.visibility = View.VISIBLE
            ivAgeWise.startAnimation(animation2)
        }
    }

    private fun todaysDate(year: Int, month: Int, day: Int) {
        todayDate = LocalDate.of(year, Month.of(month), day)
    }

    private fun bdaysDate(year: Int, month: Int, day: Int) {
        birthDate = LocalDate.of(year, Month.of(month), day)
    }

}
