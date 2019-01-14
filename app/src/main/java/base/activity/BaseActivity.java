package base.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.activity.SearchActivity;
import com.example.admin.tourproduct.application.TourApplication;
import com.example.admin.tourproduct.util.ShapeLoadingDialogUtil;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;

/**
 * Created by YH on 2018/4/12.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String loadText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        translucentStatusBar(this,false);
        setLayout();
        intiView();
    }

    public static void translucentStatusBar(Activity context, boolean isFullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setTranslucentStatusForLowVersion(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            translucentStatusBar(context, isFullScreen, Color.TRANSPARENT);
        }
    }

    private static void setTranslucentStatusForLowVersion(Activity activity) {
        if (activity != null) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View statusView = createStatusView(activity);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
        }
    }

    private static View createStatusView(Activity activity) {
        View view = new View(activity);
        int statusBarHeight = getStatusBarHeight();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));
        view.setBackgroundColor(activity.getResources().getColor(R.color.color_transparent));
        return view;
    }

    //设置状态栏融合，5.0以上
    public static void translucentStatusBar(Activity context, boolean isFullScreen, int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int visibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (isFullScreen) {
                visibility = visibility | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            }
            window.getDecorView().setSystemUiVisibility(visibility);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusBarColor);
//             window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    public static int getStatusBarHeight() {
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = TourApplication.getInstance().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = TourApplication.getInstance().getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("WangJ", "状态栏-方法1:" + statusBarHeight);
        return statusBarHeight;
    }

    protected abstract void intiView();
    protected abstract void setLayout();

    public void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    protected void showSnackBar(View view,String describe,int time){
        Snackbar.make(view,describe,time).show();
    }

    protected void showActionSnackBar(View view, String s, String btn, int time,View.OnClickListener clickListener){
        Snackbar.make(view, s,time).setAction(btn,clickListener).show();
    }

    public void openActivity(Class c,boolean finishOrNo){
        Intent intent = new Intent(this,c);
        startActivity(intent);
        overridePendingTransition(R.anim.view_exit,R.anim.view_enter);
        if (finishOrNo){
            finish();
        }
    }

    public void openActivity(Class c, Bundle bundle,boolean finishOrNo){
        Intent intent = new Intent(this,c);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.view_exit,R.anim.view_enter);
        if (finishOrNo){
            finish();
        }
    }

    public void openActivity(Context context,Class c,int animStart,int animEnd,boolean finishOrNo){
        Intent intent = new Intent(context,c);
        startActivity(intent);
        overridePendingTransition(animStart,animEnd);
        if (finishOrNo){
            finish();
        }
    }

    public static void ShowKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );

        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

    }

    public ShapeLoadingDialog getUtil(){
        return ShapeLoadingDialogUtil.getShapeLoadingDialogUtils(this).buildDialog(this,loadText);
    }

    public void openActivity(Context context, Class c, Bundle bundle,String key,int animStart,int animEnd,boolean finishOrNo){
        Intent intent = new Intent(context,c);
        intent.putExtra(key,bundle);
        startActivity(intent);
        overridePendingTransition(animStart,animEnd);
        if (finishOrNo){
            finish();
        }
    }

    public void openActivity(Context context, Class c, Bundle bundle, String key, ActivityOptions options,boolean finishOrNo){
        Intent intent = new Intent(context,c);
        intent.putExtra(key,bundle);
        startActivity(intent,options.toBundle());
        if (finishOrNo){
            finish();
        }
    }

    protected void showLongToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    protected void setEdiText(EditText etActionbarCanBack){
        etActionbarCanBack.setInputType(InputType.TYPE_NULL);
        etActionbarCanBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        openActivity(BaseActivity.this, SearchActivity.class,R.anim.activity_start,0,false);
                        break;
                }
                return true;
            }
        });
    }

    protected void showActionBar(boolean cancelOrNo, boolean inputOrNo, String name, @Nullable View.OnClickListener clickListener){
        View view = LayoutInflater.from(this).inflate(R.layout.action_bar_can_back,null);
        if (cancelOrNo) {
            findViewById(R.id.action_bar_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }else {
            view.findViewById(R.id.action_bar_cancel).setVisibility(View.INVISIBLE);
        }
        if (inputOrNo){

        }else {
            TextView actionBarName = findViewById(R.id.action_bar_name);
            actionBarName.setVisibility(View.VISIBLE);
            actionBarName.setText(name);
            findViewById(R.id.ll_action_bar).setVisibility(View.GONE);
        }

        if (clickListener!=null){
            ImageView imageView = findViewById(R.id.action_bar_menu);
            imageView.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(clickListener);
        }
    }

    protected void showActionBar(boolean cancelOrNo, String name, @Nullable View.OnClickListener clickListener,@Nullable String menu){
        View view = LayoutInflater.from(this).inflate(R.layout.action_bar,null);
        if (cancelOrNo) {
            findViewById(R.id.action_bar_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }else {
            view.findViewById(R.id.action_bar_cancel).setVisibility(View.INVISIBLE);
        }

        TextView actionBarName = findViewById(R.id.action_bar_name);
        actionBarName.setVisibility(View.VISIBLE);
        actionBarName.setText(name);


        if (clickListener!=null){
            TextView imageView = findViewById(R.id.action_bar_menu);
            imageView.setVisibility(View.VISIBLE);
            imageView.setText(menu);
            imageView.setOnClickListener(clickListener);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
