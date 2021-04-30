package com.memo.k_room.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.memo.k_room.repository.MainRepository
import com.memo.k_room.room.entity.MemberEntity

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var mainRepository = MainRepository(application)

    var LiveMembers = mainRepository.LiveMembers

    var id = String()
    var name = String()
    var info = String()
    var city = String()

    var alram = MutableLiveData<Boolean>()

    fun addButton(){
        if(id != "" && name != "" && info != "" && city != ""){
            var member = MemberEntity(id, name, info, city)
            mainRepository.insertMember(member)
        }
        else{
            if(alram.value == true){
                alram.value = false
            }
            else if(alram.value == false){
                alram.value = true
            }
            else{
                alram.value = false
            }
        }
    }

    fun deleteMember(memberEntity: MemberEntity){
        mainRepository.deleteMember(memberEntity)
    }

}