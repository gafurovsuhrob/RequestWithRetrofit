package com.example.postrequest.api

import com.example.postrequest.model.User
import com.example.postrequest.model.UserList
import com.example.postrequest.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {

    //https://gorest.co.in/public/users
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUserList(): Call<UserList>

    //https://gorest.co.in/public/users?name=a
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUser(@Query("name") searchText: String): Call<UserList>

    //https://gorest.co.in/public/v1/users/382745
    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUser(@Path("user_id") user_id: String): Call<UserResponse>

    @POST("users")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 0f87935085ef673922c768b2b82633be5d8eb794c05724300255641540a0c8c0"
    )
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 0f87935085ef673922c768b2b82633be5d8eb794c05724300255641540a0c8c0"
    )
    fun updateUser(@Path("user_id") user_id: String, @Body params: User): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 0f87935085ef673922c768b2b82633be5d8eb794c05724300255641540a0c8c0"
    )
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>
}