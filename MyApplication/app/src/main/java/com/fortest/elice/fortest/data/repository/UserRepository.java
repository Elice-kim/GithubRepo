package com.fortest.elice.fortest.data.repository;

import com.fortest.elice.fortest.data.dto.UserDTO;
import com.fortest.elice.fortest.data.dto.UserInfo;
import com.fortest.elice.fortest.data.dto.UserRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by elice.kim on 2017. 7. 16..
 */

public interface UserRepository {

    @GET("users/{username}")
    Observable<UserDTO.UserInfoResponse> getUserInfo(@Path("username") String userName);

    @GET("users/{username}/repos")
    Observable<List<UserDTO.UserRepoResponse>> getUserRepository(@Path("username") String userName);


    @GET("users/{username}")
    Observable<UserInfo> userInfo(@Path("username") String userName);

    @GET("users/{username}/repos")
    Observable<List<UserRepo>> userRepository(@Path("username") String userName);




}
