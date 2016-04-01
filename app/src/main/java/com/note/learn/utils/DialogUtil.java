package com.note.learn.utils;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.note.learn.R;

/**
 * Created by wanghui on 2016/4/1.
 */
public class DialogUtil {

    public interface OnDialogClickListener {
        void onclick();
    }

    public static void shareDailog(Activity context, final OnDialogClickListener listener) {
        final Dialog dialog = new Dialog(context, R.style.dialog_style);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
        view.findViewById(R.id.fl_share_moment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onclick();
            }
        });

        view.findViewById(R.id.fl_share_weichat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onclick();
            }
        });

        view.findViewById(R.id.fl_share_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onclick();
            }
        });

        view.findViewById(R.id.fl_share_qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onclick();
            }
        });
        dialog.setContentView(view);
        int screenWidth = ScreenUtil.getScreenWidth(context);
        int screenHeight = ScreenUtil.getScreenHeight(context);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (screenWidth * 0.7);
        p.height = (int) (screenHeight * 0.6);
        dialogWindow.setAttributes(p);
        dialogWindow.setWindowAnimations(R.style.dialog_bottom_style);
        if (!context.isFinishing()) {
            dialog.show();
        }
    }
}
