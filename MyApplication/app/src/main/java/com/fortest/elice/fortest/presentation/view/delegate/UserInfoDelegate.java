package com.fortest.elice.fortest.presentation.view.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fortest.elice.fortest.R;
import com.fortest.elice.fortest.data.dto.UserInfo;
import com.fortest.elice.fortest.data.dto.UserResponse;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elice.kim on 2017. 9. 9..
 */

public class UserInfoDelegate extends AdapterDelegate<List<UserResponse>> {

    private Context mContext;

    public UserInfoDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull List<UserResponse> items, int position) {
        return items.get(position) instanceof UserInfo;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_information, parent, false);
        return new UserInfoViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<UserResponse> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

        UserInfo userModel = (UserInfo) items.get(position);
        UserInfoViewHolder userInfoViewHolder = (UserInfoViewHolder) holder;

        Glide.with(mContext).load(userModel.avatarUrl).into(userInfoViewHolder.userImageView);
        userInfoViewHolder.userNameTextView.setText(userModel.name);
    }

    public class UserInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userImageView)
        ImageView userImageView;
        @BindView(R.id.userNameTextView)
        TextView userNameTextView;

        public UserInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
