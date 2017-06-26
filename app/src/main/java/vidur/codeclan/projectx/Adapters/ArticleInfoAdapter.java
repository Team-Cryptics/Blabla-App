package vidur.codeclan.projectx.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vidur.codeclan.projectx.Activities.WebViewActivity;
import vidur.codeclan.projectx.POJO.PostObject;
import vidur.codeclan.projectx.POJO.Post;
import vidur.codeclan.projectx.R;

/**
 * Created by Sarthak on 21-05-2017.
 */

public class ArticleInfoAdapter extends RecyclerView.Adapter<ArticleInfoAdapter.InfoViewHolder> {

    Post info = new Post();
    Context c;

    public ArticleInfoAdapter(Post info, Context ctx) {

        this.info = info;
        this.c = ctx;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_article_cardview, parent, false);
        InfoViewHolder infoViewHolder = new InfoViewHolder(view, info, c);

        return infoViewHolder;
    }

    @Override
    public void onBindViewHolder(final InfoViewHolder holder, int position) {

        List<PostObject> postObjects = info.getPostObjects();
        Picasso.with(c).load(postObjects.get(position).getImage()).into(holder.image_id);
        holder.heading.setText(postObjects.get(position).getTitle());
        holder.subheading.setText(postObjects.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return info.getPostObjects().size();
    }

    public static class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image_id;
        TextView heading, subheading;
        Post postsHolder;
        Context ctx;

        public InfoViewHolder(View view, Post info, Context c) {
            super(view);
            ctx = c;
            postsHolder = info;
            view.setOnClickListener(this);
            image_id = (ImageView) view.findViewById(R.id.imageView);
            heading = (TextView) view.findViewById(R.id.textView);
            subheading = (TextView) view.findViewById(R.id.textView1);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Log.i("TAG", "Yo " + v.getId() + " Position" + position);

            Intent intent = new Intent(ctx, WebViewActivity.class);
            intent.putExtra("url", postsHolder.getPostObjects().get(position).getLink());
            intent.putExtra("postID",postsHolder.getPostObjects().get(position).getId());
            this.ctx.startActivity(intent);

        }
    }


}
