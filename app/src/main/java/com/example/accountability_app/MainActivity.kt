package com.example.accountability_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AcAdapter = Adapter(mutableListOf())

        viewItems.adapter = AcAdapter
        viewItems.layoutManager = LinearLayoutManager(this)

        Activity_Button.setOnClickListener{
            val ActivityTitle = itemTitle.text.toString()
            if(ActivityTitle.isNotEmpty()){
                val activity = Activity(ActivityTitle)
                AcAdapter.addActivity(activity)
                itemTitle.text.clear()
            }//if Title is not empty
        }//what happens when the button is clicked
        Delete_Button.setOnClickListener{
            AcAdapter.deleteActivities()
        }//what happens when the delete button is clicked
    }
    private lateinit var AcAdapter: Adapter
}