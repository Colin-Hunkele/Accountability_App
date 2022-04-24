package com.example.accountability_app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_activity.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AcAdapter = Adapter(mutableListOf())

        val tvDate = findViewById<TextView>(R.id.tvDate)
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        itemView.adapter = AcAdapter
        itemView.layoutManager = LinearLayoutManager(this)

        Date_btn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, myear, mMonth, mday ->
                    tvDate.text = "" + mMonth + "/" + mday + "/" + myear
                }, month, day, year )
            datePickerDialog.show()
        }//Date button

        Time_btn.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                tvTime.text = SimpleDateFormat ( "HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }//Time Button

        fab.setOnClickListener{
            val ActivityTitle = itemTitle.text.toString()
            val Date = tvDate.text.toString()
            val Time = tvTime.text.toString()
            if(ActivityTitle.isNotEmpty() and  Date.isNotEmpty() and Time.isNotEmpty()){
                val activity = Activity(ActivityTitle, Date, Time)
                AcAdapter.addActivity(activity)
                itemTitle.text.clear()
            }//if Title, date, and time all have values
        }//what happens when the button is clicked
        Delete_btn.setOnClickListener{
            AcAdapter.deleteActivities()
        }//what happens when the delete button is clicked
    }
    private lateinit var AcAdapter: Adapter
}