package com.kiscode.jetpack.navigation.withbar.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kiscode.jetpack.navigation.R;

/**
 * Description: Fragment中包含Webview由于在navigation管理下的fragment在每次页面跳转时
 * 会重复的createView 导致产生多个webview对象造成内存占用过高 致使页面卡顿，因此需要每次onDestroyView主动销毁webview
 * Author: keno
 * Date : 2022/8/17 13:13
 **/
public class WebviewFragment extends Fragment {
    private static final String TAG_LIFE = "tag_life";
    private final String WEB_CONTENT = "<!DOCTYPE html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "<body>\n" +
            "    <p style=\"font-size: 15vw;text-align: center;\">%s</p>\n" +
            "</body>";
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        initWebview(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG_LIFE, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG_LIFE, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG_LIFE, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG_LIFE, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG_LIFE, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG_LIFE, "onStop");
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG_LIFE, "onDestroyView");
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG_LIFE, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG_LIFE, "onDetach");
    }

    private void initWebview(View view) {
        webView = view.findViewById(R.id.webview);
        load("一二三四");
    }

    public void load(String title) {
        String data = String.format(WEB_CONTENT, title);
        webView.loadData(data, "text/html", "utf-8");
    }
}