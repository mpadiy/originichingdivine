package comp.android.e;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webView4Translation extends Activity {
    public String DB_PATH_NAME;
    public Bundle bundle;
    public Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.webview_translate);
        WebView myBrowser = (WebView) findViewById(R.id.mybrowser);
        this.intent = getIntent();
        this.bundle = this.intent.getExtras();
        String webLink = this.bundle.getString("webLink");
        this.DB_PATH_NAME = this.bundle.getString("dbPath");
        WebSettings websettings = myBrowser.getSettings();
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        websettings.setJavaScriptEnabled(true);
        myBrowser.setWebViewClient(new WebViewClient());
        myBrowser.loadUrl(webLink);
    }

    public void onBackPressed() {
        finish();
    }
}
