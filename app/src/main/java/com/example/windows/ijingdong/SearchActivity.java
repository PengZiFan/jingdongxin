package com.example.windows.ijingdong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.windows.ijingdong.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {


    @BindView(R.id.flow)
    FlowLayout flow;
    @BindView(R.id.et_black)
    TextView etBlack;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.btn_decrease)
    Button btnDecrease;
    private String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.et_black, R.id.btn_add, R.id.btn_decrease})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_black:
                finish();
                break;
            case R.id.btn_add:
                TextView txt=new TextView(this);
                input = etText.getText().toString();
                if(!TextUtils.isEmpty(input)){
                    txt.setText(""+ input);
                    txt.setPadding(15,15,15,15);
                    flow.addView(txt);
                }
                Intent intent = new Intent(this,ShowActivity.class);
                intent.putExtra("input",input);
                startActivity(intent);
                break;
            case R.id.btn_decrease:
                flow.removeAllViews();
                break;
        }
    }



}
