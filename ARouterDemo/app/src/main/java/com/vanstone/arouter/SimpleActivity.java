package com.vanstone.arouter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vanstone.arouter.base.BaseActivity;
import com.vanstone.arouter.util.Constance;

/**
 * @author AsiaLYF
 * @date 2018/12/21
 */
@Route(path = Constance.ACTIVITY_URL_SIMPLE)
public class SimpleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        TestFragment testFragment = (TestFragment) ARouter.getInstance().build(Constance.ACTIVITY_URL_FRAGMENT)
                .withString("info", "从SimpleActivity跳转过来的").navigation();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.ll, testFragment);
        transaction.commit();
    }
}
