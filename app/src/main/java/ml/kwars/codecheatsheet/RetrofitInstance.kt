package ml.kwars.codecheatsheet

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: CodeApi by lazy {
        Retrofit.Builder().baseUrl("http://192.168.1.116:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CodeApi::class.java)
    }

}