package com.fortest.elice.fortest.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fortest.elice.fortest.R;

import butterknife.ButterKnife;

/**
 * Created by elice.kim on 2017. 7. 16..
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO viewtype 분기
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_information, parent, false);
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_repository, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //TODO binds
    }

    @Override
    public int getItemCount() {
        //TODO count
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
