package com.vanstone.arouter.interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.vanstone.arouter.util.Constance;

/**
 * @author AsiaLYF
 * @date 2018/12/21
 */

@Interceptor(priority = 7)
public class IUseSecondInterceptor implements IInterceptor {
    /**
     * 数值越小 优先级越大，定义多个拦截器，数值不能一样
     */
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String name = Thread.currentThread().getName();
        Log.d("lyf", "===IUseSecondInterceptor 拦截器开始执行,线程名称：" + name);
        String path = postcard.getPath();
        Log.d("lyf", "===IUseSecondInterceptor 拦截器 process===" + path);
        if (Constance.USEIINTERCEPTOR) {
//            允许放行
            callback.onContinue(postcard);
        } else {
//            终止路由
            callback.onInterrupt(null);
        }
    }

    @Override
    public void init(Context context) {
        Log.d("lyf", "===IUseSecondInterceptor 拦截器 init===");
    }
}
