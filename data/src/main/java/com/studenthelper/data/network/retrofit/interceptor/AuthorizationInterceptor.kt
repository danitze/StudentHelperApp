package com.studenthelper.data.network.retrofit.interceptor

import com.studenthelper.data.datastore.auth.AuthDataStore
import com.studenthelper.data.network.retrofit.api.ApiConstants
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject internal constructor(
    private val authDataStore: AuthDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        var request = chain.request()
        if (request.header(ApiConstants.NO_AUTH_HEADER) == null) {
            val token: String? = authDataStore.getToken()
            if (!token.isNullOrBlank()) {
                request = request.newBuilder()
                    .addHeader(ApiConstants.AUTH_HEADER, "Bearer $token")
                    .build()
            }
        }
        return@runBlocking chain.proceed(request)
    }
}