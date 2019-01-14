package com.example.admin.tourproduct.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.example.admin.tourproduct.R;
import com.example.admin.tourproduct.db.DBProcess;
import com.example.admin.tourproduct.entry.Delicacy;
import com.example.admin.tourproduct.entry.UserCoupon;
import com.example.admin.tourproduct.interfaces.AddCoupon;
import com.example.admin.tourproduct.presenter.HomeFragmentPresenter;
import com.example.admin.tourproduct.util.Constant;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.OrderInfoUtil2_0;
import com.example.admin.tourproduct.util.PayResult;
import com.google.gson.Gson;

import java.util.Map;

import base.activity.BaseActivity;

public class CouponActivity extends BaseActivity {

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2018080760933363";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "2088131579057615";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "";

    private Delicacy.EntityBean.CouponListBean coupon;

    private DBProcess dbProcess;

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDY+mBrFPB3BoX7" +
            "N8P07rgZfECqPPtqnmNa5rZKkd12nv6XVnm8R+NfAF63JHg198jd1w/gh86VfxgU" +
            "D9fedP7xDQW49XwNtDrMTERAIF8sKvG2sC9DDKdhEqjV33dm+Y4ulpNigxW2lxN0" +
            "Z6D6/bK2Q/OypVWeEC/c6b84ORjpOZ52H9u1ImB//gBYNvk1yhFShfxADPty9OtX" +
            "R3XmTrdTx+Ur068TkXnGwWhxH/8ElU3vD8aP01l2dKNM7zo/btTl3GnzGfVJijcj" +
            "xlZmnfknNunyF/CbOxBhIeljWBkJdz1td2bAkSQATmlGGeP1k7dIN4WrF/qysui8" +
            "OpaY0q/vAgMBAAECggEAbGlFTZuLvlzTCr9FOJciAxHTGXwYvp/e0qddw4p4FfHB" +
            "EQqTDqD4mRoNOQKO0aA5i6YN98jDTmzPMg7sc+kPDI/OISj+z7YSfstIeqXyB5Z5" +
            "aSdEpgKnitgQ5bOe7COah+qSP3FxouZ/QPUaOO4Tof/3vnXffndmAsID2eqlaLmG" +
            "EvhdBYzI8r4z5yV/74A29MOaRqRJZrYOEuGsJNVvj2Dnoc0v4SvkrEzg3a+eqlUd" +
            "J7+nmlIEBD/txIDmF2okX8i3vCvoiHVInlPhhAr8l4YJc439JSx70Bkx1XaKj/Qe" +
            "jm4gIN4w/zzzHbGS9WOo1d+nO0e2qpcc9cyTCIh0mQKBgQD/F2wU95PsZx3gVgEY" +
            "sFrGc59b+SlNi6Y1VH5N8gFOIegT6O6xN55SelHTqhw3+pra12qnGq9BDyHzfN0K" +
            "CcQAbjCDK4cnw87Z+GHnJpb2UQ0hngE3DaRQf1c2UEcuBA4DBjni+OJa0gUh5TN8" +
            "+w/uXLhATGYuL1PTdGeEKptluwKBgQDZwDRr32br/109MsHLf6f0I1vPQNd3n47n" +
            "+eQQvR5V8smNPUJXRUyJq7mZvsdTeU7lSnChUoajb8ZQjhb/4ltknbiOA9hYm/26" +
            "8QTyZ78kpzgW8whnlIi9ZC4dEswem7DeQZVic7UOk+WkxGTmZ3whGEZW7fun5C7r" +
            "hn9DtNsBXQKBgQDL1Vt4JZz9+ifVauPVZpYinaFpTN/EJyLY2ntSrV59oHph7m9N" +
            "DLRmE3fvOq7xzD6RQDDo9lm0EvN+LPgzXTpRXbtGFTIahEx1wBP7MMrQaFOl2Sv/" +
            "Ydc6u4Zk0CEQlAzePctjopy9FbUkMJHD/JnuTgis2F+N6CODhDBzW9hwoQKBgDEi" +
            "UbHVox+1a5CXl2sXzwojwF+vve7cLh7iIDg2tFWohxo7rHCMzoxyF1s91TRctxS+" +
            "C1Qbjw3p9tHOfhoXNZAqlq5U58h2ynYYVIluJ49ZvCpRhJ/6wDAcHtuoli6B6xL7" +
            "sX2Zpk03yiW3uxzSVQwSsiyDXrmvn3a9zpG0iYZxAoGBANkZpBRg3c3SwnxU9RD8" +
            "fnwFN6nyfQKugRAS0QkvFRTX142eSoMGmtP6aynhOPLO6JzXwipw60Zecmi0sGcA" +
            "9uDwh2MGvZCCnAy+zM0iQHKJMIxPqNvRUKUkdkFt6iMzpheArV8tcWCM7K7SBe1c" +
            "kWaHHukckzlp3f0XT3wjHack";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private HomeFragmentPresenter homeFragmentPresenter;

    private int shopId;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(CouponActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        UserCoupon userCoupon = new UserCoupon(0,shopId,HomeActivity.user.getEntity().getID()+""
                                ,coupon.getName(),coupon.getContent(),coupon.getTermOfValidity(),coupon.getTime(),coupon.getUserule()
                                ,0+"",coupon.getLimit(),coupon.getUniqueCode(),coupon.getDenomination(),coupon.getMoney());
                        String addCoupon = new Gson().toJson(userCoupon);
                        homeFragmentPresenter.loadingData(Constant.ADD_COUPON,addCoupon,Constant.POST,false);
                        dbProcess.addCoupon(coupon);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(CouponActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void intiView() {
        hideActionBar();

        showActionBar(true,false,"优惠券",null);

        Bundle bundle = getIntent().getExtras();
        coupon = (Delicacy.EntityBean.CouponListBean) bundle.getSerializable(Constant.CONPON);
        String couponPicture = bundle.getString(Constant.CONPON_PICTURE);
        shopId = getIntent().getIntExtra(Constant.SHOPID,0);
        ImageView ivCoupon = findViewById(R.id.iv_coupon);
        Glide.with(this).load(HttpUtil.DELICACY_DRAWABLE+couponPicture).into(ivCoupon);

        TextView tvCoupon = findViewById(R.id.tv_coupon);
        tvCoupon.setText(coupon.getName());

        TextView tvMoney = findViewById(R.id.tv_money);
        tvMoney.setText("价值"+coupon.getDenomination()+"元的优惠券，现在仅售"+coupon.getMoney()+"元");

        LinearLayout llCoupon = findViewById(R.id.ll_coupon);
        String[] dish = coupon.getContent().split("n");
        for (int i = 0;i < dish.length;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.coupon_dishes,null);
            String[] number = dish[i].split("t");
            TextView tvDish = view.findViewById(R.id.tv_dish);
            tvDish.setText(number[0]);
            TextView tvDishNumber = view.findViewById(R.id.tv_dish_number);
            tvDishNumber.setText(number[1]);
            llCoupon.addView(view);
        }

        TextView tvPayMoney = findViewById(R.id.tv_pay_money);
        tvPayMoney.setText("￥"+coupon.getMoney()+"");

        homeFragmentPresenter = new HomeFragmentPresenter(addCoupon);

        dbProcess = new DBProcess(this);
    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_coupon);
    }

    AddCoupon addCoupon = new AddCoupon() {
        @Override
        public void addCoupon(String re) {

        }

        @Override
        public void addCouponFail() {

        }
    };
    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2,coupon.getMoney(),coupon.getName());
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(CouponActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
