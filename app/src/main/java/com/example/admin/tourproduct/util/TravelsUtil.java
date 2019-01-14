package com.example.admin.tourproduct.util;


import com.example.admin.tourproduct.entry.Type;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TravelsUtil {
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10*10000000; //超时时间
    private static final String CHARSET = "utf-8"; //设置编码
    private static final String BOUNDARY = "FlPm4LpSXsE" ; //UUID.randomUUID().toString(); //边界标识 随机生成 String PREFIX = "--" , LINE_END = "\r\n";
    private static final String PREFIX="--";
    private static final String LINE_END="\n\r";
    private static final String CONTENT_TYPE = "multipart/form-data"; //内容类型

    /** * android上传文件到服务器
     * @param file 需要上传的文件
     * @param requestURL 请求的rul
     * @return 返回响应的内容
     */
    public static String uploadFile(File file,String requestURL,int NewID,String inputUser) {
        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            conn.setRequestMethod("POST"); //请求方式
            conn.setRequestProperty("Charset", CHARSET);//设置编码

            Type type = FileUtil.fileType(file.getName());
            //头信息
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            if(file!=null) {
                /** * 当文件不为空，把文件包装并且上传 */
                OutputStream outputSteam=conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);

//                String[] params = {"\"NewID\"","\"docName\"","\"docType\"","\"sessionKey\"","\"sig\""};
//                String[] values = {String.valueOf(NewID),file.getName(),type.getType(),"dfbe0e1686656d5a0c8de11347f93bb6","e70cff74f433ded54b014e7402cf094a"};
                String[] params = {"\"TNID\"","\"inputuser\""};
                String[] values = {String.valueOf(NewID),inputUser};
                //添加docName,docType,sessionKey,sig参数
                for(int i=0;i<params.length;i++){
                    //添加分割边界
                    StringBuffer sb = new StringBuffer();
                    sb.append(PREFIX);
                    sb.append(BOUNDARY);
                    sb.append(LINE_END);

                    sb.append("Content-Disposition: form-data; name=" + params[i] + LINE_END);
                    sb.append(LINE_END);
                    sb.append(values[i]);
                    sb.append(LINE_END);
                    dos.write(sb.toString().getBytes());

                    LogUtil.e("onResponse:",sb.toString());
                }

                //file内容
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);

                String[] name = file.getName().split("."+type.getType());
                sb.append("Content-Disposition: form-data; name=" + "\"“" + name[0] + "\";filename=" + "\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type:"+ type.getFileType()+LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                //读取文件的内容
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len=is.read(bytes))!=-1)
                {
                    dos.write(bytes, 0, len);
                }
                is.close();
                //写入文件二进制内容
                dos.write(LINE_END.getBytes());
                //写入end data
                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                LogUtil.e("onResponse:",sb.toString());
                /**
                 * 获取响应码 200=成功
                 * 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                LogUtil.e(TAG, "onResponse:"+res);
                if(res==200) {
                    String oneLine;
                    StringBuffer response = new StringBuffer();
                    BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((oneLine = input.readLine()) != null) {
                        response.append(oneLine);
                    }
                    return response.toString();
                }else{
                    return 1+"";
                }
            }else{
                return 2+"";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return 3+"";
        } catch (IOException e) {
            e.printStackTrace();
            return 4+"";
        }
    }

    // 上傳圖片
    public static String uploadImage(String requestURL, int NewID, String inputUser, List<File> list) {
        String uploadResult;
        OkHttpClient okHttpClent = new OkHttpClient();
        try {

            // MultipartBuilder，是上传文件的query
            // addFormDataPart方法：@param [String]name, [String]value
            // addFormDataPart方法：@param [String]name, [String]fileName, [String]fileType, [String]file

            MultipartBody.Builder builder = new MultipartBody.Builder("AaB03x").setType(MultipartBody.FORM);
            for (File file:list) {
                Type type = FileUtil.fileType(file.getName());
                String[] name = file.getName().split("."+type.getType());
                builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse(type.getFileType()), file));
            }
            RequestBody requestBody =
                    builder
                    .addFormDataPart("inputUser", inputUser)
                    .addFormDataPart("TNId", NewID+"")
                    .build();

            // request方法： @param [String]URL, [RequestBody]requestBody
            Request request = new Request.Builder()
                    .url(requestURL)
                    .post(requestBody)
                    .build();

            // response储存服务器的回应
            Response response = okHttpClent.newCall(request).execute();
            // 把response转换成string
            uploadResult = response.body().string();
            LogUtil.e("onResponse22",uploadResult);
        } catch (IOException e) {
            uploadResult = 1+"";
        }
        return uploadResult;
    }


}
