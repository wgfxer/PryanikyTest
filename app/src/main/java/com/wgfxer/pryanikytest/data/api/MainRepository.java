package com.wgfxer.pryanikytest.data.api;

import com.wgfxer.pryanikytest.data.JsonRootConverter;
import com.wgfxer.pryanikytest.data.models.list.BaseListItem;
import com.wgfxer.pryanikytest.data.models.web.JsonRoot;
import com.wgfxer.pryanikytest.domain.IMainRepository;

import java.io.IOException;
import java.util.List;


import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Реализация репозитория для загрузки данных
 */
public class MainRepository implements IMainRepository {

    public static final String BASE_URL = "https://chat.pryaniky.com/";
    private PryanikyService apiService;
    private JsonRootConverter converter = new JsonRootConverter();

    public MainRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        apiService = retrofit.create(PryanikyService.class);
    }

    @Override
    public List<BaseListItem> loadData() throws IOException {
        Response<JsonRoot> response = apiService.loadData().execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить данные");
        }
        return converter.convert(response.body());
    }
}
