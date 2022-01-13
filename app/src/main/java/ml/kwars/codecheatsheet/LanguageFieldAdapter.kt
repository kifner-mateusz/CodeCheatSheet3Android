package ml.kwars.codecheatsheet


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ml.kwars.codecheatsheet.databinding.LanguageFieldBinding

class LanguageFieldAdapter: RecyclerView.Adapter<LanguageFieldAdapter.LanguageFieldViewHolder>() {

    var onItemClick: ((LanguageField) -> Unit)? = null


    inner class LanguageFieldViewHolder(val binding: LanguageFieldBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {

                onItemClick?.invoke(languageFields[bindingAdapterPosition])
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<LanguageField>(){
        override fun areItemsTheSame(
            oldItem: LanguageField,
            newItem: LanguageField
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LanguageField,
            newItem: LanguageField
        ): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var languageFields : List<LanguageField>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    override fun getItemCount(): Int {
        return languageFields.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageFieldViewHolder {

        return LanguageFieldViewHolder(LanguageFieldBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: LanguageFieldViewHolder, position: Int) {
        holder.binding.apply {
            val languageField = languageFields[position]
            tvTitle.text = languageField.short
        }
    }

}