package ir.protelco.pidget.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by farhad on 9/15/16.
 */
public class ManualViewPager extends ViewPager
{
    private boolean scrollEnabled = false ;
    private FixedSpeedScroller mScroller ;

    public ManualViewPager(Context context)
    {
        super(context);

        setScrollEnabled(true);

        init();
    }

    public ManualViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        setScrollEnabled(true);

        init();
    }

    private void init()
    {
        try
        {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            mScroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator());
            scroller.set(this, mScroller);
        }

        catch (Exception ignored)
        {

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        if (this.scrollEnabled)
        {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (this.scrollEnabled)
        {
            return super.onTouchEvent(event);
        }

        return false;
    }

    public boolean isScrollEnabled() {
        return scrollEnabled;
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.scrollEnabled = scrollEnabled;
    }






    private class FixedSpeedScroller extends Scroller
    {
        private int mDuration = 300;

        public FixedSpeedScroller(Context context)
        {
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
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setScrollDuration(int duration) {
            mDuration = duration;
        }
    }
}
