package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    lateinit var dataSet: MutableList<ToDo>
    lateinit var recyclerView: RecyclerView
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
        "data.db").allowMainThreadQueries().build()

        dataSet = db.Dao().getToDos()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var adapter = ItemAdapter(this, dataSet)
        recyclerView.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layout

        val divider = DividerItemDecoration(recyclerView.context, layout.orientation)
        recyclerView.addItemDecoration(divider)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { addTask () }

        adapter.onItemClick = { item ->
            db.Dao().deleteToDo(item)
            dataSet.remove(item)
            adapter.notifyDataSetChanged()
        }
    }

    fun addTask(){
        val editTask = findViewById<EditText>(R.id.editTask)
        val editTaskDeadline = findViewById<EditText>(R.id.editTaskDeadline)

        val task = editTask.text.toString()
        val deadline = editTaskDeadline.text.toString()

        if(task != ""){
            val newTask = ToDo(null, task, deadline)
            dataSet.add(newTask)
            db.Dao().createToDo(newTask)
            recyclerView.adapter?.notifyDataSetChanged()
            editTask.text.clear()
            editTaskDeadline.text.clear()
        }

    }
}












