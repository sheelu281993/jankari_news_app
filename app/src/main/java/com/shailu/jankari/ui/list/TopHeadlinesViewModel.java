package com.shailu.jankari.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import com.shailu.jankari.data.model.Article;
import com.shailu.jankari.data.model.TopHeadlines;
import com.shailu.jankari.data.rest.TopHeadlinesRepository;

public class TopHeadlinesViewModel extends ViewModel {

    private final TopHeadlinesRepository topHeadlinesRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Article>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public TopHeadlinesViewModel(TopHeadlinesRepository topHeadlinesRepository) {
        this.topHeadlinesRepository = topHeadlinesRepository;
        disposable = new CompositeDisposable();
    }

    LiveData<List<Article>> getRepos() {
        return repos;
    }
    LiveData<Boolean> getError() {
        return repoLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    public void getNewsData(String category) {
        loading.setValue(true);
        disposable.add(topHeadlinesRepository.getTopHeadlines(category).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<TopHeadlines>() {
                    @Override
                    public void onSuccess(TopHeadlines value) {
                        repoLoadError.setValue(false);
                        repos.setValue(value.getArticles());
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
