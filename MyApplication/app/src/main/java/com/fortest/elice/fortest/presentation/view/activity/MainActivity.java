package com.fortest.elice.fortest.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fortest.elice.fortest.R;
import com.fortest.elice.fortest.data.dto.UserInfo;
import com.fortest.elice.fortest.data.dto.UserRepo;
import com.fortest.elice.fortest.data.net.RestClient;
import com.fortest.elice.fortest.presentation.presenter.UserPresenter;
import com.fortest.elice.fortest.presentation.view.delegate.NewUserAdapter;

import java.util.List;

import butterknife.BindView;
import rx.Subscription;

public class MainActivity extends BaseActivity {

    private static final String QUERY = "jakewharton";

    @BindView(R.id.repoRecyclerView)
    RecyclerView recyclerView;
//    private UserAdapter adapter;
    private UserPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private NewUserAdapter mAdapter;

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
        mAdapter = new NewUserAdapter(this);
        recyclerView.setAdapter(mAdapter);
//        adapter = new UserAdapter(this);
//        recyclerView.setAdapter(adapter);
        presenter = new UserPresenter(new RestClient(this).getUserRepository());
        presenter.setView(this);
    }

    public void onCompleteGetUserInfo(UserInfo userInfoResponse, List<UserRepo> userRepoResponse) {
        mAdapter.replaceAll(userInfoResponse, userRepoResponse);
    }

    public void onFail() {
        Toast.makeText(this, "유저 정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        Subscription subscription = presenter.getSubscription();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    public void onComplete() {
        Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show();
    }
}
