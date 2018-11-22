package com.example.windows.ijingdong.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Annotation;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.windows.ijingdong.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoundFragment extends Fragment {

    @BindView(R.id.image_found)
    ImageView imageFound;
    @BindView(R.id.et_text_erweima)
    EditText etTextErweima;
    @BindView(R.id.btn_logo)
    Button btnLogo;
    @BindView(R.id.btn_nologo)
    Button btnNologo;
    @BindView(R.id.image_logo)
    ImageView imageLogo;
    private AnimatorSet animatorSet;
    private Bitmap mBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_found, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //横向
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageFound, "translationX", 0, 670);
        //纵向
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageFound, "translationY", 0, 1120);
        //透明度
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageFound, "alpha", 1, 0, 1);
        //旋转
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageFound, "rotation", 0f, 360f, 0f);
        //缩放
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageFound, "scaleX", 1f, 4f, 1f);
        animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(animator1).with(animator3).with(animator2).with(animator4);
        animatorSet.setDuration(10000);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.image_found)
    public void onViewClicked() {
        animatorSet.start();
    }

    @OnClick({R.id.btn_logo, R.id.btn_nologo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_logo:
                String textContent = etTextErweima.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(getActivity(), "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                etTextErweima.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                imageLogo.setImageBitmap(mBitmap);
                break;
            case R.id.btn_nologo:
                String textContent1 = etTextErweima.getText().toString();
                if (TextUtils.isEmpty(textContent1)) {
                    Toast.makeText(getActivity(), "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                etTextErweima.setText("");
                mBitmap = CodeUtils.createImage(textContent1, 400, 400, null);
                imageLogo.setImageBitmap(mBitmap);
                break;
        }
    }
}
