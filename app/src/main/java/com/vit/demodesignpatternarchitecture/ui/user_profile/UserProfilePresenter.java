package com.vit.demodesignpatternarchitecture.ui.user_profile;

import com.vit.demodesignpatternarchitecture.data.UserRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Demo MVP Architecture
 */

public class UserProfilePresenter implements UserProfileContract.Presenter{

    private final UserRepository mRepository;

    private final UserProfileContract.View mView;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public UserProfilePresenter(UserRepository repository, UserProfileContract.View view) {
        this.mRepository = repository;
        this.mView = view;
    }

    @Override
    public void loadUser(int id) {
        mDisposable.add(mRepository.getUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> mView.showUserProfile(user),
                        throwable -> mView.showError(throwable)));
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mDisposable.clear();
    }
}
