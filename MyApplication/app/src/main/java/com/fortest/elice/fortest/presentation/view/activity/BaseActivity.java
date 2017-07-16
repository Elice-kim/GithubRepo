package com.fortest.elice.fortest.presentation.view.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fortest.elice.fortest.R;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    boolean dialogVisible;
    private AlertDialog loadingDialog;

    public void showLoading() {
        hideLoading();
        dialogVisible = true;
        buildNewLoadingDialog().show();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void hideLoading() {
        if (isDialogShowing()) {
            dialogVisible = false;
            try {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
            } catch (IllegalArgumentException ignored) {
                Log.e(TAG, ignored.getMessage().toString());
            }
        }
    }

    boolean isDialogShowing() {
        return dialogVisible;
    }

    AlertDialog buildNewLoadingDialog() {
        loadingDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .create();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return loadingDialog;
    }
}
