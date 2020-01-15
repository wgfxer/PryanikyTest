package com.wgfxer.pryanikytest.presentation;


import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wgfxer.pryanikytest.R;
import com.wgfxer.pryanikytest.data.api.MainRepository;
import com.wgfxer.pryanikytest.data.models.list.BaseListItem;
import com.wgfxer.pryanikytest.domain.ViewListOwner;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements ViewListOwner {

    private FrameLayout progressFrameLayout;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    MainPresenter providePresenter() {
        return new MainPresenter(new MainRepository());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRecyclerView();
    }

    private void initViews() {
        progressFrameLayout = findViewById(R.id.progress_frame_layout);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {
        listAdapter = new ListAdapter();
        listAdapter.setOnItemClickListener(itemName -> showToast("Был нажат элемент " + itemName));
        listAdapter.setOnRbClickListener(rbId -> showToast("Выбран элемент селектора номер " + rbId));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
    }


    @Override
    public void showLoading() {
        progressFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<BaseListItem> list) {
        listAdapter.setListItems(list);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
