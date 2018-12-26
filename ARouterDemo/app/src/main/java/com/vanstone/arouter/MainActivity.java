package com.vanstone.arouter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vanstone.arouter.base.BaseActivity;
import com.vanstone.arouter.bean.Person;
import com.vanstone.arouter.util.Constance;

/**
 * @author user
 */
@Route(path = Constance.ACTIVITY_URL_MAIN,group = Constance.GROUP_FIRST)
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btn, btnResult, btnListener, btnParams, btnAnim, btnUri, btnFragment, btnInterceptor,btnGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btnResult = findViewById(R.id.btn_result);
        btnListener = findViewById(R.id.btn_listener);
        btnParams = findViewById(R.id.btn_params);
        btnAnim = findViewById(R.id.btn_anim);
        btnUri = findViewById(R.id.btn_uri);
        btnFragment = findViewById(R.id.btn_fragment);
        btnInterceptor = findViewById(R.id.btn_interceptor);
        btnGroup = findViewById(R.id.btn_group);


        btn.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        btnListener.setOnClickListener(this);
        btnParams.setOnClickListener(this);
        btnAnim.setOnClickListener(this);
        btnUri.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
        btnInterceptor.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                //发起路由跳转
                ARouter.getInstance().build(Constance.ACTIVITY_URL_SECOND).navigation();
                break;
            case R.id.btn_result:
                //相当于startActivityForResult()
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_SECOND)
                        .navigation(this, 100);
                break;
            case R.id.btn_listener:
                ARouter.getInstance().build(Constance.ACTIVITY_URL_SECOND)
                        .navigation(this, new NavCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                Log.d(TAG, "onFound:" + "找到了");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                super.onLost(postcard);
                                Log.d(TAG, "onLost:" + "找不到了");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.d(TAG, "onArrival:" + "跳转完了");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                super.onInterrupt(postcard);
                                Log.d(TAG, "onInterrupt:" + "被拦截了");
                            }
                        });
                break;
            case R.id.btn_params:
                //支持基本数据类型、bundle 数组类型 Parcelable对象 跳转动画
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_SECOND)
                        .withString("name", "android")
                        .withInt("price", 30)
                        .withParcelable("people", new Person("AsiaLYF", 28))
                        .navigation();
                break;
            case R.id.btn_anim:
                //跳转动画
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_SECOND)
                        .withTransition(R.anim.anim_in, R.anim.anim_out)
                        .navigation();
                break;
            case R.id.btn_uri:
                //URI跳转
                Uri uri = Uri.parse(Constance.ACTIVITY_URL_SECOND);
                ARouter.getInstance()
                        .build(uri)
                        .navigation();
                break;
            case R.id.btn_fragment:
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_SIMPLE)
                        .navigation();

                break;
            case R.id.btn_interceptor:
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_INTERCEPTOR)
                        .navigation(this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                String group = postcard.getGroup();
                                String path = postcard.getPath();
                                Log.d("lyf", "onFound===group:" + group + "===path:" + path);
                            }

                            @Override
                            public void onLost(Postcard postcard) {

                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.d("lyf", "===onArrival===");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {

                            }
                        });

                break;
            case R.id.btn_group:
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_GROUP,Constance.GROUP_FIRST)
                        .navigation();

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            Log.d(TAG, "result:" + result);
        }

    }
}
