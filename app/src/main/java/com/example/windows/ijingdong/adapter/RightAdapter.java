package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.RightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import static com.example.windows.ijingdong.fragment.FenLeiFragment.TAG;

/**
 * Created by Windows on 2018/11/9.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder>{
    private Context context;
    private List<RightBean.DataBean.ListBean> listBeans;

    public RightAdapter(Context context, List<RightBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    public interface OnRightItemClickListener{
        void onItemClick(View itemView , int position);
    }
    private OnRightItemClickListener clickListener;
    public void setOnRightItemClickListener(OnRightItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_right, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.imageLogos.setImageURI(listBeans.get(position).getIcon());
        holder.txtTitless.setText(listBeans.get(position).getName());
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
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView imageLogos;
        private TextView txtTitless;
        public ViewHolder(View itemView) {
            super(itemView);
            imageLogos  = itemView.findViewById(R.id.image_logos);
            txtTitless = itemView.findViewById(R.id.txt_titless);
        }
    }
}
