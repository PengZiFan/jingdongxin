package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.GridBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Windows on 2018/11/8.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Context context;
    private List<GridBean.DataBean> list;

    public GridAdapter(Context context, List<GridBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product, parent, false);
        ViewHolder holder = new ViewHolder(view);*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageLogo.setImageURI(list.get(position).getIcon());
        holder.txtTitle.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView imageLogo;
        private TextView txtTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            imageLogo = itemView.findViewById(R.id.image_logo_grid);
            txtTitle = itemView.findViewById(R.id.txt_title_gird);

        }
    }
}
