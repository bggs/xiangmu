package com.bawei.liujie.xiangmu.control.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.WatchData;
import com.bawei.liujie.xiangmu.modle.util.HttpUtils;
import com.bawei.liujie.xiangmu.view.adapter.ShopAdapter;
import com.bawei.liujie.xiangmu.view.application.NetDataCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/21
 */

public class ShopActivity extends AppCompatActivity{
    private String url = "http://169.254.35.154/mobile/index.php?act=goods&op=goods_detail&goods_id=100009";
    private List<WatchData.DatasBean.GoodsCommendListBean> goods_commend_list = new ArrayList<>();
    private WatchData.DatasBean.GoodsInfoBean goodsInfoBean=new WatchData.DatasBean.GoodsInfoBean();
    private WatchData.DatasBean.GoodsHairInfoBean goodsHairInfoBean=new WatchData.DatasBean.GoodsHairInfoBean();
    private ListView mListview;
    private ImageView mIcon;
    private WatchData.DatasBean.StoreInfoBean storeInfoBean = new WatchData.DatasBean.StoreInfoBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_item);
        initView();
        HttpUtils utils = new HttpUtils();
        Intent intent = getIntent();
        String goods_id = intent.getStringExtra("goods_id");
        Log.i("aaaaaaaaa", "onCreate: "+goods_id);
        utils.LoadDataFromServer(url, new NetDataCallBack() {

            @Override
            public void callback(Object o) {
                WatchData data = (WatchData) o;

                goods_commend_list = data.getDatas().getGoods_commend_list();
                goodsInfoBean=data.getDatas().getGoods_info();
                String goods_url = goodsInfoBean.getGoods_url();
                goodsHairInfoBean = data.getDatas().getGoods_hair_info();
                storeInfoBean=data.getDatas().getStore_info();
                ShopAdapter adapter=new ShopAdapter(ShopActivity.this,goods_commend_list,goodsInfoBean,goodsHairInfoBean,storeInfoBean);
                mListview.setAdapter(adapter);
            }

            @Override
            public void error(String message, int code) {

            }
        }, WatchData.class);
    }

    private void initView() {
        mListview = (ListView) findViewById(R.id.listview);
        mIcon = (ImageView) findViewById(R.id.image);
    }

    }
