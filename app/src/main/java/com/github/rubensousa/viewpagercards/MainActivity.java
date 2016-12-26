package com.github.rubensousa.viewpagercards;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import static com.github.rubensousa.viewpagercards.CardAdapter.MAX_ELEVATION_FACTOR;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

//    private Button mButton;
    private CustomViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;

    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mButton = (Button) findViewById(R.id.cardTypeBtn);
//        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);
//        mButton.setOnClickListener(this);

        mViewPager = (CustomViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter();
        mViewPager.setAdapter(mCardAdapter);

        //        View view = LayoutInflater.from(container.getContext())
//                .inflate(R.layout.adapter, container, false);
//        container.addView(view);
//        CardView cardView = (CardView) view.findViewById(R.id.cardView);
//
//        if (mBaseElevation == 0) {
//            mBaseElevation = cardView.getCardElevation();
//        }
//
//        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
//        mViews.set(position, cardView);
//        return view;

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

//        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
//                dpToPixels(2, this));

//        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
//        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

//        mViewPager.setPageTransformer(false, mCardShadowTransformer);
//        mViewPager.setOffscreenPageLimit(3);
    }

    public void mOnClick(View v) {
        LayoutInflater inflater = this.getLayoutInflater();
        View v0 = inflater.inflate(R.layout.adapter, null);
        CardView cardView = (CardView) v0.findViewById(R.id.cardView);

        float mBaseElevation = mCardAdapter.getBaseElevation();
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);

        addView(v0);
    }

    @Override
    public void onClick(View view) {
        if (!mShowingFragments) {
//            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
//            mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
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
        return mCardAdapter.getView(mViewPager.getCurrentItem());
    }

    public void setCurrentItem(View pageToShow) {
        mViewPager.setCurrentItem(mCardAdapter.getItemPosition(pageToShow), true);
    }
}
