package com.example.admin.tourproduct.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

/**
 * Created by YH on 2018/4/23.
 */

public class TimeUtil {

    private Context mContext;
    private static TimeUtil mTimeUtil;

    private TimeUtil(Context context) {
        this.mContext = context;
    }

    public static TimeUtil getFragmentUtil(Context context){
        if (mTimeUtil == null){
            synchronized (TimeUtil.class){
                if (mTimeUtil == null){
                    mTimeUtil = new TimeUtil(context);
                }
            }
        }
        return mTimeUtil;
    }

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static String contrasTime(String inputDate){
        if (inputDate == null){
            return "";
        }
        if (inputDate.equals("")){
            return "";
        }
        String[] allDate = inputDate.split("/");
        String inputYear = allDate[0];
        String inputMonth = allDate[1];
        String inputDay = allDate[2].split(" ")[0];
        String[] allTime = allDate[2].split(" ")[1].split(":");
        String inputHour = allTime[0];
        String inputMinute = allTime[1];
        String inputSecond = allTime[2];
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        LogUtil.e("asjhdjkhajs",date+"");
        if (Integer.valueOf(inputYear) > year){
            return (Integer.valueOf(inputYear) - year)+"年前";
        }else if ((date - Integer.valueOf(inputDay)) > 2){
            return inputMonth+"月"+inputDate+"日\t"+inputHour+":"+inputMinute;
        }else if ((date - Integer.valueOf(inputDay)) == 2){
            return "前天\t"+inputHour+":"+inputMinute;
        }else if ((date - Integer.valueOf(inputDay)) == 1){
            return "昨天\t"+inputHour+":"+inputMinute;
        }else{
            return "今天\t"+inputHour+":"+inputMinute;
        }


    }

    public static void thisDate(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
    }
}
