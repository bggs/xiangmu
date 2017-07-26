package com.bawei.liujie.xiangmu.control.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liujie.xiangmu.R;

public class LoginActivity extends AppCompatActivity {

    private TextView mRegister;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        initView();
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackActivity backActivity=new BackActivity();
                backActivity.back(LoginActivity.this);
            }
        });
    }
            private void initView() {
                mRegister = (TextView) findViewById(R.id.txt_register);
                mImage = (ImageView) findViewById(R.id.image_back);
            }
    }

