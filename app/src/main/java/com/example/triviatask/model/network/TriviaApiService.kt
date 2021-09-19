package com.example.triviatask.model.network

import com.example.triviatask.model.data.apiCategory.ApiCategoryResponse
import com.example.triviatask.model.data.apiCountGlobal.ApiCountGlobalResponse
import com.example.triviatask.model.data.triviaStart.TriviaStartResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApiService {

    @GET("api.php")
    fun getQuestions(
        @Query("amount") amountKey: Int,
        @Query("category") categoryKey: Int?,
        @Query("difficulty") difficultyKey: String?,
        @Query("type") typeKey: String?,
    ): Single<Response<TriviaStartResponse>>

    @GET("api_category.php")
    fun getApiCategory(): Single<Response<ApiCategoryResponse>>

    @GET("api_count_global")
    fun getApiCountGlobal(): Single<Response<ApiCountGlobalResponse>>
}