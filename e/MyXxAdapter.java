package comp.android.e;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyXxAdapter extends BaseAdapter {
    private Context c;
    public int start = 0;
    public int stop = 0;
    private String[] vD;
    public int vid;

    public MyXxAdapter(Context mc, int id, String[] vData) {
        this.c = mc;
        this.vD = vData;
        this.vid = id;
    }

    public int getCount() {
        return this.vD.length;
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public int getStart() {
        return this.start;
    }

    public int getStop() {
        return this.stop;
    }

    public int setStart(int s) {
        this.start = s;
        return this.start;
    }

    public int setStop(int s) {
        this.stop = s;
        return this.stop;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(this.c);
            tv.setTextSize(30.0f);
        } else {
            tv = (TextView) convertView;
        }
        tv.setText(this.vD[position]);
        if (this.vD[position].equals("，")) {
            setStart(1);
        }
        if (getStart() == 1 && getStop() == 0) {
            for (String equals : gv.keyWord) {
                if (equals.equals(this.vD[position])) {
                    tv.setTextColor(-65536);
                }
                if (this.vD[position + 3].equals("：")) {
                    setStop(1);
                }
            }
        }
        return tv;
    }
}
