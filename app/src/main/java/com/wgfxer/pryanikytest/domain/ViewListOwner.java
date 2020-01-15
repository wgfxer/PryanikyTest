package com.wgfxer.pryanikytest.domain;


import com.wgfxer.pryanikytest.data.models.list.BaseListItem;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

/**
 * Интерфейс для View которая содержит список с данными
 */
@StateStrategyType(value = AddToEndStrategy.class)
public interface ViewListOwner extends MvpView {
    void showLoading();

    void hideLoading();

    void showData(List<BaseListItem> list);

    void showError(String message);
}
