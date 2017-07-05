package vidur.codeclan.projectx.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import vidur.codeclan.projectx.POJO.Bookmark;
import vidur.codeclan.projectx.R;

public class ScrollingActivity extends AppCompatActivity {
    Bookmark bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        ImageView ivUser = (ImageView) findViewById(R.id.ivUser);
        Picasso.with(this).load("ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/getbitmap/" + LoginActivity.globalUser.getObjects().get(0).getNickname()).into(ivUser);
        TextView tvNickname = (TextView) findViewById(R.id.tvName);
        tvNickname.setText(LoginActivity.globalUser.getObjects().get(0).getNickname());
        final TextView tvBookmarks = (TextView) findViewById(R.id.tvBookmarks);

        findViewById(R.id.fabSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity.this, SettingsActivity.class));
            }
        });

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,
                "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/bookmark",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        bookmark = new GsonBuilder().create().fromJson(response, Bookmark.class);
                        tvBookmarks.setText(String.valueOf(bookmark.getObjects().size()));
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvBookmarks);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ScrollingActivity.this));
                        recyclerView.setAdapter(new rvBookmarkAdapter());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

    public class rvBookmarksViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView, textView1;
        public rvBookmarksViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivUser);
            textView = (TextView) itemView.findViewById(R.id.tvBookmarks);
            textView1 = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    public class rvBookmarkAdapter extends RecyclerView.Adapter<rvBookmarksViewHolder> {

        @Override
        public rvBookmarksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new rvBookmarksViewHolder(getLayoutInflater().inflate(R.layout.layout_article_cardview, parent, false));
        }

        @Override
        public void onBindViewHolder(rvBookmarksViewHolder holder, int position) {
            Picasso.with(ScrollingActivity.this).load(bookmark.getObjects().get(position).getImage().toString()).into(holder.imageView);
            holder.textView.setText(bookmark.getObjects().get(position).getTitle().toString());
            holder.textView.setText(bookmark.getObjects().get(position).getBody().toString());
        }

        @Override
        public int getItemCount() {
            return bookmark.getObjects().size();
        }
    }
}
