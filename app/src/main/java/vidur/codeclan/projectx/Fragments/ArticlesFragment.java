package vidur.codeclan.projectx.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import vidur.codeclan.projectx.Adapters.ArticleInfoAdapter;
import vidur.codeclan.projectx.POJO.ArticleInfoClass;
import vidur.codeclan.projectx.POJO.Object;
import vidur.codeclan.projectx.POJO.Post;
import vidur.codeclan.projectx.R;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class ArticlesFragment extends Fragment {

    RecyclerView recyclerView;
    ArticleInfoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Post post;

    String Article_URL = "http://ec2-13-58-169-227.us-east-2.compute.amazonaws.com/api/post?q={%22filters%22:[{%22name%22:%22type%22,%22op%22:%22eq%22,%22val%22:%22ARTICLE%22}]}";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_articles);
        layoutManager = new LinearLayoutManager(getActivity());

        Volley.newRequestQueue(getActivity()).add(new StringRequest(Request.Method.GET,Article_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                post = new GsonBuilder().create().fromJson(response,Post.class);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                adapter = new ArticleInfoAdapter(post, getActivity());
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));




        return view;
    }
}
