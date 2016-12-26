package com.github.rubensousa.viewpagercards;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import static com.github.rubensousa.viewpagercards.CardAdapter.MAX_ELEVATION_FACTOR;

public class MainActivity extends AppCompatActivity {

    private CustomViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (CustomViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter();
        mViewPager.setAdapter(mCardAdapter);

        LayoutInflater inflater = this.getLayoutInflater();
        View v0 = inflater.inflate(R.layout.adapter, null);
        CardView cardView = (CardView) v0.findViewById(R.id.cardView);

        float mBaseElevation = mCardAdapter.getBaseElevation();
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);

        mCardAdapter.addView(v0, 0);
        mCardAdapter.notifyDataSetChanged();
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                LayoutInflater inflater = this.getLayoutInflater();
                View v0 = inflater.inflate(R.layout.adapter, null);
                CardView cardView = (CardView) v0.findViewById(R.id.cardView);

                ImageView img = (ImageView) v0.findViewById(R.id.profile_img);
                img.setImageResource(R.drawable.blackvoice_2);

                float mBaseElevation = mCardAdapter.getBaseElevation();
                if (mBaseElevation == 0) {
                    mBaseElevation = cardView.getCardElevation();
                }

                cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);

                addView(v0);
                break;
            case R.id.btn_del:
                removeView((View) v.getParent().getParent().getParent());
                break;
        }
    }

    public void addView(View newPage) {
        int pageIndex = mCardAdapter.addView(newPage);
        mCardAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(pageIndex, true);
    }

    public void removeView(View defuncPage) {
        int pageIndex = mCardAdapter.removeView(mViewPager, defuncPage);

        if (pageIndex == mCardAdapter.getCount())
            pageIndex--;
        mViewPager.setCurrentItem(pageIndex);
    }

    public View getCurrentPage()
    {
        return mCardAdapter.getView( mViewPager.getCurrentItem() );
    }

    public void setCurrentItem(View pageToShow) {
        mViewPager.setCurrentItem(mCardAdapter.getItemPosition(pageToShow), true);
    }
}
