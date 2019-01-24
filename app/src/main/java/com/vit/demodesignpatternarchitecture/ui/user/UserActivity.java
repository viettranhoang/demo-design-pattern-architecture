package com.vit.demodesignpatternarchitecture.ui.user;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vit.demodesignpatternarchitecture.R;
import com.vit.demodesignpatternarchitecture.data.model.User;
import com.vit.demodesignpatternarchitecture.ui.Injection;
import com.vit.demodesignpatternarchitecture.ui.base.BaseActivity;
import com.vit.demodesignpatternarchitecture.ui.user.adapter.UserAdapter;
import com.vit.demodesignpatternarchitecture.ui.user.listener.OnClickUserItemListener;
import com.vit.demodesignpatternarchitecture.ui.user_profile.UserProfileActivity;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserActivity extends BaseActivity implements OnClickUserItemListener {

    @BindView(R.id.list_user)
    RecyclerView mRcvUsers;

    private UserAdapter mAdapter;

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

        mAdapter = new UserAdapter(this);

        initRcv();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDisposable.add(mUserViewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> mAdapter.setList(users),
                        throwable -> Log.e(TAG, "Error: ", throwable)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    @Override
    public void onClickUser(User user) {
        Log.e(TAG, "onClickUser: " + user.toString());
        UserProfileActivity.moveUserProfileActivity(UserActivity.this, user.getId());
    }

    private void initRcv() {
        mRcvUsers.setLayoutManager(new LinearLayoutManager(this));
        mRcvUsers.setHasFixedSize(true);
        mRcvUsers.setItemAnimator(new DefaultItemAnimator());
        mRcvUsers.setAdapter(mAdapter);
    }


}
