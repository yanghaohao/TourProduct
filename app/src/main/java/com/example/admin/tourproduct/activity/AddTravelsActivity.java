package com.example.admin.tourproduct.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.adapter.ImageAdapter;
import com.example.admin.tourproduct.db.DBProcess;
import com.example.admin.tourproduct.entry.AddTravelsResult;
import com.example.admin.tourproduct.entry.Travels;
import com.example.admin.tourproduct.entry.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import com.example.admin.tourproduct.entry.Image;
import com.example.admin.tourproduct.interfaces.AddTravelView;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.LogUtil;
import com.example.admin.tourproduct.util.SharedUtils;
import com.example.admin.tourproduct.util.TravelsUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import base.activity.BaseActivity;

public class AddTravelsActivity extends BaseActivity {

    private static final int REQUEST_CODE = 0x00000011;
    private HomeFragmentPresenter homeFragmentPresenter;
    private ImageAdapter mAdapter;
    private EditText mEdTravel;
    private ArrayList<String> images;
    private AddTravelsResult addTravelsResult;
    private LinearLayout mLlAddTra;
    private String result;
    private int addResult = 0;
    private DBProcess dbProcess;
    private User user;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            user = HomeActivity.user;

            //year+"年"+month+"月"+date+"日\t"+hour+":"+minute
            String add = new Gson().toJson(new Travels(0,user.getEntity().getUserName(),null,mEdTravel.getText().toString(),user.getEntity().getID()+""
                    ,null,0,null,null));
            homeFragmentPresenter.loadingData(Constant.ADDTRAVEL,add,Constant.POST,false);
        }
    };

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            List<File> list = new ArrayList<>();
                            for (int i = 0;i < images.size();i++) {
                                list.add(new File(images.get(i)));
                            }
                            result = TravelsUtil.uploadImage(HttpUtil.UP_IMAGE
                                    ,addTravelsResult.getNewID(),user.getEntity().getID()+"",list);
                            LogUtil.e("result",result);
                            if (!result.equals("1")&&!result.equals("2")&&!result.equals("3")&&!result.equals("4")){
                                handler.sendEmptyMessage(2);
                            }else {
                                handler.sendEmptyMessage(3);
                            }
                        }
                    }.start();
                    break;
                case 2:
                    showShortToast("上传成功");
                    SharedUtils.getSharedUtils(AddTravelsActivity.this).saveBoolean(SharedUtils.IS_UPDATE_TRAVEL,true);
                    finish();
                    break;
                case 3:
                    showSnackBar(mLlAddTra,"上传失败，请检查您的网络连接从重新再试",2000);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void intiView() {

        ImageSelector.builder()
                .useCamera(true) // 设置是否使用拍照
                .setSingle(false)  //设置是否单选
                .setViewImage(true) //是否点击放大图片查看,，默认为true
                .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
                .start(this, REQUEST_CODE); // 打开相册

        hideActionBar();

        mEdTravel = findViewById(R.id.ed_travel);

        RecyclerView glTravel = findViewById(R.id.gl_travel);
        glTravel.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new ImageAdapter(this);
        glTravel.setAdapter(mAdapter);

        mLlAddTra = findViewById(R.id.ll_add_tr);

        homeFragmentPresenter = new HomeFragmentPresenter(addTravelView);
        showActionBar(true, "添加游记", clickListener,"发表");

        dbProcess = new DBProcess(this);

    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_add_travels);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            images = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            mAdapter.refresh(images);
        }
    }


    private void upImage(){
        try {
            String response = "";

            BufferedReader in = new BufferedReader(new FileReader("config/actionUrl.properties"));
            String actionUrl = in.readLine();

            // 读取表单对应的字段名称及其值
            Properties formDataParams = new Properties();
            formDataParams.load(new FileInputStream(new File("config/formDataParams.properties")));
            Set<Map.Entry<Object,Object>> params = formDataParams.entrySet();

            // 读取图片所对应的表单字段名称及图片路径
            Properties imageParams = new Properties();
            imageParams.load(new FileInputStream(new File("config/imageParams.properties")));
            Set<Map.Entry<Object,Object>> images = imageParams.entrySet();
            com.example.admin.tourproduct.entry.Image[] files = new com.example.admin.tourproduct.entry.Image[images.size()];
            int i = 0;
            for(Map.Entry<Object,Object> image : images) {
                Image file = new Image(image.getValue().toString(), image.getKey().toString());
                files[i++] = file;
            }
//            Image file = new Image("images/apple.jpg", "upload_file");
//            Image[] files = new Image[0];
//            files[0] = file;

//            response = new TravelsUtil.post(actionUrl, params, files);
            System.out.println("返回结果：" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    AddTravelView addTravelView = new AddTravelView() {
        @Override
        public void addTravel(String result) {
            addTravelsResult = (AddTravelsResult) new Gson().fromJson(result, new TypeToken<AddTravelsResult>() {}.getType());
            if(addTravelsResult.getStatus()!=-1)
                handler.sendEmptyMessage(Constant.STATUS_NORMAL);
            else {
                handler.sendEmptyMessage(Constant.STATUS_UNUSUAL);
            }
        }

        @Override
        public void addTravelFail() {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
//        AlertDialog.Builder builder = new AlertDialog.Builder(AddTravelsActivity.this);
//        //    设置Title的内容
//        builder.setTitle("提示");
//        //    设置Content来显示一个信息
//        builder.setMessage("保存本次编辑内容？");
//        //    设置一个PositiveButton
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//
//            }
//        });
//        //    设置一个NegativeButton
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                finish();
//            }
//        });
//        //    显示出该对话框
//        builder.show();
    }
}
