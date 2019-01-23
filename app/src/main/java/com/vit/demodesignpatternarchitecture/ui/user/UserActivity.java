package com.vit.demodesignpatternarchitecture.ui.user;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vit.demodesignpatternarchitecture.R;
import com.vit.demodesignpatternarchitecture.ui.Injection;
import com.vit.demodesignpatternarchitecture.ui.base.BaseActivity;
import com.vit.demodesignpatternarchitecture.ui.user.adapter.UserAdapter;

import butterknife.BindView;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserActivity extends BaseActivity {

    @BindView(R.id.list_user)
    RecyclerView mRcvUsers;

    UserAdapter adapter;

    private ViewModelFactory mViewModelFactory;

    private UserViewModel mUserViewModel;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected int getLayoutId() {
        return R.layout.user_activity;
    }

    @Override
    protected void initView() {
        mViewModelFactory = Injection.provideViewModelFactory(this);
        mUserViewModel = ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel.class);

        adapter = new UserAdapter();
        initRcv();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDisposable.add(mUserViewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> adapter.setList(users),
                        throwable -> Log.e(TAG, "Error: ", throwable)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void initRcv() {
        mRcvUsers.setLayoutManager(new LinearLayoutManager(this));
        mRcvUsers.setHasFixedSize(true);
        mRcvUsers.setItemAnimator(new DefaultItemAnimator());
        mRcvUsers.setAdapter(adapter);
    }
}
