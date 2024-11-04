package com.example.createtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<String>()
    private var completedCount = 0
    private var incompleteCount = 0

    private val assignTaskLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newTask = result.data?.getStringExtra("task_name")
            if (!newTask.isNullOrEmpty()) {
                tasks.add(newTask)
                incompleteCount++
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val taskListView = findViewById<ListView>(R.id.task_list)
        val addTaskButton = findViewById<Button>(R.id.add_task_button)
        val completedCounter = findViewById<TextView>(R.id.completed_counter)
        val incompleteCounter = findViewById<TextView>(R.id.incomplete_counter)

        taskAdapter = TaskAdapter(this, tasks) { position, isChecked ->
            if (isChecked) {
                completedCount++
                incompleteCount--
            } else {
                completedCount--
                incompleteCount++
            }
            completedCounter.text = "$completedCount ✔"
            incompleteCounter.text = "$incompleteCount ❌"
        }
        taskListView.adapter = taskAdapter

        addTaskButton.setOnClickListener {
            val intent = Intent(this, AssignTaskActivity::class.java)
            assignTaskLauncher.launch(intent)
        }
    }
}


