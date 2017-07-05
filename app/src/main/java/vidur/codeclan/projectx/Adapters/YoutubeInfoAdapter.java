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

import vidur.codeclan.projectx.Activities.YoutubeActivity;
import vidur.codeclan.projectx.POJO.Post;
import vidur.codeclan.projectx.R;

/**
 * Created by SUPERUSER on 23-06-2017.
 */

public class YoutubeInfoAdapter extends RecyclerView.Adapter<YoutubeInfoAdapter.InfoViewHolder> {

    Post info = new Post();
    Context c;

    public YoutubeInfoAdapter(Post info, Context ctx) {

        this.info = info;
        this.c = ctx;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_youtube_list_viewer, parent, false);
        InfoViewHolder infoViewHolder = new InfoViewHolder(v, info, c);

        return infoViewHolder;
    }

    @Override
    public void onBindViewHolder(final InfoViewHolder holder, int position) {

        Picasso.with(c).load(info.getPostObjects().get(position).getImage()).into(holder.image_id);
        holder.heading.setText(info.getPostObjects().get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return info.getPostObjects().size();
    }

    public static class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image_id;
        TextView heading;
        Post infoForHolder;
        Context ctx;

        public InfoViewHolder(View view, Post info, Context c) {
            super(view);
            infoForHolder = info;
            ctx = c;
            view.setOnClickListener(this);
            image_id = (ImageView) view.findViewById(R.id.iv_youtube_image);
            heading = (TextView) view.findViewById(R.id.tv_youtube_title);


        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Intent intent = new Intent(ctx, YoutubeActivity.class);
            intent.putExtra("VideoUrl", infoForHolder.getPostObjects().get(position).getLink());
            this.ctx.startActivity(intent);

        }
    }

}















