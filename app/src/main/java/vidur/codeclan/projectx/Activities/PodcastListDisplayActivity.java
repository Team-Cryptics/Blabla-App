package vidur.codeclan.projectx.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import vidur.codeclan.projectx.Adapters.PodcastInfoAdapter;
import vidur.codeclan.projectx.POJO.PodcastInfoClass;
import vidur.codeclan.projectx.R;

public class PodcastListDisplayActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PodcastInfoClass> list = new ArrayList<PodcastInfoClass>();
    String[] image,heading,subheading,subdisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_podcast);

        image = getResources().getStringArray(R.array.image);
        heading = getResources().getStringArray(R.array.heading);
        subheading = getResources().getStringArray(R.array.subheading);
        subdisp = getResources().getStringArray(R.array.subdisp);

        int count = 0;
        for(String Heading : heading)
        {
            PodcastInfoClass podClass = new PodcastInfoClass(image[count],heading[count],subheading[count], subdisp[count]);
            count++;
            list.add(podClass);
        }

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new PodcastInfoAdapter(list, this);
        recyclerView.setAdapter(adapter);

    }
}
