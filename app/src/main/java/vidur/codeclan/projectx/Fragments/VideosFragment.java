package vidur.codeclan.projectx.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vidur.codeclan.projectx.Activities.TimeSelectionActivity;
import vidur.codeclan.projectx.Adapters.PodcastInfoAdapter;
import vidur.codeclan.projectx.Adapters.YoutubeInfoAdapter;
import vidur.codeclan.projectx.POJO.PodcastInfoClass;
import vidur.codeclan.projectx.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class VideosFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_videos,container,false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_videos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new YoutubeInfoAdapter(TimeSelectionActivity.posts, getContext()));

        return v;
    }
}
