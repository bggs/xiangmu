package com.bawei.liujie.xiangmu.control.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.FirstData;
import com.bawei.liujie.xiangmu.modle.bean.SecondData;
import com.bawei.liujie.xiangmu.modle.util.HttpUtils;
import com.bawei.liujie.xiangmu.view.adapter.MyAdapter;
import com.bawei.liujie.xiangmu.view.adapter.SecondAdapter;
import com.bawei.liujie.xiangmu.view.application.NetDataCallBack;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/18
 */

public class WeitaoFragment extends Fragment {
    private String url = "http://169.254.53.97/mobile/index.php?act=goods_class";
    private String Secondurl = "http://169.254.53.97/mobile/index.php?act=goods_class";
    private ListView mListview,mSecondListview;
    private List<FirstData.DatasBean.ClassListBean> class_list = new ArrayList<>();
    private TextView mTextview;
    HttpUtils utils = new HttpUtils();
    private List<SecondData.DatasBean.ClassListBean> second_list=new ArrayList<>();
    private ImageView mScan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weitao_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        utils.LoadDataFromServer(url, new NetDataCallBack() {
            @Override
            public void callback(Object o) {
                FirstData first = (FirstData) o;
                class_list = first.getDatas().getClass_list();
                MyAdapter  adapter = new MyAdapter(getContext(), class_list);
                mListview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void error(String message, int code) {

            }
        }, FirstData.class);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                utils.LoadDataFromServer(Secondurl+ "&gc_id=" +class_list.get(position).getGc_id(), new NetDataCallBack() {
                    @Override
                    public void callback(Object o) {
                        SecondData secondData = (SecondData) o;
                        second_list = secondData.getDatas().getClass_list();
                        SecondAdapter adapter=new SecondAdapter(getContext(),second_list,position);
                        mSecondListview.setAdapter(adapter);
                    }

                    @Override
                    public void error(String message, int code) {

                    }
                }, SecondData.class);
            }
        });

        mScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListview = (ListView) view.findViewById(R.id.listview);
        mSecondListview = (ListView) view.findViewById(R.id.secondListview);
        mTextview = (TextView) view.findViewById(R.id.textview);
        mScan = (ImageView) view.findViewById(R.id.scan);
    }
}