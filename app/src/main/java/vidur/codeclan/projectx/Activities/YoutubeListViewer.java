package vidur.codeclan.projectx.Activities;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import vidur.codeclan.projectx.Adapters.ArticleInfoAdapter;
import vidur.codeclan.projectx.Adapters.YoutubeInfoAdapter;
import vidur.codeclan.projectx.POJO.ArticleInfoClass;
import vidur.codeclan.projectx.POJO.Post;
import vidur.codeclan.projectx.POJO.YoutubeInfoClass;
import vidur.codeclan.projectx.R;

public class YoutubeListViewer extends AppCompatActivity {


    RecyclerView recyclerView;
   YoutubeInfoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list_viewer);



        recyclerView = (RecyclerView)findViewById(R.id.recycler_youtube);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, " ", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                post = new GsonBuilder().create().fromJson(response,Post.class);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                adapter = new YoutubeInfoAdapter(post,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));





    }
}
