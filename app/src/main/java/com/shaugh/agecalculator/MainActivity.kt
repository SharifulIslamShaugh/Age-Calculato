package com.shaugh.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etToday.setOnClickListener(this)
        etBday.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        if (v == etToday) {
            // Get Current Date
            val c: Calendar = Calendar.getInstance()
            var mYear = c.get(Calendar.YEAR)
            var mMonth = c.get(Calendar.MONTH)
            var mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    etToday.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
        if (v == etBday) {
            // Get Birth Date
            val c: Calendar = Calendar.getInstance()
            var mYear = c.get(Calendar.YEAR)
            var mMonth = c.get(Calendar.MONTH)
            var mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    etBday.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
    }
}
