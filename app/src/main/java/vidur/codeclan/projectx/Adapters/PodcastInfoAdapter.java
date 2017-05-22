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

import vidur.codeclan.projectx.POJO.PodcastInfoClass;
import vidur.codeclan.projectx.Activities.PodcastPlayerActivity;
import vidur.codeclan.projectx.R;

//*
// * Created by Sarthak on 21-05-2017.



public class PodcastInfoAdapter extends RecyclerView.Adapter<PodcastInfoAdapter.PodViewHolder> {

    ArrayList<PodcastInfoClass> pod = new ArrayList<PodcastInfoClass>();
    Context c;
    //Context ctx;

    public PodcastInfoAdapter(ArrayList<PodcastInfoClass> pod, Context ctx){

        this.pod = pod;
        this.c = ctx;
    }

    @Override
    public PodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pod_cardview,parent,false);
        PodViewHolder podViewHolder = new PodViewHolder(view,pod,c);

        return podViewHolder;
    }

    @Override
    public void onBindViewHolder(PodViewHolder holder, int position) {

        PodcastInfoClass data = pod.get(position);
        Picasso.with(c).load(data.getImage_id()).into(holder.image_id);
        holder.heading.setText(data.getHeading());
        holder.subheading.setText(data.getSubheading());
        holder.subdisp.setText(data.getSubdisp());
    }

    @Override
    public int getItemCount() {
        return pod.size();
    }

public static class PodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView image_id;
    TextView heading, subheading,subdisp;
    ArrayList<PodcastInfoClass> podForHolder = new ArrayList<PodcastInfoClass>();
    Context ctx;

    public PodViewHolder(View view, ArrayList<PodcastInfoClass> pod, Context c) {
        super(view);
        podForHolder=pod;
        ctx=c;
        view.setOnClickListener(this);
        image_id = (ImageView)view.findViewById(R.id.imageView1);
        heading = (TextView) view.findViewById(R.id.textView3);
        subheading = (TextView) view.findViewById(R.id.textView4);
        subdisp = (TextView) view.findViewById(R.id.textView5);

    }

    @Override
    public void onClick(View v) {

        int position = getAdapterPosition();
        Log.i("TAG","Yo"+v.getId()+" Position" + position);

        PodcastInfoClass podClass = this.podForHolder.get(position);
        Intent intent = new Intent(ctx, PodcastPlayerActivity.class);

        intent.putExtra("img_id", podClass.getImage_id());
        intent.putExtra("heading_id", podClass.getHeading());
        intent.putExtra("subheading_id", podClass.getSubheading());
        intent.putExtra("subdisp", podClass.getSubdisp());
        this.ctx.startActivity(intent);

    }
}


}
