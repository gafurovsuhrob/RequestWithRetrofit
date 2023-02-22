package com.example.postrequest.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postrequest.api.RetroInstance
import com.example.postrequest.api.RetroService
import com.example.postrequest.model.User
import com.example.postrequest.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewUserViewModel : ViewModel() {

    var createNewUserLiveData: MutableLiveData<UserResponse?>
    var loadUserData: MutableLiveData<UserResponse?>
    var deleteUserLiveData: MutableLiveData<UserResponse?>

    init {
        createNewUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()
        deleteUserLiveData = MutableLiveData()
    }

    fun getCreateNewUserObserver(): MutableLiveData<UserResponse?> {
        return createNewUserLiveData
    }

    fun getLoadUserObserver(): MutableLiveData<UserResponse?> {
        return loadUserData
    }

    fun getDeleteUserObserver(): MutableLiveData<UserResponse?> {
        return deleteUserLiveData
    }

    fun createUser(user: User) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
        })
    }

    fun updateUser(user_id: String, user: User) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateUser(user_id, user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
        })
    }

    fun deleteUser(user_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    deleteUserLiveData.postValue(response.body())
                } else {
                    deleteUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }
        })
    }

    fun getUserData(user_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    loadUserData.postValue(response.body())
                } else {
                    loadUserData.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserData.postValue(null)
            }
        })
    }

}