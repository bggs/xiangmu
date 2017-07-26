package com.bawei.liujie.xiangmu.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.modle.bean.WatchData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/21
 */

public class ShopAdapter extends BaseAdapter {
    private Context context;
    private List<WatchData.DatasBean.GoodsCommendListBean> list;
    private WatchData.DatasBean.GoodsInfoBean goodsInfoBean = new WatchData.DatasBean.GoodsInfoBean();
    private WatchData.DatasBean.GoodsHairInfoBean goodsHairInfoBean = new WatchData.DatasBean.GoodsHairInfoBean();
    private WatchData.DatasBean.StoreInfoBean storeInfoBean = new WatchData.DatasBean.StoreInfoBean();

    public ShopAdapter(Context context, List<WatchData.DatasBean.GoodsCommendListBean> list, WatchData.DatasBean.GoodsInfoBean goodsInfoBean, WatchData.DatasBean.GoodsHairInfoBean goodsHairInfoBean, WatchData.DatasBean.StoreInfoBean storeInfoBean) {
        this.context = context;
        this.list = list;
        this.goodsInfoBean = goodsInfoBean;
        this.goodsHairInfoBean = goodsHairInfoBean;
        this.storeInfoBean = storeInfoBean;
    }

    public ShopAdapter(Context context, List<WatchData.DatasBean.GoodsCommendListBean> list, WatchData.DatasBean.GoodsInfoBean goodsInfoBean, WatchData.DatasBean.GoodsHairInfoBean goodsHairInfoBean) {
        this.context = context;
        this.list = list;
        this.goodsInfoBean = goodsInfoBean;
        this.goodsHairInfoBean = goodsHairInfoBean;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shop_listview_item, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.middle = (TextView) convertView.findViewById(R.id.middle);
            holder.country = (TextView) convertView.findViewById(R.id.country);
            holder.goods = (TextView) convertView.findViewById(R.id.goods);
            holder.fire = (TextView) convertView.findViewById(R.id.fire);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.shop = (TextView) convertView.findViewById(R.id.shop);
            holder.des = (TextView) convertView.findViewById(R.id.des);
            holder.des_id = (TextView) convertView.findViewById(R.id.des_id);
            holder.service = (TextView) convertView.findViewById(R.id.service);
            holder.service_id = (TextView) convertView.findViewById(R.id.service_id);
            holder.wuliu = (TextView) convertView.findViewById(R.id.wuliu);
            holder.wuliu_id = (TextView) convertView.findViewById(R.id.wuliu_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getGoods_image_url()).into(holder.icon);
        holder.title.setText(list.get(position).getGoods_name());
        holder.middle.setText(goodsInfoBean.getGoods_jingle());
        holder.country.setText("送至" + goodsHairInfoBean.getArea_name());
        holder.goods.setText(goodsHairInfoBean.getIf_store_cn());
        holder.fire.setText(goodsHairInfoBean.getContent());
        holder.price.setText("§" + list.get(position).getGoods_promotion_price());
        holder.shop.setText(storeInfoBean.getStore_name());
        holder.des.setText(storeInfoBean.getStore_credit().getStore_desccredit().getText());
        holder.des_id.setText(storeInfoBean.getStore_credit().getStore_desccredit().getCredit());
        holder.service.setText(storeInfoBean.getStore_credit().getStore_servicecredit().getText());
        holder.service_id.setText(storeInfoBean.getStore_credit().getStore_servicecredit().getCredit());
        holder.wuliu.setText(storeInfoBean.getStore_credit().getStore_deliverycredit().getText());
        holder.wuliu_id.setText(storeInfoBean.getStore_credit().getStore_deliverycredit().getCredit());
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView title;
        TextView middle;
        TextView country;
        TextView goods;
        TextView fire;
        TextView price;
        TextView shop;
        TextView des,des_id,service,service_id,wuliu,wuliu_id;
    }
}
