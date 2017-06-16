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

import vidur.codeclan.projectx.Adapters.ArticleInfoAdapter;
import vidur.codeclan.projectx.POJO.ArticleInfoClass;
import vidur.codeclan.projectx.R;

/**
 * Created by samarthgupta on 16/06/17.
 */

public class ArticlesFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ArticleInfoClass> list = new ArrayList<ArticleInfoClass>();
    String[] image,heading,subheading,subdisp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles,container,false);

        image = getResources().getStringArray(R.array.image);
        heading = getResources().getStringArray(R.array.heading);
        subheading = getResources().getStringArray(R.array.subheading);
        subdisp = getResources().getStringArray(R.array.url);

        int count = 0;
        for(String Heading : heading)
        {
            ArticleInfoClass infoClass = new ArticleInfoClass(image[count],heading[count],subheading[count], subdisp[count]);
            count++;
            list.add(infoClass);
        }

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_articles);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ArticleInfoAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);


        return view;
    }
}
