package com.note.learn.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.note.learn.R;

import butterknife.BindView;

/**
 * Created by wanghui on 2016/3/31.
 */
public class AboutFragment extends BaseFragment {

    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    @Override
    protected int getLayout() {
        return R.layout.fragment_about;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showVersion();
    }

    private void showVersion() {
        PackageManager packageManager = getActivity().getPackageManager();
        String packageName = getActivity().getPackageName();
        int flags = 0;
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (packageInfo != null) {
            String versionName = packageInfo.versionName;
            tvVersionCode.setText("当前版本v" + versionName);
        }
    }
}
