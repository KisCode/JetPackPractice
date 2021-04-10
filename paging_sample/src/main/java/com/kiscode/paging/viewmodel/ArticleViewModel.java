package com.kiscode.paging.viewmodel;


import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kiscode.paging.model.datasoure.ArticleDataSourceFactory;
import com.kiscode.paging.model.pojo.Article;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/10 19:37
 */

public class ArticleViewModel extends ViewModel {
    ArticleDataSourceFactory factory;
    public final ObservableField<String> title;
    public LiveData<PagedList<Article>> pagedListLiveData;

    public ArticleViewModel() {
        title = new ObservableField<>();

        factory = new ArticleDataSourceFactory(title);
        pagedListLiveData = new LivePagedListBuilder<>(factory, 100).build();

    }

    public void doQuery() {
        factory.dataSourceLiveData.getValue().reQuery();
    }

    public void resetQuery() {
        title.set("");
        factory.dataSourceLiveData.getValue().reQuery();
    }
}
