package com.example.scopefunction_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_user_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailFragment : Fragment(R.layout.fragment_user_detail){
    private lateinit var responseDTO: ResponseDTO
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnCallApi.setOnClickListener {
            callApi()
        }
    }

    private fun callApi() {
        val apiClient = Network.getRetrofitInstance().create(ApiClient::class.java)
        apiClient.getUserDetails(2).enqueue(
            object  : Callback<ResponseDTO>{
                override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                    response.body()?.data?.apply {
                        tvFirstName.text = firstName
                        tvLastName.text = lastName
                    }

                }

                override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

}