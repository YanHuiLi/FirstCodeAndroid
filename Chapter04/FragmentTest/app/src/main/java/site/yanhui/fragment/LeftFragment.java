package site.yanhui.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.yanhui.fragmenttest.R;

/**
 * Created by Archer on 2017/8/21.
 *
 * 使用fragment
 */

public class LeftFragment extends Fragment {

    //重写OncreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.left_fragment, container, false);//使用infater解析到Xml文件
        return inflate;
    }
}
