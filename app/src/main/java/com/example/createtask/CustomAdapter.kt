package com.example.createtask

    import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.BaseAdapter
    import android.widget.CheckBox
    import android.widget.TextView


    class CustomAdapter (private val context: Context, private val tasks: MutableList<String>, private val onTaskChecked: (Int, Boolean) -> Unit) : BaseAdapter() {
        override fun getCount(): Int = tasks.size
        override fun getItem(position: Int): Any = tasks[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        val taskName = view.findViewById<TextView>(R.id.task_name)
        val taskCheckbox = view.findViewById<CheckBox>(R.id.task_checkbox)

        taskName.text = tasks[position]
        taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
            onTaskChecked(position, isChecked)
        }

        return view
    }
}
