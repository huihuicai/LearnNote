package com.note.learn.utils;

import android.app.Dialog;
import android.content.Context;

import com.note.learn.R;

/**
 * Created by wanghui on 2016/4/22.
 */
public class ProgressDialogUtil {

    private static Dialog mLoadingDialog;

    public static void showProgressDialog(Context context) {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            mLoadingDialog = new Dialog(context, android.R.style.Theme_Holo_Dialog_NoActionBar);
            mLoadingDialog.setContentView(R.layout.activity_progress_dialog);
            mLoadingDialog.show();
        }
    }

    public static void cancleProgressDialog(Context context) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
