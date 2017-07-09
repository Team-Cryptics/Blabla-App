package vidur.codeclan.projectx.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
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


        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        userEmail = sharedPreferences.getString("email", null);

        progress = new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.setIndeterminate(true);

        progress.show();

        url = getIntent().getStringExtra("url");
        postID = getIntent().getIntExtra("postID", -1);

        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);

        web.loadUrl(url);


    }

    public class myWebClient extends WebViewClient {
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
            progress.dismiss();

            return true;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            Log.i("TAG", "onKeyDown");
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

                String bookmarkUrl = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/bookmark/" + String.valueOf(postID) + "/" + LoginActivity.globalUser.getUserObjects().get(0).getId();
                Log.i("TAG",bookmarkUrl);
                Volley.newRequestQueue(WebViewActivity.this).add(new StringRequest(Request.Method.POST,bookmarkUrl
                        ,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(WebViewActivity.this, "New Bookmark Added", Toast.LENGTH_SHORT).show();
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
        }

        return true;
    }
}
