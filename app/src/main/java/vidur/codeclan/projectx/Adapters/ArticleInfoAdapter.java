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

import java.util.ArrayList;

import vidur.codeclan.projectx.POJO.ArticleInfoClass;
import vidur.codeclan.projectx.R;
import vidur.codeclan.projectx.Activities.ViewArticleActivity;

/**
 * Created by Sarthak on 21-05-2017.
 */

public class ArticleInfoAdapter extends RecyclerView.Adapter<ArticleInfoAdapter.InfoViewHolder> {

    ArrayList<ArticleInfoClass> info = new ArrayList<ArticleInfoClass>();
    Context c;
    //Context ctx;

    public ArticleInfoAdapter(ArrayList<ArticleInfoClass> info, Context ctx){

        this.info = info;
        this.c = ctx;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_article_cardview,parent,false);
        InfoViewHolder infoViewHolder = new InfoViewHolder(view,info,c);

        return infoViewHolder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {

        ArticleInfoClass data = info.get(position);
        Picasso.with(c).load(data.getImage_id()).into(holder.image_id);
        holder.heading.setText(data.getHeading());
        holder.subheading.setText(data.getSubheading());
        holder.subdisp.setText(data.getSubdisp());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

public static class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView image_id;
    TextView heading, subheading,subdisp;
    ArrayList<ArticleInfoClass> infoForHolder = new ArrayList<ArticleInfoClass>();
    Context ctx;

    public InfoViewHolder(View view, ArrayList<ArticleInfoClass> info, Context c) {
        super(view);
        infoForHolder=info;
        ctx=c;
        view.setOnClickListener(this);
        image_id = (ImageView)view.findViewById(R.id.click1);
        heading = (TextView) view.findViewById(R.id.textView);
        subheading = (TextView) view.findViewById(R.id.textView1);
        subdisp = (TextView) view.findViewById(R.id.textView2);

    }

    @Override
    public void onClick(View v) {

        int position = getAdapterPosition();
        Log.i("TAG","Yo"+v.getId()+" Position" + position);

        ArticleInfoClass infoClass = this.infoForHolder.get(position);
        Intent intent = new Intent(ctx, ViewArticleActivity.class);

        intent.putExtra("img_id", infoClass.getImage_id());
        intent.putExtra("heading_id", infoClass.getHeading());
        intent.putExtra("subheading_id", infoClass.getSubheading());
        intent.putExtra("subdisp", infoClass.getSubdisp());
        this.ctx.startActivity(intent);

    }
}


}
