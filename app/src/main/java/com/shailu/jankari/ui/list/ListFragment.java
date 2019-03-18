package com.shailu.jankari.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.shailu.jankari.R;
import com.shailu.jankari.base.BaseFragment;
import com.shailu.jankari.data.model.Article;
import com.shailu.jankari.ui.WebViewActivity;
import com.shailu.jankari.util.MySharedPreference;
import com.shailu.jankari.util.ViewModelFactory;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ListFragment extends BaseFragment implements ArticleSelectedListener {

    @BindView(R.id.recyclerView) RecyclerView listView;
    @BindView(R.id.error_view) View errorView;
    @BindView(R.id.loading_view) View loadingView;

    @Inject ViewModelFactory viewModelFactory;
    private TopHeadlinesViewModel viewModel;


    @Inject
    MySharedPreference mySharedPreference;

    private static final String ARGS_MEWS_CATEGORY = "NEWS_CATEGORY";

    private String category;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }


    public static ListFragment newInstance(String newsCategory){
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_MEWS_CATEGORY, newsCategory);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopHeadlinesViewModel.class);
        LinearLayoutManager linearVertical = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listView.setLayoutManager(linearVertical);
        listView.setAdapter(new TopHeadlinesListAdapter(getContext(), viewModel, this, this));
        observableViewModel();
        category = getArguments().getString(ARGS_MEWS_CATEGORY);
        viewModel.getNewsData(category);
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
                errorView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }else {
                errorView.setVisibility(View.GONE);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick(R.id.btn_error)
    public void retry(){
      viewModel.getNewsData(category);
    }
}
