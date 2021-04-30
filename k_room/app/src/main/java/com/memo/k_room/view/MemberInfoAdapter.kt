package com.memo.k_room.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memo.k_room.databinding.MainadapterBinding
import com.memo.k_room.databinding.MemberinfoadapterBinding
import com.memo.k_room.room.entity.BookEntity
import com.memo.k_room.room.entity.MemberEntity

class MemberInfoAdapter : RecyclerView.Adapter<MemberInfoAdapter.ViewHolder>() {

    var books = ArrayList<BookEntity>()
    private lateinit var listener: OnItemClickListener

    inner class ViewHolder(binding : MemberinfoadapterBinding) : RecyclerView.ViewHolder(binding.root){
        var binding = binding

        init {
            itemView.setOnClickListener {
                var position = adapterPosition
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(this, itemView, adapterPosition)
                }
            }
        }

        fun bind(info : BookEntity){
            binding.bookModel = info
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MemberinfoadapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books.get(position))

    }

    fun setBooks(books: List<BookEntity>){
        this.books = books as ArrayList
        notifyDataSetChanged()
    }

    fun getBookAt(position: Int): BookEntity {
        return books.get(position)
    }

    interface OnItemClickListener{
        fun onItemClick(holder: MemberInfoAdapter.ViewHolder?, view: View?, position:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}