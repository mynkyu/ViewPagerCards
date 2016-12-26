package com.github.rubensousa.viewpagercards;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private ArrayList<View> mViews = new ArrayList<View>();
    private float mBaseElevation;

    public CardPagerAdapter() {
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return (CardView) mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mViews.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        int index = mViews.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    public int addView(View v)
    {
        return addView(v, mViews.size());
    }

    public int addView(View v, int position)
    {
        mViews.add(position, v);
        return position;
    }

    public int removeView(ViewPager pager, View v)
    {
        return removeView(pager, mViews.indexOf(v));
    }

    public int removeView(ViewPager pager, int position)
    {
        pager.setAdapter(null);
        mViews.remove(position);
        pager.setAdapter(this);

        return position;
    }

    public View getView(int position) {
        return mViews.get(position);
    }
}
