package com.memo.k_room.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.memo.k_room.viewmodel.MainViewModel
import com.memo.k_room.R
import com.memo.k_room.databinding.ActivityMainBinding
import com.memo.k_room.room.entity.MemberEntity

class MainActivity : AppCompatActivity() {

    lateinit var MainBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    var mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        MainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        MainBinding.setLifecycleOwner(this)
        MainBinding.mainViewModel = mainViewModel

        settingRCV()
        deleteMember()
        RCVitemOnClick()

        var LiveMembers = Observer<List<MemberEntity>>{
            mainAdapter.setMembers(it)
            mainAdapter.notifyDataSetChanged()
        }
        mainViewModel.LiveMembers.observe(this, LiveMembers)

        var alarm = Observer<Boolean> {
            Toast.makeText(MainBinding.root.context,"모두 입력해주세요.",Toast.LENGTH_SHORT).show()
        }
        mainViewModel.alram.observe(this, alarm)

    }

    fun settingRCV(){
        var layoutmanager = LinearLayoutManager(MainBinding.memberListRCV.context)
        MainBinding.memberListRCV.layoutManager = layoutmanager
        MainBinding.memberListRCV.adapter = mainAdapter
    }

    fun deleteMember(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mainViewModel.deleteMember(mainAdapter.getMemberAt(viewHolder.getAdapterPosition()))
            }

        }).attachToRecyclerView(MainBinding.memberListRCV)
    }

    fun RCVitemOnClick(){
        mainAdapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(holder: MainAdapter.ViewHolder?, view: View?, position: Int) {
                var intent = Intent(applicationContext, MemberInfoActivity::class.java)
                intent.putExtra("memberId", mainViewModel.LiveMembers.value?.get(position)?.memberId)
                intent.putExtra("name", mainViewModel.LiveMembers.value?.get(position)?.name)
                intent.putExtra("info", mainViewModel.LiveMembers.value?.get(position)?.info)
                intent.putExtra("city", mainViewModel.LiveMembers.value?.get(position)?.city)
                startActivity(intent)
            }
        })
    }
}