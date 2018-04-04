package com.bocweb.fly.locatecenterhorizontalview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bocweb.fly.locatecenterhorizontalview.view.LocateCenterHorizontalView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by fly on 2018/4/4.
 */

public class IndexZhouTextAdapter extends RecyclerView.Adapter<IndexZhouTextAdapter.AgeViewHolder>
        implements LocateCenterHorizontalView.IAutoLocateHorizontalView {
    private Context mContext;
    private View mView;
    private List<ContinentModel> mDatas;
    private int circle;


    public IndexZhouTextAdapter(Context context, List<ContinentModel> datas, int circle) {
        this.mContext = context;
        this.mDatas = datas;
        this.circle = circle;
    }

    @Override
    public AgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_text_zhou_index, parent, false);
        return new AgeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(AgeViewHolder holder, final int position) {
        int size = mDatas.size();

        holder.name.setText(mDatas.get(position % size).getContinentName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ZhouTextClickEvent(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size() * circle;
    }

    @Override
    public View getItemView() {
        return mView;
    }

    public List<ContinentModel> getData() {
        return mDatas;
    }

    @Override
    public void onViewSelected(boolean isSelected, int pos, RecyclerView.ViewHolder holder, int itemWidth) {
        if (isSelected) {
            ((AgeViewHolder) holder).name.setTextSize(20);
            ((AgeViewHolder) holder).name.setTextColor(Color.parseColor("#FD7422"));
        } else {
            ((AgeViewHolder) holder).name.setTextSize(14);
            ((AgeViewHolder) holder).name.setTextColor(Color.parseColor("#999999"));
        }
    }

    static class AgeViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        AgeViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
