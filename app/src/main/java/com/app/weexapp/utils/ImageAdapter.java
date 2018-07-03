package com.app.weexapp.utils;

import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

import cn.com.acoe.weexapp.app.R;

public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url, ImageView imageView, WXImageQuality wxImageQuality, WXImageStrategy wxImageStrategy) {
        DrawableTypeRequest<String> loadRequest = Glide.with(imageView.getContext()).load(url);
        if (wxImageStrategy != null && wxImageStrategy.placeHolder != null) { // 如果设置有展位图，则展位图和加载失败都用展位图来显示
            int resId = 0;
            try {
                resId = R.drawable.class.getField(wxImageStrategy.placeHolder.replace("local:///", "")).getInt(null);
            } catch (Exception e) {
            }
            if (resId != 0) {
                loadRequest.placeholder(resId).error(resId);
            }
        }
        loadRequest.into(imageView);
    }
}
