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
import java.util.List;

import vidur.codeclan.projectx.Activities.TimeSelectionActivity;
import vidur.codeclan.projectx.Adapters.ArticleInfoAdapter;
import vidur.codeclan.projectx.POJO.Post;
import vidur.codeclan.projectx.POJO.PostObject;
import vidur.codeclan.projectx.R;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class ArticlesFragment extends Fragment {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_articles);
        layoutManager = new LinearLayoutManager(getActivity());

        Post articles = new Post();
        List<PostObject> articleList = new ArrayList<>();

        for (int i = 0; i < TimeSelectionActivity.posts.getNumResults(); i++) {
            if (TimeSelectionActivity.posts.getPostObjects().get(i).getContentType().equals("Text")) {
                articleList.add(TimeSelectionActivity.posts.getPostObjects().get(i));
            }
        }

        articles.setPostObjects(articleList);

        ArticleInfoAdapter adapter = new ArticleInfoAdapter(articles, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        return view;
    }
}
