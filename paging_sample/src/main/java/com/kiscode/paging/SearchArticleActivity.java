package com.kiscode.paging;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiscode.paging.adapter.ArticleAdapter;
import com.kiscode.paging.databinding.ActivitySearchArticleBinding;
import com.kiscode.paging.model.pojo.Article;
import com.kiscode.paging.viewmodel.ArticleViewModel;

public class SearchArticleActivity extends BaseActionBarActivity {

    private ActivitySearchArticleBinding binding;
    private ArticleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_article);

        getSupportActionBar().setTitle(R.string.title_mock_net_with_search);

        initRecyclerView(binding.recyclerView);

        initViewModel();
    }

    private void initSearchView(EditText searchView) {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticleAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void initViewModel() {
        ArticleViewModel articleViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel.class);
        binding.setViewmodel(articleViewModel);

        articleViewModel.pagedListLiveData.observe(this, new Observer<PagedList<Article>>() {
            @Override
            public void onChanged(PagedList<Article> articles) {
                adapter.submitList(articles);
            }
        });
    }


}