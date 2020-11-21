package com.ahba1.geographylearningapp.mvp;

import android.content.Context;

public interface BaseView<T> {
    void setPresenter(T presenter);
    Context getContext();
}
