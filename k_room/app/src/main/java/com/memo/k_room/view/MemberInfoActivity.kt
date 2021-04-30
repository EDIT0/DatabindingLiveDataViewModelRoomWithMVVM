package com.memo.k_room.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.memo.k_room.R
import com.memo.k_room.databinding.MemberinfoactivityBinding
import com.memo.k_room.room.entity.BookEntity
import com.memo.k_room.room.entity.MemberEntity
import com.memo.k_room.viewmodel.MemberInfoViewModel

class MemberInfoActivity : AppCompatActivity() {

    val TAG = "MemberInfoActivity"

    lateinit var MemberInfoBinding: MemberinfoactivityBinding
    lateinit var memberInfoViewModel: MemberInfoViewModel

    var memberInfoAdapter = MemberInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        memberInfoViewModel = ViewModelProvider(this).get(MemberInfoViewModel::class.java)
        MemberInfoBinding = DataBindingUtil.setContentView(this, R.layout.memberinfoactivity)
        MemberInfoBinding.lifecycleOwner = this
        MemberInfoBinding.memberInfoViewModel = memberInfoViewModel

        getIntentData()
        settingRCV()
        RCVitemOnClick()
        deleteBook()

        var LiveBooks = Observer<List<BookEntity>>{
            memberInfoAdapter.setBooks(it)
            memberInfoAdapter.notifyDataSetChanged()

            memberInfoViewModel.Books.clear()
            memberInfoViewModel.Books = it as ArrayList
        }
        memberInfoViewModel.LiveBooks.observe(this, LiveBooks)

        MemberInfoBinding.backButton.setOnClickListener {
            finish()
        }


    }

    fun settingRCV(){
        var layoutmanager = LinearLayoutManager(MemberInfoBinding.bookRCV.context)
        MemberInfoBinding.bookRCV.layoutManager = layoutmanager
        MemberInfoBinding.bookRCV.adapter = memberInfoAdapter
    }

    fun getIntentData(){
        var getintent = getIntent()
        memberInfoViewModel.memberId = getintent.getStringExtra("memberId")?:"None"
        memberInfoViewModel.name = getintent.getStringExtra("name")?:"None"
        memberInfoViewModel.info = getintent.getStringExtra("info")?:"None"
        memberInfoViewModel.city = getintent.getStringExtra("city")?:"None"
    }

    fun deleteBook(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                memberInfoViewModel.deleteBook(memberInfoAdapter.getBookAt(viewHolder.getAdapterPosition()))
            }

        }).attachToRecyclerView(MemberInfoBinding.bookRCV)
    }

    fun RCVitemOnClick(){
        memberInfoAdapter.setOnItemClickListener(object : MemberInfoAdapter.OnItemClickListener {
            override fun onItemClick(holder: MemberInfoAdapter.ViewHolder?, view: View?, position: Int) {
                var intent = Intent(applicationContext, ReviseBookNameActivity::class.java)
                intent.putExtra("bookOrder", memberInfoViewModel.Books.get(position).bookOrder.toString())
                intent.putExtra("bookName", memberInfoViewModel.Books.get(position).bookName)
                intent.putExtra("memberIdInBook", memberInfoViewModel.Books.get(position).memberIdInBook)
                Log.i(TAG,"${memberInfoViewModel.Books.get(position).bookOrder}," +
                        " ${memberInfoViewModel.Books.get(position).bookName}, " +
                        "${memberInfoViewModel.Books.get(position).memberIdInBook}")
                startActivity(intent)
            }
        })
    }



}