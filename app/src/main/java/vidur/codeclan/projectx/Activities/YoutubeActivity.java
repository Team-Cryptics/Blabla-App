package vidur.codeclan.projectx.Activities;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import vidur.codeclan.projectx.R;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayer YPlayer;
    private static final String YoutubeDeveloperKey = "AIzaSyBdVhwTNf1FbJJ8uhoQ3QWzp0r99TJToDI";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    String video;
    Integer postID;
    String Url;
    String postName;
    TextView postname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        postname = (TextView)findViewById(R.id.postName);
        postID = getIntent().getIntExtra("postID", -1);
        postName = getIntent().getStringExtra("postName");
        Url = getIntent().getStringExtra("VideoUrl");
        video = Url.replace("https://www.youtube.com/watch?v=","");
        postname.setText(postName);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(YoutubeDeveloperKey, this);
    }
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.you_tube_api, menu);
//            return true;
//        }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(YoutubeDeveloperKey, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    public void onInitializationSuccess(Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        YPlayer = player;
        if (!wasRestored) {
            YPlayer.cueVideo(video);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_activity_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("TAG","BACK");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bookmark:

                final ProgressDialog dial = new ProgressDialog(YoutubeActivity.this);
                dial.setMessage("Adding bookmark...");
                dial.setTitle("Please wait");
                dial.show();
                String bookmarkUrl = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/bookmark/" + String.valueOf(postID) + "/" + LoginActivity.globalUser.getUserObjects().get(0).getId();
                Log.i("TAG",bookmarkUrl);
                Volley.newRequestQueue(YoutubeActivity.this).add(new StringRequest(Request.Method.POST,bookmarkUrl
                        ,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(YoutubeActivity.this, "New Bookmark Added", Toast.LENGTH_SHORT).show();
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
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Url);
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
