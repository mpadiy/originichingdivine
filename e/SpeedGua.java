package comp.android.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SpeedGua extends Activity {
    private int e2;
    private int e3;
    private int e4;
    private int e5;
    private int ei;
    private int eu;
    private int futureNum;
    private View guaView;
    private Integer[] gua_component_small = new Integer[]{Integer.valueOf(R.drawable.small_ying), Integer.valueOf(R.drawable.small_yang), Integer.valueOf(R.drawable.ch_small_ying), Integer.valueOf(R.drawable.ch_small_yang)};
    private int oriNum;
    private Button share_button;
    private Button translateBtn;

    public void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.speed_gua);
        LinearLayout speed_gua = (LinearLayout) findViewById(R.id.speed_gua);
        this.guaView = getLayoutInflater().inflate(R.layout.speed_gua_layout, null);
        LinearLayout guaLayout = (LinearLayout) this.guaView.findViewById(R.id.speed_gua_layout);
        this.share_button = (Button) guaLayout.findViewById(R.id.share_btn);
        this.translateBtn = (Button) guaLayout.findViewById(R.id.translateBtn);
        TextView xx = (TextView) guaLayout.findViewById(R.id.xx);
        TextView guaChange = (TextView) guaLayout.findViewById(R.id.gua_change);
        speed_gua.removeAllViews();
        if (gv.speedGuaExists) {
            this.ei = gv.ei;
            this.e2 = gv.e2;
            this.e3 = gv.e3;
            this.e4 = gv.e4;
            this.e5 = gv.e5;
            this.eu = gv.eu;
        } else {
            this.ei = e_lib.big_e_go(49);
            this.e2 = e_lib.big_e_go(49);
            this.e3 = e_lib.big_e_go(49);
            this.e4 = e_lib.big_e_go(49);
            this.e5 = e_lib.big_e_go(49);
            this.eu = e_lib.big_e_go(49);
            gv.ei = this.ei;
            gv.e2 = this.e2;
            gv.e3 = this.e3;
            gv.e4 = this.e4;
            gv.e5 = this.e5;
            gv.eu = this.eu;
        }
        String tmp = "";
        String i_poetry = "";
        int[] chosenXX = new int[6];
        int[] chosenXX4 = new int[6];
        int[] chosenXX5 = new int[6];
        this.oriNum = ((((((this.eu % 2) * 32) + ((this.e5 % 2) * 16)) + ((this.e4 % 2) * 8)) + ((this.e3 % 2) * 4)) + ((this.e2 % 2) * 2)) + (this.ei % 2);
        if (this.ei == 6) {
            this.ei = 1;
            chosenXX[0] = 1;
        }
        if (this.ei == 9) {
            this.ei = 0;
            chosenXX[0] = 1;
        }
        if (this.e2 == 6) {
            this.e2 = 1;
            chosenXX[1] = 1;
        }
        if (this.e2 == 9) {
            this.e2 = 0;
            chosenXX[1] = 1;
        }
        if (this.e3 == 6) {
            this.e3 = 1;
            chosenXX[2] = 1;
        }
        if (this.e3 == 9) {
            this.e3 = 0;
            chosenXX[2] = 1;
        }
        if (this.e4 == 6) {
            this.e4 = 1;
            chosenXX[3] = 1;
        }
        if (this.e4 == 9) {
            this.e4 = 0;
            chosenXX[3] = 1;
        }
        if (this.e5 == 6) {
            this.e5 = 1;
            chosenXX[4] = 1;
        }
        if (this.e5 == 9) {
            this.e5 = 0;
            chosenXX[4] = 1;
        }
        if (this.eu == 6) {
            this.eu = 1;
            chosenXX[5] = 1;
        }
        if (this.eu == 9) {
            this.eu = 0;
            chosenXX[5] = 1;
        }
        this.futureNum = ((((((this.eu % 2) * 32) + ((this.e5 % 2) * 16)) + ((this.e4 % 2) * 8)) + ((this.e3 % 2) * 4)) + ((this.e2 % 2) * 2)) + (this.ei % 2);
        int changeXXNum = ((((chosenXX[0] + chosenXX[1]) + chosenXX[2]) + chosenXX[3]) + chosenXX[4]) + chosenXX[5];
        if (changeXXNum == 4) {
            boolean gotIt = false;
            for (i = 0; i < chosenXX.length; i++) {
                if (chosenXX[i] == 0 && !gotIt) {
                    chosenXX4[i] = 1;
                    gotIt = true;
                }
            }
        } else if (changeXXNum == 5) {
            for (i = 0; i < chosenXX.length; i++) {
                if (chosenXX[i] == 1) {
                    chosenXX5[i] = 0;
                } else if (chosenXX[i] == 0) {
                    chosenXX5[i] = 1;
                }
            }
        }
        String oriGuaName = getString(getResources().getIdentifier("e" + Integer.toString(this.oriNum) + "name", "string", getPackageName())) + "之爻";
        String futureGuaName = getString(getResources().getIdentifier("e" + Integer.toString(this.futureNum) + "name", "string", getPackageName())) + "之爻";
        guaChange.setText(getString(getResources().getIdentifier("e" + Integer.toString(this.oriNum) + "name", "string", getPackageName())) + " 之 " + getString(getResources().getIdentifier("e" + Integer.toString(this.futureNum) + "name", "string", getPackageName())));
        char[] oriBin = e_lib.bin_format(this.oriNum);
        char[] futureBin = e_lib.bin_format(this.futureNum);
        ImageButton e5o = (ImageButton) guaLayout.findViewById(R.id.e5o);
        ImageButton e4o = (ImageButton) guaLayout.findViewById(R.id.e4o);
        ImageButton e3o = (ImageButton) guaLayout.findViewById(R.id.e3o);
        ImageButton e2o = (ImageButton) guaLayout.findViewById(R.id.e2o);
        ImageButton eio = (ImageButton) guaLayout.findViewById(R.id.eio);
        ImageButton euf = (ImageButton) guaLayout.findViewById(R.id.euf);
        ImageButton e5f = (ImageButton) guaLayout.findViewById(R.id.e5f);
        ImageButton e4f = (ImageButton) guaLayout.findViewById(R.id.e4f);
        ImageButton e3f = (ImageButton) guaLayout.findViewById(R.id.e3f);
        ImageButton e2f = (ImageButton) guaLayout.findViewById(R.id.e2f);
        ImageButton eif = (ImageButton) guaLayout.findViewById(R.id.eif);
        ((ImageButton) guaLayout.findViewById(R.id.euo)).setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(oriBin, 0, 1))].intValue()));
        e5o.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(oriBin, 1, 1))].intValue()));
        e4o.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(oriBin, 2, 1))].intValue()));
        e3o.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(oriBin, 3, 1))].intValue()));
        e2o.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(oriBin, 4, 1))].intValue()));
        eio.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(oriBin, 5, 1))].intValue()));
        String tmpGuaName = "";
        for (int ii = 0; ii < 2; ii++) {
            i = 0;
            while (i < chosenXX.length) {
                char[] tmpGuaBin;
                int tmpGuaNum;
                if (ii == 1) {
                    tmpGuaBin = futureBin;
                    tmpGuaNum = this.futureNum;
                    tmpGuaName = futureGuaName;
                } else {
                    tmpGuaBin = oriBin;
                    tmpGuaNum = this.oriNum;
                    tmpGuaName = oriGuaName;
                }
                if (i == 0) {
                    tmp = "_i";
                }
                if (i == 1) {
                    tmp = "_2";
                }
                if (i == 2) {
                    tmp = "_3";
                }
                if (i == 3) {
                    tmp = "_4";
                }
                if (i == 4) {
                    tmp = "_5";
                }
                if (i == 5) {
                    tmp = "_u";
                }
                if (chosenXX[i] != 1) {
                    if (chosenXX4[i] == 1 || chosenXX5[i] == 1) {
                        i_poetry = getString(getResources().getIdentifier("e" + Integer.toString(tmpGuaNum) + tmp, "string", getPackageName())) + "\n" + i_poetry;
                    }
                    switch (i) {
                        case 0:
                            eif.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 5, 1))].intValue()));
                            break;
                        case 1:
                            e2f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 4, 1))].intValue()));
                            break;
                        case AnalyticsGmsCoreClient.REMOTE_EXECUTION_FAILED /*2*/:
                            e3f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 3, 1))].intValue()));
                            break;
                        case 3:
                            e4f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 2, 1))].intValue()));
                            break;
                        case 4:
                            e5f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 1, 1))].intValue()));
                            break;
                        case 5:
                            euf.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 0, 1))].intValue()));
                            break;
                        default:
                            break;
                    }
                }
                if (!(changeXXNum == 4 || changeXXNum == 5)) {
                    i_poetry = getString(getResources().getIdentifier("e" + Integer.toString(tmpGuaNum) + tmp, "string", getPackageName())) + "\n" + i_poetry;
                }
                switch (i) {
                    case 0:
                        eif.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 5, 1)) + 2].intValue()));
                        break;
                    case 1:
                        e2f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 4, 1)) + 2].intValue()));
                        break;
                    case AnalyticsGmsCoreClient.REMOTE_EXECUTION_FAILED /*2*/:
                        e3f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 3, 1)) + 2].intValue()));
                        break;
                    case 3:
                        e4f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 2, 1)) + 2].intValue()));
                        break;
                    case 4:
                        e5f.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 1, 1)) + 2].intValue()));
                        break;
                    case 5:
                        euf.setBackgroundDrawable(getResources().getDrawable(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 0, 1)) + 2].intValue()));
                        break;
                    default:
                        break;
                }
                i++;
            }
            int countXX = 0;
            for (int i2 : chosenXX) {
                countXX += i2;
            }
            i_poetry = "\n" + tmpGuaName + "\n" + i_poetry;
            if (countXX == 0) {
                i_poetry = "\n";
            }
        }
        xx.setText(new StringBuilder(String.valueOf(i_poetry)).append("\n").append(getString(getResources().getIdentifier("e" + Integer.toString(this.oriNum) + "x" + "e" + Integer.toString(this.futureNum), "string", getPackageName()))).toString());
        speed_gua.addView(this.guaView);
        this.share_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SpeedGua.this.share_button.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        SpeedGua.this.share_button.setTextColor(-256);
                    }
                }, 300);
                gv.path = new StringBuilder(String.valueOf(SpeedGua.this.getFilesDir().getParent())).append(File.separator).append(SpeedGua.this.getFilesDir().getName()).toString();
                File imageFile = new File(gv.path + File.separator + "DroEd_" + e_lib.timeNow() + ".jpg");
                View vImage = SpeedGua.this.guaView;
                vImage.setDrawingCacheEnabled(true);
                Bitmap bm = Bitmap.createBitmap(vImage.getDrawingCache());
                vImage.setDrawingCacheEnabled(false);
                try {
                    FileOutputStream imageFout = SpeedGua.this.openFileOutput(imageFile.getName(), 1);
                    bm.compress(CompressFormat.JPEG, 100, imageFout);
                    imageFout.close();
                } catch (IOException e) {
                    Log.e("panel", "IOEception", e);
                }
                Uri imageUri = Uri.fromFile(imageFile);
                Intent shareIntent = new Intent("android.intent.action.SEND");
                shareIntent.setType("image/*");
                shareIntent.putExtra("android.intent.extra.SUBJECT", SpeedGua.this.getResources().getString(R.string.速占));
                shareIntent.putExtra("android.intent.extra.TEXT", SpeedGua.this.getResources().getString(R.string.速占));
                shareIntent.putExtra("android.intent.extra.STREAM", imageUri);
                SpeedGua.this.startActivity(Intent.createChooser(shareIntent, "分享到…"));
            }
        });
        this.translateBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                gv.speedGuaExists = true;
                Builder xab = new Builder(SpeedGua.this);
                xab.setTitle(SpeedGua.this.getResources().getString(R.string.白話文));
                xab.setPositiveButton(SpeedGua.this.getString(SpeedGua.this.getResources().getIdentifier("e" + Integer.toString(SpeedGua.this.oriNum) + "name", "string", SpeedGua.this.getPackageName())), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        String webLink = SpeedGua.this.getString(SpeedGua.this.getResources().getIdentifier("e" + Integer.toString(SpeedGua.this.oriNum) + "web", "string", SpeedGua.this.getPackageName()));
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("webLink", webLink);
                        intent.putExtras(bundle);
                        intent.setClass(SpeedGua.this, webView4Translation.class);
                        SpeedGua.this.startActivity(intent);
                    }
                });
                xab.setNegativeButton(SpeedGua.this.getString(SpeedGua.this.getResources().getIdentifier("e" + Integer.toString(SpeedGua.this.futureNum) + "name", "string", SpeedGua.this.getPackageName())), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        String webLink = SpeedGua.this.getString(SpeedGua.this.getResources().getIdentifier("e" + Integer.toString(SpeedGua.this.futureNum) + "web", "string", SpeedGua.this.getPackageName()));
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("webLink", webLink);
                        intent.putExtras(bundle);
                        intent.setClass(SpeedGua.this, webView4Translation.class);
                        SpeedGua.this.startActivity(intent);
                    }
                });
                AlertDialog xb = xab.create();
                xb.setCanceledOnTouchOutside(true);
                xb.show();
                xb.getButton(-1).setTextSize(30.0f);
                xb.getButton(-2).setTextSize(30.0f);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    public void onBackPressed() {
        finish();
    }
}
