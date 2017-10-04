package com.google.developer.bugmaster.customViews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.google.developer.bugmaster.R;

//TODO: This class should be used in the insect list to display danger level
public class DangerLevelView extends android.support.v7.widget.AppCompatTextView {

    private int mDangerLevel;
    private Drawable mDrawable;
    private String[] mDangerColors;

    public DangerLevelView(Context context, int mDangerLevel) {
        super(context);
        init();
    }

    public DangerLevelView(Context context) {
        super(context);
        init();
    }

    public DangerLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DangerLevelView(Context context, AttributeSet attrs, int defStyleAttr, int mDangerLevel) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DangerLevelView(Context context, AttributeSet attrs, int defStyleAttr, int mDangerLevel, Drawable mDrawable, String[] mDangerColors) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DangerLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setDangerLevel(int dangerLevel) {
        //TODO: Update the view appropriately based on the level input

        setText(Integer.toString(dangerLevel));
        setTextColor(Color.parseColor("#FFFFFF"));
        mDangerLevel = dangerLevel;
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(Color.parseColor(mDangerColors[dangerLevel - 1]),
                PorterDuff.Mode.MULTIPLY));
    }

    public int getDangerLevel() {
        //TODO: Report the current level back as an integer
        return mDangerLevel;
    }

    private void init() {
        mDrawable = ContextCompat.getDrawable(getContext(), R.drawable.background_danger);
        mDangerColors = getResources().getStringArray(R.array.dangerColors);
        setBackground(mDrawable);
        setGravity(Gravity.CENTER);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
    }
}
