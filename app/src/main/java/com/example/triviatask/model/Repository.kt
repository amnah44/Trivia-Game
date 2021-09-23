package com.example.triviatask.model

import android.util.Log
import com.example.triviatask.model.network.API
import com.example.triviatask.utils.Constant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import com.example.triviatask.utils.convertToLocalTriviaStart
import com.example.triviatask.utils.convertToLocalTriviaStartResponse

object Repository {
    private val api = API.apiService

    fun getQuestion(
        amount: Int,
        category: Int?,
        level: String?,
        type: String?
    ) =
        wrap(api.getQuestions(amount, category, level, type)).map {
            State.Success(it.toData()?.convertToLocalTriviaStartResponse())
        }

    fun getCategories() =
        wrap(api.getApiCategory())

    fun getCountGlobal() =
        wrap(api.getApiCountGlobal())


    private fun <T> wrap(response: Single<Response<T>>): Single<State<T>> {
        return response.map {
            State.Success(it.body())
        }
    }
}