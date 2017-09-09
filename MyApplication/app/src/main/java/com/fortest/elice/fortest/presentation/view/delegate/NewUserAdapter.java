package com.fortest.elice.fortest.presentation.view.delegate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.fortest.elice.fortest.data.dto.UserInfo;
import com.fortest.elice.fortest.data.dto.UserRepo;
import com.fortest.elice.fortest.data.dto.UserResponse;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elice.kim on 2017. 9. 9..
 */

public class NewUserAdapter extends RecyclerView.Adapter {

    public static final int USER_INFO_VIEW_TYPE = 0;
    public static final int USER_REPOSITORY_VIEW_TYPE = 1;

    private List<UserResponse> items;
    private AdapterDelegatesManager<List<UserResponse>> manager;
    private Context mContext;


    public NewUserAdapter(Context mContext) {
        items = new ArrayList<>();
        this.mContext = mContext;
        manager = new AdapterDelegatesManager<>();
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = manager.getItemViewType(items, position);
        Log.e("elice", "getItemViewType: "+itemViewType );
        return itemViewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return manager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        manager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void replaceAll(UserInfo userInfoResponse, List<UserRepo> userRepoResponse) {
        items.add(userInfoResponse);
        items.addAll(userRepoResponse);

        manager.addDelegate(new UserInfoDelegate(mContext));
        manager.addDelegate(new UserRepoDelegate());
        notifyDataSetChanged();
    }
}
