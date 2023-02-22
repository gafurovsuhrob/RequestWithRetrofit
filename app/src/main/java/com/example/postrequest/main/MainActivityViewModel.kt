package com.example.postrequest.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postrequest.api.RetroInstance
import com.example.postrequest.api.RetroService
import com.example.postrequest.model.UserList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    lateinit var recyclerListData: MutableLiveData<UserList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getUserListObservables(): MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUserList() {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUserList()
        call.enqueue(object : Callback<UserList?> {
            override fun onResponse(call: Call<UserList?>, response: Response<UserList?>) {
                if (response.isSuccessful){
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserList?>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }

    fun searchUser(searchText: String) {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchUser(searchText)
        call.enqueue(object : Callback<UserList?> {
            override fun onResponse(call: Call<UserList?>, response: Response<UserList?>) {
                if (response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserList?>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}