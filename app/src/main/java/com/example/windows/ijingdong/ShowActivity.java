package com.example.windows.ijingdong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.windows.ijingdong.adapter.SearchAdapter;
import com.example.windows.ijingdong.bean.SearchBean;
import com.example.windows.ijingdong.search.presenter.SearchPresenter;
import com.example.windows.ijingdong.search.view.ISearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity implements ISearchView {

    @BindView(R.id.fl_back)
    ImageView flBack;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.rc_search)
    RecyclerView rcSearch;
    private List<SearchBean.DataBean> list;
    private SearchAdapter searchAdapter;
    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        String keywords = getIntent().getStringExtra("input");
        searchPresenter = new SearchPresenter();
        searchPresenter.attach(this);
        searchPresenter.searchProducts(keywords,1);
        list = new ArrayList<>();
        searchAdapter = new SearchAdapter(this,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcSearch.setLayoutManager(layoutManager);
        rcSearch.setAdapter(searchAdapter);

    }

    @Override
    public void success(SearchBean searchBean) {
        list.clear();
        list.addAll(searchBean.getData());
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"请求失败"+e,Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.fl_back, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_back:
                finish();
                break;
            case R.id.btn_add:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.dettach();
    }
}
