package com.example.horoscapp.data.core.interceptors

// This Interceptor will assign the token to every request that we make to the API
// Why? To make sure that the data we are getting is not fake, for example.
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// chain is the call we are making to Retrofit
class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        // This is the request that will receive the server
        val request = chain.request()

        /**
         * This is the same request, only that the newBuilder() will make the request to be recreated,
         * with new information, like the header that we add
         * as a token so we can make sure that we are receiving a valid Retrofit response
         */
        val newRequest = request.newBuilder().header("Authorization", tokenManager.getToken()).build()

        // We return the request, continue with it, normally, BUT, we integrate the newRequest, that contains the header with an Authorization token
        return chain.proceed(newRequest)
    }
}

// This class will get the token value so we can integrate it into the request
class TokenManager @Inject constructor(){
    fun getToken():String = "TEST"
}