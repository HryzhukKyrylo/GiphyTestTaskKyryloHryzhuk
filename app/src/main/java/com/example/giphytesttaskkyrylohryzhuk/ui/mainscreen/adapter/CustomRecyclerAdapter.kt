package com.example.giphytesttaskkyrylohryzhuk.ui.mainscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphytesttaskkyrylohryzhuk.data.model.Data
import com.example.giphytesttaskkyrylohryzhuk.databinding.CardItemGiphyBinding

class CustomRecyclerAdapter : RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder>() {

    private val gipItems: ArrayList<Data> = arrayListOf()
    private lateinit var listener: OnItemClickedListener


    class CustomViewHolder(
        private var listener: OnItemClickedListener,
        private val itemBinding: CardItemGiphyBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(giphyModel: Data) {
            itemBinding.apply {
                Glide.with(this.root.context)
                    .load(giphyModel.images.original.url)
                    .into(imgView)

                imgView.setOnClickListener {
                    listener.onImageClicked(giphyModel.images.original.url)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =
            CardItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(listener, binding)
    }

    override fun getItemCount(): Int = gipItems.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(gipItems[position])
    }

    fun updateItems(gipList: List<Data>) {
        gipItems.clear()
        gipItems.addAll(gipList)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickedListener) {
        this.listener = listener
    }

    interface OnItemClickedListener {
        fun onImageClicked(gifUrl: String)
    }
}