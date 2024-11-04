package com.example.createtask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AssignTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.assign_task)

        val createButton: Button = findViewById(R.id.btnCreateTask)
        val taskNameField: EditText = findViewById(R.id.task_name_field)

        createButton.setOnClickListener {
            val taskName = taskNameField.text.toString()
            if (taskName.isNotBlank()) {
                val resultIntent = Intent().apply {
                    putExtra("taskName", taskName)
                }
                setResult(RESULT_OK, resultIntent)
                finish() // Close AssignTaskActivity
            } else {
                Toast.makeText(this, "Please enter a task name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
