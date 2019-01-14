package base.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.activity.LoginActivity;
import com.example.admin.tourproduct.activity.SearchActivity;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.ShapeLoadingDialogUtil;
import com.example.admin.tourproduct.util.SharedUtils;
import com.example.admin.tourproduct.view.ShapeLoadingDialog;

/**
 * Created by YH on 2018/4/13.
 */

public abstract class BaseFragment extends Fragment {

    protected static String loadText = "加载中...";
    protected static String login = "登陆中...";

    protected SharedUtils sharedUtils;
    protected boolean mIsLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedUtils = SharedUtils.getSharedUtils(getContext());
        return inflater.inflate(setLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public ShapeLoadingDialog getUtil(){
        return ShapeLoadingDialogUtil.getShapeLoadingDialogUtils(getContext()).buildDialog(getContext(),loadText);
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsLogin = sharedUtils.readBoolean(Constant.IS_LOGIN);
    }

    protected abstract int setLayout();

    protected abstract void initView(View view);


    public void openActivity(Context context,Class c,boolean finishOrNo){
        Intent intent = new Intent(context,c);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.view_exit,R.anim.view_enter);
        if (finishOrNo){
            getActivity().finish();
        }
    }

    public void openActivity(Context context, Class c, Bundle bundle,boolean finishOrNo){
        Intent intent = new Intent(context,c);
        intent.putExtras(bundle);
        startActivity(intent);
        if (finishOrNo){
            getActivity().finish();
        }
    }
    public void openActivity(Context context, Class c,int startAnim,int endAnim, boolean finishOrNo){
        Intent intent = new Intent(context,c);
        startActivity(intent);
        getActivity().overridePendingTransition(startAnim,endAnim);
        if (finishOrNo){
            getActivity().finish();
        }
    }

    public void openActivity(Context context, Class c, Bundle bundle,String key,boolean finishOrNo){
        Intent intent = new Intent(context,c);
        intent.putExtra(key,bundle);
        startActivity(intent);
        if (finishOrNo){
            getActivity().finish();
        }
    }

    public void openActivity(Context context, Class c, Bundle bundle, String key, ActivityOptions options, boolean finishOrNo){
        Intent intent = new Intent(context,c);
        intent.putExtra(key,bundle);
        startActivity(intent,options.toBundle());
        if (finishOrNo){
            getActivity().finish();
        }
    }

    protected void showLongToast(String str){
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String str){
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    protected void setEdiText(EditText etActionbarCanBack){
        etActionbarCanBack.setInputType(InputType.TYPE_NULL);
        etActionbarCanBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        openActivity(getContext(), SearchActivity.class,false);
                        break;
                }
                return true;
            }
        });
    }

    protected View.OnClickListener openClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openActivity(getContext(), LoginActivity.class,false);
        }
    };
}
