package com.chenliuliu.PhotoReview.activitys;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chenliuliu.PhotoReview.R;
import com.chenliuliu.PhotoReview.Utils.Utils;
import com.chenliuliu.PhotoReview.adapter.RecommendAdapter;
import com.chenliuliu.PhotoReview.bean.TestBean;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;


public class GiftActivity extends Activity {

    private ViewPager viewpage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        applyKitKatTranslucency();
    }


    private void initViews() {
        ((TextView) findViewById(R.id.title_tv)).setText("图片预览");
        findViewById(R.id.title_right_btn).setVisibility(View.GONE);
        viewpage = (ViewPager) findViewById(R.id.recommend_viewpager);
        ArrayList<TestBean> list = new ArrayList<>();
        list.add(new TestBean("http://e.hiphotos.baidu.com/image/pic/item/8c1001e93901213f6c2cdc0256e736d12e2e95d5.jpg", "陈柳柳1", "陈柳柳1"));
        list.add(new TestBean("http://g.hiphotos.baidu.com/image/pic/item/a50f4bfbfbedab64dd596a2ef336afc379311e30.jpg", "陈柳柳2", "陈柳柳2"));
        list.add(new TestBean("http://f.hiphotos.baidu.com/image/pic/item/8b13632762d0f703147406020afa513d2797c52e.jpg", "陈柳柳3", "陈柳柳3"));
        list.add(new TestBean("http://h.hiphotos.baidu.com/image/pic/item/503d269759ee3d6d4a56a32547166d224e4ade4f.jpg", "陈柳柳4", "陈柳柳4"));
        list.add(new TestBean("http://f.hiphotos.baidu.com/image/pic/item/18d8bc3eb13533fa6f1f18c3acd3fd1f40345bb6.jpg", "陈柳柳5", "陈柳柳5"));
        list.add(new TestBean("http://e.hiphotos.baidu.com/image/pic/item/fcfaaf51f3deb48ff04f8630f41f3a292df5783d.jpg", "陈柳柳6", "陈柳柳6"));
        list.add(new TestBean("http://f.hiphotos.baidu.com/image/pic/item/730e0cf3d7ca7bcb2793e3c1ba096b63f724a877.jpg", "陈柳柳7", "陈柳柳7"));
        list.add(new TestBean("http://a.hiphotos.baidu.com/image/pic/item/0b46f21fbe096b63c60179be08338744eaf8acc7.jpg", "陈柳柳8", "陈柳柳8"));
        list.add(new TestBean("http://h.hiphotos.baidu.com/image/pic/item/c995d143ad4bd1130c0ee8e55eafa40f4afb0521.jpg", "陈柳柳9", "陈柳柳9"));
        list.add(new TestBean("http://a.hiphotos.baidu.com/image/pic/item/0df3d7ca7bcb0a462ad7d6426863f6246a60afd0.jpg", "陈柳柳10", "陈柳柳10"));
        list.add(new TestBean("http://a.hiphotos.baidu.com/image/pic/item/b3b7d0a20cf431ad7b7ec8d44f36acaf2fdd98ab.jpg", "陈柳柳11", "陈柳柳11"));
        setView(list);
    }


    public void setView(List<TestBean> data) {
        viewpage.setAdapter(new RecommendAdapter(this, data));
        viewpage.setOffscreenPageLimit(2);
        // 设置viewpager每页margin和padding,页面显示类似画廊模式
        viewpage.setPageMargin(Utils.dipToPxInt(this, 20));
        viewpage.setPadding(Utils.dipToPxInt(this, 40), 0, Utils.dipToPxInt(this, 40), 0);
        viewpage.setClipToPadding(false);
       // viewpage.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    private void applyKitKatTranslucency() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setNavigationBarTintEnabled(true);
            // mTintManager.setTintColor(0x63AE0A);
            mTintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.backgroud_title_state));

        }

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
