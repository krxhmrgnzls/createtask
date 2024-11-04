package com.example.createtask

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class AssignTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.assign_task)

        supportActionBar?.hide()

        val taskNameField = findViewById<EditText>(R.id.task_name_field)
        val createButton = findViewById<Button>(R.id.btnCreateTask)

        createButton.setOnClickListener {
            val taskName = taskNameField.text.toString()
            if (taskName.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("task_name", taskName)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}



