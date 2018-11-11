package comp.android.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.ModelFields;
import java.io.File;
import java.util.Random;

public class e_go_anim extends Activity {
    public static String DB_NAME = "tmpDB";
    public static String DB_PATH_NAME = null;
    public static String TAG = "HIPPO_DEBUG";
    public static MySQLiteOpenHelper edb = null;
    public static final String[] ff = new String[]{"e_id", "e_bin"};
    public static final String[][] fieldNames;
    public static final String[][] fieldTypes;
    public static final String[] tables = new String[]{"te_go"};
    public static final int version = 1;
    int[] Confucius = new int[66];
    public String DB_PATH_NAME_USER = null;
    Button deletebutton;
    EditText ePage;
    public SpannableString eXSpan;
    public TextView etree;
    public File fileDir;
    Button g_ch_button;
    Button g_ex_button;
    Button g_init_button;
    Button g_mu_button;
    Button g_usd_button;
    Button gua64button;
    CharSequence[] items = new CharSequence[66];
    LinearLayout layout;
    Button leave_button;
    private ViewFlipper mViewFlipper01;
    public EditText myDialogEditText;
    Random myRand;
    public float oldTouchValue;
    public String saveFileName;
    public File sdcardDir;
    Button searchbutton;
    View textEntryView;
    TextView tmp;

    static {
        String[][] strArr = new String[1][];
        strArr[0] = new String[]{"e_id", "e_bin"};
        fieldNames = strArr;
        strArr = new String[1][];
        strArr[0] = new String[]{"INTEGER PRIMARY KEY AUTOINCREMENT", "text"};
        fieldTypes = strArr;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        setContentView(R.layout.animation);
        this.fileDir = getFilesDir();
        this.sdcardDir = Environment.getExternalStorageDirectory();
        gv.path = new StringBuilder(String.valueOf(this.fileDir.getParent())).append(File.separator).append(this.fileDir.getName()).append(File.separator).append("andro易d").toString();
        gv.path_SD = new StringBuilder(String.valueOf(this.sdcardDir.getParent())).append(File.separator).append(this.sdcardDir.getName()).append(File.separator).append("andro易d").toString();
        File fileE = new File(gv.path);
        if (!fileE.exists()) {
            fileE.mkdir();
        }
        File fileSD = new File(gv.path_SD);
        if (!fileSD.exists()) {
            fileSD.mkdir();
        }
        if (fileE.exists() && !gv.flagAlreadyInit) {
            DB_PATH_NAME = gv.path + File.separator + DB_NAME + ".db";
            this.DB_PATH_NAME_USER = DB_PATH_NAME;
            deleteDatabase(DB_PATH_NAME);
        }
        AdView adView = new AdView((Activity) this, AdSize.BANNER, "a14e4933ced69de");
        this.layout = (LinearLayout) findViewById(R.id.ads);
        this.layout.addView(adView);
        adView.loadAd(new AdRequest());
        this.mViewFlipper01 = (ViewFlipper) findViewById(R.id.myViewFlipper1);
        this.tmp = (TextView) findViewById(R.id.tmp);
        this.ePage = (EditText) findViewById(R.id.epage);
        this.etree = (TextView) findViewById(R.id.etree);
        Log.i("resultOK", "create");
        Log.i("resultOK", "initAlready" + Boolean.toString(gv.flagAlreadyInit));
        Log.i("resultOK", "menu" + Boolean.toString(gv.flagChooseGuaMenu));
        if (!gv.flagAlreadyInit) {
            gua_init();
            gv.flagAlreadyInit = true;
        } else if (gv.flagChooseGuaMenu) {
            gua_init();
        }
        this.g_ch_button = (Button) findViewById(R.id.gua_change);
        this.g_ch_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "changeGua", "clicked", Long.valueOf(0));
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                String gua_bin = (String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText();
                char[] cse = e_lib.bin_format(Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 11)).getText(), 2));
                e_go_anim.this.gua_layout(Integer.parseInt(new String(e_lib.bin_format(Integer.parseInt(gua_bin, 2)), 0, 6), 2));
                curId /= 100;
                Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToLast();
                int lastId = Integer.parseInt(c.getString(0));
                c.close();
                String[] f = new String[]{"e_bin"};
                if (lastId == curId) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{new String(cse, 0, 6)});
                    c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToLast();
                    e_go_anim.this.auto_gen_layout(Integer.parseInt(new String(c.getString(0))), Integer.parseInt(new String(cse, 0, 6), 2));
                    c.close();
                    return;
                }
                String[] _id = new String[(lastId + 1)];
                String[] _bin = new String[(lastId + 1)];
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                int i = 0;
                do {
                    if (i == curId) {
                        _id[i] = String.valueOf(curId);
                        _bin[i] = new String(cse, 0, 6);
                        i++;
                        c.moveToPrevious();
                    } else {
                        _id[i] = c.getString(0);
                        _bin[i] = c.getString(1);
                        i++;
                    }
                } while (c.moveToNext());
                c.close();
                e_go_anim.this.deleteDatabase(e_go_anim.DB_PATH_NAME);
                e_go_anim.edb = new MySQLiteOpenHelper(e_go_anim.this, e_go_anim.DB_PATH_NAME, null, 1, e_go_anim.tables, e_go_anim.fieldNames, e_go_anim.fieldTypes);
                for (i = 0; i <= lastId; i++) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{_bin[i]});
                }
                e_go_anim.this.mViewFlipper01.removeAllViews();
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                do {
                    e_go_anim.this.db_gua_loading(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1), 2));
                } while (c.moveToNext());
                c.moveToLast();
                if (curId - 1 == Integer.parseInt(c.getString(0))) {
                    e_go_anim.this.mViewFlipper01.setDisplayedChild(curId - 2);
                    e_go_anim.this.ePage.setText(String.valueOf(curId - 1));
                    return;
                }
                e_go_anim.this.mViewFlipper01.setDisplayedChild(curId);
                e_go_anim.this.ePage.setText(String.valueOf(curId + 1));
                curId++;
                String gua_bin_now = (String) ((TextView) e_go_anim.this.findViewById((curId * 100) + 10)).getText();
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", e_go_anim.this.getPackageName()));
            }
        });
        this.g_mu_button = (Button) findViewById(R.id.gua_mutual);
        this.g_mu_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "muGua", "clicked", Long.valueOf(0));
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                int tmp = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cs = e_lib.bin_format(tmp);
                char[] cse = e_lib.bin_format(tmp);
                cse[5] = cs[4];
                cse[4] = cs[3];
                cse[3] = cs[2];
                cse[2] = cs[3];
                cse[1] = cs[2];
                cse[0] = cs[1];
                curId /= 100;
                Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToLast();
                int lastId = Integer.parseInt(c.getString(0));
                c.close();
                String[] f = new String[]{"e_bin"};
                if (lastId == curId) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{new String(cse, 0, 6)});
                    c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToLast();
                    e_go_anim.this.auto_gen_layout(Integer.parseInt(new String(c.getString(0))), Integer.parseInt(new String(cse, 0, 6), 2));
                    c.close();
                    return;
                }
                String[] _id = new String[(lastId + 1)];
                String[] _bin = new String[(lastId + 1)];
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                int i = 0;
                do {
                    if (i == curId) {
                        _id[i] = String.valueOf(curId);
                        _bin[i] = new String(cse, 0, 6);
                        i++;
                        c.moveToPrevious();
                    } else {
                        _id[i] = c.getString(0);
                        _bin[i] = c.getString(1);
                        i++;
                    }
                } while (c.moveToNext());
                c.close();
                e_go_anim.this.deleteDatabase(e_go_anim.DB_PATH_NAME);
                e_go_anim.edb = new MySQLiteOpenHelper(e_go_anim.this, e_go_anim.DB_PATH_NAME, null, 1, e_go_anim.tables, e_go_anim.fieldNames, e_go_anim.fieldTypes);
                for (i = 0; i <= lastId; i++) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{_bin[i]});
                }
                e_go_anim.this.mViewFlipper01.removeAllViews();
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                do {
                    e_go_anim.this.db_gua_loading(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1), 2));
                } while (c.moveToNext());
                c.moveToLast();
                if (curId - 1 == Integer.parseInt(c.getString(0))) {
                    e_go_anim.this.mViewFlipper01.setDisplayedChild(curId - 2);
                    e_go_anim.this.ePage.setText(String.valueOf(curId - 1));
                    return;
                }
                e_go_anim.this.mViewFlipper01.setDisplayedChild(curId);
                e_go_anim.this.ePage.setText(String.valueOf(curId + 1));
                curId++;
                String gua_bin_now = (String) ((TextView) e_go_anim.this.findViewById((curId * 100) + 10)).getText();
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", e_go_anim.this.getPackageName()));
            }
        });
        this.g_ex_button = (Button) findViewById(R.id.gua_exchange);
        this.g_ex_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "exchangeGua", "clicked", Long.valueOf(0));
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                char[] cse = e_lib.bin_format(63 - Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2));
                curId /= 100;
                Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToLast();
                int lastId = Integer.parseInt(c.getString(0));
                c.close();
                String[] f = new String[]{"e_bin"};
                if (lastId == curId) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{new String(cse, 0, 6)});
                    c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToLast();
                    e_go_anim.this.auto_gen_layout(Integer.parseInt(new String(c.getString(0))), Integer.parseInt(new String(cse, 0, 6), 2));
                    c.close();
                    return;
                }
                String[] _id = new String[(lastId + 1)];
                String[] _bin = new String[(lastId + 1)];
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                int i = 0;
                do {
                    if (i == curId) {
                        _id[i] = String.valueOf(curId);
                        _bin[i] = new String(cse, 0, 6);
                        i++;
                        c.moveToPrevious();
                    } else {
                        _id[i] = c.getString(0);
                        _bin[i] = c.getString(1);
                        i++;
                    }
                } while (c.moveToNext());
                c.close();
                e_go_anim.this.deleteDatabase(e_go_anim.DB_PATH_NAME);
                e_go_anim.edb = new MySQLiteOpenHelper(e_go_anim.this, e_go_anim.DB_PATH_NAME, null, 1, e_go_anim.tables, e_go_anim.fieldNames, e_go_anim.fieldTypes);
                for (i = 0; i <= lastId; i++) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{_bin[i]});
                }
                e_go_anim.this.mViewFlipper01.removeAllViews();
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                do {
                    e_go_anim.this.db_gua_loading(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1), 2));
                } while (c.moveToNext());
                c.moveToLast();
                if (curId - 1 == Integer.parseInt(c.getString(0))) {
                    e_go_anim.this.mViewFlipper01.setDisplayedChild(curId - 2);
                    e_go_anim.this.ePage.setText(String.valueOf(curId - 1));
                    return;
                }
                e_go_anim.this.mViewFlipper01.setDisplayedChild(curId);
                e_go_anim.this.ePage.setText(String.valueOf(curId + 1));
                curId++;
                String gua_bin_now = (String) ((TextView) e_go_anim.this.findViewById((curId * 100) + 10)).getText();
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", e_go_anim.this.getPackageName()));
            }
        });
        this.g_usd_button = (Button) findViewById(R.id.gua_upsidedown);
        this.g_usd_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "upsidedownGua", "clicked", Long.valueOf(0));
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                int tmp = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cs = e_lib.bin_format(tmp);
                char[] cse = e_lib.bin_format(tmp);
                cse[5] = cs[0];
                cse[4] = cs[1];
                cse[3] = cs[2];
                cse[2] = cs[3];
                cse[1] = cs[4];
                cse[0] = cs[5];
                curId /= 100;
                Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToLast();
                int lastId = Integer.parseInt(c.getString(0));
                c.close();
                String[] f = new String[]{"e_bin"};
                if (lastId == curId) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{new String(cse, 0, 6)});
                    c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToLast();
                    e_go_anim.this.auto_gen_layout(Integer.parseInt(new String(c.getString(0))), Integer.parseInt(new String(cse, 0, 6), 2));
                    c.close();
                    return;
                }
                String[] _id = new String[(lastId + 1)];
                String[] _bin = new String[(lastId + 1)];
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                int i = 0;
                do {
                    if (i == curId) {
                        _id[i] = String.valueOf(curId);
                        _bin[i] = new String(cse, 0, 6);
                        i++;
                        c.moveToPrevious();
                    } else {
                        _id[i] = c.getString(0);
                        _bin[i] = c.getString(1);
                        i++;
                    }
                } while (c.moveToNext());
                c.close();
                e_go_anim.this.deleteDatabase(e_go_anim.DB_PATH_NAME);
                e_go_anim.edb = new MySQLiteOpenHelper(e_go_anim.this, e_go_anim.DB_PATH_NAME, null, 1, e_go_anim.tables, e_go_anim.fieldNames, e_go_anim.fieldTypes);
                for (i = 0; i <= lastId; i++) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{_bin[i]});
                }
                e_go_anim.this.mViewFlipper01.removeAllViews();
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                do {
                    e_go_anim.this.db_gua_loading(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1), 2));
                } while (c.moveToNext());
                c.moveToLast();
                if (curId - 1 == Integer.parseInt(c.getString(0))) {
                    e_go_anim.this.mViewFlipper01.setDisplayedChild(curId - 2);
                    e_go_anim.this.ePage.setText(String.valueOf(curId - 1));
                    return;
                }
                e_go_anim.this.mViewFlipper01.setDisplayedChild(curId);
                e_go_anim.this.ePage.setText(String.valueOf(curId + 1));
                curId++;
                String gua_bin_now = (String) ((TextView) e_go_anim.this.findViewById((curId * 100) + 10)).getText();
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", e_go_anim.this.getPackageName()));
            }
        });
        this.gua64button = (Button) findViewById(R.id.gua_map);
        this.gua64button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackView("guaMap");
                Intent intent = new Intent();
                intent.setClass(e_go_anim.this, gua_map.class);
                Bundle bundle = new Bundle();
                bundle.putString("dbPath", e_go_anim.DB_PATH_NAME);
                intent.putExtras(bundle);
                e_go_anim.this.startActivityForResult(intent, 10);
            }
        });
        this.searchbutton = (Button) findViewById(R.id.searchxx);
        this.searchbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "searchXX", "clicked", Long.valueOf(0));
                e_go_anim.this.textEntryView = e_go_anim.this.getLayoutInflater().inflate(R.layout.save_dialog, null);
                ((TextView) e_go_anim.this.textEntryView.findViewById(R.id.sTextViewTitle)).setText("輸入爻辭");
                Builder rbuilder = new Builder(e_go_anim.this);
                rbuilder.setView(e_go_anim.this.textEntryView);
                e_go_anim.this.myDialogEditText = (EditText) e_go_anim.this.textEntryView.findViewById(R.id.myDialogEditText);
                e_go_anim.this.myDialogEditText.setHint(e_go_anim.this.getResources().getString(R.string.edit_hint));
                rbuilder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int ii) {
                        String searchxxText = e_go_anim.this.myDialogEditText.getText().toString();
                        if (e_go_anim.this.myDialogEditText.getText().length() == 0) {
                            Toast.makeText(e_go_anim.this, "請埴入爻辭", 1).show();
                            return;
                        }
                        View xxRelativeView = e_go_anim.this.getLayoutInflater().inflate(R.layout.xx_relative_gua_layout, null);
                        LinearLayout xxRelativeViewGua = (LinearLayout) xxRelativeView.findViewById(R.id.xx_relative_gua_layout);
                        int string_exist_or_not = 0;
                        for (int i = 0; i <= 63; i++) {
                            char[] oriBin = e_lib.bin_format(i);
                            String eXmain = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i), "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "e", "string", e_go_anim.this.getPackageName()))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "t", "string", e_go_anim.this.getPackageName()))).toString();
                            String eXu = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_u", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_ue", "string", e_go_anim.this.getPackageName()))).toString();
                            String eX5 = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_5", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_5e", "string", e_go_anim.this.getPackageName()))).toString();
                            String eX4 = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_4", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_4e", "string", e_go_anim.this.getPackageName()))).toString();
                            String eX3 = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_3", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_3e", "string", e_go_anim.this.getPackageName()))).toString();
                            String eX2 = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_2", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_2e", "string", e_go_anim.this.getPackageName()))).toString();
                            String eXi = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_i", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_ie", "string", e_go_anim.this.getPackageName()))).toString();
                            if (i == 0) {
                                String eX6 = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_6", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_6e", "string", e_go_anim.this.getPackageName()))).toString();
                                if (eX6.contains(searchxxText)) {
                                    e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eX6, searchxxText);
                                    string_exist_or_not = 1;
                                    e_go_anim.this.searchBtnFunction(i, 6, oriBin, xxRelativeViewGua);
                                }
                            }
                            if (i == 63) {
                                String eX9 = new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_9", "string", e_go_anim.this.getPackageName())))).append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(i) + "_9e", "string", e_go_anim.this.getPackageName()))).toString();
                                if (eX9.contains(searchxxText)) {
                                    e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eX9, searchxxText);
                                    string_exist_or_not = 1;
                                    e_go_anim.this.searchBtnFunction(i, 9, oriBin, xxRelativeViewGua);
                                }
                            }
                            if (eXmain.contains(searchxxText)) {
                                e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eXmain, searchxxText);
                                string_exist_or_not = 1;
                                e_go_anim.this.searchBtnFunction(i, 10, oriBin, xxRelativeViewGua);
                            }
                            if (eXu.contains(searchxxText)) {
                                e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eXu, searchxxText);
                                string_exist_or_not = 1;
                                e_go_anim.this.searchBtnFunction(i, 5, oriBin, xxRelativeViewGua);
                            }
                            if (eX5.contains(searchxxText)) {
                                e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eX5, searchxxText);
                                string_exist_or_not = 1;
                                e_go_anim.this.searchBtnFunction(i, 4, oriBin, xxRelativeViewGua);
                            }
                            if (eX4.contains(searchxxText)) {
                                e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eX4, searchxxText);
                                string_exist_or_not = 1;
                                e_go_anim.this.searchBtnFunction(i, 3, oriBin, xxRelativeViewGua);
                            }
                            if (eX3.contains(searchxxText)) {
                                e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eX3, searchxxText);
                                string_exist_or_not = 1;
                                e_go_anim.this.searchBtnFunction(i, 2, oriBin, xxRelativeViewGua);
                            }
                            if (eX2.contains(searchxxText)) {
                                e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eX2, searchxxText);
                                string_exist_or_not = 1;
                                e_go_anim.this.searchBtnFunction(i, 1, oriBin, xxRelativeViewGua);
                            }
                            if (eXi.contains(searchxxText)) {
                                e_go_anim.this.eXSpan = e_lib.searchxxTextSpan(eXi, searchxxText);
                                string_exist_or_not = 1;
                                e_go_anim.this.searchBtnFunction(i, 0, oriBin, xxRelativeViewGua);
                            }
                        }
                        if (string_exist_or_not == 1) {
                            Builder builder = new Builder(e_go_anim.this);
                            builder.setInverseBackgroundForced(true);
                            builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });
                            builder.setView(xxRelativeView);
                            AlertDialog xb = builder.create();
                            xb.setTitle("點擊文字可增加入演卦中！");
                            xb.setInverseBackgroundForced(true);
                            xb.setCanceledOnTouchOutside(true);
                            xb.show();
                            return;
                        }
                        Toast.makeText(e_go_anim.this, "無此爻辭", 1).show();
                    }
                });
                AlertDialog rb = rbuilder.create();
                rb.setCanceledOnTouchOutside(true);
                rb.show();
            }
        });
        this.leave_button = (Button) findViewById(R.id.leave);
        this.leave_button.setText(getResources().getString(R.string.strSaveTo));
        this.leave_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "save", "clicked", Long.valueOf(0));
                e_go_anim.edb.close();
                Intent intent = new Intent();
                intent.setClass(e_go_anim.this, filebrowser.class);
                Bundle bundle = new Bundle();
                bundle.putString("dbPath", e_go_anim.DB_PATH_NAME);
                intent.putExtras(bundle);
                e_go_anim.this.startActivity(intent);
            }
        });
        this.deletebutton = (Button) findViewById(R.id.deleteGua);
        this.deletebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "deleteGua", "clicked", Long.valueOf(0));
                if (new File(e_go_anim.DB_PATH_NAME).exists()) {
                    int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId() / 100;
                    Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToLast();
                    int lastId = Integer.parseInt(c.getString(0));
                    if (curId == 1 && curId == lastId) {
                        e_go_anim.this.deleteDatabase(e_go_anim.DB_PATH_NAME);
                        e_go_anim.this.mViewFlipper01.removeAllViews();
                        e_go_anim.this.gua_init();
                        return;
                    }
                    String[] _id = new String[lastId];
                    String[] _bin = new String[lastId];
                    c.moveToFirst();
                    int i = 0;
                    do {
                        if (i == curId - 1 && i != lastId - 1) {
                            c.moveToNext();
                        }
                        _id[i] = c.getString(0);
                        _bin[i] = c.getString(1);
                        i++;
                    } while (c.moveToNext());
                    c.close();
                    e_go_anim.this.deleteDatabase(e_go_anim.DB_PATH_NAME);
                    e_go_anim.edb = new MySQLiteOpenHelper(e_go_anim.this, e_go_anim.DB_PATH_NAME, null, 1, e_go_anim.tables, e_go_anim.fieldNames, e_go_anim.fieldTypes);
                    String[] f = new String[]{"e_bin"};
                    for (i = 0; i < lastId - 1; i++) {
                        e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{_bin[i]});
                    }
                    e_go_anim.this.mViewFlipper01.removeAllViews();
                    c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToFirst();
                    do {
                        e_go_anim.this.db_gua_loading(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1), 2));
                    } while (c.moveToNext());
                    c.moveToLast();
                    if (curId - 1 == Integer.parseInt(c.getString(0))) {
                        e_go_anim.this.mViewFlipper01.setDisplayedChild(curId - 2);
                        e_go_anim.this.ePage.setText(String.valueOf(curId - 1));
                        return;
                    }
                    e_go_anim.this.mViewFlipper01.setDisplayedChild(curId - 1);
                    e_go_anim.this.ePage.setText(String.valueOf(curId));
                    if (curId == 1) {
                        e_go_anim.this.etree.setText("易林");
                        return;
                    }
                    String gua_bin_now = (String) ((TextView) e_go_anim.this.findViewById((curId * 100) + 10)).getText();
                    e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", e_go_anim.this.getPackageName()));
                }
            }
        });
        ((Button) findViewById(R.id.jumpto)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.tracker.trackEvent("e_go", "jumptoGua", "clicked", Long.valueOf(0));
                Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToLast();
                int lastId = Integer.parseInt(c.getString(0));
                try {
                    int pageNum = Integer.parseInt(e_go_anim.this.ePage.getText().toString());
                    if (pageNum == 0 || pageNum > lastId) {
                        Toast.makeText(e_go_anim.this, "沒有此頁…", 0).show();
                        return;
                    }
                    e_go_anim.this.mViewFlipper01.setDisplayedChild(pageNum - 1);
                    int curId = pageNum;
                    if (curId == 1) {
                        e_go_anim.this.etree.setText("易林");
                        return;
                    }
                    String gua_bin_now = (String) ((TextView) e_go_anim.this.findViewById((curId * 100) + 10)).getText();
                    e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", e_go_anim.this.getPackageName()));
                } catch (Exception e) {
                    Toast.makeText(e_go_anim.this, "請填入頁數…", 0).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case AdSize.FULL_WIDTH /*-1*/:
                if (requestCode == 0) {
                    int gua_num = data.getExtras().getInt("gi");
                    if (gua_num == 65) {
                        startActivity(getIntent());
                        finish();
                        return;
                    }
                    char[] cs = e_lib.bin_format(gua_num);
                    edb = new MySQLiteOpenHelper(this, DB_PATH_NAME, null, 1, tables, fieldNames, fieldTypes);
                    edb.insert(tables[0], new String[]{"e_bin"}, new String[]{new String(cs, 0, 6)});
                    Cursor c = edb.select(tables[0], ff, "", null, null, null, null);
                    c.moveToLast();
                    auto_gen_layout(Integer.parseInt(c.getString(0)), gua_num);
                    c.close();
                    return;
                } else if (requestCode == 10) {
                    DB_PATH_NAME = data.getExtras().getString("dbPath");
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.layout.removeAllViewsInLayout();
        AdView adView = new AdView((Activity) this, AdSize.SMART_BANNER, "a14e4933ced69de");
        this.layout.addView(adView);
        adView.loadAd(new AdRequest());
    }

    public void gua_init() {
        for (int i = 0; i < this.items.length; i++) {
            if (i == 0) {
                this.items[i] = "從上次開始";
                this.Confucius[i] = 64;
            } else if (i == 1) {
                this.items[i] = "隨機起卦";
                this.Confucius[i] = 64;
            } else {
                this.items[i] = gv.guaName[i - 2];
                this.Confucius[i] = gv.guaNum[i - 2];
            }
        }
        Builder builder = new Builder(this);
        builder.setTitle("選擇起卦");
        builder.setCancelable(false);
        builder.setItems(this.items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Intent intent;
                Bundle bundle;
                if (item == 0) {
                    gv.flagChooseGuaMenu = false;
                    intent = new Intent();
                    intent.setClass(e_go_anim.this, dbSelect.class);
                    bundle = new Bundle();
                    bundle.putString("dbPath", e_go_anim.this.DB_PATH_NAME_USER);
                    intent.putExtras(bundle);
                    e_go_anim.this.startActivityForResult(intent, 10);
                } else if (item == 1) {
                    gv.flagChooseGuaMenu = false;
                    Toast.makeText(e_go_anim.this, "請輕搖手機…", 0).show();
                    intent = new Intent();
                    intent.setClass(e_go_anim.this, acce.class);
                    bundle = new Bundle();
                    bundle.putInt("gi", 1);
                    intent.putExtras(bundle);
                    e_go_anim.this.startActivityForResult(intent, 0);
                } else {
                    gv.flagChooseGuaMenu = false;
                    e_go_anim.edb = new MySQLiteOpenHelper(e_go_anim.this, e_go_anim.DB_PATH_NAME, null, 1, e_go_anim.tables, e_go_anim.fieldNames, e_go_anim.fieldTypes);
                    char[] cs = e_lib.bin_format(e_go_anim.this.Confucius[item]);
                    e_go_anim.edb.insert(e_go_anim.tables[0], new String[]{"e_bin"}, new String[]{new String(cs, 0, 6)});
                    Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToLast();
                    e_go_anim.this.auto_gen_layout(Integer.parseInt(c.getString(0)), e_go_anim.this.Confucius[item]);
                }
            }
        }).create().show();
    }

    public void gua_layout(int gua_num) {
        char[] cse = e_lib.bin_format(gua_num);
        int curId = this.mViewFlipper01.getCurrentView().getId();
        ImageButton eu = (ImageButton) findViewById(curId + 6);
        ImageButton e5 = (ImageButton) findViewById(curId + 5);
        ImageButton e4 = (ImageButton) findViewById(curId + 4);
        ImageButton e3 = (ImageButton) findViewById(curId + 3);
        ImageButton e2 = (ImageButton) findViewById(curId + 2);
        ImageButton ei = (ImageButton) findViewById(curId + 1);
        TextView e_t = (TextView) findViewById(curId + 8);
        TextView e_e = (TextView) findViewById(curId + 9);
        TextView e_num = (TextView) findViewById(curId + 10);
        TextView e_num_i = (TextView) findViewById(curId + 11);
        ((TextView) findViewById(curId + 7)).setText(getResources().getIdentifier("e" + Integer.toString(gua_num), "string", getPackageName()));
        e_t.setText(getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", getPackageName()));
        e_e.setText(getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", getPackageName()));
        e_num.setText(new String(cse, 0, 6));
        e_num_i.setText(new String(cse, 0, 6));
        Cursor c = edb.select(tables[0], new String[]{"e_id", "e_bin"}, "", null, null, null, null);
        if ((curId / 100) - 1 > 0) {
            c.moveToPosition((curId / 100) - 1);
            c.moveToPrevious();
            this.etree.setText(getResources().getIdentifier("e" + Integer.parseInt(c.getString(1), 2) + "xe" + Integer.toString(gua_num), "string", getPackageName()));
        } else {
            this.etree.setText("易林");
        }
        eu.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 0, 1))].intValue()));
        e5.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 1, 1))].intValue()));
        e4.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 2, 1))].intValue()));
        e3.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 3, 1))].intValue()));
        e2.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 4, 1))].intValue()));
        ei.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 5, 1))].intValue()));
        this.ePage.setText(String.valueOf(curId / 100));
    }

    public void auto_gen_layout(int gua_id, int gua_num) {
        char[] cse = e_lib.bin_format(gua_num);
        View guaView = getLayoutInflater().inflate(R.layout.gua_layout, null);
        ScrollView svtext = (ScrollView) guaView.findViewById(R.id.svtext);
        ScrollView svimagebutton = (ScrollView) guaView.findViewById(R.id.svimagebutton);
        gua_id *= 100;
        guaView.setId(gua_id);
        ImageButton eu = (ImageButton) guaView.findViewById(R.id.eu);
        ImageButton e5 = (ImageButton) guaView.findViewById(R.id.e5);
        ImageButton e4 = (ImageButton) guaView.findViewById(R.id.e4);
        ImageButton e3 = (ImageButton) guaView.findViewById(R.id.e3);
        ImageButton e2 = (ImageButton) guaView.findViewById(R.id.e2);
        ImageButton ei = (ImageButton) guaView.findViewById(R.id.ei);
        TextView e_gua = (TextView) guaView.findViewById(R.id.e_gua);
        TextView e_t = (TextView) guaView.findViewById(R.id.e_t);
        TextView e_e = (TextView) guaView.findViewById(R.id.e_e);
        TextView e_num = (TextView) guaView.findViewById(R.id.e_num);
        TextView e_num_i = (TextView) guaView.findViewById(R.id.e_num_i);
        TextView ttmp = (TextView) guaView.findViewById(R.id.tmp);
        eu.setId(gua_id + 6);
        e5.setId(gua_id + 5);
        e4.setId(gua_id + 4);
        e3.setId(gua_id + 3);
        e2.setId(gua_id + 2);
        ei.setId(gua_id + 1);
        e_gua.setId(gua_id + 7);
        e_t.setId(gua_id + 8);
        e_e.setId(gua_id + 9);
        e_num.setId(gua_id + 10);
        e_num_i.setId(gua_id + 11);
        ttmp.setId(gua_id + 12);
        e_gua.setText(getResources().getIdentifier("e" + Integer.toString(gua_num), "string", getPackageName()));
        e_t.setText(getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", getPackageName()));
        e_e.setText(getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", getPackageName()));
        e_num.setText(new String(cse, 0, 6));
        e_num_i.setText(new String(cse, 0, 6));
        Cursor c = edb.select(tables[0], ff, "", null, null, null, null);
        if ((gua_id / 100) - 1 > 0) {
            c.moveToPosition((gua_id / 100) - 1);
            c.moveToPrevious();
            this.etree.setText(getResources().getIdentifier("e" + Integer.parseInt(c.getString(1), 2) + "xe" + Integer.toString(gua_num), "string", getPackageName()));
        }
        c.close();
        svtext.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        svimagebutton.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        eu.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e5.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e4.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e3.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e2.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        ei.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        eu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton eu = (ImageButton) e_go_anim.this.findViewById(curId + 6);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[0] == '1') {
                    cse[0] = '0';
                } else {
                    cse[0] = '1';
                }
                eu.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 0, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e5 = (ImageButton) e_go_anim.this.findViewById(curId + 5);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[1] == '1') {
                    cse[1] = '0';
                } else {
                    cse[1] = '1';
                }
                e5.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 1, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e4 = (ImageButton) e_go_anim.this.findViewById(curId + 4);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[2] == '1') {
                    cse[2] = '0';
                } else {
                    cse[2] = '1';
                }
                e4.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 2, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e3 = (ImageButton) e_go_anim.this.findViewById(curId + 3);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[3] == '1') {
                    cse[3] = '0';
                } else {
                    cse[3] = '1';
                }
                e3.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 3, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e2 = (ImageButton) e_go_anim.this.findViewById(curId + 2);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[4] == '1') {
                    cse[4] = '0';
                } else {
                    cse[4] = '1';
                }
                e2.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 4, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        ei.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton ei = (ImageButton) e_go_anim.this.findViewById(curId + 1);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[5] == '1') {
                    cse[5] = '0';
                } else {
                    cse[5] = '1';
                }
                ei.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 5, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        eu.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_u", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_ue", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e5.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_5", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_5e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e4.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_4", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_4e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e3.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_3", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_3e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e2.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_2", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_2e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        ei.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_i", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_ie", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        eu.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 0, 1))].intValue()));
        e5.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 1, 1))].intValue()));
        e4.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 2, 1))].intValue()));
        e3.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 3, 1))].intValue()));
        e2.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 4, 1))].intValue()));
        ei.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 5, 1))].intValue()));
        this.mViewFlipper01.addView(guaView, (gua_id / 100) - 1);
        getWindow().setSoftInputMode(3);
        this.ePage.setText(String.valueOf(gua_id / 100));
        this.mViewFlipper01.setInAnimation(AnimationUtils.loadAnimation(this, R.layout.rinanim));
        this.mViewFlipper01.setOutAnimation(AnimationUtils.loadAnimation(this, R.layout.routanim));
        if (gua_id / 100 != 1) {
            this.mViewFlipper01.showNext();
        }
    }

    public void db_gua_loading(int gua_id, int gua_num) {
        char[] cse = e_lib.bin_format(gua_num);
        View guaView = getLayoutInflater().inflate(R.layout.gua_layout, null);
        View guaLayout = (LinearLayout) guaView.findViewById(R.id.gua_layout);
        ScrollView svtext = (ScrollView) guaView.findViewById(R.id.svtext);
        ScrollView svimagebutton = (ScrollView) guaView.findViewById(R.id.svimagebutton);
        gua_id *= 100;
        guaLayout.setId(gua_id);
        ImageButton eu = (ImageButton) guaView.findViewById(R.id.eu);
        ImageButton e5 = (ImageButton) guaView.findViewById(R.id.e5);
        ImageButton e4 = (ImageButton) guaView.findViewById(R.id.e4);
        ImageButton e3 = (ImageButton) guaView.findViewById(R.id.e3);
        ImageButton e2 = (ImageButton) guaView.findViewById(R.id.e2);
        ImageButton ei = (ImageButton) guaView.findViewById(R.id.ei);
        TextView e_gua = (TextView) guaView.findViewById(R.id.e_gua);
        TextView e_t = (TextView) guaView.findViewById(R.id.e_t);
        TextView e_e = (TextView) guaView.findViewById(R.id.e_e);
        TextView e_num = (TextView) guaView.findViewById(R.id.e_num);
        TextView e_num_i = (TextView) guaView.findViewById(R.id.e_num_i);
        TextView ttmp = (TextView) guaView.findViewById(R.id.tmp);
        eu.setId(gua_id + 6);
        e5.setId(gua_id + 5);
        e4.setId(gua_id + 4);
        e3.setId(gua_id + 3);
        e2.setId(gua_id + 2);
        ei.setId(gua_id + 1);
        e_gua.setId(gua_id + 7);
        e_t.setId(gua_id + 8);
        e_e.setId(gua_id + 9);
        e_num.setId(gua_id + 10);
        e_num_i.setId(gua_id + 11);
        ttmp.setId(gua_id + 12);
        e_gua.setText(getResources().getIdentifier("e" + Integer.toString(gua_num), "string", getPackageName()));
        e_t.setText(getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", getPackageName()));
        e_e.setText(getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", getPackageName()));
        e_num.setText(new String(cse, 0, 6));
        e_num_i.setText(new String(cse, 0, 6));
        Cursor c = edb.select(tables[0], ff, "", null, null, null, null);
        if ((gua_id / 100) - 1 > 0) {
            c.moveToPosition((gua_id / 100) - 1);
            c.moveToPrevious();
            this.etree.setText(getResources().getIdentifier("e" + Integer.parseInt(c.getString(1), 2) + "xe" + Integer.toString(gua_num), "string", getPackageName()));
        }
        svtext.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        svimagebutton.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        eu.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e5.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e4.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e3.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        e2.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        ei.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    e_go_anim.this.oldTouchValue = event.getX();
                }
                if (event.getAction() == 1) {
                    e_go_anim.this.animation(e_go_anim.this.oldTouchValue, event.getX());
                }
                return false;
            }
        });
        eu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton eu = (ImageButton) e_go_anim.this.findViewById(curId + 6);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[0] == '1') {
                    cse[0] = '0';
                } else {
                    cse[0] = '1';
                }
                eu.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 0, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e5 = (ImageButton) e_go_anim.this.findViewById(curId + 5);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[1] == '1') {
                    cse[1] = '0';
                } else {
                    cse[1] = '1';
                }
                e5.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 1, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e4 = (ImageButton) e_go_anim.this.findViewById(curId + 4);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[2] == '1') {
                    cse[2] = '0';
                } else {
                    cse[2] = '1';
                }
                e4.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 2, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e3 = (ImageButton) e_go_anim.this.findViewById(curId + 3);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[3] == '1') {
                    cse[3] = '0';
                } else {
                    cse[3] = '1';
                }
                e3.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 3, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        e2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton e2 = (ImageButton) e_go_anim.this.findViewById(curId + 2);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[4] == '1') {
                    cse[4] = '0';
                } else {
                    cse[4] = '1';
                }
                e2.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 4, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        ei.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                TextView enii = (TextView) e_go_anim.this.findViewById(curId + 11);
                TextView egg = (TextView) e_go_anim.this.findViewById(curId + 7);
                TextView ett = (TextView) e_go_anim.this.findViewById(curId + 8);
                TextView eee = (TextView) e_go_anim.this.findViewById(curId + 9);
                ImageButton ei = (ImageButton) e_go_anim.this.findViewById(curId + 1);
                String gua_bin = (String) enii.getText();
                int tmpOri = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(curId + 10)).getText(), 2);
                char[] cse = e_lib.bin_format(Integer.parseInt(gua_bin, 2));
                if (cse[5] == '1') {
                    cse[5] = '0';
                } else {
                    cse[5] = '1';
                }
                ei.setBackgroundDrawable(e_go_anim.this.getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 5, 1))].intValue()));
                enii.setText(new String(cse, 0, 6));
                int gua_num = Integer.parseInt(new String(cse, 0, 6), 2);
                egg.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
                ett.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "t", "string", e_go_anim.this.getPackageName()));
                eee.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "e", "string", e_go_anim.this.getPackageName()));
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + tmpOri + "xe" + Integer.toString(gua_num), "string", e_go_anim.this.getPackageName()));
            }
        });
        eu.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_u", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_ue", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e5.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_5", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_5e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e4.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_4", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_4e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e3.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_3", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_3e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        e2.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_2", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_2e", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        ei.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                int gua_num = Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(e_go_anim.this.mViewFlipper01.getCurrentView().getId() + 11)).getText(), 2);
                e_lib.alertDialogSet(new Builder(e_go_anim.this), gua_num, new StringBuilder(String.valueOf(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_i", "string", e_go_anim.this.getPackageName())))).append("\n").append(e_go_anim.this.getString(e_go_anim.this.getResources().getIdentifier("e" + Integer.toString(gua_num) + "_ie", "string", e_go_anim.this.getPackageName()))).toString(), e_go_anim.this);
                return false;
            }
        });
        eu.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 0, 1))].intValue()));
        e5.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 1, 1))].intValue()));
        e4.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 2, 1))].intValue()));
        e3.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 3, 1))].intValue()));
        e2.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 4, 1))].intValue()));
        ei.setBackgroundDrawable(getResources().getDrawable(gv.gua_component[Integer.parseInt(new String(cse, 5, 1))].intValue()));
        this.mViewFlipper01.addView(guaLayout, (gua_id / 100) - 1);
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
        int curId;
        String gua_bin;
        if (oldX - currX < -80.0f) {
            curId = this.mViewFlipper01.getCurrentView().getId();
            gua_bin = (String) ((TextView) findViewById(curId + 10)).getText();
            if (Integer.parseInt((String) ((TextView) findViewById(curId + 11)).getText(), 2) != Integer.parseInt(gua_bin, 2)) {
                gua_layout(Integer.parseInt(new String(e_lib.bin_format(Integer.parseInt(gua_bin, 2)), 0, 6), 2));
            }
            this.mViewFlipper01.setInAnimation(AnimationUtils.loadAnimation(this, R.layout.linanim));
            this.mViewFlipper01.setOutAnimation(AnimationUtils.loadAnimation(this, R.layout.loutanim));
            this.mViewFlipper01.showPrevious();
            curId = this.mViewFlipper01.getCurrentView().getId();
            this.ePage.setText(String.valueOf(curId / 100));
            curId /= 100;
            if (curId == 1) {
                this.etree.setText("易林");
            } else {
                String gua_bin_now = (String) ((TextView) findViewById((curId * 100) + 10)).getText();
                this.etree.setText(getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", getPackageName()));
            }
        }
        if (oldX - currX > 80.0f) {
            curId = this.mViewFlipper01.getCurrentView().getId();
            gua_bin = (String) ((TextView) findViewById(curId + 10)).getText();
            if (Integer.parseInt((String) ((TextView) findViewById(curId + 11)).getText(), 2) != Integer.parseInt(gua_bin, 2)) {
                gua_layout(Integer.parseInt(new String(e_lib.bin_format(Integer.parseInt(gua_bin, 2)), 0, 6), 2));
            }
            this.mViewFlipper01.setInAnimation(AnimationUtils.loadAnimation(this, R.layout.rinanim));
            this.mViewFlipper01.setOutAnimation(AnimationUtils.loadAnimation(this, R.layout.routanim));
            this.mViewFlipper01.showNext();
            curId = this.mViewFlipper01.getCurrentView().getId();
            this.ePage.setText(String.valueOf(curId / 100));
            curId /= 100;
            if (curId == 1) {
                this.etree.setText("易林");
                return;
            }
            gua_bin_now = (String) ((TextView) findViewById((curId * 100) + 10)).getText();
            this.etree.setText(getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", getPackageName()));
        }
    }

    private void searchBtnFunction(int i, final int xx, char[] oriBin, LinearLayout xxRelativeViewGua) {
        View xxGuaLayout = getLayoutInflater().inflate(R.layout.very_small_gua_layout, null);
        LinearLayout xxGuaLayoutLinearLayout = (LinearLayout) xxGuaLayout.findViewById(R.id.very_small_gua_layout);
        e_lib.verySmallGuaArrange(xxGuaLayoutLinearLayout, oriBin, this);
        TextView xxRelativeTextView = (TextView) xxGuaLayoutLinearLayout.findViewById(R.id.xxrelative);
        xxRelativeTextView.setId((i * 100) + xx);
        xxRelativeTextView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int curId = e_go_anim.this.mViewFlipper01.getCurrentView().getId();
                char[] cse = e_lib.bin_format((v.getId() - xx) / 100);
                curId /= 100;
                Cursor c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToLast();
                int lastId = Integer.parseInt(c.getString(0));
                c.close();
                String[] f = new String[]{"e_bin"};
                if (lastId == curId) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{new String(cse, 0, 6)});
                    c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                    c.moveToLast();
                    e_go_anim.this.auto_gen_layout(Integer.parseInt(new String(c.getString(0))), Integer.parseInt(new String(cse, 0, 6), 2));
                    c.close();
                    return;
                }
                String[] _id = new String[(lastId + 1)];
                String[] _bin = new String[(lastId + 1)];
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                int i = 0;
                do {
                    if (i == curId) {
                        _id[i] = String.valueOf(curId);
                        _bin[i] = new String(cse, 0, 6);
                        i++;
                        c.moveToPrevious();
                    } else {
                        _id[i] = c.getString(0);
                        _bin[i] = c.getString(1);
                        i++;
                    }
                } while (c.moveToNext());
                c.close();
                e_go_anim.this.deleteDatabase(e_go_anim.DB_PATH_NAME);
                e_go_anim.edb = new MySQLiteOpenHelper(e_go_anim.this, e_go_anim.DB_PATH_NAME, null, 1, e_go_anim.tables, e_go_anim.fieldNames, e_go_anim.fieldTypes);
                for (i = 0; i <= lastId; i++) {
                    e_go_anim.edb.insert(e_go_anim.tables[0], f, new String[]{_bin[i]});
                }
                e_go_anim.this.mViewFlipper01.removeAllViews();
                c = e_go_anim.edb.select(e_go_anim.tables[0], e_go_anim.ff, "", null, null, null, null);
                c.moveToFirst();
                do {
                    e_go_anim.this.db_gua_loading(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1), 2));
                } while (c.moveToNext());
                c.moveToLast();
                if (curId - 1 == Integer.parseInt(c.getString(0))) {
                    e_go_anim.this.mViewFlipper01.setDisplayedChild(curId - 2);
                    e_go_anim.this.ePage.setText(String.valueOf(curId - 1));
                    return;
                }
                e_go_anim.this.mViewFlipper01.setDisplayedChild(curId);
                e_go_anim.this.ePage.setText(String.valueOf(curId + 1));
                curId++;
                String gua_bin_now = (String) ((TextView) e_go_anim.this.findViewById((curId * 100) + 10)).getText();
                e_go_anim.this.etree.setText(e_go_anim.this.getResources().getIdentifier("e" + Integer.parseInt((String) ((TextView) e_go_anim.this.findViewById(((curId - 1) * 100) + 10)).getText(), 2) + "xe" + Integer.toString(Integer.parseInt(gua_bin_now, 2)), "string", e_go_anim.this.getPackageName()));
            }
        });
        xxRelativeTextView.setText(this.eXSpan);
        xxRelativeViewGua.addView(xxGuaLayout);
    }

    protected void onResume() {
        super.onResume();
        gv.trackerInstance = GoogleAnalytics.getInstance(getApplicationContext());
        GAServiceManager.getInstance().setDispatchPeriod(60);
        gv.tracker = gv.trackerInstance.getTracker("UA-4367088-5");
        edb = new MySQLiteOpenHelper(this, DB_PATH_NAME, null, 1, tables, fieldNames, fieldTypes);
        Cursor c = edb.select(tables[0], ff, "", null, null, null, null);
        while (c.moveToNext()) {
            db_gua_loading(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1), 2));
        }
        c.close();
    }

    protected void onStart() {
        super.onStart();
        Log.i("resultOK", "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.i("resultOK", "onStop");
    }

    protected void onPause() {
        super.onPause();
        Log.i("resultOK", "onPause");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("resultOK", "onRestart");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.i("resultOK", "onDestroy");
        Log.i("resultOK", "menu" + Boolean.toString(gv.flagChooseGuaMenu));
    }

    public void onBackPressed() {
        gv.tracker.trackTiming("e_go", System.currentTimeMillis() - gv.trackerRecStartTiming, ModelFields.TIMING, null);
        finish();
    }
}
