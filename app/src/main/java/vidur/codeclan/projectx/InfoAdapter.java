package vidur.codeclan.projectx;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sarthak on 21-05-2017.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>{

    ArrayList<InfoClass> info = new ArrayList<InfoClass>();
    Context c;
    public InfoAdapter (ArrayList<InfoClass> info, Context context){

        this.info = info;
        c = context;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        InfoViewHolder infoViewHolder = new InfoViewHolder(view);
        return infoViewHolder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {

        InfoClass data = info.get(position);
        Picasso.with(c)
                .load(data.getImage_id())
                .into(holder.image_id);
        holder.heading.setText(data.getHeading());
        holder.subheading.setText(data.getSubheading());
        holder.subdisp.setText(data.getSubdisp());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

public static class InfoViewHolder extends RecyclerView.ViewHolder{

    ImageView image_id;
    TextView heading, subheading,subdisp;

    public InfoViewHolder(View view) {
        super(view);
        image_id = (ImageView)view.findViewById(R.id.imageView);
        heading = (TextView) view.findViewById(R.id.textView);
        subheading = (TextView) view.findViewById(R.id.textView1);
        subdisp = (TextView) view.findViewById(R.id.textView2);

    }
}


}
