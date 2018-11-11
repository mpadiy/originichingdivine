package comp.android.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.google.ads.AdActivity;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.ModelFields;
import comp.android.e.R.string;

public class Test extends Activity {
    private static char[] ansBin;
    private static TextView downGua2;
    private static TextView downGua3;
    private static TextView downGuaI;
    private static String downGuaQ;
    private static ImageButton e2;
    private static ImageButton e3;
    private static ImageButton e4;
    private static ImageButton e5;
    private static ImageButton ei;
    private static ImageButton eu;
    private static char[] quzBin;
    private static TextView testTitle;
    private static TextView upGua4;
    private static TextView upGua5;
    private static String upGuaQ;
    private static TextView upGuaU;
    private int ansError = 0;
    private int ansRight = 0;
    private int ansTimes = 0;
    private final int breakRound = 30;
    private final float colorStep = 6.0f;
    private Button descri_button;
    private LinearLayout layout;
    private Button leave_button;
    private Button okBtn;
    private int onePointHeight = 30;
    private int score = 0;
    private LinearLayout scoreBar;
    private int scoreBarHeight;
    private String stageTitle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        final SharedPreferences pre = getSharedPreferences(gv.pre, 0);
        if (gv.quzStart.equals("s")) {
            setContentView(R.layout.gua_test_adv);
        } else if (gv.quzStart.equals("ab")) {
            setContentView(R.layout.gua_test_b1);
        } else {
            setContentView(R.layout.gua_test);
        }
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        this.layout = (LinearLayout) findViewById(R.id.ads);
        this.layout.addView(adView);
        adView.loadAd(new AdRequest());
        this.descri_button = (Button) findViewById(R.id.game_description);
        this.descri_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Builder xab = new Builder(Test.this);
                xab.setTitle(Test.this.getResources().getString(R.string.說明));
                if (gv.quzStart.equals("b")) {
                    xab.setMessage(Test.this.getResources().getString(R.string.初級說明));
                    Test.this.stageTitle = Test.this.getResources().getString(R.string.初級班一);
                } else if (gv.quzStart.equals("ab")) {
                    xab.setMessage(Test.this.getResources().getString(R.string.初級說明二));
                    Test.this.stageTitle = Test.this.getResources().getString(R.string.初級班二);
                } else if (gv.quzStart.equals("j")) {
                    xab.setMessage(Test.this.getResources().getString(R.string.中級說明));
                    Test.this.stageTitle = Test.this.getResources().getString(R.string.中級班);
                } else if (gv.quzStart.equals("s")) {
                    xab.setMessage(Test.this.getResources().getString(R.string.高級說明));
                    Test.this.stageTitle = Test.this.getResources().getString(R.string.高級班);
                }
                xab.setPositiveButton(Test.this.getResources().getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                AlertDialog xb = xab.create();
                xb.setCanceledOnTouchOutside(true);
                xb.show();
            }
        });
        this.leave_button = (Button) findViewById(R.id.leave);
        this.leave_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackTiming("test", System.currentTimeMillis() - gv.trackerRecStartTiming, ModelFields.TIMING, gv.quzStart);
                Test.this.finish();
            }
        });
        if (gv.quzStart.equals("b")) {
            this.stageTitle = getResources().getString(R.string.初級班一);
        } else if (gv.quzStart.equals("ab")) {
            this.stageTitle = getResources().getString(R.string.初級班二);
        } else if (gv.quzStart.equals("j")) {
            this.stageTitle = getResources().getString(R.string.中級班);
        } else if (gv.quzStart.equals("s")) {
            this.stageTitle = getResources().getString(R.string.高級班);
        }
        testTitle = (TextView) findViewById(R.id.gua_test_title);
        testTitleSet();
        this.scoreBar = (LinearLayout) findViewById(R.id.score_button_grp);
        upGuaU = (TextView) findViewById(R.id.up_gua_u);
        upGua5 = (TextView) findViewById(R.id.up_gua_5);
        upGua4 = (TextView) findViewById(R.id.up_gua_4);
        downGua3 = (TextView) findViewById(R.id.down_gua_3);
        downGua2 = (TextView) findViewById(R.id.down_gua_2);
        downGuaI = (TextView) findViewById(R.id.down_gua_i);
        eu = (ImageButton) findViewById(R.id.eu);
        e5 = (ImageButton) findViewById(R.id.e5);
        e4 = (ImageButton) findViewById(R.id.e4);
        e3 = (ImageButton) findViewById(R.id.e3);
        e2 = (ImageButton) findViewById(R.id.e2);
        ei = (ImageButton) findViewById(R.id.ei);
        eu.setBackgroundDrawable(getResources().getDrawable(R.drawable.yang_q));
        e5.setBackgroundDrawable(getResources().getDrawable(R.drawable.yang_q));
        e4.setBackgroundDrawable(getResources().getDrawable(R.drawable.yang_q));
        e3.setBackgroundDrawable(getResources().getDrawable(R.drawable.yang_q));
        e2.setBackgroundDrawable(getResources().getDrawable(R.drawable.yang_q));
        ei.setBackgroundDrawable(getResources().getDrawable(R.drawable.yang_q));
        eu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Test.ansBin[0] == '1') {
                    Test.ansBin[0] = '0';
                } else {
                    Test.ansBin[0] = '1';
                }
                Test.eu.setBackgroundResource(gv.gua_component[Integer.parseInt(new String(Test.ansBin, 0, 1))].intValue());
            }
        });
        e5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Test.ansBin[1] == '1') {
                    Test.ansBin[1] = '0';
                } else {
                    Test.ansBin[1] = '1';
                }
                Test.e5.setBackgroundResource(gv.gua_component[Integer.parseInt(new String(Test.ansBin, 1, 1))].intValue());
            }
        });
        e4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Test.ansBin[2] == '1') {
                    Test.ansBin[2] = '0';
                } else {
                    Test.ansBin[2] = '1';
                }
                Test.e4.setBackgroundResource(gv.gua_component[Integer.parseInt(new String(Test.ansBin, 2, 1))].intValue());
            }
        });
        e3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Test.ansBin[3] == '1') {
                    Test.ansBin[3] = '0';
                } else {
                    Test.ansBin[3] = '1';
                }
                Test.e3.setBackgroundResource(gv.gua_component[Integer.parseInt(new String(Test.ansBin, 3, 1))].intValue());
            }
        });
        e2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Test.ansBin[4] == '1') {
                    Test.ansBin[4] = '0';
                } else {
                    Test.ansBin[4] = '1';
                }
                Test.e2.setBackgroundResource(gv.gua_component[Integer.parseInt(new String(Test.ansBin, 4, 1))].intValue());
            }
        });
        ei.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Test.ansBin[5] == '1') {
                    Test.ansBin[5] = '0';
                } else {
                    Test.ansBin[5] = '1';
                }
                Test.ei.setBackgroundResource(gv.gua_component[Integer.parseInt(new String(Test.ansBin, 5, 1))].intValue());
            }
        });
        this.okBtn = (Button) findViewById(R.id.ansok);
        this.okBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Test test = Test.this;
                test.ansTimes = test.ansTimes + 1;
                boolean ansIsRightOrNot = true;
                for (int i = 0; i < Test.quzBin.length; i++) {
                    Builder xab;
                    CharSequence string;
                    final SharedPreferences sharedPreferences;
                    if (Test.quzBin[i] != Test.ansBin[i]) {
                        ansIsRightOrNot = false;
                        test = Test.this;
                        test.score = test.score - 1;
                        test = Test.this;
                        test.score = test.score - 1;
                        if (Test.this.score < 0) {
                            Test.this.score = 0;
                        }
                        test = Test.this;
                        test.ansError = test.ansError + 1;
                        if (ansIsRightOrNot) {
                            test = Test.this;
                            test.score = test.score + 1;
                            test = Test.this;
                            test.ansRight = test.ansRight + 1;
                            Toast.makeText(Test.this, Test.this.getResources().getString(R.string.加油), 0).show();
                            if (Test.this.score >= 30) {
                                if (gv.quzStart.equals("b") && !pre.getBoolean("ab", false)) {
                                    xab = new Builder(Test.this);
                                    xab.setTitle(Test.this.getResources().getString(R.string.過關));
                                    xab.setMessage(Test.this.getResources().getString(R.string.進入初級二));
                                    string = Test.this.getResources().getString(R.string.str_alert_ok);
                                    sharedPreferences = pre;
                                    xab.setPositiveButton(string, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            gv.quzStart = "ab";
                                            Test.this.score = 0;
                                            if (sharedPreferences != null) {
                                                Editor ed = sharedPreferences.edit();
                                                ed.putBoolean("ab", true);
                                                ed.commit();
                                            }
                                            Intent intent = new Intent();
                                            intent.setClass(Test.this, Test.class);
                                            intent.setFlags(32768);
                                            Test.this.startActivity(intent);
                                            Test.this.finish();
                                        }
                                    });
                                    xab.create().show();
                                }
                                if (gv.quzStart.equals("ab") && !pre.getBoolean("j", false)) {
                                    xab = new Builder(Test.this);
                                    xab.setTitle(Test.this.getResources().getString(R.string.過關));
                                    xab.setMessage(Test.this.getResources().getString(R.string.進入中級));
                                    string = Test.this.getResources().getString(R.string.str_alert_ok);
                                    sharedPreferences = pre;
                                    xab.setPositiveButton(string, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            gv.quzStart = "j";
                                            Test.this.score = 0;
                                            if (sharedPreferences != null) {
                                                Editor ed = sharedPreferences.edit();
                                                ed.putBoolean("j", true);
                                                ed.commit();
                                            }
                                            Intent intent = new Intent();
                                            intent.setClass(Test.this, Test.class);
                                            intent.setFlags(32768);
                                            Test.this.startActivity(intent);
                                            Test.this.finish();
                                        }
                                    });
                                    xab.create().show();
                                }
                                if (gv.quzStart.equals("j") && !pre.getBoolean("s", false)) {
                                    xab = new Builder(Test.this);
                                    xab.setTitle(Test.this.getResources().getString(R.string.過關));
                                    xab.setMessage(Test.this.getResources().getString(R.string.進入高級));
                                    string = Test.this.getResources().getString(R.string.str_alert_ok);
                                    sharedPreferences = pre;
                                    xab.setPositiveButton(string, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            gv.quzStart = "s";
                                            Test.this.score = 0;
                                            if (sharedPreferences != null) {
                                                Editor ed = sharedPreferences.edit();
                                                ed.putBoolean("s", true);
                                                ed.commit();
                                            }
                                            Intent intent = new Intent();
                                            intent.setClass(Test.this, Test.class);
                                            intent.setFlags(32768);
                                            Test.this.startActivity(intent);
                                            Test.this.finish();
                                        }
                                    });
                                    xab.create().show();
                                }
                            }
                            Test.quzMake(Test.this, gv.quzStart);
                            gv.tracker.trackEvent("inTest", gv.quzStart, String.valueOf(Test.this.score), Long.valueOf(0));
                        } else if (Test.this.score != 0) {
                            gv.tracker.trackTiming("test", System.currentTimeMillis() - gv.trackerRecStartTiming, ModelFields.TIMING, gv.quzStart);
                            xab = new Builder(Test.this);
                            xab.setTitle(Test.this.getResources().getString(R.string.你已經零分了));
                            xab.setMessage(new StringBuilder(String.valueOf("上卦為：" + e_lib.getQuzUpGua(Test.quzBin) + "\n下卦為：" + e_lib.getQuzDownGua(Test.quzBin))).append("\n").append(Test.this.getResources().getString(R.string.再來過)).toString());
                            xab.setPositiveButton(Test.this.getResources().getString(R.string.str_alert_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Test.this.finish();
                                }
                            });
                            xab.create().show();
                        } else {
                            xab = new Builder(Test.this);
                            xab.setTitle(Test.this.getResources().getString(R.string.正確答案));
                            xab.setMessage("上卦為：" + e_lib.getQuzUpGua(Test.quzBin) + "\n下卦為：" + e_lib.getQuzDownGua(Test.quzBin));
                            xab.setPositiveButton(Test.this.getResources().getString(R.string.str_alert_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                            xab.create().show();
                            Toast.makeText(Test.this, Test.this.getResources().getString(R.string.再接再厲), 1).show();
                        }
                        Test.this.adjustScoreBarPointHeightByConfigure();
                        Test.this.testTitleSet();
                    }
                }
                if (ansIsRightOrNot) {
                    test = Test.this;
                    test.score = test.score + 1;
                    test = Test.this;
                    test.ansRight = test.ansRight + 1;
                    Toast.makeText(Test.this, Test.this.getResources().getString(R.string.加油), 0).show();
                    if (Test.this.score >= 30) {
                        xab = new Builder(Test.this);
                        xab.setTitle(Test.this.getResources().getString(R.string.過關));
                        xab.setMessage(Test.this.getResources().getString(R.string.進入初級二));
                        string = Test.this.getResources().getString(R.string.str_alert_ok);
                        sharedPreferences = pre;
                        xab.setPositiveButton(string, /* anonymous class already generated */);
                        xab.create().show();
                        xab = new Builder(Test.this);
                        xab.setTitle(Test.this.getResources().getString(R.string.過關));
                        xab.setMessage(Test.this.getResources().getString(R.string.進入中級));
                        string = Test.this.getResources().getString(R.string.str_alert_ok);
                        sharedPreferences = pre;
                        xab.setPositiveButton(string, /* anonymous class already generated */);
                        xab.create().show();
                        xab = new Builder(Test.this);
                        xab.setTitle(Test.this.getResources().getString(R.string.過關));
                        xab.setMessage(Test.this.getResources().getString(R.string.進入高級));
                        string = Test.this.getResources().getString(R.string.str_alert_ok);
                        sharedPreferences = pre;
                        xab.setPositiveButton(string, /* anonymous class already generated */);
                        xab.create().show();
                    }
                    Test.quzMake(Test.this, gv.quzStart);
                    gv.tracker.trackEvent("inTest", gv.quzStart, String.valueOf(Test.this.score), Long.valueOf(0));
                } else if (Test.this.score != 0) {
                    xab = new Builder(Test.this);
                    xab.setTitle(Test.this.getResources().getString(R.string.正確答案));
                    xab.setMessage("上卦為：" + e_lib.getQuzUpGua(Test.quzBin) + "\n下卦為：" + e_lib.getQuzDownGua(Test.quzBin));
                    xab.setPositiveButton(Test.this.getResources().getString(R.string.str_alert_ok), /* anonymous class already generated */);
                    xab.create().show();
                    Toast.makeText(Test.this, Test.this.getResources().getString(R.string.再接再厲), 1).show();
                } else {
                    gv.tracker.trackTiming("test", System.currentTimeMillis() - gv.trackerRecStartTiming, ModelFields.TIMING, gv.quzStart);
                    xab = new Builder(Test.this);
                    xab.setTitle(Test.this.getResources().getString(R.string.你已經零分了));
                    xab.setMessage(new StringBuilder(String.valueOf("上卦為：" + e_lib.getQuzUpGua(Test.quzBin) + "\n下卦為：" + e_lib.getQuzDownGua(Test.quzBin))).append("\n").append(Test.this.getResources().getString(R.string.再來過)).toString());
                    xab.setPositiveButton(Test.this.getResources().getString(R.string.str_alert_ok), /* anonymous class already generated */);
                    xab.create().show();
                }
                Test.this.adjustScoreBarPointHeightByConfigure();
                Test.this.testTitleSet();
            }
        });
        quzMake(this, gv.quzStart);
    }

    public static void quzMake(Context mc, String quzType) {
        int guaNum = gv.myRand.nextInt(12343) % 64;
        if (quzType.equals("b")) {
            quzSG(mc, guaNum);
        } else if (quzType.equals("ab")) {
            quzSHGZHG(mc, guaNum);
        } else if (quzType.equals("j")) {
            quzXX(mc, guaNum);
        } else if (quzType.equals("s")) {
            quzXXEvery(mc, guaNum);
        }
    }

    public static void quzSG(Context mc, int guaNum) {
        quzBin = e_lib.bin_format(guaNum);
        ansBin = e_lib.bin_format(0);
        int upGuaNum = guaNum >> 3;
        int downGuaNum = guaNum % 8;
        if (upGuaNum == 0) {
            upGuaQ = gv.earth[gv.myRand.nextInt(gv.earth.length)];
        } else if (upGuaNum == 1) {
            upGuaQ = gv.thunder[gv.myRand.nextInt(gv.thunder.length)];
        } else if (upGuaNum == 2) {
            upGuaQ = gv.water[gv.myRand.nextInt(gv.water.length)];
        } else if (upGuaNum == 3) {
            upGuaQ = gv.lake[gv.myRand.nextInt(gv.lake.length)];
        } else if (upGuaNum == 4) {
            upGuaQ = gv.mountain[gv.myRand.nextInt(gv.mountain.length)];
        } else if (upGuaNum == 5) {
            upGuaQ = gv.fire[gv.myRand.nextInt(gv.fire.length)];
        } else if (upGuaNum == 6) {
            upGuaQ = gv.wind[gv.myRand.nextInt(gv.wind.length)];
        } else if (upGuaNum == 7) {
            upGuaQ = gv.heaven[gv.myRand.nextInt(gv.heaven.length)];
        }
        if (downGuaNum == 0) {
            downGuaQ = gv.earth[gv.myRand.nextInt(gv.earth.length)];
        } else if (downGuaNum == 1) {
            downGuaQ = gv.thunder[gv.myRand.nextInt(gv.thunder.length)];
        } else if (downGuaNum == 2) {
            downGuaQ = gv.water[gv.myRand.nextInt(gv.water.length)];
        } else if (downGuaNum == 3) {
            downGuaQ = gv.lake[gv.myRand.nextInt(gv.lake.length)];
        } else if (downGuaNum == 4) {
            downGuaQ = gv.mountain[gv.myRand.nextInt(gv.mountain.length)];
        } else if (downGuaNum == 5) {
            downGuaQ = gv.fire[gv.myRand.nextInt(gv.fire.length)];
        } else if (downGuaNum == 6) {
            downGuaQ = gv.wind[gv.myRand.nextInt(gv.wind.length)];
        } else if (downGuaNum == 7) {
            downGuaQ = gv.heaven[gv.myRand.nextInt(gv.heaven.length)];
        }
        upGua4.setText(upGuaQ);
        upGua4.setGravity(17);
        downGua3.setText(downGuaQ);
        downGua3.setGravity(17);
        eu.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e5.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e4.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e3.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e2.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        ei.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
    }

    public static void quzSHGZHG(Context mc, int guaNum) {
        int i;
        String shgu = null;
        String shgd = null;
        String zhg1 = null;
        int guaNumForQuz = 0;
        String quzString = null;
        Class<string> res = string.class;
        try {
            int shguId = res.getField("shgu").getInt(null);
            int shgdId = res.getField("shgd").getInt(null);
            int zgId = res.getField("zg").getInt(null);
            shgu = mc.getResources().getString(shguId);
            shgd = mc.getResources().getString(shgdId);
            zhg1 = mc.getResources().getString(zgId);
        } catch (Exception e) {
        }
        String shg = shgu.replaceAll("\\n", "") + shgd.replaceAll("\\n", "");
        String zhg = zhg1.replaceAll("\\n", "");
        String[] shgSplit = shg.split("。");
        String[] zhgSplit = zhg.split("。");
        for (i = 0; i < gv.guaNum.length; i++) {
            if (gv.guaNum[i] == guaNum) {
                guaNumForQuz = i;
            }
        }
        if (gv.myRand.nextInt(1547) % 2 == 0) {
            String quzFind = "故受之以" + gv.guaName[guaNumForQuz];
            for (i = 0; i < shgSplit.length; i++) {
                if (shgSplit[i].contains(quzFind)) {
                    if (shgSplit[i].contains("；")) {
                        String[] quzSplit = shgSplit[i].split("；");
                        int semiColon = gv.myRand.nextInt(1543) % 2;
                        if (semiColon == 0) {
                            quzString = quzSplit[semiColon].replace(quzFind, "故受之以??");
                        } else {
                            quzString = quzSplit[semiColon].replace(gv.guaName[guaNumForQuz], "??");
                        }
                    } else {
                        quzString = shgSplit[i].replace(quzFind, "故受之以??");
                    }
                    if (quzString == null) {
                        quzSHGZHG(mc, gv.myRand.nextInt(64));
                        return;
                    }
                }
            }
            if (quzString == null) {
                quzSHGZHG(mc, gv.myRand.nextInt(64));
                return;
            }
        }
        for (i = 0; i < zhgSplit.length; i++) {
            if (zhgSplit[i].contains(gv.guaName[guaNumForQuz])) {
                quzString = zhgSplit[i].replace(gv.guaName[guaNumForQuz], "??");
                break;
            }
        }
        if (quzString == null) {
            quzSHGZHG(mc, gv.myRand.nextInt(64));
            return;
        }
        upGua4.setText(quzString);
        upGua4.setGravity(17);
        upGua4.invalidate();
        quzBin = e_lib.bin_format(guaNum);
        ansBin = e_lib.bin_format(0);
        eu.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e5.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e4.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e3.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e2.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        ei.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
    }

    public static void quzXX(Context mc, int guaNum) {
        int i;
        quzBin = e_lib.bin_format(guaNum);
        ansBin = e_lib.bin_format(0);
        int upGuaNum = guaNum >> 3;
        int downGuaNum = guaNum % 8;
        int upXX = gv.myRand.nextInt(3) + 4;
        int downXX = gv.myRand.nextInt(3) + 1;
        String upXXStr = upXX != 6 ? String.valueOf(upXX) : AdActivity.URL_PARAM;
        String downXXStr = downXX != 1 ? String.valueOf(downXX) : AdActivity.INTENT_ACTION_PARAM;
        String upCombineBin = Integer.toBinaryString(gv.myRand.nextInt(8));
        if (upCombineBin.length() != 3) {
            upCombineBin = upCombineBin.length() != 2 ? "00" + upCombineBin : "0" + upCombineBin;
        }
        downCombineBin = Integer.toBinaryString(downGuaNum).length() != 3 ? Integer.toBinaryString(downGuaNum).length() != 2 ? "00" + Integer.toBinaryString(downGuaNum) : "0" + Integer.toBinaryString(downGuaNum) : Integer.toBinaryString(downGuaNum);
        int upCombine = Integer.parseInt(Integer.toBinaryString(upGuaNum) + upCombineBin, 2);
        int downCombine = Integer.parseInt(new StringBuilder(String.valueOf(Integer.toBinaryString(gv.myRand.nextInt(8)))).append(downCombineBin).toString(), 2);
        int a = mc.getResources().getIdentifier("e" + String.valueOf(upCombine) + "_" + upXXStr, "string", mc.getPackageName());
        int b = mc.getResources().getIdentifier("e" + String.valueOf(downCombine) + "_" + downXXStr, "string", mc.getPackageName());
        upGuaQ = mc.getResources().getString(a);
        downGuaQ = mc.getResources().getString(b);
        String[] upGuaQSplit = upGuaQ.split("，");
        String[] downGuaQSplit = downGuaQ.split("，");
        upGuaQ = "";
        downGuaQ = "";
        for (i = 1; i < upGuaQSplit.length; i++) {
            if (i == 1) {
                upGuaQ = upGuaQSplit[i];
            } else {
                upGuaQ += "，" + upGuaQSplit[i];
            }
        }
        for (i = 1; i < downGuaQSplit.length; i++) {
            if (i == 1) {
                downGuaQ = downGuaQSplit[i];
            } else {
                downGuaQ += "，" + downGuaQSplit[i];
            }
        }
        upGua4.setText(upGuaQ);
        downGua3.setText(downGuaQ);
        eu.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e5.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e4.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e3.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e2.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        ei.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
    }

    public static void quzXXEvery(Context mc, int guaNum) {
        int i;
        quzBin = e_lib.bin_format(guaNum);
        ansBin = e_lib.bin_format(0);
        tmpQ = new char[6];
        int resId = 0;
        Class<string> res = string.class;
        tmpQ[0] = quzBin[0];
        tmpQ[1] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[2] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[3] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[4] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[5] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        try {
            resId = res.getField("e" + String.valueOf(Integer.parseInt(new String(tmpQ), 2)) + "_u").getInt(null);
        } catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }
        String[] tmpGuaQSplit = mc.getResources().getString(resId).split("，");
        String tmpGuaQ = "";
        for (i = 1; i < tmpGuaQSplit.length; i++) {
            if (i == 1) {
                tmpGuaQ = tmpGuaQSplit[i];
            } else {
                tmpGuaQ = new StringBuilder(String.valueOf(tmpGuaQ)).append("，").append(tmpGuaQSplit[i]).toString();
            }
        }
        upGuaU.setText(tmpGuaQ);
        tmpQ[0] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[1] = quzBin[1];
        tmpQ[2] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[3] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[4] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[5] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        try {
            resId = res.getField("e" + String.valueOf(Integer.parseInt(new String(tmpQ), 2)) + "_5").getInt(null);
        } catch (Exception e2) {
            Log.e("MyTag", "Failure to get drawable id.", e2);
        }
        tmpGuaQSplit = mc.getResources().getString(resId).split("，");
        tmpGuaQ = "";
        for (i = 1; i < tmpGuaQSplit.length; i++) {
            if (i == 1) {
                tmpGuaQ = tmpGuaQSplit[i];
            } else {
                tmpGuaQ = new StringBuilder(String.valueOf(tmpGuaQ)).append("，").append(tmpGuaQSplit[i]).toString();
            }
        }
        upGua5.setText(tmpGuaQ);
        tmpQ[0] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[1] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[2] = quzBin[2];
        tmpQ[3] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[4] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[5] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        try {
            resId = res.getField("e" + String.valueOf(Integer.parseInt(new String(tmpQ), 2)) + "_4").getInt(null);
        } catch (Exception e22) {
            Log.e("MyTag", "Failure to get drawable id.", e22);
        }
        tmpGuaQSplit = mc.getResources().getString(resId).split("，");
        tmpGuaQ = "";
        for (i = 1; i < tmpGuaQSplit.length; i++) {
            if (i == 1) {
                tmpGuaQ = tmpGuaQSplit[i];
            } else {
                tmpGuaQ = new StringBuilder(String.valueOf(tmpGuaQ)).append("，").append(tmpGuaQSplit[i]).toString();
            }
        }
        upGua4.setText(tmpGuaQ);
        tmpQ[0] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[1] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[2] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[3] = quzBin[3];
        tmpQ[4] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[5] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        try {
            resId = res.getField("e" + String.valueOf(Integer.parseInt(new String(tmpQ), 2)) + "_3").getInt(null);
        } catch (Exception e222) {
            Log.e("MyTag", "Failure to get drawable id.", e222);
        }
        tmpGuaQSplit = mc.getResources().getString(resId).split("，");
        tmpGuaQ = "";
        for (i = 1; i < tmpGuaQSplit.length; i++) {
            if (i == 1) {
                tmpGuaQ = tmpGuaQSplit[i];
            } else {
                tmpGuaQ = new StringBuilder(String.valueOf(tmpGuaQ)).append("，").append(tmpGuaQSplit[i]).toString();
            }
        }
        downGua3.setText(tmpGuaQ);
        tmpQ[0] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[1] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[2] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[3] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[4] = quzBin[4];
        tmpQ[5] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        try {
            resId = res.getField("e" + String.valueOf(Integer.parseInt(new String(tmpQ), 2)) + "_2").getInt(null);
        } catch (Exception e2222) {
            Log.e("MyTag", "Failure to get drawable id.", e2222);
        }
        tmpGuaQSplit = mc.getResources().getString(resId).split("，");
        tmpGuaQ = "";
        for (i = 1; i < tmpGuaQSplit.length; i++) {
            if (i == 1) {
                tmpGuaQ = tmpGuaQSplit[i];
            } else {
                tmpGuaQ = new StringBuilder(String.valueOf(tmpGuaQ)).append("，").append(tmpGuaQSplit[i]).toString();
            }
        }
        downGua2.setText(tmpGuaQ);
        tmpQ[0] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[1] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[2] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[3] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[4] = Character.forDigit(gv.myRand.nextInt(10) % 2, 10);
        tmpQ[5] = quzBin[5];
        try {
            resId = res.getField("e" + String.valueOf(Integer.parseInt(new String(tmpQ), 2)) + "_i").getInt(null);
        } catch (Exception e22222) {
            Log.e("MyTag", "Failure to get drawable id.", e22222);
        }
        tmpGuaQSplit = mc.getResources().getString(resId).split("，");
        tmpGuaQ = "";
        for (i = 1; i < tmpGuaQSplit.length; i++) {
            if (i == 1) {
                tmpGuaQ = tmpGuaQSplit[i];
            } else {
                tmpGuaQ = new StringBuilder(String.valueOf(tmpGuaQ)).append("，").append(tmpGuaQSplit[i]).toString();
            }
        }
        downGuaI.setText(tmpGuaQ);
        eu.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e5.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e4.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e3.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        e2.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
        ei.setBackgroundDrawable(mc.getResources().getDrawable(R.drawable.yang_q));
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.layout.removeAllViewsInLayout();
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        this.layout.addView(adView);
        adView.loadAd(new AdRequest());
        adjustScoreBarPointHeightByConfigure();
    }

    public void adjustScoreBarPointHeightByConfigure() {
        if (getResources().getConfiguration().orientation == 1) {
            adjustScoreBarPointHeight(gv.disHeight);
        } else {
            adjustScoreBarPointHeight(gv.disWidth);
        }
    }

    public void adjustScoreBarPointHeight(int h) {
        this.scoreBar.removeAllViewsInLayout();
        this.scoreBarHeight = h;
        LayoutParams lp = new LayoutParams(-2, -2);
        lp.topMargin = 2;
        LayoutParams lp1 = new LayoutParams(-1, -2);
        lp1.topMargin = 2;
        if (this.score == 0) {
            Button onePoint = new Button(this);
            onePoint.setHeight(this.scoreBarHeight);
            this.scoreBar.addView(onePoint);
        }
        int i = this.score;
        while (i >= 1) {
            onePoint = new Button(this);
            onePoint.setLayoutParams(lp);
            if (((float) i) <= 6.0f) {
                onePoint.setBackgroundColor(Color.rgb(250, i * 40, 0));
            } else if (((float) i) > 6.0f && ((float) i) <= 12.0f) {
                onePoint.setBackgroundColor(Color.rgb(250 - ((i - 6) * 40), 250, 0));
            } else if (((float) i) > 12.0f && ((float) i) <= 18.0f) {
                onePoint.setBackgroundColor(Color.rgb(0, 250, ((i - 12) * 40) + 10));
            } else if (((float) i) > 18.0f && ((float) i) <= 24.0f) {
                onePoint.setBackgroundColor(Color.rgb(0, 250 - ((i - 18) * 40), 250));
            } else if (((float) i) > 24.0f && ((float) i) <= 30.0f) {
                onePoint.setBackgroundColor(Color.rgb((i - 24) * 40, 0, 250));
            }
            onePoint.setHeight(this.onePointHeight);
            if (this.score <= 30) {
                this.onePointHeight = this.scoreBarHeight / 45;
                onePoint.setHeight(this.onePointHeight);
            } else {
                lp1.weight = 1.0f;
                onePoint.setLayoutParams(lp1);
                onePoint.setHeight(this.scoreBarHeight);
            }
            this.scoreBar.addView(onePoint);
            i--;
        }
    }

    private void testTitleSet() {
        testTitle.setText("\\m/" + this.stageTitle + "\\m/  " + getResources().getString(R.string.答題數) + this.ansTimes + " " + getResources().getString(R.string.答對) + this.ansRight + " " + getResources().getString(R.string.答錯) + this.ansError);
    }
}
