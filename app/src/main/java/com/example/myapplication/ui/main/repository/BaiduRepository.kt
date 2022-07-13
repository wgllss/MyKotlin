package com.example.myapplication.ui.main.repository

import com.example.myapplication.base.activity.BaseRepository
import com.scclzkj.api.Api
import retrofit2.Retrofit
import javax.inject.Inject

class BaiduRepository @Inject constructor(retrofit: Retrofit) : BaseRepository<Api>(retrofit), Api {

    override suspend fun get899(word: String, queryWord: String, pn: Int, gsm: String) =
        service.get899(word, queryWord, pn, gsm)
}