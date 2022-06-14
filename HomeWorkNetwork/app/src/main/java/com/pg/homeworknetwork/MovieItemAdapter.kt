package com.pg.homeworknetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation

internal class MovieItemAdapter : ListAdapter<User, MovieItemAdapter.ViewHolder>(COMPARATOR) {
    private var clickListener: IOnItemClick? = null

    fun setClickListener(listener: IOnItemClick?) {
        clickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.parentItem.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_anim)
    }

    interface IOnItemClick {
        fun onItemClick(user: User)
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var parentItem: LinearLayout = itemView.findViewById(R.id.parentItem)
        private var image: ImageView = itemView.findViewById(R.id.image)
        private var itemTitle: TextView = itemView.findViewById(R.id.itemTitle)

        fun bind(user: User) {
            itemView.setOnClickListener {
                clickListener?.onItemClick(user)
            }
            image.requestLayout()
            itemTitle.text = user.email

            image.load("${user.avatar}") {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun getChangePayload(oldItem: User, newItem: User): Any? {
                return null
            }
        }
    }
}