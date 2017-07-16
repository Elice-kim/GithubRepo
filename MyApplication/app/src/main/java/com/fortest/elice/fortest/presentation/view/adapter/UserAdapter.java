package com.fortest.elice.fortest.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fortest.elice.fortest.R;
import com.fortest.elice.fortest.data.dto.UserDTO;
import com.fortest.elice.fortest.util.CollectionUtil;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elice.kim on 2017. 7. 16..
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int USER_INFO_VIEW_TYPE = 0;
    public static final int USER_REPOSITORY_VIEW_TYPE = 1;

    private Context context;
    private UserDTO.UserInfoResponse userInfoResponse;
    private List<UserDTO.UserRepoResponse> userRepoResponse;

    public UserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == USER_INFO_VIEW_TYPE) {
            View userInfoView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user_information, parent, false);
            return new UserViewHolder(userInfoView);
        } else {
            View userRepositoryView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user_repository, parent, false);
            return new RepositoryViewHolder(userRepositoryView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            Glide.with(context).load(userInfoResponse.avatarUrl).into(userViewHolder.userImageView);
            userViewHolder.userNameTextView.setText(userInfoResponse.name);
        } else {
            RepositoryViewHolder repositoryViewHolder = (RepositoryViewHolder) holder;
            //상위 userInfo 레이아웃 때문에 해당 레이아웃의 position은 1부터 시작함으로 -1을 한다.
            UserDTO.UserRepoResponse userRepoResponse = this.userRepoResponse.get(position - 1);
            repositoryViewHolder.repoNameTextView.setText(userRepoResponse.name);
            repositoryViewHolder.repoDescTextView.setText(userRepoResponse.description);
            repositoryViewHolder.starCountTextView.setText(String.valueOf(userRepoResponse.stargazersCount));
        }
    }

    @Override
    public int getItemCount() {
        if (CollectionUtil.isEmpty(userRepoResponse)) {
            return 0;
        }
        // userInfo 정보 layout 때문에 +1
        return userRepoResponse.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return USER_INFO_VIEW_TYPE;
        } else {
            return USER_REPOSITORY_VIEW_TYPE;
        }
    }

    public void replaceAll(UserDTO.UserInfoResponse userInfoResponse, List<UserDTO.UserRepoResponse> userRepoResponse) {
        if (userInfoResponse != null && CollectionUtil.isEmpty(userRepoResponse)) {
            return;
        }
        this.userInfoResponse = userInfoResponse;
        Collections.sort(userRepoResponse, (o1, o2) -> o1.stargazersCount > o2.stargazersCount ? -1 : 1);
        this.userRepoResponse = userRepoResponse;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userImageView)
        ImageView userImageView;
        @BindView(R.id.userNameTextView)
        TextView userNameTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.repoNameTextView)
        TextView repoNameTextView;
        @BindView(R.id.repoDescTextView)
        TextView repoDescTextView;
        @BindView(R.id.starCountTextView)
        TextView starCountTextView;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
