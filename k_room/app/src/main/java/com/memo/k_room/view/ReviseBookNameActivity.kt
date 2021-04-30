package com.memo.k_room.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.memo.k_room.R
import com.memo.k_room.databinding.RevisebooknameactivityBinding
import com.memo.k_room.viewmodel.ReviseBookNameViewModel

class ReviseBookNameActivity : AppCompatActivity() {

    val TAG = "ReviseBookNameActivity"

    lateinit var ReviseBookNameBinding: RevisebooknameactivityBinding
    lateinit var reviseBookNameViewModel: ReviseBookNameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reviseBookNameViewModel = ViewModelProvider(this).get(ReviseBookNameViewModel::class.java)
        ReviseBookNameBinding = DataBindingUtil.setContentView(this, R.layout.revisebooknameactivity)
        ReviseBookNameBinding.lifecycleOwner = this
        ReviseBookNameBinding.reviseBookNameViewModel = reviseBookNameViewModel

        var getintent = getIntent()
        reviseBookNameViewModel.bookOrder = getintent.getStringExtra("bookOrder")?:"None"
        reviseBookNameViewModel.bookName = getintent.getStringExtra("bookName")?:"None"
        reviseBookNameViewModel.memberIdInBook = getintent.getStringExtra("memberIdInBook")?:"None"

        var alarm = Observer<Boolean>{
            Toast.makeText(ReviseBookNameBinding.root.context,"변경할 책 이름을 입력하세요.", Toast.LENGTH_SHORT).show()
        }
        reviseBookNameViewModel.alram.observe(this, alarm)

        var finishUpdate = Observer<Boolean> {
            if(it == true){
                Toast.makeText(ReviseBookNameBinding.root.context,"수정되었습니다.",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        reviseBookNameViewModel.finishUpdate.observe(this, finishUpdate)

        ReviseBookNameBinding.backButton.setOnClickListener {
            finish()
        }

    }
}