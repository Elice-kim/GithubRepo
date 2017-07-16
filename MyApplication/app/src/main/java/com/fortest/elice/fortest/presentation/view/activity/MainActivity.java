package com.fortest.elice.fortest.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fortest.elice.fortest.R;
import com.fortest.elice.fortest.data.dto.UserDTO;
import com.fortest.elice.fortest.data.net.RestClient;
import com.fortest.elice.fortest.presentation.presenter.UserPresenter;
import com.fortest.elice.fortest.presentation.view.adapter.UserAdapter;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final String QUERY = "jakewharton";

    @BindView(R.id.repoRecyclerView)
    RecyclerView recyclerView;
    private UserAdapter adapter;
    private UserPresenter presenter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        presenter.loadImgList(QUERY);
    }

    private void setUpView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new UserAdapter(this);
        recyclerView.setAdapter(adapter);
        presenter = new UserPresenter(new RestClient(this).getUserRepository());
        presenter.setView(this);
    }

    public void onCompleteGetUserInfo(UserDTO.UserInfoResponse userInfoResponse, List<UserDTO.UserRepoResponse> userRepoResponse) {
        adapter.replaceAll(userInfoResponse, userRepoResponse);
    }

    public void onFail() {
        Toast.makeText(this, "유저 정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show();
    }
}
