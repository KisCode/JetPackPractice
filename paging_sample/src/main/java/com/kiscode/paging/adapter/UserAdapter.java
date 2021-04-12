package com.kiscode.paging.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.paging.R;
import com.kiscode.paging.comman.LoadStatus;
import com.kiscode.paging.model.pojo.User;
import com.kiscode.paging.viewmodel.UserViewModel;

import java.util.Objects;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:44
 */

public class UserAdapter extends PagedListAdapter<User, RecyclerView.ViewHolder> {
    private final int TYPE_FOOTER = 909;
    private final int TYPE_USER = 101;


    private boolean hasFooter = false;
    private LoadStatus loadStatus;


    /***
     * 更新加载状态
     * @param status
     */
    public void updateLoadStatus(LoadStatus status) {
        this.loadStatus = status;
        if (status == LoadStatus.INITAL_LOADING) {
            hideFooter();
        } else {
            showFooter();
        }
    }

    public UserAdapter() {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return Objects.equals(oldItem, newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getNumber() == newItem.getNumber();
            }
        });
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasFooter ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return (hasFooter && position == getItemCount() - 1) ? TYPE_FOOTER : TYPE_USER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (TYPE_FOOTER == viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer, parent, false);
            return new FooterViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new UserViewHolder(itemView);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (TYPE_USER == getItemViewType(position)) {
            User user = getItem(position);
            if (user == null) {
                return;
            }
            ((UserViewHolder) holder).bindWithUser(user);
        } else if (TYPE_FOOTER == getItemViewType(position)) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindWithLoadStatus(loadStatus);


            if (footerViewHolder.isClickable) {
                holder.itemView.setOnClickListener(v -> {
                    if (mOnRetryListener != null) {
                        mOnRetryListener.onRetry();
                    }
                });
            }
        }
    }

    private void hideFooter() {
        if (hasFooter) {
            notifyItemRemoved(getItemCount() - 1);
        }
        hasFooter = false;
    }

    private void showFooter() {
        if (hasFooter) {
            notifyItemChanged(getItemCount() - 1);
        } else {
            hasFooter = true;
            notifyItemInserted(getItemCount() - 1);
        }
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvNumber;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }

        public void bindWithUser(User user) {
            tvNumber.setText(String.valueOf(user.getNumber()));
            tvId.setText(user.getId());
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        private TextView tvStatus;
        private ProgressBar pbar;
        private boolean isClickable;


        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStatus = itemView.findViewById(R.id.tv_status);
            pbar = itemView.findViewById(R.id.progressBar);
        }

        public void bindWithLoadStatus(LoadStatus loadStatus) {
            switch (loadStatus) {
                case FAILED:
                    pbar.setVisibility(View.GONE);
                    tvStatus.setText("点击重试");
                    //可以点击
                    isClickable = true;
                    break;
                case COMPLETE:
                    pbar.setVisibility(View.GONE);
                    tvStatus.setText("加载完毕");
                    isClickable = false;
                    break;
                case LOADING:
                    pbar.setVisibility(View.VISIBLE);
                    tvStatus.setText("正在加载中...");
                    isClickable = false;
                    break;
            }
        }
    }

    private OnRetryListener mOnRetryListener;

    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.mOnRetryListener = onRetryListener;
    }

    public interface OnRetryListener {
        void onRetry();
    }
}