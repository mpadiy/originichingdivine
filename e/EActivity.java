package comp.android.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import java.io.File;

public class EActivity extends Activity {
    public static final String myPre = "mySharedPreferences";
    LinearLayout adsll;
    private Builder alert;
    private CheckBox dontShowAgain;
    Button e_go_button;
    public File fileDir;
    File imageFile;
    File imageFileSD;
    Button leave_button;
    Button s_g_button;
    Button s_z_button;
    public File sdcardDir;
    Button sh_g_button;
    Button share_button;
    private boolean showed;
    private boolean skipMessage;
    Button speedg_button;
    Button test_button;
    View tmpView;
    Button wy_button;
    Button z_g_button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        gv.disWidth = dm.widthPixels;
        gv.disHeight = dm.heightPixels;
        gv.trackerInstance = GoogleAnalytics.getInstance(getApplicationContext());
        GAServiceManager.getInstance().setDispatchPeriod(60);
        gv.tracker = gv.trackerInstance.getTracker("UA-4367088-5");
        final SharedPreferences pre = getSharedPreferences(gv.pre, 0);
        this.fileDir = getFilesDir();
        this.sdcardDir = Environment.getExternalStorageDirectory();
        gv.path = new StringBuilder(String.valueOf(this.fileDir.getParent())).append(File.separator).append(this.fileDir.getName()).toString();
        gv.path_SD = new StringBuilder(String.valueOf(this.sdcardDir.getParent())).append(File.separator).append(this.sdcardDir.getName()).append(File.separator).append("andro易d").toString();
        File fileE = new File(gv.path);
        if (!fileE.exists()) {
            fileE.mkdir();
        }
        File fileSD = new File(gv.path_SD);
        if (!fileSD.exists()) {
            fileSD.mkdir();
        }
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        this.adsll = (LinearLayout) findViewById(R.id.ads);
        this.adsll.addView(adView);
        adView.loadAd(new AdRequest());
        this.adsll.setVisibility(4);
        ScrollView svpage = (ScrollView) findViewById(R.id.svpage);
        svpage.setVerticalScrollBarEnabled(false);
        svpage.setPadding(0, (int) (((float) gv.disHeight) / 8.0f), 0, 0);
        this.speedg_button = (Button) findViewById(R.id.speedg);
        this.speedg_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.speedGuaExists = false;
                EActivity.this.speedg_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.speedg_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(0);
                    }
                }, 300);
                Builder xab = new Builder(EActivity.this);
                xab.setTitle(EActivity.this.getResources().getString(R.string.速占));
                xab.setMessage(EActivity.this.getResources().getString(R.string.speed_gua));
                xab.setPositiveButton(EActivity.this.getResources().getString(R.string.str_alert_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        gv.tracker.trackView("speedg");
                        Intent intent = new Intent();
                        intent.setClass(EActivity.this, SpeedGua.class);
                        EActivity.this.startActivity(intent);
                        EActivity.this.overridePendingTransition(0, R.anim.activity_exit);
                    }
                });
                xab.setNegativeButton(EActivity.this.getResources().getString(R.string.str_alert_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                AlertDialog xb = xab.create();
                xb.setCanceledOnTouchOutside(true);
                xb.show();
            }
        });
        this.e_go_button = (Button) findViewById(R.id.e_go);
        this.e_go_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackView("e_go");
                gv.trackerRecStartTiming = System.currentTimeMillis();
                EActivity.this.e_go_button.setTextColor(-65536);
                Intent intent = new Intent();
                intent.setClass(EActivity.this, e_go_anim.class);
                EActivity.this.startActivity(intent);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.e_go_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(4);
                    }
                }, 300);
            }
        });
        this.s_z_button = (Button) findViewById(R.id.sz);
        this.s_z_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackView("sz");
                EActivity.this.s_z_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.s_z_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(4);
                    }
                }, 300);
                Intent intent = new Intent();
                intent.setClass(EActivity.this, sz.class);
                EActivity.this.startActivity(intent);
            }
        });
        this.s_g_button = (Button) findViewById(R.id.sg);
        this.s_g_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackView("sg");
                EActivity.this.s_g_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.s_g_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(4);
                    }
                }, 300);
                Intent intent = new Intent();
                intent.setClass(EActivity.this, sg.class);
                EActivity.this.startActivity(intent);
            }
        });
        this.sh_g_button = (Button) findViewById(R.id.shg);
        this.sh_g_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackView("shg");
                EActivity.this.sh_g_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.sh_g_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(4);
                    }
                }, 300);
                Intent intent = new Intent();
                intent.setClass(EActivity.this, shg.class);
                EActivity.this.startActivity(intent);
            }
        });
        this.z_g_button = (Button) findViewById(R.id.zg);
        this.z_g_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackView("zg");
                EActivity.this.z_g_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.z_g_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(4);
                    }
                }, 300);
                Intent intent = new Intent();
                intent.setClass(EActivity.this, zg.class);
                EActivity.this.startActivity(intent);
            }
        });
        this.wy_button = (Button) findViewById(R.id.wy);
        this.wy_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackView("wy");
                EActivity.this.wy_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.wy_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(4);
                    }
                }, 300);
                Intent intent = new Intent();
                intent.setClass(EActivity.this, wy.class);
                EActivity.this.startActivity(intent);
            }
        });
        this.test_button = (Button) findViewById(R.id.test);
        this.test_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.trackerRecStartTiming = System.currentTimeMillis();
                EActivity.this.test_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        EActivity.this.test_button.setTextColor(-256);
                        EActivity.this.adsll.setVisibility(4);
                    }
                }, 300);
                Builder xab = new Builder(EActivity.this);
                CharSequence[] items = new CharSequence[]{EActivity.this.getResources().getString(R.string.初級班一), EActivity.this.getResources().getString(R.string.初級班二), EActivity.this.getResources().getString(R.string.中級班), EActivity.this.getResources().getString(R.string.高級班)};
                xab.setTitle(EActivity.this.getResources().getString(R.string.測驗));
                xab.setCancelable(false);
                final SharedPreferences sharedPreferences = pre;
                xab.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int item) {
                        Intent intent;
                        if (item == 0) {
                            gv.tracker.trackView("test0");
                            gv.tracker.trackEvent("test", "play0", "clicked", Long.valueOf(0));
                            gv.quzStart = "b";
                            intent = new Intent();
                            intent.setClass(EActivity.this, Test.class);
                            EActivity.this.startActivity(intent);
                        } else if (item == 1) {
                            if (sharedPreferences.getBoolean("ab", false)) {
                                gv.tracker.trackView("test1");
                                gv.tracker.trackEvent("test", "play1", "clicked", Long.valueOf(0));
                                gv.quzStart = "ab";
                                intent = new Intent();
                                intent.setClass(EActivity.this, Test.class);
                                EActivity.this.startActivity(intent);
                                return;
                            }
                            xab = new Builder(EActivity.this);
                            xab.setTitle(EActivity.this.getResources().getString(R.string.時機未到));
                            xab.setMessage(EActivity.this.getResources().getString(R.string.初一過));
                            xab.setPositiveButton(EActivity.this.getResources().getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                            xb = xab.create();
                            xb.setCanceledOnTouchOutside(true);
                            xb.show();
                        } else if (item == 2) {
                            if (sharedPreferences.getBoolean("j", false)) {
                                gv.tracker.trackView("test2");
                                gv.tracker.trackEvent("test", "play2", "clicked", Long.valueOf(0));
                                gv.quzStart = "j";
                                intent = new Intent();
                                intent.setClass(EActivity.this, Test.class);
                                EActivity.this.startActivity(intent);
                                return;
                            }
                            xab = new Builder(EActivity.this);
                            xab.setTitle(EActivity.this.getResources().getString(R.string.時機未到));
                            xab.setMessage(EActivity.this.getResources().getString(R.string.初二過));
                            xab.setPositiveButton(EActivity.this.getResources().getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                            xb = xab.create();
                            xb.setCanceledOnTouchOutside(true);
                            xb.show();
                        } else if (item != 3) {
                        } else {
                            if (sharedPreferences.getBoolean("s", false)) {
                                gv.tracker.trackView("test3");
                                gv.tracker.trackEvent("test", "play3", "clicked", Long.valueOf(0));
                                gv.quzStart = "s";
                                intent = new Intent();
                                intent.setClass(EActivity.this, Test.class);
                                EActivity.this.startActivity(intent);
                                return;
                            }
                            xab = new Builder(EActivity.this);
                            xab.setTitle(EActivity.this.getResources().getString(R.string.時機未到));
                            xab.setMessage(EActivity.this.getResources().getString(R.string.中過));
                            xab.setPositiveButton(EActivity.this.getResources().getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                            xb = xab.create();
                            xb.setCanceledOnTouchOutside(true);
                            xb.show();
                        }
                    }
                });
                AlertDialog dc = xab.create();
                dc.setCanceledOnTouchOutside(true);
                dc.show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, R.string.app_about);
        menu.add(0, 1, 1, R.string.leave);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 0:
                openOptionsDialog();
                break;
            case 1:
                System.exit(0);
                break;
        }
        return true;
    }

    private void openOptionsDialog() {
        new Builder(this).setTitle(R.string.app_about).setMessage(R.string.app_about_msg).setPositiveButton(R.string.str_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
            }
        }).show();
    }

    public void leaveDiagbox() {
        this.alert = new Builder(this);
        View checkboxLayout = LayoutInflater.from(this).inflate(R.layout.reviews, null);
        this.dontShowAgain = (CheckBox) checkboxLayout.findViewById(R.id.skip);
        this.alert.setView(checkboxLayout);
        this.alert.setTitle(R.string.exit);
        this.alert.setIcon(17301543);
        this.alert.setMessage(getResources().getString(R.string.reviews));
        this.alert.setPositiveButton(R.string.leave, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                boolean checkBoxResult = false;
                if (EActivity.this.dontShowAgain.isChecked()) {
                    checkBoxResult = true;
                }
                Editor editor = EActivity.this.getSharedPreferences(EActivity.myPre, 0).edit();
                editor.putBoolean("skipMessage", checkBoxResult);
                editor.commit();
                EActivity.this.finish();
            }
        });
        this.alert.setNegativeButton(R.string.reviewbtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                boolean checkBoxResult = false;
                if (EActivity.this.dontShowAgain.isChecked()) {
                    checkBoxResult = true;
                }
                Editor editor = EActivity.this.getSharedPreferences(EActivity.myPre, 0).edit();
                editor.putBoolean("skipMessage", checkBoxResult);
                editor.commit();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("market://details?id=comp.android.e"));
                EActivity.this.startActivity(intent);
                EActivity.this.finish();
            }
        });
        this.skipMessage = getSharedPreferences(myPre, 0).getBoolean("skipMessage", false);
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("resultOK", "EactivityonDestroy");
    }

    public void onBackPressed() {
        leaveDiagbox();
        if (!this.showed) {
            if (this.skipMessage) {
                finish();
                return;
            }
            this.alert.show();
            this.showed = true;
        }
    }

    protected void onResume() {
        super.onResume();
        gv.flagAlreadyInit = false;
        gv.flagChooseGuaMenu = true;
    }
}
