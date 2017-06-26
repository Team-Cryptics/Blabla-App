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
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import vidur.codeclan.projectx.Activities.TabbedActivity;
import vidur.codeclan.projectx.Adapters.ArticleInfoAdapter;
import vidur.codeclan.projectx.POJO.Object;
import vidur.codeclan.projectx.POJO.Post;
import vidur.codeclan.projectx.R;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class ArticlesFragment extends Fragment {

    private static final String TAG = "tag";
    RecyclerView recyclerView;
    ArticleInfoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_articles);
        layoutManager = new LinearLayoutManager(getActivity());
        progressBar = (ProgressBar) view.findViewById(R.id.progress_articles);

        progressBar.setVisibility(View.VISIBLE);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ArticleInfoAdapter(TabbedActivity.posts, getActivity());
        recyclerView.setAdapter(adapter);



        return view;
    }
}
