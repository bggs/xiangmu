package com.bawei.liujie.xiangmu.control.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/20
 */

public class BackActivity{

    public void back(Context context){

        Dialog alertDialog = new AlertDialog.Builder(context).
                setTitle("提示").//设置标题
                setMessage("你确定要退出吗?"). //设置内容
                //设置按钮事件
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                }).
                        create();  //显示
        alertDialog.show();  //显示
    }
}
