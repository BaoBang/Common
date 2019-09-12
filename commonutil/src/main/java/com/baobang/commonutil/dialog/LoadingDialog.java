package com.baobang.commonutil.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.baobang.commonutil.R;
import com.tuyenmonkey.mkloader.MKLoader;

public class LoadingDialog extends DialogFragment {

    private boolean statusAppear = false;
    private MKLoader mkLoader;
    private int mColor;

    public static LoadingDialog getInstance(int color) {
        return new LoadingDialog(color);
    }

    public LoadingDialog(int color) {
        this.mColor = color;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.loading_dialog, container, false);

        mkLoader = view.findViewById(R.id.mk_loader);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setCancelable(false);
        setColor(mColor);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!statusAppear) {
            super.show(manager, tag);
            statusAppear = true;
        }
    }

    @Override
    public void dismiss() {
        if (statusAppear) {
            super.dismiss();
            statusAppear = false;
        }
    }

    public void setColor(int color) {
        if (mkLoader != null) {
            mkLoader.setBackgroundColor(color);
        }
    }
}
