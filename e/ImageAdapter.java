package comp.android.e;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {
    private Integer height;
    private Context mContext;
    private Integer[] mImageIds;
    int mgalleryItemBackground;
    private Integer width;

    public ImageAdapter(Context c) {
        this.mContext = c;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageResource(this.mImageIds[position].intValue());
        imageView.setAlpha(255);
        imageView.setPadding(0, 10, 0, 10);
        imageView.setLayoutParams(new LayoutParams(this.width.intValue(), this.height.intValue()));
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setBackgroundResource(this.mgalleryItemBackground);
        return imageView;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer[] getmImageIds() {
        return this.mImageIds;
    }

    public void setmImageIds(Integer[] mImageIds) {
        this.mImageIds = mImageIds;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public int getCount() {
        return this.mImageIds.length;
    }

    public Object getItem(int position) {
        return Integer.valueOf(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }
}
