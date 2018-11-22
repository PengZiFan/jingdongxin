package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.GridBean;
import java.util.List;

/**
 * Created by Windows on 2018/11/9.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context context;
    private List<GridBean.DataBean> list;

    public LeftAdapter(Context context, List<GridBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView , int position);
    }
    private OnItemClickListener clickListener;
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_left, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtTitles.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    clickListener.onItemClick(v,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitles;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTitles = itemView.findViewById(R.id.txt_titles);
        }
    }
}
