package com.example.admin.tourproduct.index;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.admin.tourproduct.entry.Version;
import com.example.admin.tourproduct.util.DownloadService;
import com.example.admin.tourproduct.util.HttpUtil;
import com.example.admin.tourproduct.util.SpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by snail
 * on 2017/12/6.
 * Todo
 */

public class IndexPresenter implements IndexContract.Presenter {

    private IndexContract.View view;
    private ServiceConnection conn;
    private String ver;
    private String local;
    private String fileName;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int update = Integer.parseInt(ver.replace(".",""));
            int updates = Integer.parseInt(local.replace(".",""));
            if (update>updates) {
                view.showUpdate(ver);
            }
        }
    };

    public IndexPresenter(IndexContract.View view) {
        this.view = view;
    }


    /**
     * 请求网络
     * 获取网络版本号
     * 获取成功后与本地版本号比对
     * 符合更新条件就控制view弹窗
     */
    @Override
    public void checkUpdate(final String local) {
        //假设获取得到最新版本
        //一般还要和忽略的版本做比对。。这里就不累赘了
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(HttpUtil.GET_LAST_VERSION)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                Log.e("onResponse: +version", responseStr);
                Version version = (Version) new Gson().fromJson(responseStr, new TypeToken<Version>() {}.getType());
                ver = version.getEntity().getVersionNum().replace("V","");
                fileName = version.getEntity().getRes();

                IndexPresenter.this.local = local;
                handler.sendEmptyMessage(1);
            }
        });

    }



    /**
     * 设置忽略版本
     */
    @Override
    public void setIgnore(String version) {
        SpUtils.getInstance().putString("ignore",version);
    }

    /**
     * 模拟网络下载
     */
    @Override
    public void downApk(Context context) {
        Log.e("onServiceConnected: ",conn+"fghjkl;" );
        final String url = HttpUtil.DELICACY_DRAWABLE+fileName;
        ///UpFiles/VersionFiles/20180716/e9eb98e9c6e345cd94fb4ba2bc98e8ff.apk
        if (conn == null)
            Log.e("onServiceConnected: ","fghjkl1111" );
            conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
                    DownloadService myService = binder.getService();
                    Log.e("onServiceConnected: ","fghjkl;" );
                    myService.downApk(url, new DownloadService.DownloadCallback() {
                        @Override
                        public void onPrepare() {

                        }

                        @Override
                        public void onProgress(int progress) {
                            view.showProgress(progress);
                        }

                        @Override
                        public void onComplete(File file) {
                            view.showComplete(file);
                        }

                        @Override
                        public void onFail(String msg) {
                            view.showFail(msg);
                        }
                    });
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    //意味中断，较小发生，酌情处理
                }
            };
        Intent intent = new Intent(context,DownloadService.class);
        context.bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }

    @Override
    public void unbind(Context context) {
        context.unbindService(conn);
    }

}
