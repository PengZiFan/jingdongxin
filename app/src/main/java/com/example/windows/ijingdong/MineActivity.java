package com.example.windows.ijingdong;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.ijingdong.bean.LoginBean;
import com.example.windows.ijingdong.bean.TouXiangBean;
import com.example.windows.ijingdong.bean.UserBean;
import com.example.windows.ijingdong.login.presenter.LoginPresenter;
import com.example.windows.ijingdong.login.view.ILoginView;
import com.example.windows.ijingdong.touxiang.ITouXiangView;
import com.example.windows.ijingdong.touxiang.TouXiangPresenter;
import com.example.windows.ijingdong.user.IUserView;
import com.example.windows.ijingdong.user.UserPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,ITouXiangView,IUserView {

    @BindView(R.id.image_back_mine)
    ImageView imageBackMine;
    @BindView(R.id.grname)
    TextView grname;
    @BindView(R.id.gr_datee)
    TextView grDatee;
    @BindView(R.id.gr_phone)
    TextView grPhone;
    @BindView(R.id.upload_image)
    SimpleDraweeView uploadImage;
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;

    //调用照相机返回图片文件
    private File tempFile;
    private TouXiangPresenter touXiangPresenter;
    private File file;
    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        touXiangPresenter = new TouXiangPresenter();
        touXiangPresenter.attach(this);
        userPresenter = new UserPresenter();
        userPresenter.attach(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);


        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(MineActivity.this, "com.hansion.chosehead", tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:     //调用剪裁后返回
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    uploadImage.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                    file = new File(path);
                    touXiangPresenter.upLoad(21241,file);
                }
                break;
        }

    }


    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @OnClick({R.id.image_back_mine, R.id.grname, R.id.gr_datee, R.id.upload_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back_mine:
                finish();
                break;
            case R.id.grname:
                Intent intent = new Intent(this, UpdateNameActivity.class);
                startActivity(intent);
                break;
            case R.id.gr_datee:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.upload_image:
                AlertDialog.Builder builder = new AlertDialog.Builder(MineActivity.this);
                View inflate = View.inflate(this, R.layout.layout_alert, null);
                builder.setView(inflate);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                inflate.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPicFromCamera();
                        alertDialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPicFromAlbm();
                        alertDialog.dismiss();
                    }
                });
                break;

        }
    }


    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        File tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MineActivity.this, "com.hansion.chosehead", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            Log.e("dasd", contentUri.toString());
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * 从相册获取图片
     */
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String result = String.format("%d年%d月%d日", year, month + 1, dayOfMonth);
        grDatee.setText(result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        String nickname = sp.getString("nickname", null);
        String username = sp.getString("username", null);
        int uid = sp.getInt("uid", 0);

        userPresenter.getUser(uid);
        grname.setText(nickname);
        grPhone.setText(username);
    }

    @Override
    public void success(TouXiangBean touXiangBean) {
        Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();
        SharedPreferences sp = getSharedPreferences("config",MODE_PRIVATE);
        int uid = sp.getInt("uid", 0);

        userPresenter.getUser(uid);

    }


    @Override
    public void success(UserBean userBean) {

        String replace = userBean.getData().getIcon().replace("https", "http");

        SharedPreferences sp = getSharedPreferences("config",MODE_PRIVATE);
        String login = sp.getString("login", "");
        if (login.equals("true")){
            uploadImage.setImageURI(replace);
        }else{
            uploadImage.setImageURI("");
        }
        sp.edit().putString("icon", replace).commit();
    }

    @Override
    public void failed(Exception e) {

    }
}
