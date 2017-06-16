package vidur.codeclan.projectx.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vidur.codeclan.projectx.Adapters.PodcastInfoAdapter;
import vidur.codeclan.projectx.POJO.PodcastInfoClass;
import vidur.codeclan.projectx.R;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class PodcastFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PodcastInfoClass> list = new ArrayList<PodcastInfoClass>();
    String[] image,heading,subheading,subdisp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_podcast,container,false);



        image = getResources().getStringArray(R.array.image);
        heading = getResources().getStringArray(R.array.heading);
        subheading = getResources().getStringArray(R.array.subheading);
        subdisp = getResources().getStringArray(R.array.url);

        int count = 0;
        for(String Heading : heading)
        {
            PodcastInfoClass podClass = new PodcastInfoClass(image[count],heading[count],subheading[count], subdisp[count]);
            count++;
            list.add(podClass);
        }

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_podcasts);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new PodcastInfoAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        return v;

    }
}
