package com.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 340144 on 2018/1/12.
 * 存储登录账号密码信息
 *
 */

public  class SharedHelper {
    /**
     * 保存在手机里的SP文件名
     */
    private static final String FILE_NAME = "lsying";



    /**
     * 保存数据
     */
    public static void save(Context context, String key, Object obj) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else {
            editor.putString(key, (String) obj);
        }
        editor.apply();
    }

    /**
     * 获取指定数据
     */
    public static String get(Context context,String name) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return  sp.getString(name, "");

    }
//    public static Object get(Context context, String key, Object defaultObj) {
//        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
//        if (defaultObj instanceof Boolean) {
//            return sp.getBoolean(key, (Boolean) defaultObj);
//        } else if (defaultObj instanceof Float) {
//            return sp.getFloat(key, (Float) defaultObj);
//        } else if (defaultObj instanceof Integer) {
//            return sp.getInt(key, (Integer) defaultObj);
//        } else if (defaultObj instanceof Long) {
//            return sp.getLong(key, (Long) defaultObj);
//        } else if (defaultObj instanceof String) {
//            return sp.getString(key, (String) defaultObj);
//        }
//        return null;
//    }

    /**
     * 删除指定数据
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 删除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * MD5加密  以后再加入0.0
     */

    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }
}
