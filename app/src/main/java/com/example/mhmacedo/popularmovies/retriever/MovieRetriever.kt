package com.example.mhmacedo.popularmovies.retriever

import com.example.mhmacedo.popularmovies.api.MovieService
import com.example.mhmacedo.popularmovies.model.MovieListResult
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class MovieRetriever {

    private val service: MovieService

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val API_KEY = "ff1c1e681fe8d4205ac3fa84c7cd2880"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                nullOnEmptyConverterFactory
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        service = retrofit.create(MovieService::class.java)
    }

    fun getFilmTopRated(callback: Callback<MovieListResult>) {
        val call = service.listTopRated(API_KEY)
        call.enqueue(callback)
    }

    fun getFilmPopularMovies(callback: Callback<MovieListResult>) {
        val call = service.listPopularMovies(API_KEY)
        call.enqueue(callback)
    }

}

val nullOnEmptyConverterFactory = object : Converter.Factory() {
    fun converterFactory() = this
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) =
        object : Converter<ResponseBody, Any?> {
            val nextResponseBodyConverter =
                retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

            override fun convert(value: ResponseBody) =
                if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
        }
}