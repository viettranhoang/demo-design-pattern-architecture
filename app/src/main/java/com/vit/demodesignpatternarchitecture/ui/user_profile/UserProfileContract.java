package com.vit.demodesignpatternarchitecture.ui.user_profile;

import com.vit.demodesignpatternarchitecture.data.model.User;
import com.vit.demodesignpatternarchitecture.ui.base.BasePresenter;
import com.vit.demodesignpatternarchitecture.ui.base.BaseView;

public interface UserProfileContract {

    interface View extends BaseView<Presenter> {

        void showUserProfile(User user);

        void showError(Throwable throwable);
    }

    interface Presenter extends BasePresenter {

        void loadUser(int id);
    }
}
