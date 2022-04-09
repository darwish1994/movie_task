package com.demo.movieapp.common.network

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.demo.movieapp.R
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.io.IOException

class RetrofitException(
    override var message: String?,
    @StringRes
    val titleMessageResourceId: Int,
    @DrawableRes
    val imageResourceId: Int,
    val errorCode: Int,
    /**
     * The event kind which triggered this error.
     */
    val kind: Kind,
    val baseResponse: BaseResponse? = null,
    exception: Throwable?,
) : RuntimeException(exception) {

    enum class Kind {

        NETWORK,

        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,

        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    companion object {
        private val sGson = Gson()

        @JvmStatic
        fun httpError(response: Response<*>?): RetrofitException {
            var message: String?
            var responseBody: String? = ""
            val responseCode = response?.code() ?: 0

            try {

                if (response?.errorBody() != null) {
                    responseBody = response.errorBody()?.string()
                }

                val apiErrorJson = sGson.fromJson(responseBody, BaseResponse::class.java)

                message = if (!apiErrorJson.errors.isNullOrEmpty()) {
                    apiErrorJson.errors.toString()
                } else {
                    apiErrorJson.message
                }

            } catch (e: Exception) {
                message = responseCode.toString() + " " + response!!.message()
                e.printStackTrace()
            }
            return RetrofitException(
                message ?: "Something went wrong",
                getErrorCodeMessage(responseCode),
                getErrorCodeImage(responseCode),
                responseCode,
                Kind.HTTP,
                null,
                null,
            )
        }

        @JvmStatic
        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(
                exception.message!!,
                R.string.no_internet_connection_error,
                R.drawable.ic_no_internet,
                0,
                Kind.NETWORK,
                null,
                exception,
            )
        }

        @JvmStatic
        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(
                exception.message,
                R.string.someting_wrong,
                R.drawable.ic_error,
                0,
                Kind.UNEXPECTED,
                null,
                exception,
            )
        }
    @JvmStatic
        fun responseError(exception: Throwable): RetrofitException {
            return RetrofitException(
                exception.message,
                R.string.responseProblem_wrong,
                R.drawable.ic_error,
                0,
                Kind.UNEXPECTED,
                null,
                exception,
            )
        }


        private fun getErrorCodeMessage(code: Int): Int {
            return when (code) {
                401 -> R.string.unAuthentication
                404 -> R.string.not_found
                400 -> R.string.bad_request
                403, in (500..599) -> R.string.server_error //HTTP 405 Method Not Allowed
                else -> R.string.someting_wrong

            }
        }

        private fun getErrorCodeImage(code: Int): Int {
            return when (code) {
                401 -> R.drawable.ic_auth_error
                else -> R.drawable.ic_error

            }
        }
    }
}

data class BaseResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("errors")
    val errors: Map<String, List<String>>? = null
)