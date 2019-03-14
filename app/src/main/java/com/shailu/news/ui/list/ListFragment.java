package com.shailu.news.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import com.shailu.news.R;
import com.shailu.news.base.BaseFragment;
import com.shailu.news.data.model.Article;
import com.shailu.news.ui.WebViewActivity;
import com.shailu.news.util.MySharedPreference;
import com.shailu.news.util.ViewModelFactory;

public class ListFragment extends BaseFragment implements ArticleSelectedListener {

    @BindView(R.id.recyclerView) RecyclerView listView;
    @BindView(R.id.tv_error) TextView errorTextView;
    @BindView(R.id.loading_view) View loadingView;

    @Inject ViewModelFactory viewModelFactory;
    private TopHeadlinesViewModel viewModel;


    @Inject
    MySharedPreference mySharedPreference;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopHeadlinesViewModel.class);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listView.setLayoutManager(linearVertical);
        listView.setAdapter(new TopHeadlinesListAdapter(getContext(), viewModel, this, this));

        observableViewModel();
    }

    public void updateData(){
        viewModel.updateData();
    }

    @Override
    public void onRepoSelected(Article repo) {
      Intent intent = new Intent(getActivity(), WebViewActivity.class);
      intent.putExtra("LOADING_URL", repo.getUrl());
      startActivity(intent);
    }

    private void observableViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if(repos != null) listView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if(isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            }else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }
}
