package com.example.accountability_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AcAdapter = Adapter(mutableListOf())

        itemView.adapter = AcAdapter
        itemView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener{
            val ActivityTitle = itemTitle.text.toString()
            val Date = etdate.text.toString()
            val Time = etTime.text.toString()
            if(ActivityTitle.isNotEmpty()){
                val activity = Activity(ActivityTitle, Date, Time)
                AcAdapter.addActivity(activity)
                itemTitle.text.clear()
                etdate.text.clear()
                etTime.text.clear()
            }//if Title, date, and time all have values
        }//what happens when the button is clicked
        Delete_Button.setOnClickListener{
            AcAdapter.deleteActivities()
        }//what happens when the delete button is clicked
    }
    private lateinit var AcAdapter: Adapter
}