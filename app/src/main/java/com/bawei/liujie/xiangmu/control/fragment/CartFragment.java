package com.bawei.liujie.xiangmu.control.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.Test;
import com.bawei.liujie.xiangmu.view.adapter.ShoppingCartAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/18
 */

public class CartFragment extends Fragment {

    private List<Test> data;
    private ListView mListView;
    private ShoppingCartAdapter adapter;

    private LinearLayout clear;
    private CheckBox checkBox_select_all;
    private CheckBox checkBox_add;
    private TextView integral_sum;
    private int sum = 0;
    private int[] sumIntegral;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cart_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkBox_add = (CheckBox) view.findViewById(R.id.checkbox_add);
        integral_sum = (TextView) view.findViewById(R.id.integral_sum);
        clear = (LinearLayout) view.findViewById(R.id.clear);
        checkBox_select_all = (CheckBox) view.findViewById(R.id.checkbox_select);
        mListView = (ListView) view.findViewById(R.id.finance_list);
    }

    private void initData() {
        data = new ArrayList<Test>();
        // 测试数据
        for (int i = 0; i < 20; i++) {
            Test test = new Test("id" + i, "color" + i, "type" + i, "100" + i);
            data.add(test);
        }
        adapter = new ShoppingCartAdapter(getContext(), handler, data);
        sumIntegral = new int[data.size() + 1];
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                adapter.notifyDataSetChanged();
                integral_sum.setText(0 + "");
                checkBox_select_all.setChecked(false);
                checkBox_add.setClickable(false);
            }
        });

        checkBox_select_all.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                HashMap<Integer, Boolean> isSelected = ShoppingCartAdapter
                        .getIsSelected();
                Iterator iterator = isSelected.entrySet().iterator();
                List<Boolean> array = new ArrayList<Boolean>();//列表中checkbox选中状态
                List<Integer> nums = new ArrayList<Integer>();//列表中商品数量
                while (iterator.hasNext()) {
                    HashMap.Entry entry = (HashMap.Entry) iterator.next();
                    Integer key = (Integer) entry.getKey();
                    Boolean val = (Boolean) entry.getValue();
                    array.add(val);
                }
                for (int i = 0; i < data.size(); i++) {
                    int num = data.get(i).getNum();
                    int integral = Integer.valueOf(data.get(i).getIntegral());
                    nums.add(num);
                }
                if (checkBox_select_all.isChecked()) {
                    for (int i = 0; i < data.size(); i++) {
                        ShoppingCartAdapter.getIsSelected().put(i, true);
                    }
                    checkBox_add.setChecked(true);
                    adapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        ShoppingCartAdapter.getIsSelected().put(i, false);
                    }
                    checkBox_add.setChecked(false);
                    adapter.notifyDataSetChanged();
                    integral_sum.setText(0 + "");
                }

            }
        });

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(context, position + "", Toast.LENGTH_LONG)
                        .show();
                int pos = position - 1;
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                int num = data.get(pos).getNum();
                if (num == 0) {
                    Toast.makeText(context, "请选择商品数量", Toast.LENGTH_LONG).show();
                } else {
                    boolean cu = !ShoppingCartAdapter.getIsSelected().get(pos);
                    ShoppingCartAdapter.getIsSelected().put(pos, cu);
                    adapter.notifyDataSetChanged();
                    //遍历获取列表中checkbox的选中状态
                    HashMap<Integer, Boolean> isSelected = ShoppingCartAdapter.getIsSelected();
                    Iterator iterator = isSelected.entrySet().iterator();
                    List<Boolean> array = new ArrayList<Boolean>();
                    while (iterator.hasNext()) {
                        HashMap.Entry entry = (HashMap.Entry) iterator.next();
                        Integer key = (Integer) entry.getKey();
                        Boolean val = (Boolean) entry.getValue();
                        array.add(val);
                    }
                }
            }
        });

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) { //更改选中商品的总价格
                float price = (Float) msg.obj;
                if (price > 0) {
                    integral_sum.setText(price + "");
                } else {
                    integral_sum.setText("0");
                }
            } else if (msg.what == 11) {
                //列表选中状态
                List<Boolean> array = (List<Boolean>) msg.obj;
            }
        }
    };

    class ViewHolder { // 自定义控件集合
        public CheckBox ck_select;
        public ImageView pic_goods;
        public TextView id_goods;
        public TextView color_goods;
        public TextView type_goods;
        public TextView integral_goods;
        public LinearLayout layout;
        public TextView number;
        public Button minus;
        public Button plus;
    }
}
