package id.co.arya.kumparan.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    val timeout = 60

    val API_SERVICE: ApiEndpoint by lazy {
        val okHttpClient = okHttpClientInstance()
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiEndpoint::class.java)
    }

    private fun okHttpClientInstance(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        client.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        client.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        client.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
        return client.build()
    }

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }

}