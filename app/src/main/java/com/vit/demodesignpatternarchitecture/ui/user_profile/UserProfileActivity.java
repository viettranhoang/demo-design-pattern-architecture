package com.vit.demodesignpatternarchitecture.ui.user_profile;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.vit.demodesignpatternarchitecture.R;
import com.vit.demodesignpatternarchitecture.data.model.User;
import com.vit.demodesignpatternarchitecture.ui.Injection;
import com.vit.demodesignpatternarchitecture.ui.base.BaseActivity;
import com.vit.demodesignpatternarchitecture.util.GlideApp;

import butterknife.BindView;

public class UserProfileActivity extends BaseActivity implements UserProfileContract.View{

    static final String EXTRA_USER_ID = "EXTRA_USER_ID";

    public static void moveUserProfileActivity(Activity activity, int userId) {
        Intent intent = new Intent(activity, UserProfileActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        activity.startActivity(intent);
    }

    @BindView(R.id.image_avatar)
    ImageView mImageAvatar;

    @BindView(R.id.text_name)
    TextView mTextName;

    private int mUserId;

    private UserProfileContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.user_profile_activity;
    }

    @Override
    protected void initView() {
        mPresenter = Injection.provideUserProfilePresenter(this);

        mUserId = getIntent().getIntExtra(EXTRA_USER_ID, -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mUserId != -1) {
            mPresenter.loadUser(mUserId);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }

    @Override
    public void showUserProfile(User user) {
        mTextName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        GlideApp.with(this)
                .load(user.getAvatar())
                .circleCrop()
                .into(mImageAvatar);
    }

    @Override
    public void showError(Throwable throwable) {
        showToast(throwable.getMessage());
        Log.e(TAG, "showError: ", throwable);
    }
}
