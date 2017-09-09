package com.fortest.elice.fortest.presentation.view.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fortest.elice.fortest.R;
import com.fortest.elice.fortest.data.dto.UserRepo;
import com.fortest.elice.fortest.data.dto.UserResponse;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elice.kim on 2017. 9. 8..
 */

public class UserRepoDelegate extends AdapterDelegate<List<UserResponse>>{

    @Override
    protected boolean isForViewType(@NonNull List<UserResponse> items, int position) {
        return items.get(position) instanceof UserRepo;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_repository, parent, false);
        return new UserRepoViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<UserResponse> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

        UserRepo userRepoModel = (UserRepo) items.get(position);
        UserRepoViewHolder repoHolder = (UserRepoViewHolder) holder;

        repoHolder.repoNameTextView.setText(userRepoModel.name);
        repoHolder.repoDescTextView.setText(userRepoModel.description);
        repoHolder.starCountTextView.setText(String.valueOf(userRepoModel.stargazersCount));
    }

    public class UserRepoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.repoNameTextView)
        TextView repoNameTextView;
        @BindView(R.id.repoDescTextView)
        TextView repoDescTextView;
        @BindView(R.id.starCountTextView)
        TextView starCountTextView;

        public UserRepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
