package com.kiscode.paging.model.datasoure;


import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.paging.PageKeyedDataSource;

import com.kiscode.paging.model.mock.MockApi;
import com.kiscode.paging.model.pojo.Article;

import java.util.List;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/10 20:09
 */

public class ArticleDataSource extends PageKeyedDataSource<Integer, Article> {
    private final ObservableField<String> title;


    private static final int PAGE_INNITIAL = 1;
    private static final int PAGE_SIZE = 10;

    public ArticleDataSource(ObservableField<String> title) {
        this.title = title;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Article> callback) {
        String searchTitle = TextUtils.isEmpty(title.get()) ? "" : title.get();

        MockApi.loadArticleList(PAGE_INNITIAL, PAGE_SIZE, searchTitle, new MockApi.OnLoadArticleListener() {
            @Override
            public void onLoad(List<Article> articleList) {
                callback.onResult(articleList, null, PAGE_INNITIAL + 1);
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {
        String searchTitle = TextUtils.isEmpty(title.get()) ? "" : title.get();
        MockApi.loadArticleList(params.key, PAGE_SIZE, searchTitle, new MockApi.OnLoadArticleListener() {
            @Override
            public void onLoad(List<Article> articleList) {
                callback.onResult(articleList, params.key + 1);
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }

    public void reQuery() {
        invalidate();
    }

}
