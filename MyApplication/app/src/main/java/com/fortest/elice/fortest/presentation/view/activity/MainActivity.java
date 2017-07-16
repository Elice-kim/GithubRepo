package com.fortest.elice.fortest.presentation.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.fortest.elice.fortest.R;
import com.fortest.elice.fortest.data.dto.UserDTO;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCompleteGetUserInfo(UserDTO.UserInfoResponse userInfoResponse, UserDTO.UserRepoResponse userRepoResponse) {

    }

    public void onFail() {
        Toast.makeText(this, "유저 정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show();
    }
}
