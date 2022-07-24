package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private val context: Context, private val dataSet: MutableList<ToDo>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder> () {

    var onItemClick: ((ToDo) -> Unit)? = null

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textTaskName: TextView = view.findViewById(R.id.textTaskName)
        val textTaskDeadline: TextView = view.findViewById(R.id.textTaskDeadline)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recylerview_row, parent, false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val todo = dataSet.get(position)
        holder.textTaskName.text = todo.task
        holder.textTaskDeadline.text = todo.deadline
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}