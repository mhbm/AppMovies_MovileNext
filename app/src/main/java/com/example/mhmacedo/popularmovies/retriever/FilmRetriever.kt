package com.example.mhmacedo.popularmovies.retriever

import com.example.mhmacedo.popularmovies.api.FilmService
import com.example.mhmacedo.popularmovies.model.FilmListResult
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class FilmRetriever {

    private val service: FilmService

    companion object {
        //    const val BASE_URL_TOPRATED = "https://api.themoviedb.org/3/movie/top_rated"
        const val BASE_URL_POPULAR = "https://api.themoviedb.org/3/movie/"
        const val API_KEY = "ff1c1e681fe8d4205ac3fa84c7cd2880"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_POPULAR)
            .addConverterFactory(
                nullOnEmptyConverterFactory
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        service = retrofit.create(FilmService::class.java)
    }

    fun getFilmTopRated(callback: Callback<FilmListResult>) {
        val call = service.listTopRated(API_KEY)
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