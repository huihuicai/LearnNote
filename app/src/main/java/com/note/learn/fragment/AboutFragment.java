package com.note.learn.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.note.learn.R;

/**
 * Created by wanghui on 2016/3/31.
 */
public class AboutFragment extends Fragment {

    private TextView mTvVersion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        mTvVersion = (TextView) view.findViewById(R.id.tv_version_code);
        return view;
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
            mTvVersion.setText("当前版本v" + versionName);
        }
    }
}
