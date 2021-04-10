package com.kiscode.paging.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.paging.R;
import com.kiscode.paging.model.pojo.Article;

import java.util.Objects;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/10 19:51
 */

public class ArticleAdapter extends PagedListAdapter<Article, ArticleAdapter.ArticleViewHolder> {

    public ArticleAdapter() {
        super(new DiffUtil.ItemCallback<Article>() {
            @Override
            public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
                return Objects.equals(oldItem.getTitle(), newItem.getTitle());
            }
        });
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = getItem(position);
        if (article == null) {
            return;
        }

        holder.tvId.setText(String.valueOf(article.getId()));
        holder.tvTitle.setText(article.getTitle());

    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvTitle;


        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
