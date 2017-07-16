package com.fortest.elice.fortest.presentation.presenter;

import com.fortest.elice.fortest.data.dto.UserDTO;
import com.fortest.elice.fortest.data.repository.UserRepository;
import com.fortest.elice.fortest.presentation.view.activity.MainActivity;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by elice.kim on 2017. 7. 16..
 */

public class UserPresenter extends BasePresenter<MainActivity> {

    private UserRepository userRepository;

    public UserPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void loadImgList(String userName) {
        subscription = Observable.zip(userRepository.getUserInfo(userName),
                userRepository.getUserRepository(userName),
                (UserDTO.UserInfoResponse userInfoResponse, List<UserDTO.UserRepoResponse> userRepoResponse)
                        -> new UserInfoZipItem(userInfoResponse, userRepoResponse)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> view.showLoading())
                .doOnTerminate(() -> view.hideLoading())
                .subscribe(response ->
                                view.onCompleteGetUserInfo(response.userInfoResponse, response.userRepoResponse)
                        , e -> view.onFail());
    }

    public class UserInfoZipItem {
        public UserDTO.UserInfoResponse userInfoResponse;
        public List<UserDTO.UserRepoResponse> userRepoResponse;

        public UserInfoZipItem(UserDTO.UserInfoResponse userInfoResponse, List<UserDTO.UserRepoResponse> userRepoResponse) {
            this.userInfoResponse = userInfoResponse;
            this.userRepoResponse = userRepoResponse;
        }
    }
}
