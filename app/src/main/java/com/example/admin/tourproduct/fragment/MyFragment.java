package com.example.admin.tourproduct.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.activity.DraftActivity;
import com.example.admin.tourproduct.activity.MyCollectActivity;
import com.example.admin.tourproduct.activity.MyCouponActivity;
import com.example.admin.tourproduct.activity.MyTravelActivity;
import com.example.admin.tourproduct.activity.SettingActivity;
import com.example.admin.tourproduct.adapter.MycouponAdapter;
import com.example.admin.tourproduct.interfaces.IsLogin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import base.fragment.BaseFragment;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by YH on 2018/4/23.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径
    private CircleImageView mCircleImageView;
    private Uri desUri;
    private LinearLayout mLlMy;
    private IsLogin isLogin;

    public IsLogin getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(IsLogin isLogin) {
        this.isLogin = isLogin;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        mCircleImageView = view.findViewById(R.id.civ_head);
        mCircleImageView.setOnClickListener(this);

        LinearLayout myTravel = view.findViewById(R.id.my_travel);
        ImageView imageView = myTravel.findViewById(R.id.iv_icon);
        imageView.setImageResource(R.mipmap.travels_my);
        TextView textView = myTravel.findViewById(R.id.tv_name);
        textView.setText(R.string.my_travels);
        myTravel.setOnClickListener(this);

        LinearLayout myCollect = view.findViewById(R.id.my_collect);
        ImageView imageView1 = myCollect.findViewById(R.id.iv_icon);
        imageView1.setImageResource(R.mipmap.collect);
        TextView textView1 = myCollect.findViewById(R.id.tv_name);
        textView1.setText(R.string.my_collect);
        myCollect.setOnClickListener(this);


        LinearLayout draft = view.findViewById(R.id.draft);
        ImageView imageView2 = draft.findViewById(R.id.iv_icon);
        imageView2.setImageResource(R.mipmap.draft);
        TextView textView2 = draft.findViewById(R.id.tv_name);
        textView2.setText(R.string.draft);
        draft.setOnClickListener(this);

        LinearLayout myCoupon = view.findViewById(R.id.my_coupon);
        ImageView imageView3 = myCoupon.findViewById(R.id.iv_icon);
        imageView3.setImageResource(R.mipmap.draft);
        TextView textView3 = myCoupon.findViewById(R.id.tv_name);
        textView3.setText(R.string.my_coupon);
        myCoupon.setOnClickListener(this);

        view.findViewById(R.id.tv_setting).setOnClickListener(this);

        mLlMy = view.findViewById(R.id.ll_my);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.civ_head:
                if (mIsLogin) {
//                    showTypeDialog();
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }
                break;
            case R.id.my_travel:
                if (mIsLogin) {
                    openActivity(getContext(), MyTravelActivity.class, false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }
                break;
            case R.id.my_collect:
                if (mIsLogin) {
                    openActivity(getContext(), MyCollectActivity.class,false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }

                break;
            case R.id.draft:
                if (mIsLogin) {
                    openActivity(getContext(), DraftActivity.class,false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }

                break;
            case R.id.tv_setting:
                if (mIsLogin) {
                    openActivity(getContext(), SettingActivity.class,false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }
                break;
            case R.id.my_coupon:
                if (mIsLogin) {
                    openActivity(getContext(), MyCouponActivity.class,false);
                }else {
                    isLogin.isLogin("您还未登陆，请先登陆","登陆",2000,openClick);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        setPicToView(head);// 保存在SD卡中
                        mCircleImageView.setImageBitmap(head);// 用ImageButton显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        startActivityForResult(intent, 3);
    }

    private void showTypeDialog() {
        //显示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
                    if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},222);
                        return;
                    }else{
                        startActivityForResult(intent2, 2);// 采用ForResult打开
                    }
                } else {

                    startActivityForResult(intent2, 2);// 采用ForResult打开
                }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
