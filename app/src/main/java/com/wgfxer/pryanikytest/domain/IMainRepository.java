package com.wgfxer.pryanikytest.domain;

import com.wgfxer.pryanikytest.data.models.list.BaseListItem;

import java.io.IOException;
import java.util.List;

/**
 * Репозиторий для загрузки данных
 */

public interface IMainRepository {
    /**
     * Загружает данные
     */
    List<BaseListItem> loadData() throws IOException;
}
