package com.fortest.elice.fortest.presentation.presenter;

import com.fortest.elice.fortest.data.dto.UserInfo;
import com.fortest.elice.fortest.data.dto.UserRepo;
import com.fortest.elice.fortest.data.repository.UserRepository;
import com.fortest.elice.fortest.presentation.view.activity.MainActivity;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
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

        subscription = Observable.zip(userRepository.userInfo(userName).subscribeOn(Schedulers.io()),
                userRepository.userRepository(userName).subscribeOn(Schedulers.io()),
                new Func2<UserInfo, List<UserRepo>, UserInfoZipItem>() {
                    @Override
                    public UserInfoZipItem call(UserInfo userInfo, List<UserRepo> userRepos) {
                        return new UserInfoZipItem(userInfo, userRepos);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> view.showLoading())
                .doOnTerminate(() -> view.hideLoading())
                .subscribe(response -> view.onCompleteGetUserInfo(response.userInfo,
                        response.userRepoResponse), e -> view.onFail());


//        subscription = Observable.zip(userRepository.getUserInfo(userName).subscribeOn(Schedulers.io()),
//                userRepository.getUserRepository(userName).subscribeOn(Schedulers.io()),
//                new Func2<UserDTO.UserInfoResponse, List<UserDTO.UserRepoResponse>, UserInfoZipItem>() {
//                    @Override
//                    public UserInfoZipItem call(UserDTO.UserInfoResponse userInfoResponse, List<UserDTO.UserRepoResponse> userRepoResponse) {
//                        return new UserInfoZipItem(userInfoResponse, userRepoResponse);
//                    }
//                })
//                //위와 같음
//////                Observable.zip(userRepository.getUserInfo(userName).subscribeOn(Schedulers.io()),
//////                userRepository.getUserRepository(userName).subscribeOn(Schedulers.io()),
//////                (UserDTO.UserInfoResponse userInfoResponse, List<UserDTO.UserRepoResponse> userRepoResponse)
//////                        -> new UserInfoZipItem(userInfoResponse, userRepoResponse))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(() -> view.showLoading())
//                .doOnTerminate(() -> view.hideLoading())
//                .subscribe(response ->
//                                view.onCompleteGetUserInfo(response.userInfoResponse, response.userRepoResponse)
//                        , e -> view.onFail());

    }
//
//    private class UserInfoZipItem {
//        public UserDTO.UserInfoResponse userInfoResponse;
//        public List<UserDTO.UserRepoResponse> userRepoResponse;
//
//        public UserInfoZipItem(UserDTO.UserInfoResponse userInfoResponse, List<UserDTO.UserRepoResponse> userRepoResponse) {
//            this.userInfoResponse = userInfoResponse;
//            this.userRepoResponse = userRepoResponse;
//        }
//    }


    private class UserInfoZipItem {
        public UserInfo userInfo;
        public List<UserRepo> userRepoResponse;

        public UserInfoZipItem(UserInfo userInfoResponse, List<UserRepo> userRepoResponse) {
            this.userInfo = userInfoResponse;
            this.userRepoResponse = userRepoResponse;
        }
    }
}
