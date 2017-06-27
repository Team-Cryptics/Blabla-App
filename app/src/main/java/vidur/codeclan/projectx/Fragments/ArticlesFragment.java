package vidur.codeclan.projectx.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vidur.codeclan.projectx.Activities.TimeSelectionActivity;
import vidur.codeclan.projectx.Adapters.ArticleInfoAdapter;
import vidur.codeclan.projectx.R;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class ArticlesFragment extends Fragment {

    private static final String TAG = "tag";
    RecyclerView recyclerView;
    ArticleInfoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_articles);
        layoutManager = new LinearLayoutManager(getActivity());


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ArticleInfoAdapter(TimeSelectionActivity.posts, getActivity());
        recyclerView.setAdapter(adapter);



        return view;
    }
}
