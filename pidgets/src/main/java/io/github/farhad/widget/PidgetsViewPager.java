package io.github.farhad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import io.github.farhad.R;
import java.lang.reflect.Field;

/**
 * Created by farhad on 9/15/16.
 */
public class PidgetsViewPager extends ViewPager {

  private static final int DEFAULT_SWIPE_DURATION = 500;

  private int swipeDurationMillis = DEFAULT_SWIPE_DURATION;
  private boolean enableManualSwipe = true;

  public PidgetsViewPager(Context context) {
    super(context);

    TypedArray typedArray = context.obtainStyledAttributes(R.styleable.PidgetsViewPager);

    initialize(context, typedArray);

    typedArray.recycle();
  }

  public PidgetsViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PidgetsViewPager);

    initialize(context, typedArray);

    typedArray.recycle();
  }

  public PidgetsViewPager(Context context, AttributeSet attributeSet, int defStyle) {
    super(context, attributeSet);

    TypedArray typedArray = context
        .obtainStyledAttributes(attributeSet, R.styleable.PidgetsViewPager, defStyle, defStyle);

    initialize(context, typedArray);

    typedArray.recycle();
  }

  private void initialize(Context context, TypedArray typedArray) {
    if (!isInEditMode()) {
      swipeDurationMillis = typedArray
          .getInt(R.styleable.PidgetsViewPager_swipeDurationMillis, DEFAULT_SWIPE_DURATION);
      enableManualSwipe = typedArray
          .getBoolean(R.styleable.PidgetsViewPager_enableManualSwipe, true);

      if (!enableManualSwipe) {
        changeManualSwipe(context);
      }
    }
  }

  private void changeManualSwipe(Context context) {
    try {
      Class<?> viewpager = ViewPager.class;
      Field scroller = viewpager.getDeclaredField("mScroller");
      scroller.setAccessible(true);
      FixedSpeedScroller mScroller = new FixedSpeedScroller(context, new DecelerateInterpolator());
      scroller.set(this, mScroller);
    } catch (Exception ignored) {
      ignored.printStackTrace();
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {

    return this.enableManualSwipe && super.onInterceptTouchEvent(event);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    return this.enableManualSwipe && super.onTouchEvent(event);
  }

  public boolean isManualSwipeEnabled() {
    return enableManualSwipe;
  }

  public void setEnableManualSwipe(boolean enableManualSwipe) {
    this.enableManualSwipe = enableManualSwipe;
  }

  public int getSwipeDurationMillis() {
    return swipeDurationMillis;
  }

  public void setSwipeDurationMillis(int swipeDurationMillis) {
    this.swipeDurationMillis = swipeDurationMillis;

    requestLayout();
  }

  private class FixedSpeedScroller extends Scroller {

    public FixedSpeedScroller(Context context) {
      super(context);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
      super(context, interpolator);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
      super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
      // Ignore received duration, use fixed one instead
      super.startScroll(startX, startY, dx, dy, swipeDurationMillis);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
      // Ignore received duration, use fixed one instead
      super.startScroll(startX, startY, dx, dy, swipeDurationMillis);
    }
  }
}
