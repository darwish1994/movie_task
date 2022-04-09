package com.demo.movieapp.common.network


interface IViewState<T> {

    fun whichState(): CommonStates

    fun fetchData(): T?

    fun fetchError(): RetrofitException?


}


interface CommonStates

enum class CommonStatus : CommonStates {
    SUCCESS, LOADING, ERROR
}


data class Result<T>(
    val status: CommonStates,
    val data: T?,
    val errorException: RetrofitException?) : IViewState<T> {

    companion object {
        fun <T> success(data: T) = Result<T>(CommonStatus.SUCCESS, data, null)

        fun <T> success() = Result<T>(CommonStatus.SUCCESS, null, null)

        fun <T> error(retrofitException: RetrofitException) = Result<T>(CommonStatus.ERROR, null, retrofitException)

        fun <T> loading() = Result<T>(CommonStatus.LOADING, null, null)
    }

    override fun whichState(): CommonStates = status

    override fun fetchData(): T? = data

    override fun fetchError(): RetrofitException? = errorException

}

