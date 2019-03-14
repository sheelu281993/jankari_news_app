package com.shailu.news.ui.list;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shailu.news.R;
import com.shailu.news.data.model.Article;
import com.shailu.news.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopHeadlinesListAdapter extends RecyclerView.Adapter<TopHeadlinesListAdapter.RepoViewHolder>{

    private ArticleSelectedListener articleSelectedListener;
    private final List<Article> data = new ArrayList<>();
    private static Context context;


    TopHeadlinesListAdapter(Context context, TopHeadlinesViewModel viewModel, LifecycleOwner lifecycleOwner, ArticleSelectedListener articleSelectedListener) {
        this.context = context;
        this.articleSelectedListener = articleSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_news_list_item, parent, false);
        return new RepoViewHolder(view, articleSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_article_source) TextView articleSourceTextView;
        @BindView(R.id.tv_article_title) TextView articleTitleTextView;
        @BindView(R.id.tv_article_description) TextView articleDescriptionTextView;
        @BindView(R.id.iv_article_bg) ImageView articleBg;

        private Article article;

        RepoViewHolder(View itemView, ArticleSelectedListener articleSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if(article != null) {
                    articleSelectedListener.onRepoSelected(article);
                }
            });
        }

        void bind(Article article) {
            this.article = article;

            articleSourceTextView.setText(article.getSource().getName());
            articleTitleTextView.setText(article.getTitle());
            if(article.getDescription() != null) {
                String styledText = "<font color='#add8e6'>  Read More</font>.";
                articleDescriptionTextView.setText(Html.fromHtml(article.getDescription() + styledText));
            }
            GlideApp.with(context)
                    .asBitmap()
                    .load(article.getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.cardview_light_background)
                    .into(articleBg);
        }
    }
}
