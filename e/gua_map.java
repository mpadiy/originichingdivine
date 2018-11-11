package comp.android.e;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import java.lang.reflect.Array;

public class gua_map extends Activity {
    public static gua_map_view _panel;
    public static boolean _run = false;
    public static SurfaceHolder _surfaceHolder;
    public static int beforeWidth;
    public static Handler guaMapHandler;
    public static HandlerThread guaMapThread;
    public static LinearLayout layout;
    public static long timePeriod;
    public static long timeStart;
    public static long timeStop;
    public String DB_PATH_NAME;
    public Integer[][] _little_guaId;
    public Bitmap bmp64;
    public int bmpCenX = 0;
    public int bmpCenY = 0;
    public Bitmap bmpFit;
    public int bmpLeftConX = 0;
    public int bmpLeftConY = 0;
    public Bundle bundle;
    public Button chmapbutton;
    public final double deltaDegree = 5.625d;
    public DisplayMetrics dm;
    public String[] e_bin = null;
    public String[] e_id = null;
    public MySQLiteOpenHelper edb = null;
    public int guaCircleNow = 63;
    public float[][] guaPath = ((float[][]) Array.newInstance(Float.TYPE, new int[]{64, 2}));
    public int[] guaTranslate = new int[64];
    public TextView gua_name;
    public int heightOrig;
    public Intent intent;
    public Button leavebutton;
    public final Integer[] littleGuaId2 = new Integer[]{Integer.valueOf(R.drawable.hex000000), Integer.valueOf(R.drawable.hex000001), Integer.valueOf(R.drawable.hex000010), Integer.valueOf(R.drawable.hex000011), Integer.valueOf(R.drawable.hex000100), Integer.valueOf(R.drawable.hex000101), Integer.valueOf(R.drawable.hex000110), Integer.valueOf(R.drawable.hex000111), Integer.valueOf(R.drawable.hex001000), Integer.valueOf(R.drawable.hex001001), Integer.valueOf(R.drawable.hex001010), Integer.valueOf(R.drawable.hex001011), Integer.valueOf(R.drawable.hex001100), Integer.valueOf(R.drawable.hex001101), Integer.valueOf(R.drawable.hex001110), Integer.valueOf(R.drawable.hex001111), Integer.valueOf(R.drawable.hex010000), Integer.valueOf(R.drawable.hex010001), Integer.valueOf(R.drawable.hex010010), Integer.valueOf(R.drawable.hex010011), Integer.valueOf(R.drawable.hex010100), Integer.valueOf(R.drawable.hex010101), Integer.valueOf(R.drawable.hex010110), Integer.valueOf(R.drawable.hex010111), Integer.valueOf(R.drawable.hex011000), Integer.valueOf(R.drawable.hex011001), Integer.valueOf(R.drawable.hex011010), Integer.valueOf(R.drawable.hex011011), Integer.valueOf(R.drawable.hex011100), Integer.valueOf(R.drawable.hex011101), Integer.valueOf(R.drawable.hex011110), Integer.valueOf(R.drawable.hex011111), Integer.valueOf(R.drawable.hex100000), Integer.valueOf(R.drawable.hex100001), Integer.valueOf(R.drawable.hex100010), Integer.valueOf(R.drawable.hex100011), Integer.valueOf(R.drawable.hex100100), Integer.valueOf(R.drawable.hex100101), Integer.valueOf(R.drawable.hex100110), Integer.valueOf(R.drawable.hex100111), Integer.valueOf(R.drawable.hex101000), Integer.valueOf(R.drawable.hex101001), Integer.valueOf(R.drawable.hex101010), Integer.valueOf(R.drawable.hex101011), Integer.valueOf(R.drawable.hex101100), Integer.valueOf(R.drawable.hex101101), Integer.valueOf(R.drawable.hex101110), Integer.valueOf(R.drawable.hex101111), Integer.valueOf(R.drawable.hex110000), Integer.valueOf(R.drawable.hex110001), Integer.valueOf(R.drawable.hex110010), Integer.valueOf(R.drawable.hex110011), Integer.valueOf(R.drawable.hex110100), Integer.valueOf(R.drawable.hex110101), Integer.valueOf(R.drawable.hex110110), Integer.valueOf(R.drawable.hex110111), Integer.valueOf(R.drawable.hex111000), Integer.valueOf(R.drawable.hex111001), Integer.valueOf(R.drawable.hex111010), Integer.valueOf(R.drawable.hex111011), Integer.valueOf(R.drawable.hex111100), Integer.valueOf(R.drawable.hex111101), Integer.valueOf(R.drawable.hex111110), Integer.valueOf(R.drawable.hex111111)};
    public float mTouchX0;
    public float mTouchX1;
    public float mTouchY0;
    public float mTouchY1;
    private AnimationSet manimationSet;
    public Matrix matrix = new Matrix();
    public PointF mid;
    public float midx = 0.0f;
    public float midy = 0.0f;
    public float mmTouchX0;
    public float mmTouchX1;
    public float mmTouchY0;
    public float mmTouchY1;
    public int mode = 0;
    public int movingDistX = 0;
    public int movingDistXStart = 0;
    public int movingDistY = 0;
    public int movingDistYStart = 0;
    public boolean movingFlag = false;
    public float movingStepX = 0.0f;
    public float movingStepY = 0.0f;
    public float oldDist;
    final Options options = new Options();
    public Paint paint = new Paint();
    public Path path = new Path();
    public float picScale;
    public Runnable r1 = new Runnable() {
        public void run() {
            while (gua_map._run) {
                Canvas c = gua_map._surfaceHolder.lockCanvas(null);
                if (gua_map.this.movingFlag) {
                    gua_map comp_android_e_gua_map;
                    if (gua_map.this.bmpLeftConX < 0) {
                        gua_map.this.bmpLeftConX = 0;
                        comp_android_e_gua_map = gua_map.this;
                        comp_android_e_gua_map.movingStepX *= -1.0f;
                    } else if (gua_map.this.bmpLeftConX + gua_map.this.bmpFit.getWidth() > gv.disWidth) {
                        gua_map.this.bmpLeftConX = gv.disWidth - gua_map.this.bmpFit.getWidth();
                        comp_android_e_gua_map = gua_map.this;
                        comp_android_e_gua_map.movingStepX *= -1.0f;
                    }
                    if (gua_map.this.bmpLeftConY < 0) {
                        gua_map.this.bmpLeftConY = 0;
                        comp_android_e_gua_map = gua_map.this;
                        comp_android_e_gua_map.movingStepY *= -1.0f;
                    } else if (gua_map.this.bmpLeftConY + gua_map.this.bmpFit.getHeight() > gv.disHeight) {
                        gua_map.this.bmpLeftConY = gv.disHeight - gua_map.this.bmpFit.getHeight();
                        comp_android_e_gua_map = gua_map.this;
                        comp_android_e_gua_map.movingStepY *= -1.0f;
                    }
                    comp_android_e_gua_map = gua_map.this;
                    comp_android_e_gua_map.bmpLeftConX += (int) gua_map.this.movingStepX;
                    comp_android_e_gua_map = gua_map.this;
                    comp_android_e_gua_map.bmpLeftConY += (int) gua_map.this.movingStepY;
                }
                synchronized (gua_map._surfaceHolder) {
                    gua_map._panel.onDraw(c);
                }
                if (c != null) {
                    gua_map._surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    };
    public float radius = 0.0f;
    public Matrix saveMatrix = new Matrix();
    public float speedX = 0.0f;
    public float speedY = 0.0f;
    public int widthOrig;
    public int xDisMove = 0;
    public float xScale;
    public int yDisMove = 0;
    public float yScale;

    class gua_map_view extends SurfaceView implements Callback {
        public int _x = 0;
        public int _y = 0;
        public int xDis = 0;
        public int x_ = 0;
        public int yDis = 0;
        public int y_ = 0;

        public gua_map_view(Context context) {
            super(context);
            getHolder().addCallback(this);
            gua_map._surfaceHolder = getHolder();
            gua_map._panel = this;
            setFocusable(true);
            gua_map.this.paint.setStyle(Style.STROKE);
            gua_map.this.paint.setStrokeWidth(3.0f);
            gua_map.this.paint.setTextSize(24.0f);
        }

        protected void onDraw(Canvas canvas) {
            int i;
            super.onDraw(canvas);
            canvas.drawColor(Color.rgb(32, 32, 32));
            gua_map.this.bmpCenX = gua_map.this.bmpFit.getWidth() / 2;
            gua_map.this.bmpCenY = gua_map.this.bmpFit.getHeight() / 2;
            for (i = 1; i <= 64; i++) {
                double rad = Math.toRadians(5.625d * (((double) i) - 0.5d));
                float tmpY = ((float) (gua_map.this.bmpLeftConY + gua_map.this.bmpCenY)) + ((float) (((double) gua_map.this.radius) * Math.sin(rad)));
                gua_map.this.guaPath[gua_map.this.guaTranslate[i - 1]][0] = ((float) (gua_map.this.bmpLeftConX + gua_map.this.bmpCenX)) + ((float) (((double) gua_map.this.radius) * Math.cos(rad)));
                gua_map.this.guaPath[gua_map.this.guaTranslate[i - 1]][1] = tmpY;
            }
            canvas.drawBitmap(gua_map.this.bmpFit, (float) gua_map.this.bmpLeftConX, (float) gua_map.this.bmpLeftConY, null);
            for (i = 1; i <= 64; i++) {
                gua_map.this.paint.setStrokeWidth(10.0f);
                gua_map.this.paint.setColor(Color.rgb(43, 198, 113));
                canvas.drawPoint(gua_map.this.guaPath[gua_map.this.guaTranslate[i - 1]][0], gua_map.this.guaPath[gua_map.this.guaTranslate[i - 1]][1], gua_map.this.paint);
            }
            Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
            c.moveToFirst();
            int gua_num = Integer.parseInt(c.getString(1), 2);
            float startX = gua_map.this.guaPath[gua_num][0];
            float startY = gua_map.this.guaPath[gua_num][1];
            gua_map.this.path.reset();
            gua_map.this.path.moveTo(gua_map.this.guaPath[gua_num][0], gua_map.this.guaPath[gua_num][1]);
            do {
                gua_num = Integer.parseInt(c.getString(1), 2);
                gua_map.this.path.lineTo(gua_map.this.guaPath[gua_num][0], gua_map.this.guaPath[gua_num][1]);
                gua_map.this.paint.setColor(Color.rgb(218, 56, 140));
                canvas.drawCircle(gua_map.this.guaPath[gua_num][0], gua_map.this.guaPath[gua_num][1], 6.0f, gua_map.this.paint);
            } while (c.moveToNext());
            c.close();
            gua_map.this.paint.setStrokeWidth(5.0f);
            gua_map.this.path.lineTo(startX, startY);
            gua_map.this.paint.setColor(Color.rgb(218, 56, 140));
            canvas.drawPath(gua_map.this.path, gua_map.this.paint);
            gua_map.this.paint.setStrokeWidth(6.0f);
            gua_map.this.paint.setColor(Color.rgb(0, 150, 207));
            canvas.drawCircle(gua_map.this.guaPath[gua_map.this.guaCircleNow][0], gua_map.this.guaPath[gua_map.this.guaCircleNow][1], 16.0f, gua_map.this.paint);
        }

        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & 255) {
                case 0:
                    gua_map.this.movingFlag = false;
                    this._x = (int) event.getX();
                    this._y = (int) event.getY();
                    gua_map.this.movingDistXStart = this._x;
                    gua_map.this.movingDistYStart = this._y;
                    gua_map.timeStart = System.currentTimeMillis();
                    gua_map.this.mode = 1;
                    break;
                case 1:
                    gua_map.timeStop = System.currentTimeMillis();
                    gua_map.timePeriod = gua_map.timeStop - gua_map.timeStart;
                    gua_map.this.movingDistX = ((int) event.getX()) - gua_map.this.movingDistXStart;
                    gua_map.this.movingDistY = ((int) event.getY()) - gua_map.this.movingDistYStart;
                    if (gua_map.timePeriod != 0) {
                        gua_map.this.movingFlag = true;
                        gua_map.this.speedX = ((float) gua_map.this.movingDistX) / ((float) gua_map.timePeriod);
                        gua_map.this.speedY = ((float) gua_map.this.movingDistY) / ((float) gua_map.timePeriod);
                        gua_map.this.movingStepX = gua_map.this.speedX * 10.0f;
                        gua_map.this.movingStepY = gua_map.this.speedY * 10.0f;
                    }
                    gua_map.this.mode = 0;
                    break;
                case AnalyticsGmsCoreClient.REMOTE_EXECUTION_FAILED /*2*/:
                    if (gua_map.this.mode != 1) {
                        if (gua_map.this.mode == 2) {
                            gua_map.this.mmTouchX0 = event.getX(0);
                            gua_map.this.mmTouchY0 = event.getY(0);
                            gua_map.this.mmTouchX1 = event.getX(1);
                            gua_map.this.mmTouchY1 = event.getY(1);
                            float x2 = gua_map.this.mmTouchX0 - gua_map.this.mmTouchX1;
                            float y2 = gua_map.this.mmTouchY0 - gua_map.this.mmTouchY1;
                            float dist = FloatMath.sqrt((x2 * x2) + (y2 * y2)) - gua_map.this.oldDist;
                            gua_map.this.picScale = (((float) gua_map.this.bmpFit.getWidth()) + dist) / ((float) gua_map.this.widthOrig);
                            if (((float) gua_map.this.bmpFit.getWidth()) + dist >= ((float) gv.disWidth)) {
                                gua_map.this.picScale = ((float) gv.disWidth) / ((float) gua_map.this.widthOrig);
                            }
                            if (((float) gua_map.this.bmpFit.getWidth()) + dist < 0.1f * ((float) gua_map.this.widthOrig)) {
                                gua_map.this.picScale = 0.1f;
                            }
                            gua_map.this.matrix.reset();
                            gua_map.this.matrix.postScale(gua_map.this.picScale, gua_map.this.picScale);
                            gua_map.this.bmpFit = Bitmap.createBitmap(gua_map.this.bmp64, 0, 0, gua_map.this.widthOrig, gua_map.this.heightOrig, gua_map.this.matrix, true);
                            gua_map.this.radius = (float) (gua_map.this.bmpFit.getWidth() / 2);
                            gua_map.this.bmpLeftConX = (gua_map.this.bmpLeftConX + (((int) (gua_map.this.mmTouchX0 - gua_map.this.mTouchX0)) / 2)) - (((int) (gua_map.this.mmTouchX1 - gua_map.this.mTouchX1)) / 2);
                            gua_map.this.bmpLeftConY = (gua_map.this.bmpLeftConY + (((int) (gua_map.this.mmTouchY0 - gua_map.this.mTouchY0)) / 2)) + (((int) (gua_map.this.mmTouchY1 - gua_map.this.mTouchY1)) / 2);
                            gua_map.this.mTouchX0 = gua_map.this.mmTouchX0;
                            gua_map.this.mTouchY0 = gua_map.this.mmTouchY0;
                            gua_map.this.mTouchX1 = gua_map.this.mmTouchX1;
                            gua_map.this.mTouchY1 = gua_map.this.mmTouchY1;
                            float x3 = gua_map.this.mTouchX0 - gua_map.this.mTouchX1;
                            float y3 = gua_map.this.mTouchY0 - gua_map.this.mTouchY1;
                            gua_map.this.oldDist = FloatMath.sqrt((x3 * x3) + (y3 * y3));
                            break;
                        }
                    }
                    gua_map.this.bmpLeftConX = (((int) event.getX()) - this._x) + gua_map.this.bmpLeftConX;
                    gua_map.this.bmpLeftConY = (((int) event.getY()) - this._y) + gua_map.this.bmpLeftConY;
                    this._x = (int) event.getX();
                    this._y = (int) event.getY();
                    break;
                    break;
                case 5:
                    gua_map.this.mTouchX0 = event.getX(0);
                    gua_map.this.mTouchY0 = event.getY(0);
                    gua_map.this.mTouchX1 = event.getX(1);
                    gua_map.this.mTouchY1 = event.getY(1);
                    float x1 = gua_map.this.mTouchX0 - gua_map.this.mTouchX1;
                    float y1 = gua_map.this.mTouchY0 - gua_map.this.mTouchY1;
                    gua_map.this.oldDist = FloatMath.sqrt((x1 * x1) + (y1 * y1));
                    gua_map.this.mode = 2;
                    break;
                case 6:
                    gua_map.this.mode = 0;
                    break;
            }
            return true;
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        public void surfaceCreated(SurfaceHolder holder) {
            gua_map._run = true;
            gua_map.guaMapHandler.post(gua_map.this.r1);
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            if (gua_map.guaMapHandler != null) {
                gua_map._run = false;
                gua_map.guaMapHandler.removeCallbacks(gua_map.this.r1);
            }
            if (gua_map.guaMapThread != null) {
                gua_map.guaMapThread.quit();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        setContentView(R.layout.gua_map);
        gv.guaMapThreadOut = false;
        guaMapThread = new HandlerThread("print gua circle");
        guaMapThread.start();
        guaMapHandler = new Handler(guaMapThread.getLooper());
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        layout = (LinearLayout) findViewById(R.id.ads);
        layout.addView(adView);
        adView.loadAd(new AdRequest());
        this.gua_name = (TextView) findViewById(R.id.gua_name);
        this.intent = getIntent();
        this.bundle = getIntent().getExtras();
        this.DB_PATH_NAME = this.bundle.getString("dbPath");
        Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
        c.moveToLast();
        int lastOne = Integer.parseInt(c.getString(0));
        Integer[] little_guaId = new Integer[lastOne];
        this._little_guaId = (Integer[][]) Array.newInstance(Integer.class, new int[]{lastOne, 2});
        int i = 0;
        c.moveToFirst();
        do {
            int gua_num = Integer.parseInt(c.getString(1), 2);
            little_guaId[i] = this.littleGuaId2[gua_num];
            this._little_guaId[i][0] = Integer.valueOf(i);
            this._little_guaId[i][1] = Integer.valueOf(gua_num);
            i++;
        } while (c.moveToNext());
        c.close();
        this.options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.kan64, this.options);
        this.options.inSampleSize = e_lib.calculateInSampleSize(this.options, gv.disWidth, gv.disHeight);
        this.options.inJustDecodeBounds = false;
        this.bmp64 = BitmapFactory.decodeResource(getResources(), R.drawable.kan64, this.options);
        this.guaTranslate = gv.guaTranslateKan64;
        this.gua_name.setText(getResources().getString(R.string.邵雍圓圖));
        this.widthOrig = this.bmp64.getWidth();
        this.heightOrig = this.bmp64.getHeight();
        this.picScale = ((float) gv.disWidth) / ((float) this.widthOrig);
        this.matrix.reset();
        this.matrix.postScale(this.picScale, this.picScale);
        this.bmpFit = this.bmp64;
        this.radius = ((float) this.bmpFit.getWidth()) / 2.0f;
        gua_map_view gua_canvas = new gua_map_view(this);
        this.leavebutton = (Button) findViewById(R.id.leave);
        this.leavebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gua_map._run = false;
                if (gua_map.guaMapHandler != null) {
                    gua_map._run = false;
                    gua_map.guaMapHandler.removeCallbacks(gua_map.this.r1);
                }
                if (gua_map.guaMapThread != null) {
                    gua_map.guaMapThread.quit();
                }
                Bundle bundle = new Bundle();
                bundle.putString("dbPath", gua_map.this.DB_PATH_NAME);
                gua_map.this.intent.putExtras(bundle);
                gua_map.this.setResult(-1, gua_map.this.intent);
                gua_map.this.finish();
            }
        });
        this.chmapbutton = (Button) findViewById(R.id.btn_image);
        this.chmapbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CharSequence[] items = new CharSequence[]{gua_map.this.getResources().getString(R.string.邵雍圓圖), gua_map.this.getResources().getString(R.string.孟喜圓圖), gua_map.this.getResources().getString(R.string.來氏圓圖), gua_map.this.getResources().getString(R.string.京房圓圖), gua_map.this.getResources().getString(R.string.人道圓圖)};
                Builder builder = new Builder(gua_map.this);
                builder.setTitle(gua_map.this.getResources().getString(R.string.選擇圓圖));
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        gua_map.this.options.inJustDecodeBounds = true;
                        if (item == 0) {
                            BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.kan64, gua_map.this.options);
                            gua_map.this.options.inSampleSize = e_lib.calculateInSampleSize(gua_map.this.options, gv.disWidth, gv.disHeight);
                            gua_map.this.options.inJustDecodeBounds = false;
                            gua_map.this.bmp64 = BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.kan64, gua_map.this.options);
                            gua_map.this.guaTranslate = gv.guaTranslateKan64;
                            gua_map.this.gua_name.setText(gua_map.this.getResources().getString(R.string.邵雍圓圖));
                            gua_map.this.widthOrig = gua_map.this.bmp64.getWidth();
                            gua_map.this.heightOrig = gua_map.this.bmp64.getHeight();
                            gua_map.this.bmpFit = gua_map.this.bmp64;
                            gua_map.this.radius = ((float) gua_map.this.bmpFit.getWidth()) / 2.0f;
                        } else if (item == 1) {
                            BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.monsi64, gua_map.this.options);
                            gua_map.this.options.inSampleSize = e_lib.calculateInSampleSize(gua_map.this.options, gv.disWidth, gv.disHeight);
                            gua_map.this.options.inJustDecodeBounds = false;
                            gua_map.this.bmp64 = BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.monsi64, gua_map.this.options);
                            gua_map.this.guaTranslate = gv.guaTranslateMonsi64;
                            gua_map.this.gua_name.setText(gua_map.this.getResources().getString(R.string.孟喜圓圖));
                            gua_map.this.widthOrig = gua_map.this.bmp64.getWidth();
                            gua_map.this.heightOrig = gua_map.this.bmp64.getHeight();
                            gua_map.this.bmpFit = gua_map.this.bmp64;
                            gua_map.this.radius = ((float) gua_map.this.bmpFit.getWidth()) / 2.0f;
                        } else if (item == 2) {
                            BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.lis64, gua_map.this.options);
                            gua_map.this.options.inSampleSize = e_lib.calculateInSampleSize(gua_map.this.options, gv.disWidth, gv.disHeight);
                            gua_map.this.options.inJustDecodeBounds = false;
                            gua_map.this.bmp64 = BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.lis64, gua_map.this.options);
                            gua_map.this.guaTranslate = gv.guaTranslateLis64;
                            gua_map.this.gua_name.setText(gua_map.this.getResources().getString(R.string.來氏圓圖));
                            gua_map.this.widthOrig = gua_map.this.bmp64.getWidth();
                            gua_map.this.heightOrig = gua_map.this.bmp64.getHeight();
                            gua_map.this.bmpFit = gua_map.this.bmp64;
                            gua_map.this.radius = ((float) gua_map.this.bmpFit.getWidth()) / 2.0f;
                        } else if (item == 3) {
                            BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.gifun64, gua_map.this.options);
                            gua_map.this.options.inSampleSize = e_lib.calculateInSampleSize(gua_map.this.options, gv.disWidth, gv.disHeight);
                            gua_map.this.options.inJustDecodeBounds = false;
                            gua_map.this.bmp64 = BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.gifun64, gua_map.this.options);
                            gua_map.this.guaTranslate = gv.guaTranslateGifun64;
                            gua_map.this.gua_name.setText(gua_map.this.getResources().getString(R.string.京房圓圖));
                            gua_map.this.widthOrig = gua_map.this.bmp64.getWidth();
                            gua_map.this.heightOrig = gua_map.this.bmp64.getHeight();
                            gua_map.this.bmpFit = gua_map.this.bmp64;
                            gua_map.this.radius = ((float) gua_map.this.bmpFit.getWidth()) / 2.0f;
                        } else if (item == 4) {
                            BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.zen_dau64, gua_map.this.options);
                            gua_map.this.options.inSampleSize = e_lib.calculateInSampleSize(gua_map.this.options, gv.disWidth, gv.disHeight);
                            gua_map.this.options.inJustDecodeBounds = false;
                            gua_map.this.bmp64 = BitmapFactory.decodeResource(gua_map.this.getResources(), R.drawable.zen_dau64, gua_map.this.options);
                            gua_map.this.guaTranslate = gv.guaTranslateZendau64;
                            gua_map.this.gua_name.setText(gua_map.this.getResources().getString(R.string.人道圓圖));
                            gua_map.this.widthOrig = gua_map.this.bmp64.getWidth();
                            gua_map.this.heightOrig = gua_map.this.bmp64.getHeight();
                            gua_map.this.bmpFit = gua_map.this.bmp64;
                            gua_map.this.radius = ((float) gua_map.this.bmpFit.getWidth()) / 2.0f;
                        }
                    }
                }).create().show();
            }
        });
        Gallery gallery = new Gallery(this);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        imageAdapter.setmImageIds(little_guaId);
        imageAdapter.setHeight(Integer.valueOf(90));
        imageAdapter.setWidth(Integer.valueOf(72));
        gallery.setAdapter(imageAdapter);
        gallery.setLayoutParams(new LayoutParams(-1, -2));
        gallery.setSpacing(5);
        gallery.setBackgroundResource(R.drawable.gallery_bg);
        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ScaleAnimation scaleAnimation;
                gua_map.this.guaCircleNow = gua_map.this._little_guaId[position][1].intValue();
                AnimationSet animationSet = new AnimationSet(true);
                if (!(gua_map.this.manimationSet == null || gua_map.this.manimationSet == animationSet)) {
                    scaleAnimation = new ScaleAnimation(0.83f, 0.83f, 0.83f, 0.83f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation.setDuration(400);
                    gua_map.this.manimationSet.addAnimation(scaleAnimation);
                    gua_map.this.manimationSet.setFillAfter(true);
                    view.startAnimation(gua_map.this.manimationSet);
                }
                scaleAnimation = new ScaleAnimation(2.0f, 1.2f, 2.0f, 1.2f, 1, 0.5f, 1, 0.5f);
                scaleAnimation.setDuration(400);
                animationSet.addAnimation(scaleAnimation);
                animationSet.setFillAfter(true);
                view.startAnimation(animationSet);
                gua_map.this.manimationSet = animationSet;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ((LinearLayout) findViewById(R.id.gua_image)).addView(gua_canvas);
        ((LinearLayout) findViewById(R.id.gua_gallery)).addView(gallery);
    }

    protected void onResume() {
        super.onResume();
        if (gv.guaMapThreadOut) {
            _run = true;
            guaMapThread = new HandlerThread("print gua circle");
            guaMapThread.start();
            guaMapHandler = new Handler(guaMapThread.getLooper());
            guaMapHandler.post(this.r1);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        if (guaMapHandler != null) {
            _run = false;
            guaMapHandler.removeCallbacks(this.r1);
            gv.guaMapThreadOut = true;
        }
        if (guaMapThread != null) {
            guaMapThread.quit();
        }
        super.onPause();
        finish();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        layout.removeAllViewsInLayout();
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        layout.addView(adView);
        adView.loadAd(new AdRequest());
    }
}
