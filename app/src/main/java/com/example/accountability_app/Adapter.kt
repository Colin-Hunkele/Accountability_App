package com.example.accountability_app


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_activity.view.*


class Adapter
    (private val activities: MutableList<Activity>)
    : RecyclerView.Adapter<Adapter.ActivityViewHolder>()
{// Adapter Class
    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder
    {//OnCreateViewHolder
        return ActivityViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_activity, parent, false)
        )
    }//OnCreateViewHolder

    private fun togStrikeThrough(ActivityTitle: TextView, isChecked: Boolean)
    {//togStrikeThrough
        if(isChecked)
        {// if the check box is checked
            ActivityTitle.paintFlags = ActivityTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }//if the check box is checked
        else
        {//if check box is unchecked
            ActivityTitle.paintFlags = ActivityTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }//if checkbox is unchecked
    }//togStrikeThrough

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int)
    {//OnBindViewHolder
        val currActivity = activities[position]
        holder.itemView.apply {
            ActivityTitle.text = currActivity.title
            CheckboxDone.isChecked = currActivity.Checked
            togStrikeThrough(ActivityTitle, currActivity.Checked)
            CheckboxDone.setOnCheckedChangeListener { _, isChecked ->
                togStrikeThrough(ActivityTitle, isChecked)
                currActivity.Checked = !currActivity.Checked
            }//CheckBoxDone
        }//holder.itemView

    }//OnBindViewHolder


    fun addActivity(activity : Activity)
    {//adding activity
        activities.add(activity)// add to list
        notifyItemInserted(activities.size-1)// notify that activity has been added
    }//adding activity

    fun deleteActivities()
    {//deleting checked activities
        activities.removeAll {activity ->
            activity.Checked
        }//specifiying that all activities that are checked should be deleted
        notifyDataSetChanged()// notify that data has changed
    }//deleting checked activities

    override fun getItemCount(): Int
    {//GetItemCount
        return activities.size
    }//GetItemCount
}//End Adapter class