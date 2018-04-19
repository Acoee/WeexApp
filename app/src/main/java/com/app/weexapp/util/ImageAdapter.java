package com.app.weexapp.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String url, ImageView imageView, WXImageQuality wxImageQuality, WXImageStrategy wxImageStrategy) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
