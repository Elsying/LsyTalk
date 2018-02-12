package com.Utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 340144 on 2018/1/10.
 * 用于请求后台和读取数据
 */

public class GetUtils {


    public static String logGet(String urls,String account, String password) {
        HttpURLConnection con=null;
        InputStream is = null;
        try {
            String data = "account=" + account + "&password=" + password;
            Log.v("GetUtils",account);
            Log.v("GetUtils",password);
            URL url = new URL(urls + "?" + data);
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式
            con.setConnectTimeout(100000);
            // 读数据的超时时间
            con.setReadTimeout(5000);
            if (con.getResponseCode()==200){
                //连接成功

                 is=con.getInputStream();
                 byte[] datas = getStringInputStream(is);
                 String html = new String(datas, "UTF-8");
                 return html;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                con.disconnect(); // 关闭连接
            }
        }
        return null;
    }
//    // 将输入流转化为 String 型
//    private static String parseInfo(InputStream inStream) throws Exception {
//        byte[] data = getStringInputStream(inStream);
//        // 转化为字符串
//        return new String(data, "UTF-8");
//    }

   /**
   *从流中读取数据
   **/
    public static byte[] getStringInputStream(InputStream is)throws IOException{

        ByteArrayOutputStream bs=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        while ((len=is.read(buffer))!=-1){           //忘记读取缓冲区0.0
            bs.write(buffer,0,len);
        }

        is.close();
        //bs.close();
        return bs.toByteArray();

    }


}
