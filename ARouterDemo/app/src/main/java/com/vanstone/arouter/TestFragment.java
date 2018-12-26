package com.vanstone.arouter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.vanstone.arouter.util.Constance;

/**
 * @author AsiaLYF
 * @date 2018/12/15
 */
@Route(path = Constance.ACTIVITY_URL_FRAGMENT)
public class TestFragment extends Fragment {
    private TextView tvMsg;
    @Autowired()
    String info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(TestFragment.this);
        View view = inflater.inflate(R.layout.fragment_test, null);
        tvMsg = view.findViewById(R.id.tv_show_msg);
        tvMsg.setText(info);
        return view;
    }
}
