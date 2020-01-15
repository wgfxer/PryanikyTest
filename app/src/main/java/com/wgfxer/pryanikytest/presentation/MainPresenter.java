package com.wgfxer.pryanikytest.presentation;

import android.os.Handler;
import android.os.Looper;

import com.wgfxer.pryanikytest.data.api.MainRepository;
import com.wgfxer.pryanikytest.data.models.list.BaseListItem;
import com.wgfxer.pryanikytest.domain.IMainRepository;
import com.wgfxer.pryanikytest.domain.ViewListOwner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
class MainPresenter extends MvpPresenter<ViewListOwner> {

    private IMainRepository repository;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    MainPresenter(IMainRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void onFirstViewAttach() {
        getViewState().showLoading();
        executor.execute(() -> {
            try {
                List<BaseListItem> listItems = repository.loadData();
                handler.post(() -> getViewState().showData(listItems));
            } catch (IOException e) {
                handler.post(() -> getViewState().showError("Ошибка при загрузке данных"));
            }
            handler.post(() -> getViewState().hideLoading());
        });
    }
}
