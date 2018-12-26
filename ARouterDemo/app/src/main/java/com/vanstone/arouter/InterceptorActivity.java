package com.vanstone.arouter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.vanstone.arouter.base.BaseActivity;
import com.vanstone.arouter.util.Constance;

/**
 * @author AsiaLYF
 * @date 2018/12/26
 */
@Route(path = Constance.ACTIVITY_URL_INTERCEPTOR)
public class InterceptorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptor);
    }
}
