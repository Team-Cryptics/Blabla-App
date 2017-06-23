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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_podcast,container,false);


       PodcastInfoClass info = new PodcastInfoClass("https://image.freepik.com/free-vector/white-squares-on-colorful-squares-background_23-2147500535.jpg","Abc cde","asd hjk","asdpoiu");

        for(int i =0;i<10;i++){
            list.add(i,info);
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
