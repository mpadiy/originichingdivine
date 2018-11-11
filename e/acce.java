package comp.android.e;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class acce extends Activity implements SensorEventListener {
    private static final int FORCE_THRESHOLD_AXIS = 280;
    private static final int SHAKE_COUNT_AXIS = 4;
    private static final int SHAKE_DURATION = 1000;
    private static final int SHAKE_TIMEOUT = 300;
    private static final int TIME_THRESHOLD = 100;
    public Bundle bundle;
    int gua_num = 0;
    public Intent intent;
    private float last_x = 0.0f;
    private float last_y = 0.0f;
    private float last_z = 0.0f;
    private Sensor mAccelerometer;
    private long mLastForce;
    private long mLastShake;
    private long mLastTime;
    private final SensorEventListener mSensorLisener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == 2) {
                long now = System.currentTimeMillis();
                if (now - acce.this.mLastForce > 300) {
                    acce.this.mShakeCountAxis = 0;
                }
                if (now - acce.this.mLastTime > 100) {
                    long diffTime = now - acce.this.mLastTime;
                    acce.this.x = event.values[0];
                    acce.this.y = event.values[1];
                    acce.this.z = event.values[2];
                    float speedx = (Math.abs(acce.this.x - acce.this.last_x) / ((float) diffTime)) * 10000.0f;
                    float speedy = (Math.abs(acce.this.y - acce.this.last_y) / ((float) diffTime)) * 10000.0f;
                    float speedz = (Math.abs(acce.this.z - acce.this.last_z) / ((float) diffTime)) * 10000.0f;
                    acce.this.xc.setText(String.valueOf(((int) speedx) % 2));
                    acce.this.yc.setText(String.valueOf(((int) speedy) % 2));
                    acce.this.zc.setText(String.valueOf(((int) speedz) % 2));
                    if (speedz > 280.0f || speedy > 280.0f || !(speedx <= 280.0f || acce.this.last_x == 0.0f || acce.this.last_y == 0.0f || acce.this.last_z == 0.0f)) {
                        acce comp_android_e_acce = acce.this;
                        comp_android_e_acce.mShakeCountAxis = comp_android_e_acce.mShakeCountAxis + 1;
                        if (acce.this.mShakeCountAxis >= acce.SHAKE_COUNT_AXIS && now - acce.this.mLastShake > 1000) {
                            acce.this.mLastShake = now;
                            acce.this.mShakeCountAxis = 0;
                            acce.this.gua_num = gv.myRand.nextInt(64);
                            acce.this.bundle.putInt("gi", acce.this.gua_num);
                            acce.this.intent.putExtras(acce.this.bundle);
                            acce.this.setResult(-1, acce.this.intent);
                            acce.this.finish();
                        }
                        acce.this.mLastForce = now;
                    }
                    acce.this.mLastTime = now;
                    acce.this.last_x = event.values[0];
                    acce.this.last_y = event.values[1];
                    acce.this.last_z = event.values[2];
                }
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    private SensorManager mSensorManager01;
    private int mShakeCountAxis = 0;
    private float x;
    public TextView xc;
    private float y;
    public TextView yc;
    private float z;
    public TextView zc;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acce);
        this.mSensorManager01 = (SensorManager) getSystemService("sensor");
        this.intent = getIntent();
        this.bundle = this.intent.getExtras();
        this.gua_num = this.bundle.getInt("gi");
        this.xc = (TextView) findViewById(R.id.xcount);
        this.yc = (TextView) findViewById(R.id.ycount);
        this.zc = (TextView) findViewById(R.id.zcount);
    }

    public void onBackPressed() {
        this.gua_num = 65;
        this.bundle.putInt("gi", this.gua_num);
        this.intent.putExtras(this.bundle);
        setResult(-1, this.intent);
        finish();
    }

    protected void onResume() {
        List<Sensor> sensors = this.mSensorManager01.getSensorList(2);
        if (sensors.size() == 0) {
            Toast.makeText(this, getResources().getString(R.string.you_do_not_have_seosor), 1).show();
            this.gua_num = 65;
            this.bundle.putInt("gi", this.gua_num);
            this.intent.putExtras(this.bundle);
            setResult(-1, this.intent);
            finish();
        } else {
            this.mSensorManager01.registerListener(this.mSensorLisener, (Sensor) sensors.get(0), 0);
        }
        super.onResume();
    }

    protected void onPause() {
        this.mSensorManager01.unregisterListener(this.mSensorLisener);
        super.onPause();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    public void onSensorChanged(SensorEvent event) {
    }
}
