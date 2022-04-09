package com.demo.movieapp.common.repo

import com.demo.movieapp.common.database.MovieDao
import com.demo.movieapp.common.database.SharedData
import com.demo.movieapp.common.util.getDiffHours
import com.demo.movieapp.common.network.ResponseInterceptor
import com.demo.movieapp.common.network.Result
import com.demo.movieapp.common.network.RetrofitException
import com.demo.movieapp.common.network.calls.MovieApi
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class MovieRepo @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
    private val sharedData: SharedData
) {

    /**
     * get popular movie
     * check data validation in which
     * if data last sync is bigger than 4 hour invalid data
     *
     * **/

    suspend fun getPopularMovie() =
        if (sharedData.getLastSync().getDiffHours() < 4) {
            Timber.v("is here 1")
            Result.success()
        }
        else
            try {
                Timber.v("is here 2")
                movieDao.deleteAll()
                val result = movieApi.getPopularMovie()
                withContext(Dispatchers.IO) {
                    movieDao.insertMovie(result.data)
                    sharedData.setLastSync()
                }
                Result.success(result)
            } catch (throwable: Exception) {
                when (throwable) {
                    is ResponseInterceptor.NoInternetConnection, is IOException -> Result.error(
                        RetrofitException.networkError(IOException(throwable))
                    )
                    is HttpException -> Result.error(RetrofitException.httpError(throwable.response()))

                    /**
                     * handle json parsing exceptions
                     * */
                    is JsonParseException, is JsonSyntaxException, is JSONException, is JsonIOException -> Result.error(
                        RetrofitException.responseError(throwable)
                    )
                    /**
                     * handle realm db exceptions
                     * */
                    //is IllegalStateException
                    else -> Result.error(RetrofitException.unexpectedError(throwable))
                }
            }

    fun getMoveFrmDataBase() = movieDao.getAllMovieFrmDb()


}