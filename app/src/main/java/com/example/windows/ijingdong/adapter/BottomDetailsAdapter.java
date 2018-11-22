package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.BottomDetailsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Windows on 2018/11/8.
 */

public class BottomDetailsAdapter extends RecyclerView.Adapter<BottomDetailsAdapter.ViewHolder> {
    private Context context;
    private List<BottomDetailsBean.DataBean.TuijianBean.ListBeanX> beanList;

    public BottomDetailsAdapter(Context context, List<BottomDetailsBean.DataBean.TuijianBean.ListBeanX> beanList) {
        this.context = context;
        this.beanList = beanList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_bottom, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageLogoB.setImageURI(beanList.get(position).getImages().split("\\|")[0]);
        holder.txtTitleB.setText(beanList.get(position).getTitle());
        holder.txtPriceB.setText("$" + beanList.get(position).getPrice());
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
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imageLogoB;
        private TextView txtTitleB;
        private TextView txtPriceB;

        public ViewHolder(View itemView) {
            super(itemView);
            imageLogoB = itemView.findViewById(R.id.image_logo_bottom);
            txtTitleB = itemView.findViewById(R.id.txt_title_bottom);
            txtPriceB = itemView.findViewById(R.id.txt_price_bottom);
        }
    }
}
