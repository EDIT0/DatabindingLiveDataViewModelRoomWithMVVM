package com.memo.k_room.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memo.k_room.databinding.MainadapterBinding
import com.memo.k_room.room.entity.MemberEntity

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var members = ArrayList<MemberEntity>()
    private lateinit var listener: OnItemClickListener

    inner class ViewHolder(binding : MainadapterBinding) : RecyclerView.ViewHolder(binding.root){
        var binding = binding

        init {
            itemView.setOnClickListener {
                var position = adapterPosition
                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(this, itemView, adapterPosition)
                }
            }
        }

        fun bind(info : MemberEntity){
            binding.memberModel = info
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MainadapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(members.get(position))

    }

    fun setMembers(members: List<MemberEntity>){
        this.members = members as ArrayList
        notifyDataSetChanged()
    }

    fun getMemberAt(position: Int): MemberEntity {
        return members.get(position)
    }

    interface OnItemClickListener{
        fun onItemClick(holder: MainAdapter.ViewHolder?, view: View?, position:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}