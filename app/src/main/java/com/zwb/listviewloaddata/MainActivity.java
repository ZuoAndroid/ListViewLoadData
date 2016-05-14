package com.zwb.listviewloaddata;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.zwb.listviewloaddata.adapter.ItemAdapter;
import com.zwb.listviewloaddata.entity.ItemEntity;
import com.zwb.listviewloaddata.utils.SIMCardInfo;
import com.zwb.listviewloaddata.view.LoadListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadListView.ILoadListener{

    private ArrayList<ItemEntity> list = new ArrayList<>();
    private LoadListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SIMCardInfo info = new SIMCardInfo(getApplicationContext());
        String number = info.getNativePhoneNumber();
        String provider = info.getProvidersName();
        Log.d("TAG" , number + provider);


        //初始化数据的方法
        getData();
        showListView(list);
    }

    private ItemAdapter adapter;

    private void showListView(ArrayList<ItemEntity> list) {
        if (adapter == null){
            listView = ((LoadListView) findViewById(R.id.listView));
            listView.setInterface(this);
            adapter = new ItemAdapter(getApplicationContext() ,list);
            listView.setAdapter(adapter);
        }else {
            adapter.onDateChange(list);
        }
    }


    private void getMoreData() {
        for (int i = 0; i < 3; i++) {
            ItemEntity entity = new ItemEntity();
            entity.setName("新的测试标题");
            entity.setMsg("我是一条新的测试的内容啊～");
            list.add(entity);
        }
    }


    private void getData() {
        for (int i = 0; i < 10; i++) {
            ItemEntity entity = new ItemEntity();
            entity.setName("测试标题");
            entity.setMsg("我是一条测试的内容啊～");
            list.add(entity);
        }
    }


    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //加载数据
                getMoreData();

                //更新ListView的显示
                showListView(list);

                //ListView加载完毕
                listView.loadComplete();

            }
        } , 2000);
    }

}
