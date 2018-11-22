package com.example.windows.ijingdong.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.windows.ijingdong.R;
import com.example.windows.ijingdong.bean.CartBean;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Windows on 2018/11/14.
 */

public class ShopperAdapter extends RecyclerView.Adapter<ShopperAdapter.ViewHolder> {
    private Context context;
    private List<CartBean.DataBean> shopperList;

    public ShopperAdapter(Context context, List<CartBean.DataBean> shopperList) {
        this.context = context;
        this.shopperList = shopperList;
    }
    //商家的点击事件
    public interface onShopperClickListener{
        void onShopperClick(int position,boolean isChecked);
    }
    private onShopperClickListener shopperClickListener;
    public void setonShopperClickListener(onShopperClickListener shopperClickListener){
        this.shopperClickListener = shopperClickListener;
    }
    //商品加减器的监听
    private ProductAdapter.onAddDecreaseProductListener productListener;
    public void setonAddDecreaseProductListener(ProductAdapter.onAddDecreaseProductListener productListener){
        this.productListener = productListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_shopper,null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CartBean.DataBean shopper = shopperList.get(position);
        holder.txtShopperName.setText(shopperList.get(position).getSellerName());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        CartBean.DataBean dataBean = shopperList.get(position);
        holder.rcProduct.setLayoutManager(layoutManager);
        final ProductAdapter productAdapter = new ProductAdapter(context,dataBean.getList());
        //商品加减器的监听
        if (productListener != null){
            productAdapter.setonAddDecreaseProductListener(productListener);
        }
        productAdapter.setonProductClickListener(new ProductAdapter.onProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                //如果当前商品没有选中
                if (!isChecked){
                    //商家也是未选中
                    shopper.setChecked(false);
                    shopperClickListener.onShopperClick(position,false);
                }else {
                    boolean isAllProductSelected = true;
                    for (CartBean.DataBean.ListBean product : shopper.getList()) {
                        if (!product.isChecked()){
                            isAllProductSelected = false;
                        }
                    }
                    shopper.setChecked(isAllProductSelected);
                    shopperClickListener.onShopperClick(position,true);
                }
                notifyDataSetChanged();
                productListener.onChange(0,0);
            }
        });
        holder.rcProduct.setAdapter(productAdapter);
        holder.cbShopper.setOnCheckedChangeListener(null);
        holder.cbShopper.setChecked(shopper.isChecked());
        holder.cbShopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (CartBean.DataBean.ListBean productList : shopper.getList()) {
                    productList.setChecked(isChecked);

                }
                productAdapter.notifyDataSetChanged();
                if (shopperClickListener != null){
                    shopperClickListener.onShopperClick(position,isChecked);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return shopperList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cbShopper;
        private TextView txtShopperName;
        private RecyclerView rcProduct;
        public ViewHolder(View itemView) {
            super(itemView);
            cbShopper = itemView.findViewById(R.id.cb_shopper);
            txtShopperName = itemView.findViewById(R.id.txt_shopper_name);
            rcProduct = itemView.findViewById(R.id.rc_product);
        }
    }
}
