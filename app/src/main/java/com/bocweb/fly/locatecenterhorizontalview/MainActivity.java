package com.bocweb.fly.locatecenterhorizontalview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bocweb.fly.locatecenterhorizontalview.view.LocateCenterHorizontalView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fly on 2018/4/4.
 */

public class MainActivity extends AppCompatActivity {
    LocateCenterHorizontalView zhouText;
    RecyclerView zhouImage;
    List<ContinentModel> list;
    IndexZhouTextAdapter zhouTextAdapter;
    IndexZhouImageAdapter zhouImageAdapter;
    int circle = 250;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        EventBus.getDefault().register(this);
        zhouImage = findViewById(R.id.zhouImage);
        zhouText = findViewById(R.id.zhouText);
        initData();
        initZhouText();


        zhouImage.setHasFixedSize(true);
        zhouImage.setLayoutManager(new LinearLayoutManager(this));
        zhouImageAdapter = new IndexZhouImageAdapter(this, list);
        zhouImage.setAdapter(zhouImageAdapter);

        zhouImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (move) {
                    move = false;
                    LinearLayoutManager layoutManager = (LinearLayoutManager) zhouImage.getLayoutManager();
                    int n = mIndex - layoutManager.findFirstVisibleItemPosition();
                    if (0 <= n && n < zhouImage.getChildCount()) {
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = zhouImage.getChildAt(n).getTop();
                        //最后的移动
                        zhouImage.scrollBy(0, top);
                    }
                }
            }
        });
    }

    private void initZhouText() {
        zhouText.setHasFixedSize(true);
        zhouText.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        zhouTextAdapter = new IndexZhouTextAdapter(this, list, circle);
        zhouText.setAdapter(zhouTextAdapter);
        zhouText.setOnSelectedPositionChangedListener(new LocateCenterHorizontalView.OnSelectedPositionChangedListener() {
            @Override
            public void selectedPositionChanged(int pos) {
                if (zhouTextAdapter.getData().size() > 0) {
                    int i = pos % zhouTextAdapter.getData().size();
//                zhouImage.smoothScrollToPosition(i)
                    moveToPosition(i);
                }
            }
        });

    }

    boolean move = false;
    int mIndex = 0;

    private void moveToPosition(int n) {
        mIndex = n;
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        LinearLayoutManager layoutManager = (LinearLayoutManager) zhouImage.getLayoutManager();
        int firstItem = layoutManager.findFirstVisibleItemPosition();
        int lastItem = layoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            zhouImage.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = zhouImage.getChildAt(n - firstItem).getTop();
            zhouImage.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            zhouImage.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            move = true;
        }

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
   public void onEvent(ZhouTextClickEvent e) {
        zhouText.moveToPosition(e.position);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new ContinentModel(0, "亚洲", "1"));
        list.add(new ContinentModel(1, "欧洲", "2"));
        list.add(new ContinentModel(2, "大洋洲", "3"));
        list.add(new ContinentModel(3, "非洲", "4"));
        list.add(new ContinentModel(4, "南美洲", "5"));
        list.add(new ContinentModel(5, "北美洲", "6"));
        list.add(new ContinentModel(6, "南极洲", "7"));
    }
}
