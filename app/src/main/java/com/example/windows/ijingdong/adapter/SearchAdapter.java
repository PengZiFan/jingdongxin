package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Windows on 2018/11/13.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<SearchBean.DataBean> list;

    public SearchAdapter(Context context, List<SearchBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_search_recycleview,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageLogoss.setImageURI(list.get(position).getImages().split("\\|")[0]);
        holder.txtTitless.setText(list.get(position).getTitle());
        holder.txtPricess.setText(list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView imageLogoss;
        private TextView txtTitless;
        private TextView txtPricess;
        public ViewHolder(View itemView) {
            super(itemView);
            imageLogoss = itemView.findViewById(R.id.image_logoss);
            txtTitless = itemView.findViewById(R.id.txt_titless);
            txtPricess = itemView.findViewById(R.id.txt_pricess);
        }
    }
}
