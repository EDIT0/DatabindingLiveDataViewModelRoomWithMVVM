package com.memo.k_room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.memo.k_room.repository.ReviseBookNameRepository

class ReviseBookNameViewModel(application: Application) : AndroidViewModel(application) {

    var reviseBookNameRepository = ReviseBookNameRepository(application)

    var newBookName = String()

    var bookOrder = String()
    var bookName = String()
    var memberIdInBook = String()

    var alram = MutableLiveData<Boolean>()
    var finishUpdate = MutableLiveData<Boolean>()

    init {
        finishUpdate.value = false
    }

    fun updateButton(){
        if(newBookName != "") {
            reviseBookNameRepository.updateBook(newBookName, Integer.parseInt(bookOrder))
            finishUpdate.value = true
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

}