package vidur.codeclan.projectx.Activities;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import vidur.codeclan.projectx.R;


public class WebViewActivity extends AppCompatActivity {


    WebView web;
    private ProgressDialog progress;
    Integer postID;
    SharedPreferences sharedPreferences;
    String userEmail;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_web);
        web = (WebView) findViewById(R.id.webview);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        userEmail = sharedPreferences.getString("email", null);


        progress = new ProgressDialog(this);
        progress.setMessage("Loading page");
        mHandler.sendMessageDelayed(new Message(), 8000);
        progress.setCancelable(false);

        progress.show();

        url = getIntent().getStringExtra("url");
        postID = getIntent().getIntExtra("postID", -1);

        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);

        web.loadUrl(url);


    }

    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if (progress.isShowing())
               progress.dismiss();
        }
    };

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub

            super.onPageStarted(view, url, favicon);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);

            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progress.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (web.canGoBack()) {
                        web.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_activity_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bookmark:

                final ProgressDialog dial = new ProgressDialog(WebViewActivity.this);
                dial.setMessage("Adding bookmark...");
                dial.setTitle("Please wait");
                dial.show();
                String bookmarkUrl = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/bookmark/" + String.valueOf(postID) + "/" + LoginActivity.globalUser.getUserObjects().get(0).getId();
                Log.i("TAG",bookmarkUrl);
                Volley.newRequestQueue(WebViewActivity.this).add(new StringRequest(Request.Method.POST,bookmarkUrl
                        ,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(WebViewActivity.this, "New Bookmark Added", Toast.LENGTH_SHORT).show();
                                dial.dismiss();
                                Log.i("TAG","Done");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("TAG","Error");
                    }
                }));

                break;

            case R.id.action_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;

            case android.R.id.home:{
                Intent intent = new Intent(getApplicationContext(), TabbedActivity.class);
                startActivity(intent);


            }
        }

        return true;
    }
}
