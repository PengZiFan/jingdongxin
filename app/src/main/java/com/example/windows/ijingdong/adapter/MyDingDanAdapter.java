package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.MyDingDanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Windows on 2018/11/20.
 */

public class MyDingDanAdapter extends RecyclerView.Adapter<MyDingDanAdapter.ViewHolder> {

    private Context context;
    private List<MyDingDanBean.DataBean> list;
    private View view;

    public MyDingDanAdapter(Context context, List<MyDingDanBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.layout_dingdan, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTitleDingdan.setText(list.get(position).getTitle());
        holder.txtTimeDingdan.setText(list.get(position).getCreatetime());
        holder.txtPriceDingdan.setText(String.valueOf(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title_dingdan)
        TextView txtTitleDingdan;
        @BindView(R.id.txt_time_dingdan)
        TextView txtTimeDingdan;
        @BindView(R.id.txt_price_dingdan)
        TextView txtPriceDingdan;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }
}
