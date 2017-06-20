package vidur.codeclan.projectx.Activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import vidur.codeclan.projectx.R;


public class WebViewActivity extends AppCompatActivity {


    WebView web;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_web);
        web = (WebView) findViewById(R.id.webview);
        progress=new ProgressDialog(this);
        progress.setMessage("Loading ...");
        //progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
      //  progress.setProgress(0);
        progress.show();
        String url = getIntent().getStringExtra("url");

        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);

        web.loadUrl(url);


    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub

            super.onPageStarted(view, url, favicon);
            Log.i("TAG", "onPageStarted");


        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            Log.i("TAG", "onoverride");
            progress.dismiss();

            return true;

        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            Log.i("TAG", "onKeyDown");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
