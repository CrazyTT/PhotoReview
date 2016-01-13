package com.chenliuliu.PhotoReview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenliuliu.PhotoReview.R;
import com.chenliuliu.PhotoReview.bean.TestBean;

import java.util.List;


/**
 * Created by Simon.Wu on 2015/5/19.
 */
public class RecommendAdapter extends PagerAdapter {
    private Context mContext;
    private List<TestBean> objects;

    public RecommendAdapter(Context context, List<TestBean> objects) {
        this.mContext = context;
        this.objects = objects;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View reusableView = LayoutInflater.from(mContext).inflate(R.layout.item_recommend, container, false);
        TestBean item = objects.get(position);
        Glide.with(mContext).load(item.getUrl()).override(500, 500).into((ImageView) reusableView.findViewById(R.id.recommend_iv));
        ((TextView) reusableView.findViewById(R.id.recommend_title_tv)).setText(item.getTitle());
        ((TextView) reusableView.findViewById(R.id.recommend_content_tv)).setText(item.getContent());
        container.addView(reusableView);
        return reusableView;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView((View) object);
    }

}
