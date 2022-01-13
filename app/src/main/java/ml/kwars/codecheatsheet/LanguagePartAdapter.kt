package ml.kwars.codecheatsheet


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ml.kwars.codecheatsheet.databinding.LanguagePartBinding

class LanguagePartAdapter: RecyclerView.Adapter<LanguagePartAdapter.LanguagePartViewHolder>() {

    var onItemClick: ((LanguagePart) -> Unit)? = null


    inner class LanguagePartViewHolder(val binding: LanguagePartBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {

                onItemClick?.invoke(languageParts[bindingAdapterPosition])
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<LanguagePart>(){
        override fun areItemsTheSame(
            oldItem: LanguagePart,
            newItem: LanguagePart
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LanguagePart,
            newItem: LanguagePart
        ): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var languageParts : List<LanguagePart>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    override fun getItemCount(): Int {
        return languageParts.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguagePartViewHolder {

        return LanguagePartViewHolder(LanguagePartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: LanguagePartViewHolder, position: Int) {
        holder.binding.apply {
            val languagePart = languageParts[position]
            tvTitle.text = languagePart.name
        }
    }

}