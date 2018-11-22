package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.CartBean;
import com.example.windows.ijingdong.cart.subtracter.AddDecreaseView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by Windows on 2018/11/14.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<CartBean.DataBean.ListBean> productList;

    public ProductAdapter(Context context, List<CartBean.DataBean.ListBean> productList) {
        this.context = context;
        this.productList = productList;
    }

    //商品点击事件的监听
    public interface onProductClickListener{
        void onProductClick(int position,boolean isChecked);
    }
    private onProductClickListener productClickListener;
    public void setonProductClickListener(onProductClickListener productClickListener){
        this.productClickListener = productClickListener;
    }
    //加减器发生变化的监听
    public interface onAddDecreaseProductListener{
        void onChange(int position,int num);
    }
    private onAddDecreaseProductListener listener;
    public void setonAddDecreaseProductListener(onAddDecreaseProductListener listener){
        this.listener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_product,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CartBean.DataBean.ListBean product = productList.get(position);
        holder.imageProduct.setImageURI(product.getImages().split("\\|")[0]);
        holder.txtProductName.setText(product.getTitle());
        holder.txtProductPrice.setText(String.valueOf(product.getPrice()));
        holder.Subtracter.setNum(product.getNum());
        holder.Subtracter.setOnAddDecreaseClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {
                product.setNum(num);
                if (listener != null){
                    listener.onChange(position,num);
                }
            }

            @Override
            public void decrease(int num) {
                product.setNum(num);
                listener.onChange(position,num);
            }
        });
        holder.cbProduct.setOnCheckedChangeListener(null);
        holder.cbProduct.setChecked(product.isChecked());
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setChecked(isChecked);
                if (productClickListener != null){
                    productClickListener.onProductClick(position,isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbProduct;
        private SimpleDraweeView imageProduct;
        private TextView txtProductName;
        private TextView txtProductPrice;
        private AddDecreaseView Subtracter;
        public ViewHolder(View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.image_product);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
            txtProductPrice = itemView.findViewById(R.id.txt_product_price);
            Subtracter = itemView.findViewById(R.id.subtracter);
            cbProduct = itemView.findViewById(R.id.cb_product);
        }
    }
}
