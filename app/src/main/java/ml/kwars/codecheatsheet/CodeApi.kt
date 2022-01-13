package ml.kwars.codecheatsheet

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CodeApi {
//    fun getProgrammingLanguage(@Query("key") key:String):Response<List<ProgrammingLanguage>>

    @GET("/api/languages")
    suspend fun getProgrammingLanguage():Response<List<ProgrammingLanguage>>

    @GET("/api/languages/{lang}")
    suspend fun getLanguagePart(@Path(value = "lang", encoded = true) lang:String):Response<List<LanguagePart>>

    @GET("/api/languages/{lang}/{part}")
    suspend fun getLanguageField(@Path(value = "lang", encoded = true) lang:String,@Path(value = "part", encoded = true) part:String):Response<List<LanguageField>>

}