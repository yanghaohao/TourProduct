package com.example.admin.tourproduct.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.entry.User;
import com.example.admin.tourproduct.fragment.HomeFragment;
import com.example.admin.tourproduct.fragment.MyFragment;
import com.example.admin.tourproduct.fragment.NoticeFragment;
import com.example.admin.tourproduct.fragment.StrategyFragment;
import com.example.admin.tourproduct.fragment.TravelsFragment;
import com.example.admin.tourproduct.index.IndexContract;
import com.example.admin.tourproduct.index.IndexPresenter;
import com.example.admin.tourproduct.interfaces.IsLogin;
import com.example.admin.tourproduct.util.BottomNavigationViewHelper;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.LocalPlaceData;
import com.example.admin.tourproduct.util.LogUtil;
import com.example.admin.tourproduct.util.MainActivityPermissionsDispatcher;
import com.example.admin.tourproduct.util.SharedUtils;

import java.io.File;

import base.activity.BaseActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;

import static android.os.Process.killProcess;

public class HomeActivity extends BaseActivity implements IndexContract.View{

    private Fragment[] fragments;
    private int lastShowIndexFragment;
    public static User user;
    private HomeFragment homeFragment;
    private Dialog mDialog;
    private long exitTime = 0;
    private IndexPresenter mPresenter;
    private LocationClient mLocationClient;
    private LinearLayout container;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowIndexFragment!=0) {
                        switchFragment(lastShowIndexFragment,0);
                        lastShowIndexFragment = 0;
                    }
                    return true;
//                case R.id.navigation_strategy:
//                    if (lastShowIndexFragment!=1) {
//                        switchFragment(lastShowIndexFragment,1);
//                        lastShowIndexFragment = 1;
//                    }
//                    return true;
//                case R.id.navigation_travels:
//                    if (lastShowIndexFragment!=2) {
//                        switchFragment(lastShowIndexFragment,2);
//                        lastShowIndexFragment = 2;
//                    }
//                    return true;
                case R.id.navigation_notice:
                    if (lastShowIndexFragment!=3) {
                        switchFragment(lastShowIndexFragment,3);
                        lastShowIndexFragment = 3;
                    }
                    return true;
                case R.id.navigation_my:
                    if (lastShowIndexFragment!=4) {
                        switchFragment(lastShowIndexFragment,4);
                        lastShowIndexFragment = 4;
                    }
                    return true;
            }
            return false;
        }
    };

    private IsLogin mIsLogin = new IsLogin() {
        @Override
        public void isLogin(String s, String btn, int time, View.OnClickListener clickListener) {
            LogUtil.e("ahsgdh","uasduygu");
            showActionSnackBar(container,s,btn,time,clickListener);
        }
    };

    @Override
    protected void intiView() {
        hideActionBar();

        getFragmentArray();

        container = findViewById(R.id.container);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        mPresenter = new IndexPresenter(this);
        mLocationClient = new LocationClient(HomeActivity.this);
        if (Build.VERSION.SDK_INT>=23){
            showContacts();
        }else{
            init();//init为定位方法
        }

        MainActivityPermissionsDispatcher.needStorageWithPermissionCheck(this);

//        initJpush();
//        registerMessageReceiver();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        exit();
        return false;
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_home);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    init();
                    needStorage();
                } else {
                    // 没有获取到权限，做特殊处理
                    showShortToast("获取位置权限失败，请手动开启");
                }
                break;
        }
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(HomeActivity.this, requestCode, grantResults);
    }

    public void showContacts(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            showShortToast("没有权限,请手动开启定位权限");
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, 100);
        }else{
            init();
        }
    }

    private void init(){
        LocalPlaceData localPlaceData = LocalPlaceData.getSharedUtils(this);
        localPlaceData.bindListener(mLocationClient);
    }

    private void switchFragment(int lastShowFragment, int indexFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastShowFragment]);
        if (!fragments[indexFragment].isAdded()){
            transaction.add(R.id.message,fragments[indexFragment]);
        }
        transaction.show(fragments[indexFragment]).commitAllowingStateLoss();
    }


    private void getFragmentArray(){
        homeFragment = new HomeFragment();
        StrategyFragment strategyFragment = new StrategyFragment();
        TravelsFragment travelsFragment = new TravelsFragment();
        NoticeFragment noticeFragment = new NoticeFragment();
        MyFragment myFragment = new MyFragment();
        homeFragment.setIsLogin(mIsLogin);
        noticeFragment.setIsLogin(mIsLogin);
        myFragment.setIsLogin(mIsLogin);
        lastShowIndexFragment = 0;
        fragments = new Fragment[]{homeFragment,strategyFragment,travelsFragment,noticeFragment,myFragment};
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.message,homeFragment)
                .show(homeFragment)
                .commit();
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void needStorage() {
        try {
            PackageInfo pi = this.getPackageManager().getPackageInfo(this.getPackageName(),0);
            String local = pi.versionName;
            mPresenter.checkUpdate(local);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showRational(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("赋予此应用读写文件权限用于版本更新，是否同意?")
                .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void deniedStorage() {
//        Toast.makeText(this, "我已经拒绝过你了，别来了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpdate(final String version) {
        if (mDialog == null)
            mDialog = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("检测到有新版本")
                    .setMessage("最新版本:"+version)
                    .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.e("onClick   测试: ","到这里了" );
                            mPresenter.downApk(HomeActivity.this);
                        }
                    })
                    .setNegativeButton("忽略", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mPresenter.setIgnore(version);
                        }
                    })
                    .create();

        //重写这俩个方法，一般是强制更新不能取消弹窗
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK && mDialog != null && mDialog.isShowing();
            }
        });

        mDialog.show();
    }

    @Override
    public void showProgress(int progress) {
        Log.e("showProgress: ","ghjkl;" );
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在下载");
        dialog.setCancelable(false);// 设置点击空白处也不能关闭该对话框

        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置采用圆形进度条

        dialog.setMax(100);
        // dialog.setIndeterminate(true);//设置不显示明确的进度
        dialog.setIndeterminate(false);// 设置显示明确的进度

        dialog.show();
        if (progress<100){
            dialog.setProgress(progress);
        }else {
            dialog.cancel();
        }
    }



    @Override
    public void showFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showComplete(File file) {
        try {
            String authority = this.getApplicationContext().getPackageName() + ".fileProvider";
            Uri fileUri = FileProvider.getUriForFile(this, authority, file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //7.0以上需要添加临时读取权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            } else {
                Uri uri = Uri.fromFile(file);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
            }

            startActivity(intent);

            //弹出安装窗口把原程序关闭。
            //避免安装完毕点击打开时没反应
            killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @SuppressLint("WrongConstant")
    public void exit() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            showLongToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
            return;
        }
        finish();
        System.exit(0);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();

    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();

    }

    @Override
    protected void onDestroy() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        SharedUtils.getSharedUtils(this).remove(Constant.IS_LOGIN);
    }

    public static boolean isForeground = false;
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver  {

//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
//                    String messge = intent.getStringExtra(KEY_MESSAGE);
//                    String extras = intent.getStringExtra(KEY_EXTRAS);
//                    StringBuilder showMsg = new StringBuilder();
//                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                    if (!ExampleUtil.isEmpty(extras)) {
//                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                    }
//                }
//            } catch (Exception e){
//            }
//        }
    }


}
