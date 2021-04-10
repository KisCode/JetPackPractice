package com.kiscode.paging.model.datasoure;


import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.kiscode.paging.model.pojo.Article;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/10 20:21
 */

public class ArticleDataSourceFactory extends DataSource.Factory<Integer, Article> {
    public MutableLiveData<ArticleDataSource> dataSourceLiveData = new MutableLiveData<>();
    private final ObservableField<String> title;

    public ArticleDataSourceFactory(ObservableField<String> title) {
        this.title = title;
    }

    @NonNull
    @Override
    public DataSource<Integer, Article> create() {
        ArticleDataSource dataSource = new ArticleDataSource(title);
        dataSourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
