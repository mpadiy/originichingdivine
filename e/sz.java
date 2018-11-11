package comp.android.e;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ViewFlipper;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class sz extends Activity {
    public float oldTouchValue;
    public ScrollView svpage1;
    public ScrollView svpage2;
    public ViewFlipper vf;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.sz);
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        ((LinearLayout) findViewById(R.id.ads)).addView(adView);
        adView.loadAd(new AdRequest());
        this.svpage1 = (ScrollView) findViewById(R.id.svsz1);
        this.svpage1.setVerticalScrollBarEnabled(false);
        this.svpage1.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    sz.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    sz.this.animation(sz.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        this.svpage2 = (ScrollView) findViewById(R.id.svsz2);
        this.svpage2.setVerticalScrollBarEnabled(false);
        this.svpage2.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    sz.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    sz.this.animation(sz.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        this.vf = (ViewFlipper) findViewById(R.id.myViewFlipperSZ);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.oldTouchValue = event.getX();
                break;
            case 1:
                animation(this.oldTouchValue, event.getX());
                break;
        }
        return false;
    }

    public void animation(float oldX, float currX) {
        if (oldX - currX < -280.0f) {
            this.vf.setInAnimation(AnimationUtils.loadAnimation(this, R.layout.linanim));
            this.vf.setOutAnimation(AnimationUtils.loadAnimation(this, R.layout.loutanim));
            this.vf.showPrevious();
        }
        if (oldX - currX > 280.0f) {
            this.vf.setInAnimation(AnimationUtils.loadAnimation(this, R.layout.rinanim));
            this.vf.setOutAnimation(AnimationUtils.loadAnimation(this, R.layout.routanim));
            this.vf.showNext();
        }
    }
}
