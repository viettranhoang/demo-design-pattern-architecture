package com.vit.demodesignpatternarchitecture.ui.user.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vit.demodesignpatternarchitecture.R;
import com.vit.demodesignpatternarchitecture.data.model.User;
import com.vit.demodesignpatternarchitecture.ui.base.BaseViewHolder;
import com.vit.demodesignpatternarchitecture.ui.user.listener.OnClickUserItemListener;
import com.vit.demodesignpatternarchitecture.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Demo Adapter Design Pattern
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mUsers = new ArrayList<>();

    private final OnClickUserItemListener mListener;

    public UserAdapter(OnClickUserItemListener listener) {
        mListener = listener;
    }

    public void setList(List<User> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.bindData(mUsers.get(i));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class UserViewHolder extends BaseViewHolder<User> {

        @BindView(R.id.image_avatar)
        ImageView mImageAvatar;

        @BindView(R.id.text_name)
        TextView mTextName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(User user) {
            mTextName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
            GlideApp.with(itemView.getContext())
                    .load(user.getAvatar())
                    .circleCrop()
                    .into(mImageAvatar);
        }

        @OnClick(R.id.layout_root)
        void onClickItem() {
            mListener.onClickUser(mUsers.get(getAdapterPosition()));
        }
    }
}
