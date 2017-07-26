package com.bawei.liujie.xiangmu.control.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bawei.liujie.xiangmu.R;

public class RegisterActivity extends AppCompatActivity {

    private ImageView micon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        micon = (ImageView) findViewById(R.id.back);
        micon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackActivity backActivity=new BackActivity();
                backActivity.back(RegisterActivity.this);
            }
        });
    }
}
