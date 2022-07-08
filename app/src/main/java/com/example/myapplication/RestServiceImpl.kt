package com.example.myapplication

import com.example.myapplication.base.activity.BaseRepository
import com.example.myapplication.net.impl.RestService
import retrofit2.Retrofit
import javax.inject.Inject

class RestServiceImpl @Inject constructor(retrofit: Retrofit) : BaseRepository<RestService>(retrofit), RestService {

    override suspend fun get899(word: String, queryWord: String, pn: Int, gsm: String) =
        service.get899(word, queryWord, pn, gsm);
}