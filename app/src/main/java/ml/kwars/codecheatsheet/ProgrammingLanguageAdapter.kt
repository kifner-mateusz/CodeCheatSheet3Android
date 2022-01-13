package ml.kwars.codecheatsheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ml.kwars.codecheatsheet.databinding.ProgrammingLanguageBinding

class ProgrammingLanguageAdapter: RecyclerView.Adapter<ProgrammingLanguageAdapter.ProgrammingLanguageViewHolder>() {

    var onItemClick: ((ProgrammingLanguage) -> Unit)? = null


    inner class ProgrammingLanguageViewHolder(val binding: ProgrammingLanguageBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {

                onItemClick?.invoke(programmingLanguages[bindingAdapterPosition])
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ProgrammingLanguage>(){
        override fun areItemsTheSame(
            oldItem: ProgrammingLanguage,
            newItem: ProgrammingLanguage
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProgrammingLanguage,
            newItem: ProgrammingLanguage
        ): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var programmingLanguages : List<ProgrammingLanguage>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    override fun getItemCount(): Int {
        return programmingLanguages.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgrammingLanguageViewHolder {

        return ProgrammingLanguageViewHolder(ProgrammingLanguageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ProgrammingLanguageViewHolder, position: Int) {
        holder.binding.apply {
            val programmingLanguage = programmingLanguages[position]
            tvTitle.text = programmingLanguage.name
        }
    }

}