package com.bawei.liujie.xiangmu.control.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.GoodsData;
import com.bawei.liujie.xiangmu.modle.util.HttpUtils;
import com.bawei.liujie.xiangmu.view.adapter.GoodsAdapter;
import com.bawei.liujie.xiangmu.view.application.NetDataCallBack;

import java.util.ArrayList;
import java.util.List;

import static com.bawei.liujie.xiangmu.R.id.pop_person;
import static com.bawei.liujie.xiangmu.R.id.pop_together;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/21
 */

public class GoodsActivity extends AppCompatActivity implements View.OnClickListener {
    private String url = "http://169.254.53.97/mobile/index.php?act=goods&op=goods_list&page=100";
    private List<GoodsData.DatasBean.GoodsListBean> goods_list=new ArrayList<>();
    private ListView mlistview;
    private TextView mSort,mShaixuan;
    private PopupWindow mPopWindow;
    private LinearLayout mlinear,mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_item);
        initView();
        HttpUtils utils = new HttpUtils();
        Intent intent = getIntent();
        String goods_id = intent.getStringExtra("goods_id");
        utils.LoadDataFromServer(url+"&gc_id=" + goods_id, new NetDataCallBack() {

            @Override
            public void callback(Object o) {
                GoodsData goodsData= (GoodsData) o;
                goods_list = goodsData.getDatas().getGoods_list();
                GoodsAdapter adapter=new GoodsAdapter(GoodsActivity.this,goods_list);
                mlistview.setAdapter(adapter);
            }

            @Override
            public void error(String message, int code) {

            }
        }, GoodsData.class);
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(GoodsActivity.this,ShopActivity.class);
                intent.putExtra("data",goods_list.get(position).getGoods_id());
                startActivity(intent);

            }
        });
        mSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showPopupWindow();
            }
        });
        mShaixuan.setOnClickListener(this);
    }

    private void initView() {
        mlistview = (ListView) findViewById(R.id.listview);
        mSort = (TextView) findViewById(R.id.sort);
        mlinear = (LinearLayout) findViewById(R.id.linear);
        mtitle = (LinearLayout) findViewById(R.id.title);
        mShaixuan = (TextView) findViewById(R.id.shaixuan);

    }
    private void showPopupWindow() {

//设置contentView
        View contentView = LayoutInflater.from(GoodsActivity.this).inflate(R.layout.poplayout, null);
        mPopWindow = new PopupWindow(contentView,250,200, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView)contentView.findViewById(pop_together);
        TextView tv2 = (TextView)contentView.findViewById(pop_person);
        TextView tv3 = (TextView)contentView.findViewById(R.id.pop_tall);
        TextView tv4 = (TextView)contentView.findViewById(R.id.pop_price);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        mPopWindow.showAsDropDown(mlinear);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case pop_together:{
                mSort.setText("综合排序");
                mPopWindow.dismiss();
            }
            break;
            case pop_person:{
                mSort.setText("人气排序");
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_tall:{
                mSort.setText("价格从高到低");
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_price:{
                mSort.setText("价格从低到高");
                mPopWindow.dismiss();
            }
            break;
            case R.id.shaixuan:
                View contentView = LayoutInflater.from(GoodsActivity.this).inflate(R.layout.popprice, null);
                mPopWindow = new PopupWindow(contentView,450,200, true);
                mPopWindow.setContentView(contentView);
                mPopWindow.showAsDropDown(mtitle);
                break;
        }
    }
}
