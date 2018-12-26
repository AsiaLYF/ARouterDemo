package com.vanstone.arouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.vanstone.arouter.base.BaseActivity;
import com.vanstone.arouter.bean.Person;
import com.vanstone.arouter.util.Constance;

/**
 * @author AsiaLYF
 * @date 2018/12/15
 */

/**
 * 在支持路由的页面上添加注解(必选)
 * 这里的路径需要注意的是至少需要有两级，/xx/xx
 */
@Route(path = Constance.ACTIVITY_URL_SECOND)
public class SecondActivity extends BaseActivity implements View.OnClickListener {

    private Button btnBackData;
    private TextView tvShowInfo;
    @Autowired
    String name;
    @Autowired
    int price;
    /**
     * 只有当@Autowired(name = "people")，也就是key标签一致的情况下，才可以获取到对象的值，如果不写标签名，结果会为null
     */
    @Autowired(name = "people")
    Person mPerson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btnBackData = findViewById(R.id.btn_back_result);
        tvShowInfo = findViewById(R.id.tv_show_info);
        btnBackData.setOnClickListener(this);

        tvShowInfo.setText("显示信息：name=" + name + ",price=" + price + ",people=" + mPerson);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_result:
                Intent intent = new Intent();
                intent.putExtra("result", "我是返回数据");
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }
}
