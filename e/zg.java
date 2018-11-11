package comp.android.e;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class zg extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.zg);
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        ((LinearLayout) findViewById(R.id.ads)).addView(adView);
        adView.loadAd(new AdRequest());
        ((ScrollView) findViewById(R.id.svpage)).setVerticalScrollBarEnabled(false);
    }
}
