package br.com.dio.todolist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.todolist.data.model.Task
import br.com.dio.todolist.databinding.ItemTaskBinding
import br.com.dio.todolist.util.GetColorUtil


class TaskListAdapter(
    private val getColorUtil: GetColorUtil
): ListAdapter<Task, TaskListAdapter.ViewHolder>(DiffCallBackTask()){

    var setOnMenuClick: (View, Task) -> Unit = {_, _ ->}
    var setOnCardClick: (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemTaskBinding,

    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task){

            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvDate.text = item.date
            binding.tvHour.text = item.hour

            val backgroundColor = getColorUtil.getColorFromString(item.backgroundColor)
            binding.root.setCardBackgroundColor(backgroundColor)

            binding.btnMenu.setOnClickListener { view ->
                setOnMenuClick(view, item)
            }
            binding.root.setOnClickListener { setOnCardClick(item) }
        }
    }

}

class DiffCallBackTask: DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}
