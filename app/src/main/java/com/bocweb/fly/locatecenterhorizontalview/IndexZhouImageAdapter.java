package com.bocweb.fly.locatecenterhorizontalview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fly on 2018/4/4.
 */

public class IndexZhouImageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ContinentModel> mDatas;

    public IndexZhouImageAdapter(Context context, List<ContinentModel> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_zhou, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_name.setText(mDatas.get(position).getContinentName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
