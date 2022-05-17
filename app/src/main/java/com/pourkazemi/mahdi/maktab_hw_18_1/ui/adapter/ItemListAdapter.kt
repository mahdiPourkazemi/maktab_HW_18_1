package com.pourkazemi.mahdi.maktab_hw_18_1.ui.adapter;

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pourkazemi.mahdi.maktab_hw_18_1.R
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person
import com.pourkazemi.mahdi.maktab_hw_18_1.databinding.ModelItemBinding


class ItemListAdapter : ListAdapter<Person, ItemListAdapter.ItemViewHolder>(ItemDiffUtil()) {

    var clickListener: ((Person) -> Unit)? = null

    class ItemViewHolder(
        private val binding: ModelItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                /*clickListener?.invoke(getItem(bindingAdapterPosition))*/
                Log.d("mahdiTest","itemView clicked")
            }
        }

        fun mBind(person: Person/*,mClickListener:(Person)->Unit*/) {
            binding.textView.text = "${person.firstName} ${person.lastName}"
            person.image?.let {
                val imgUri = it.toUri().buildUpon().build()
                Glide.with(binding.root)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(binding.imageView)
            }
/*            binding.item.setOnClickListener {
                mClickListener(person)
                Log.d("mahdiTest","clicked")
            }*/
        }

        companion object {
            fun create(
                inflater: LayoutInflater,
                parent: ViewGroup?,
                attachToRoot: Boolean
            ) = ItemViewHolder(
                ModelItemBinding.inflate(
                    inflater,
                    parent,
                    attachToRoot
                )
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder.create(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.mBind(getItem(position))/*{
            clickListener?.invoke(it)
        }*/


    }
}

class ItemDiffUtil : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(
        oldItem: Person,
        newItem: Person
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Person,
        newItem: Person
    ): Boolean {
        return (oldItem._id == newItem._id &&
                oldItem.firstName == newItem.firstName)
    }
}