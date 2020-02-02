package com.example.reminder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_view_item.view.*

class ReminderAdapter(context: Context , private  val list: Array<String>) : BaseAdapter() {

    private val inflator : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val row = inflator.inflate(R.layout.list_view_item,parent,false)
        row.item_Message.text = list[position]
        row.item_Trigger.text = "Hello"

        return row
    }

    override fun getItem(position: Int): Any {
        return list[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}