package vidur.codeclan.projectx.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class ProfileActivity extends AppCompatActivity {
    Bookmark bookmark;
    TextView tvBookmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Log.i("TAG",LoginActivity.globalUser.getUserObjects().get(0).getEmail());

        ImageView ivUser = (ImageView) findViewById(R.id.ivUser);
       // Picasso.with(this).load("http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/getbitmap/" + LoginActivity.globalUser.getUserObjects().get(0).getNickname()).into(ivUser);
        TextView tvNickname = (TextView) findViewById(R.id.tvName);
        tvNickname.setText("Hi "+LoginActivity.globalUser.getUserObjects().get(0).getNickname()+"!");

        tvBookmarks = (TextView) findViewById(R.id.tvBookmarks);


        String urlBookmark = "http://ec2-52-14-50-89.us-east-2.compute.amazonaws.com/api/bookmark?q={\"filters\":[{\"name\":\"user_email\",\"op\":\"eq\",\"val\":\""+LoginActivity.globalUser.getUserObjects().get(0).getEmail()+"\"}]}";
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,urlBookmark,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("TAG",response);
                        bookmark = new GsonBuilder().create().fromJson(response, Bookmark.class);
                        tvBookmarks.setText(String.valueOf(bookmark.getObjects().size()));
                        if (bookmark.getObjects().size()==0){
                            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                            builder.setMessage("You have no bookmarks");
                            builder.setPositiveButton("Go back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });

                        }
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvBookmarks);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
                        recyclerView.setAdapter(new rvBookmarkAdapter());
                        recyclerView.setHasFixedSize(true);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

    public class rvBookmarksViewHolder extends RecyclerView.ViewHolder {
        ImageView image_id;
        TextView heading, subheading;

        public rvBookmarksViewHolder(View itemView) {
            super(itemView);
            image_id = (ImageView) itemView.findViewById(R.id.iv_image);
            heading = (TextView) itemView.findViewById(R.id.tv_heading);
            subheading = (TextView) itemView.findViewById(R.id.tv_subheading);
        }
    }

    public class rvBookmarkAdapter extends RecyclerView.Adapter<rvBookmarksViewHolder> {

        @Override
        public rvBookmarksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new rvBookmarksViewHolder(getLayoutInflater().inflate(R.layout.layout_article_cardview, parent, false));
        }

        @Override
        public void onBindViewHolder(rvBookmarksViewHolder holder, int position) {

            //Giving null point exception
            if(bookmark.getObjects().get(position).getPostImage().equals("/static/userdata/avatar.png")||bookmark.getObjects().get(position).getPostImage()==null) {
                Picasso.with(ProfileActivity.this).load(R.drawable.splashlogo).into(holder.image_id);
            }
            else
            {
                Picasso.with(ProfileActivity.this).load(bookmark.getObjects().get(position).getPostImage()).into(holder.image_id);
            }

            holder.heading.setText(bookmark.getObjects().get(position).getPostTitle());
            holder.subheading.setText(bookmark.getObjects().get(position).getPostBody());
        }

        @Override
        public int getItemCount() {
            return bookmark.getObjects().size();
        }
    }
}
