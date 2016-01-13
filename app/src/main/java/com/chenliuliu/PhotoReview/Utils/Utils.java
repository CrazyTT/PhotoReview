package com.chenliuliu.PhotoReview.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long currentTime = System.currentTimeMillis();
        long timeDiffer = currentTime - lastClickTime;
        if (0 < timeDiffer && timeDiffer < 500) {
            return true;
        }
        lastClickTime = currentTime;
        return false;
    }

    public static int getGoalWidth(int percent, Context context) {
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        int width = display.widthPixels;
        return width * percent / 100;
    }

    public static int getGoalHeight(int percent, Context context) {
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        int height = display.heightPixels;
        return height * percent / 100;
    }

    /**
     * dip转换为px
     */
    public static int dipToPxInt(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * dip转换为px
     */
    public static float dipToPxFloat(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }

    /**
     * 给TextView内的文本加粗
     *
     * @param textView 目标view
     */
    public static void fakeBoldText(TextView textView) {
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
    }

    /**
     * 给TextView内的文本加粗
     *
     * @param button 目标view
     */
    public static void fakeBoldText(Button button) {
        TextPaint tp = button.getPaint();
        tp.setFakeBoldText(true);
    }

    /**
     * 将bitmap转化成byte组
     *
     * @param bmp
     * @param needRecycle
     * @return byte组
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String PHONE_REGEX = "^1[3|4|5|8|7][0-9]\\d{8}+$";

    public static boolean isPhoneNumber(String input) {
        Pattern p = Pattern.compile(PHONE_REGEX);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    public static String getCurrentVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }
}
