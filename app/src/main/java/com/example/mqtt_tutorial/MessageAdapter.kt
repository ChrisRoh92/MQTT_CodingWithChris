/*
 * Copyright (c) 2021 CodingWithChris - All Rights Reserved
 */

package com.example.mqtt_tutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// TODO("Modify the Constructor, to receive an ArrayList<Message>")
class MessageAdapter():RecyclerView.Adapter<MessageAdapter.ViewHolder>()
{

    // TODO("Create a method, to update the content and update the adapter")




    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var tvMsg: TextView = itemView.findViewById(R.id.item_msg)
        var tvTopic: TextView = itemView.findViewById(R.id.item_topic)
        var tvTime: TextView = itemView.findViewById(R.id.item_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_messages,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Add the Data from the Message Object to the items...")

    }

    override fun getItemCount(): Int {
        TODO("Return the size of the current content")
        return 0
    }
}