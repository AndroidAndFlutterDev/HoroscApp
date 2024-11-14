package com.example.horoscapp.data.network

//This Module will allows us to inject the Retrofit library and the API Service, so we can use Retrofit in our RepositoryImpl
import com.example.horoscapp.BuildConfig.BASE_URL
import com.example.horoscapp.data.RepositoryImpl
import com.example.horoscapp.data.core.interceptors.AuthInterceptor
import com.example.horoscapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// This label indicates Hilt that this is a Module
@Module

// This code indicates Hilt that this Module will be installed in the SingletonComponent
// What is that? Basically, this means that this Module will be available for the entire application
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // This label indicates Hilt that this is a Provider
    // What does this means? It means that this function will be provided by Hilt
    @Provides

    // What does this means? This is a Design Patter.
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        // This code will create the Retrofit object with the base URL of our API, and will return it.
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
            GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton

    // This function will provide the HttpClient, so we can use an interceptor when calling Retrofit
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {

        // Set the interceptor
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        // Returns the OkHttpClient object with the interceptor
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    // This annotation tells Hilt that we are creating an Instance of this function
    @Provides

    // This function will return the API Service because we need to have it so we can use Retrofit in the APIService
    fun provideHoroscopeDetailAPIService(retrofit: Retrofit): HoroscopeDetailAPIService {
        return retrofit.create(HoroscopeDetailAPIService::class.java)
    }

    @Provides

    // This function will provide the RepositoryImpl so we can initialize it in the Use Case
    fun provideRepository(apiService: HoroscopeDetailAPIService): Repository {
        return RepositoryImpl(apiService)
    }
}