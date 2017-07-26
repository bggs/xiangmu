package com.bawei.liujie.xiangmu.control.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bawei.liujie.xiangmu.R;
import com.bawei.liujie.xiangmu.control.fragment.CartFragment;
import com.bawei.liujie.xiangmu.control.fragment.HomeFragment;
import com.bawei.liujie.xiangmu.control.fragment.UserFragment;
import com.bawei.liujie.xiangmu.control.fragment.WeitaoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mHome;
    private Button mWeitao;
    private Button mCart, mUser;
    private HomeFragment homeFragment;
    private WeitaoFragment weitaoFragment;
    private CartFragment cartFragment;
    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mHome.setOnClickListener(this);
        mWeitao.setOnClickListener(this);
        mCart.setOnClickListener(this);
        mUser.setOnClickListener(this);
    }

    private void initView() {
        mHome = (Button) findViewById(R.id.home);
        mWeitao = (Button) findViewById(R.id.weitao);
        mCart = (Button) findViewById(R.id.cart);
        mUser = (Button) findViewById(R.id.user);

    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.home:
                homeFragment = new HomeFragment();
                transaction.add(R.id.frameLayout, homeFragment);
                transaction.show(homeFragment);
                break;
            case R.id.weitao:
                weitaoFragment = new WeitaoFragment();
                transaction.add(R.id.frameLayout, weitaoFragment);
                transaction.replace(R.id.frameLayout,weitaoFragment);
                transaction.show(weitaoFragment);
                break;
            case R.id.cart:
                cartFragment = new CartFragment();
                transaction.add(R.id.frameLayout, cartFragment);
                transaction.show(cartFragment);
                transaction.replace(R.id.frameLayout,cartFragment);
                break;
            case R.id.user:
                userFragment = new UserFragment();
                transaction.add(R.id.frameLayout, userFragment);
                transaction.replace(R.id.frameLayout,userFragment);
                transaction.show(userFragment);
                break;
        }
        transaction.commit();
    }
}

