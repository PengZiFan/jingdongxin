package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.AddressBean;

import java.text.ParsePosition;
import java.util.List;

/**
 * Created by Windows on 2018/11/19.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private Context context;
    private List<AddressBean.DataBean> list;

    public AddressAdapter(Context context, List<AddressBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_address,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtPhone.setText(String.valueOf(list.get(position).getMobile()));
        holder.txtAddress.setText(list.get(position).getAddr());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtPhone;
        private TextView txtAddress;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name_address);
            txtPhone = itemView.findViewById(R.id.txt_phone_address);
            txtAddress = itemView.findViewById(R.id.txt_address);
        }
    }
}
