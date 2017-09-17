package com.ebookfrenzy.transition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.animation.BounceInterpolator;



public class TransitionActivity extends AppCompatActivity {
    ViewGroup myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        myLayout = (ViewGroup) findViewById(R.id.myLayout);

        myLayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public boolean onTouch(View view, MotionEvent m) {
                        handleTouch();
                        return false;
                    }
                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleTouch() {
        View view = findViewById(R.id.myButton1);

        Transition changeBounds = new ChangeBounds();
        changeBounds.setDuration(3000);
        changeBounds.setInterpolator(new BounceInterpolator());

        TransitionManager.beginDelayedTransition(myLayout,changeBounds);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        view.setLayoutParams(params);

        ViewGroup.LayoutParams lparams = view.getLayoutParams();

        lparams.width = 500;
        lparams.height = 350;
        view.setLayoutParams(lparams);
    }
}
