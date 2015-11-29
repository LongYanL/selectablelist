package com.lnyp.selectablelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private ListView listView;

    private List<DataBean> mDatas;

    private MyAdapter mAdapter;

    public boolean flage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DataBean dataBean = new DataBean("" + i, "上邪", "山无棱，天地合，乃敢与君绝");
            mDatas.add(dataBean);
            mDatas.add(dataBean);
        }

        mAdapter = new MyAdapter(this, mDatas);
        listView.setAdapter(mAdapter);


    }

    public void btnEditList(View view) {
        mAdapter.flage = !mAdapter.flage;

        if (mAdapter.flage) {
            button.setText("取消编辑");
        } else {
            button.setText("编辑");
        }

        mAdapter.notifyDataSetChanged();
    }

    public void btnSelectAllList(View view) {
        if (mAdapter.flage) {

        }
    }

    public void btnNoList(View view) {

        if (mAdapter.flage) {

        }
    }

    public void btnOperateList(View view) {
        if (mAdapter.flage) {
            List<String> ids = mAdapter.getIds();

            Log.e("TAG", ids.toString());
        }
    }
}
