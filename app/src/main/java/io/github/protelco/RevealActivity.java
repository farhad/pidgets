package io.github.protelco;

import android.animation.Animator;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.BoolRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

public class RevealActivity extends Activity {

    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reveal);
        textView = (TextView) findViewById(R.id.reveal_content);

        textView.post(new Runnable() {
            @Override
            public void run() {
            	/*
            	why am I handling older android versions here?
            	I don't know. Maybe because I'm douche.
            	*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    /*
                    We've passed the x and y coordinate
                    from previous activity, to reveal
                    layout from exact place where it was tapped
                    */
                    int x = getIntent().getIntExtra("x", 0);
                    int y = getIntent().getIntExtra("y", 0);
                    Animator animator = createRevealAnimator(false, x, y);
                    animator.start();
                }
                textView.setVisibility(View.VISIBLE);
            }
        });

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator =
                            createRevealAnimator(
                                    true,
                                    (int) event.getX(),
                                    (int) event.getY());
                    animator.start();
                } else {
                    finish();
                }
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator createRevealAnimator(boolean reversed, int x, int y) {

        float hypot = (float) Math.hypot(textView.getHeight(), textView.getWidth());
        float startRadius = reversed ? hypot : 0;
        float endRadius = reversed ? 0 : hypot;
        Animator animator = ViewAnimationUtils.createCircularReveal(
                textView, x, y, //center position of the animation
                startRadius,
                endRadius);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (reversed)
            animator.addListener(animatorListener);
        return animator;
    }
    //we add listener if it's revered to handle activity finish
    private Animator.AnimatorListener animatorListener =
            new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    //to remove default lollipop animation
                    textView.setVisibility(View.INVISIBLE);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            };
}
