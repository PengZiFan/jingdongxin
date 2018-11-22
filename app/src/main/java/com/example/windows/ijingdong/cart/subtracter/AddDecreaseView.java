package com.example.windows.ijingdong.cart.subtracter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.windows.ijingdong.R;

/**
 * Created by Windows on 2018/11/14.
 */

public class AddDecreaseView extends RelativeLayout implements View.OnClickListener {
    private TextView txtAdd;
    private TextView txtDecrease;
    private TextView txtNum;
    private int num = 1;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_add:
                num++;
                txtNum.setText(num + "");
                if (listener != null){
                    listener.add(num);
                }
                break;
            case R.id.txt_decrease:
                if (num>1){
                    num--;
                }
                txtNum.setText(num + "");
                if (listener != null){
                    listener.decrease(num);
                }
                break;
        }
    }

    public interface OnAddDecreaseClickListener{
        void add(int num);

        void decrease(int num);
    }
    private OnAddDecreaseClickListener listener;
    public void setOnAddDecreaseClickListener(OnAddDecreaseClickListener listener){
        this.listener = listener;
    }
    public AddDecreaseView(Context context) {
        this(context,null);
    }

    public AddDecreaseView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public AddDecreaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.layout_add_decrease_view,this);
        txtAdd = findViewById(R.id.txt_add);
        txtDecrease = findViewById(R.id.txt_decrease);
        txtNum = findViewById(R.id.txt_num);

        txtNum.setText(num+"");

        txtAdd.setOnClickListener(this);
        txtDecrease.setOnClickListener(this);
    }
    public void setNum(int num){
        this.num = num;
        txtNum.setText(num + "");
    }
    public int getNum(){
        return num;
    }
}
