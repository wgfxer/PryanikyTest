package com.wgfxer.pryanikytest.data.api;

import com.wgfxer.pryanikytest.data.models.web.JsonRoot;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Web-api для сайта
 */
public interface PryanikyService {
    /**
     * Загружает данные с сайта
     */
    @GET("json/JSONSample.json")
    Call<JsonRoot> loadData();
}
